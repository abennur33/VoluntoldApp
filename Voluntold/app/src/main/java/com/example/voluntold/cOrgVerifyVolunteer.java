package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class cOrgVerifyVolunteer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_verify_volunteer);
    }

    public void verifyVolunteer() {
        // verifies volunteer
    }

    public void goBack(View v) {
        Intent p = new Intent(this, cViewVolunteers.class);
        startActivity(p);
    }
}