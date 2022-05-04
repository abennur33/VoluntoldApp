package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class ViewVolProfile extends AppCompatActivity {

    TextView displayVolNameTV;
    TextView displayVolAgeTV;
    TextView displayVolSchoolTV;

    UserInfo currUserInfoObj;

    String TAG = "Abhi1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vol_view_profile);

        currUserInfoObj = aMainActivity.firebaseHelper.getUserInfo();

        displayVolNameTV = findViewById(R.id.displayVolNameTV);
        displayVolAgeTV = findViewById(R.id.dislpayVolAgeTV);
        displayVolSchoolTV = findViewById(R.id.displayVolSchoolTV);

        Log.i(TAG, "user info name: " + currUserInfoObj.getName());

        displayVolNameTV.setText("Name: " + aMainActivity.firebaseHelper.myInfo.getName());
        displayVolAgeTV.setText("Age: " + Integer.toString(aMainActivity.firebaseHelper.myInfo.getUserAge()));
        displayVolSchoolTV.setText("School: " + aMainActivity.firebaseHelper.myInfo.getSchool());
    }

    public void takeToEditVolProfileScreen(View v)
    {
        Intent p = new Intent(this, bEditVolProfile.class);
        startActivity(p);
    }

    public void goBackToVolDashboard(View v)
    {
        Intent p = new Intent(this, bVolDashboard.class);
        startActivity(p);
    }
}