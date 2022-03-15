package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.voluntold.R;
import com.google.firebase.auth.FirebaseAuth;

public class OrgDashboard extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_dashboard);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signOut(View v) {
        mAuth.signOut();
        Intent p = new Intent(this, MainActivity.class);
        startActivity(p);
    }
}