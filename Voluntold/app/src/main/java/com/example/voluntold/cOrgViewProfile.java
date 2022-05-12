package com.example.voluntold;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class cOrgViewProfile extends AppCompatActivity {

    TextView displayOrgNameTV;
    TextView displayOrgOrgNameTV;
    TextView displayOrgTypeTV;

    UserInfo currUserInfoObj;

    String TAG = "Abhi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_view_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.profileorg);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId())
            {
                case R.id.profileorg:
                    return true;
                case R.id.posts:
                    startActivity(new Intent(getApplicationContext(), cOrgPostedOpportunities.class));
                    return true;
                case R.id.create:
                    startActivity(new Intent(getApplicationContext(), cOrgCreateOpportunityPost.class));
                    return true;
                case R.id.signoutorg:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setTitle("Signing out")
                            .setMessage("Do you want to sign out?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    aMainActivity.firebaseHelper.getmAuth().signOut();
                                    aMainActivity.firebaseHelper.getUserInfo().setAccountType("");
                                    aMainActivity.firebaseHelper.updateUid(null);
                                    startActivity(new Intent(getApplicationContext(), aMainActivity.class));
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .show();
            }
            return false;
        });

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
        Intent p = new Intent(this, cOrgEditProfile.class);
        startActivity(p);
    }

    public void goBackToOrgDashboard(View v)
    {
        Intent p = new Intent(this, cOrgDashboard.class);
        startActivity(p);
    }
}