package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ViewVolProfile extends AppCompatActivity {

    TextView displayVolNameTV;
    TextView displayVolAgeTV;
    TextView displayVolSchoolTV;

    UserInfo currUserInfoObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vol_profile);

        currUserInfoObj = aMainActivity.firebaseHelper.getUserInfo();

        displayVolNameTV = findViewById(R.id.displayVolNameTV);
        displayVolAgeTV = findViewById(R.id.dislpayVolAgeTV);
        displayVolSchoolTV = findViewById(R.id.displayVolSchoolTV);

        displayVolNameTV.setText("Name: " + currUserInfoObj.getName());
        displayVolAgeTV.setText("Age: " + Integer.toString(currUserInfoObj.getUserAge()));
        displayVolSchoolTV.setText("School: " + currUserInfoObj.getSchool());
    }

    public void takeToEditVolProfileScreen(View v)
    {
        Intent p = new Intent(this, EditVolProfile.class);
        startActivity(p);
    }

    public void goBackToVolDashboard(View v)
    {
        Intent p = new Intent(this, bVolDashboard.class);
        startActivity(p);
    }
}