package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class aVolunteerInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vol_info_screen);
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, aMainActivity.class);
        startActivity(intent);
    }

    public void viewAsOrganization(View v) {
        Intent intent = new Intent(this, aOrganizationInformation.class);
        startActivity(intent);
    }
}