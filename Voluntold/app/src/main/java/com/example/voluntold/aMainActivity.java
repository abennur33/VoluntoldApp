package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class aMainActivity extends AppCompatActivity {

    public static FirebaseHelper firebaseHelper;
    private TextView testTV;
    private static final String TAG = "Luis";

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
        //udpateIfLoggedIn();
    }

//    public void updateIfLoggedIn() {
//        FirebaseUser user = firebaseHelper.getmAuth().getCurrentUser();
//
//        if (user != null)
//        {
//            Intent intent = new Intent(aMainActivity.this, cOrgDashboard.class);
//            startActivity(intent);
//        }
//    }

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