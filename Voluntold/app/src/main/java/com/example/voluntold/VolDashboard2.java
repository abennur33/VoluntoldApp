package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class VolDashboard2 extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_dashboard);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signOut(View v) {
        mAuth.signOut();
        Intent p = new Intent(this, MainActivity1.class);
        startActivity(p);
    }


}