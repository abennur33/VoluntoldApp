package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class bVolDashboard extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_dashboard);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signOut(View v) {
        mAuth.signOut();
        
        aMainActivity.firebaseHelper.clearAccType();
        aMainActivity.firebaseHelper.updateUid(null);

        Intent p = new Intent(this, aMainActivity.class);
        startActivity(p);
    }

    public void takeToSavedOppPage(View v)
    {
        Intent f = new Intent(this, bSavedOpportunities.class);
        startActivity(f);
    }
    public void takeToDiscoverOppPage(View v)
    {
        Intent f = new Intent(this, bDiscoverOpportunities.class);
        startActivity(f);
    }


}