package com.anthony.ltmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Login_Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login Form");
        Toast.makeText(Login_Form.this, "Connected Successfully!",Toast.LENGTH_LONG).show();
    }

    public void btn_signupForm(View view) {

        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }
}
