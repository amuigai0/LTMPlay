package com.anthony.ltmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Form extends AppCompatActivity {
    EditText txtname, txtusername, txtemail, txtpassword, txtconfirmpass;
    RadioButton male,female;
    Button btnregister;
    DatabaseReference ltm;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Sign Up");
        txtname = (EditText)findViewById(R.id.txtname);
        txtusername = (EditText)findViewById(R.id.txtusername);
        txtpassword = (EditText)findViewById(R.id.txtpassword);
        txtconfirmpass = (EditText)findViewById(R.id.txtconfirmpass);

        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);

        user = new User();

        btnregister = (Button)findViewById(R.id.btnregister);

        ltm = FirebaseDatabase.getInstance().getReference().child("User");

        btnregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                user.setName(txtname.getText().toString().trim());
                user.setUsername(txtusername.getText().toString().trim());
                user.setEmail(txtemail.getText().toString().trim());
                user.setPassword(txtpassword.getText().toString().trim());
                user.setConfirmpass(txtconfirmpass.getText().toString().trim());
                user.setGender(male.getText().toString().trim());

                ltm.push().setValue(user);
                Toast.makeText(Signup_Form.this, "Data Inserted Successfully",Toast.LENGTH_LONG).show();

            }
        });
    }
}
