package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class aMainActivity extends AppCompatActivity {

    public final String TAG = "Josh";
    public static FirebaseHelper firebaseHelper;
    private TextView testTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseHelper = new FirebaseHelper();

        testTV = findViewById(R.id.test);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly
        //updateIfLoggedIn();
    }

    public void updateIfLoggedIn() {
        FirebaseUser user = firebaseHelper.getmAuth().getCurrentUser();

        if (user != null)
        {
            Log.i(TAG, firebaseHelper.getUserInfo().getAccountType());
            Log.i(TAG, firebaseHelper.getUserInfo().getAccountType());
            if (firebaseHelper.getUserInfo().getAccountType().equals("Organization")) {
                Intent intent = new Intent(this, cOrgDashboard.class);
                startActivity(intent);
            }
            else if (firebaseHelper.getUserInfo().getAccountType().equals("Volunteer")) {
                Intent intent = new Intent(this, bVolDashboard.class);
                startActivity(intent);
            }
        }
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