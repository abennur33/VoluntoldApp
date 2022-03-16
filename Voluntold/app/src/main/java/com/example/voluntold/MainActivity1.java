package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void toSignUp(View v) {
        Intent intent = new Intent(MainActivity1.this, SignUp1.class);
        startActivity(intent);
    }
    public void toLogIn(View v) {
        Intent intent = new Intent(MainActivity1.this, LogIn.class);
        startActivity(intent);
    }
    public void toVolunteerInfo(View v) {
        Intent intent = new Intent(MainActivity1.this, VolunteerInformation.class);
        startActivity(intent);
    }
}