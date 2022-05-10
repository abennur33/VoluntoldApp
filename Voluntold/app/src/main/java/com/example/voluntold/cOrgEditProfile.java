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
    int pos;

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

        changeOrgTypeBT = findViewById(R.id.newOrgTypeBT);

        String[] arraySpinner = new String[] {
                "Agriculture/Food", "Environment", "Education", "Health", "Community", "Other"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newOrgTypeSP.setAdapter(adapter);
        newOrgNameET.setText(aMainActivity.firebaseHelper.getUserInfo().getName());
        newOrgOrgNameET.setText(aMainActivity.firebaseHelper.getUserInfo().getOrganizationName());
        String oldOrgType = aMainActivity.firebaseHelper.getUserInfo().getOrgType();
        if (oldOrgType.equals("Agriculture/Food")) {
            pos = 0;
        }
        else if (oldOrgType.equals("Environment")) {
            pos = 1;
        }
        else if (oldOrgType.equals("Education")) {
            pos = 2;
        }
        else if (oldOrgType.equals("Health")) {
            pos = 3;
        }
        else if (oldOrgType.equals("Community")) {
            pos = 4;
        }
        else if (oldOrgType.equals("Other")) {
            pos = 5;
        }
        newOrgTypeSP.setSelection(pos);
    }

    public void changeAll(View v) {
        String newOrgName = newOrgNameET.getText().toString();
        String newOrgOrgName = newOrgOrgNameET.getText().toString();
        String newOrgType = newOrgTypeSP.getSelectedItem().toString();

        aMainActivity.firebaseHelper.updateUserInfo(new UserInfo (aMainActivity.firebaseHelper.getUserInfo().getAccountType(), aMainActivity.firebaseHelper.getUserInfo().getAllOrgPosts(), newOrgName,
                newOrgType, newOrgOrgName, null, 0,
                aMainActivity.firebaseHelper.getUserInfo().getUserEmail(), aMainActivity.firebaseHelper.getUserInfo().getUserPassword(), aMainActivity.firebaseHelper.getUserInfo().getUserUID(),
                aMainActivity.firebaseHelper.getUserInfo().getVolOpportunities()));
        // goBackToOrgProfileScreen(v); data is not updating
    }

    public void goBackToOrgProfileScreen(View v)
    {
        Intent p = new Intent(this, cOrgViewProfile.class);
        startActivity(p);
    }

}