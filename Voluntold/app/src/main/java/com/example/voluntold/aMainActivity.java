package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
    }

    @Override
    public void onStart() {
        super.onStart();
        updateIfLoggedIn();
    }

    public void updateIfLoggedIn() {
        FirebaseUser user = firebaseHelper.getmAuth().getCurrentUser();

        if (user != null)
        {
            Intent s = new Intent(this, aIntermediateLogInScreen.class);
            startActivity(s);
        }
    }

    public void toSignUp(View v) {
        Intent intent = new Intent(aMainActivity.this, aSignUp.class);
        startActivity(intent);
    }
    public void toLogIn(View v) {
        LoadingDialog loadingDialog = new LoadingDialog(aMainActivity.this);
        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
            }
        }, 2500);

        Intent intent = new Intent(aMainActivity.this, aLogIn.class);
        startActivity(intent);
    }
    public void toVolunteerInfo(View v) {
        Intent intent = new Intent(aMainActivity.this, aVolunteerInformation.class);
        startActivity(intent);
    }
}