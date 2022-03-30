package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseUser;

public class aMainActivity extends AppCompatActivity {

    public static FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseHelper = new FirebaseHelper();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly
        //udpateIfLoggedIn();
    }

    public void udpateIfLoggedIn(){
        FirebaseUser user = firebaseHelper.getmAuth().getCurrentUser();

        if (user != null) {
            if (firebaseHelper.getAccountType().equals("Organization"))
            {
                Intent intent = new Intent(aMainActivity.this, cOrgDashboard.class);
                startActivity(intent);
            }
            else if (firebaseHelper.getAccountType().equals("Volunteer"))
            {
                Intent intent = new Intent(aMainActivity.this, bVolDashboard.class);
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