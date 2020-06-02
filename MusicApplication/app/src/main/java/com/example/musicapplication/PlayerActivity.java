package com.example.musicapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    Button next, previous, pause;
    TextView songTextLabel;
    SeekBar songSeekBar;

    static MediaPlayer myMediaPlayer;
    int position;
    String sname;
    ArrayList<File> mySongs;
    Thread updateSeekBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        next = (Button)findViewById(R.id.btn_next);
        previous = (Button) findViewById(R.id.btn_previous);
        pause = (Button)findViewById(R.id.btn_pause);
        songTextLabel = (TextView)findViewById(R.id.songTextLabel);
        songSeekBar = (SeekBar)findViewById(R.id.songSeekBar);

        updateSeekBar = new Thread(){
            @Override
            public void run() {

                int totalDuration = myMediaPlayer.getDuration();
                int currentPosition = 0;

                while(currentPosition<totalDuration){
                    try{
                        sleep(500);
                        songSeekBar.setProgress(currentPosition);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        };

        if (myMediaPlayer!=null){
            myMediaPlayer.stop();
            myMediaPlayer.release();
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        mySongs = (ArrayList) bundle.getParcelableArrayList("songs");

        sname = mySongs.get(position).getName().toString();
        String songName = i.getStringExtra("songname");
        songTextLabel.setText(songName);
        songTextLabel.setSelected(true);

        position = bundle.getInt("pos", 0);
        Uri u = Uri.parse(mySongs.get(position).toString());

        myMediaPlayer = MediaPlayer.create(getApplicationContext(),u);

        myMediaPlayer.start();
        songSeekBar.setMax(myMediaPlayer.getDuration());
        songSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                myMediaPlayer.seekTo(seekBar.getProgress());


            }
        });


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songSeekBar.setMax(myMediaPlayer.getDuration());

                if (myMediaPlayer.isPlaying()){

                    pause.setBackgroundResource(R.drawable.icon_play);
                    myMediaPlayer.pause();
                }
                else {
                    pause.setBackgroundResource(R.drawable.icon_pause);
                    myMediaPlayer.start();
                }
            }
        });

    }
}
