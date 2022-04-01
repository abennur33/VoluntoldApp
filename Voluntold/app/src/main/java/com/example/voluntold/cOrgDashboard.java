package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class cOrgDashboard extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseHelper.FirestoreCallback FirestoreCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_dashboard);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signOut(View v) {
        mAuth.signOut();
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
        Intent f = new Intent(this, cPostedOpportunities.class);
        startActivity(f);
    }

}