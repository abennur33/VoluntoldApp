package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class cOrgEditProfile extends AppCompatActivity {

    EditText newOrgNameET;
    EditText newOrgOrgNameET;
    Spinner newOrgTypeSP;

    Button changeOrgNameBT;
    Button changeOrgOrgNameBT;
    Button changeOrgTypeBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_edit_profile);

        newOrgNameET = findViewById(R.id.newOrgNameET);
        newOrgOrgNameET = findViewById(R.id.newOrgOrgNameET);
        newOrgTypeSP = findViewById(R.id.newOrgTypeSP);

        changeOrgNameBT = findViewById(R.id.newOrgNameBT);
        changeOrgOrgNameBT = findViewById(R.id.newOrgOrgNameBT);
        changeOrgTypeBT = findViewById(R.id.newOrgTypeBT);

        String[] arraySpinner = new String[] {
                "Agriculture/Food", "Environment", "Education", "Health", "Community", "Other"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newOrgTypeSP.setAdapter(adapter);

    }

    public void changeOrgName(View v)
    {
        String newOrgName = newOrgNameET.getText().toString();

        aMainActivity.firebaseHelper.updateUserInfo(new UserInfo (aMainActivity.firebaseHelper.getUserInfo().getAccountType(), aMainActivity.firebaseHelper.getUserInfo().getAllOrgPosts(), newOrgName,
                aMainActivity.firebaseHelper.getUserInfo().getOrgType(), aMainActivity.firebaseHelper.getUserInfo().getOrganizationName(), null, 0,
                aMainActivity.firebaseHelper.getUserInfo().getUserEmail(), aMainActivity.firebaseHelper.getUserInfo().getUserPassword(), aMainActivity.firebaseHelper.getUserInfo().getUserUID(),
                aMainActivity.firebaseHelper.getUserInfo().getVolOpportunities()));


    }

    public void changeOrgOrgName(View v)
    {
        String newOrgOrgName = newOrgOrgNameET.getText().toString();

        aMainActivity.firebaseHelper.updateUserInfo(new UserInfo (aMainActivity.firebaseHelper.getUserInfo().getAccountType(), aMainActivity.firebaseHelper.getUserInfo().getAllOrgPosts(), aMainActivity.firebaseHelper.getUserInfo().getName(),
                aMainActivity.firebaseHelper.getUserInfo().getOrgType(), newOrgOrgName, null, 0,
                aMainActivity.firebaseHelper.getUserInfo().getUserEmail(), aMainActivity.firebaseHelper.getUserInfo().getUserPassword(), aMainActivity.firebaseHelper.getUserInfo().getUserUID(),
                aMainActivity.firebaseHelper.getUserInfo().getVolOpportunities()));


    }

    public void changeOrgType(View v)
    {
        String newOrgType = newOrgTypeSP.getSelectedItem().toString();

        aMainActivity.firebaseHelper.updateUserInfo(new UserInfo (aMainActivity.firebaseHelper.getUserInfo().getAccountType(), aMainActivity.firebaseHelper.getUserInfo().getAllOrgPosts(), aMainActivity.firebaseHelper.getUserInfo().getName(),
                newOrgType, aMainActivity.firebaseHelper.getUserInfo().getOrganizationName(), null, 0,
                aMainActivity.firebaseHelper.getUserInfo().getUserEmail(), aMainActivity.firebaseHelper.getUserInfo().getUserPassword(), aMainActivity.firebaseHelper.getUserInfo().getUserUID(),
                aMainActivity.firebaseHelper.getUserInfo().getVolOpportunities()));
    }

    public void goBackToOrgProfileScreen(View v)
    {
        aMainActivity.firebaseHelper.attachReadDataToUser();
        Intent p = new Intent(this, OrgViewProfile.class);
        startActivity(p);
    }

}