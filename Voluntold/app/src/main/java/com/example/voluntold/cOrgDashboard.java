package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class cOrgDashboard extends AppCompatActivity {

    private static final String TAG = "Josh";
    private FirebaseAuth mAuth;
    private FirebaseHelper.FirestoreCallback FirestoreCallback;
    public static FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_dashboard);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signOut(View v) {
        mAuth.signOut();

        aMainActivity.firebaseHelper.getUserInfo().setAccountType("");
        aMainActivity.firebaseHelper.updateUid(null);

        Intent p = new Intent(this, aMainActivity.class);
        startActivity(p);
    }

    public void takeToCreateOppPage(View v)
    {
        Intent f = new Intent(this, cOrgCreateOpportunityPost.class);
        startActivity(f);
    }
    public void takeToPostedOppPage(View v)
    {
        Intent f = new Intent(this, cOrgPostedOpportunities.class);
        startActivity(f);
    }

    public void takeToOrgProfile(View v)
    {
        Intent g = new Intent(this, ViewOrgProfile.class);
        startActivity(g);
    }

}