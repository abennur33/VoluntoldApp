package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ViewOrgProfile extends AppCompatActivity {

    TextView displayOrgNameTV;
    TextView displayOrgOrgNameTV;
    TextView displayOrgTypeTV;

    UserInfo currUserInfoObj;

    String TAG = "Abhi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_view_profile);

        currUserInfoObj = aMainActivity.firebaseHelper.getUserInfo();

        displayOrgNameTV = findViewById(R.id.displayOrgNameTV);
        displayOrgOrgNameTV = findViewById(R.id.displayOrgOrgNameTV);
        displayOrgTypeTV = findViewById(R.id.displayOrgTypeTV);

        Log.i(TAG, "org name:" + currUserInfoObj.getName()) ;
        displayOrgNameTV.setText("Name: " + currUserInfoObj.getName());
        displayOrgOrgNameTV.setText("Organization Name: " + currUserInfoObj.getOrganizationName());
        displayOrgTypeTV.setText("Organization Type: " + currUserInfoObj.getOrgType());
    }

    public void takeToEditOrgProfileScreen(View v)
    {
        Intent p = new Intent(this, cEditOrgProfile.class);
        startActivity(p);
    }

    public void goBackToOrgDashboard(View v)
    {
        Intent p = new Intent(this, cOrgDashboard.class);
        startActivity(p);
    }
}