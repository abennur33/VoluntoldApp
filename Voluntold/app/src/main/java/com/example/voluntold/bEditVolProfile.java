package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class bEditVolProfile extends AppCompatActivity {

    EditText newVolNameET;
    EditText newVolAgeET;
    EditText newVolSchoolET;

    Button changeOrgTypeBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vol_edit_profile);

        newVolNameET = findViewById(R.id.newVolNameET);
        newVolAgeET = findViewById(R.id.newVolAgeET);
        newVolSchoolET = findViewById(R.id.newVolSchoolET);

        newVolNameET.setText(aMainActivity.firebaseHelper.getUserInfo().getName());
        newVolAgeET.setText(aMainActivity.firebaseHelper.getUserInfo().getUserAge());
        newVolSchoolET.setText(aMainActivity.firebaseHelper.getUserInfo().getSchool());
    }
    public void changeAll(View v){
        String newVolName = newVolNameET.getText().toString();
        int newVolAge = Integer.parseInt(newVolAgeET.getText().toString());
        String newVolSchool = newVolSchoolET.getText().toString();

        aMainActivity.firebaseHelper.updateUserInfo(new UserInfo(aMainActivity.firebaseHelper.getUserInfo().getAccountType(), aMainActivity.firebaseHelper.getUserInfo().getAllOrgPosts(), newVolName,
                null, null, newVolSchool, newVolAge,
                aMainActivity.firebaseHelper.getUserInfo().getUserEmail(), aMainActivity.firebaseHelper.getUserInfo().getUserPassword(), aMainActivity.firebaseHelper.getUserInfo().getUserUID(),
                aMainActivity.firebaseHelper.getUserInfo().getVolOpportunities()));
        // goBackToOrgProfileScreen(v); data is not updating
    }

    public void goBackToOrgProfileScreen(View v)
    {
        Intent p = new Intent(this, bVolViewProfile.class);
        startActivity(p);
    }
}