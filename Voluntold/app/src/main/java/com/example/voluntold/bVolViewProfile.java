package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class bVolViewProfile extends AppCompatActivity {

    TextView displayVolNameTV;
    TextView displayVolAgeTV;
    TextView displayVolSchoolTV;

    UserInfo currUserInfoObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vol_view_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId())
            {
                case R.id.discover:
                    startActivity(new Intent(getApplicationContext(), bVolDiscoverOpportunities.class));
                    return true;
                case R.id.saved:
                    startActivity(new Intent(getApplicationContext(), bVolSavedOpportunities.class));
                    return true;
                case R.id.profile:
                    return true;
                case R.id.signout:
                    aMainActivity.firebaseHelper.getmAuth().signOut();
                    aMainActivity.firebaseHelper.getUserInfo().setAccountType("");
                    aMainActivity.firebaseHelper.updateUid(null);
                    startActivity(new Intent(getApplicationContext(), aMainActivity.class));
            }
            return false;
        });

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
        Intent p = new Intent(this, bEditVolProfile.class);
        startActivity(p);
    }

}