package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VolProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_profile);

        TextView volNameTV = findViewById(R.id.volNameTV);
        TextView volAgeTV = findViewById(R.id.volAgeTV);
        TextView volSchoolTV = findViewById(R.id.volSchoolTV);

        volNameTV.setText(aMainActivity.firebaseHelper.getUserInfo().getName());
        volAgeTV.setText(aMainActivity.firebaseHelper.getUserInfo().getUserAge());
        volSchoolTV.setText(aMainActivity.firebaseHelper.getUserInfo().getSchool());
    }

    public void editVolName(View v)
    {

    }

    public void editVolAge(View v)
    {

    }
    public void editVolSchool(View v)
    {

    }

}