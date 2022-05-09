package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class aIntermediateLogInScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_log_in_screen);
    }

    public void takeToScreenUponLogIn(View v) {

        if (aMainActivity.firebaseHelper.getUserInfo().getAccountType().equals("Organization"))
        {
            Intent intent = new Intent(aIntermediateLogInScreen.this, cOrgPostedOpportunities.class);
            startActivity(intent);
        }

        else if (aMainActivity.firebaseHelper.getUserInfo().getAccountType().equals("Volunteer"))
        {
            Intent intent = new Intent(aIntermediateLogInScreen.this, bVolSavedOpportunities.class);
            startActivity(intent);
        }
    }
}