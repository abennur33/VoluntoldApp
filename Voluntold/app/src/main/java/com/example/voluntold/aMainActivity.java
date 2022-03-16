package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class aMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void toSignUp(View v) {
        Intent intent = new Intent(aMainActivity.this, aSignUp.class);
        startActivity(intent);
    }
    public void toLogIn(View v) {
        Intent intent = new Intent(aMainActivity.this, aLogIn.class);
        startActivity(intent);
    }
    public void toVolunteerInfo(View v) {
        Intent intent = new Intent(aMainActivity.this, aVolunteerInformation.class);
        startActivity(intent);
    }
}