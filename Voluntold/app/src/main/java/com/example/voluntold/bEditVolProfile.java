package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class bEditVolProfile extends AppCompatActivity {

    EditText newVolNameET;
    EditText newVolAgeET;
    EditText newVolSchoolET;

    Button changeVolNameBT;
    Button changeVolAgeBT;
    Button changeVolSchoolBT;

    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vol_edit_profile);

        newVolNameET = findViewById(R.id.newVolNameET);
        newVolAgeET = findViewById(R.id.newVolAgeET);
        newVolSchoolET = findViewById(R.id.newVolSchoolET);

        changeVolNameBT = findViewById(R.id.newVolNameBT);
        changeVolAgeBT = findViewById(R.id.newVolAgeBT);
        changeVolSchoolBT = findViewById(R.id.newVolSchoolBT);

    }

    public void changeVolName(View v)
    {
        String newVolName = newVolNameET.getText().toString();

        // Changes the name but the school to "no school"
        aMainActivity.firebaseHelper.updateUserInfo(new UserInfo (aMainActivity.firebaseHelper.getUserInfo().getAccountType(), aMainActivity.firebaseHelper.getUserInfo().getAllOrgPosts(), newVolName,
                null, null, aMainActivity.firebaseHelper.getUserInfo().getSchool(), aMainActivity.firebaseHelper.getUserInfo().getUserAge(),
                aMainActivity.firebaseHelper.getUserInfo().getUserEmail(), aMainActivity.firebaseHelper.getUserInfo().getUserPassword(), aMainActivity.firebaseHelper.getUserInfo().getUserUID(),
                aMainActivity.firebaseHelper.getUserInfo().getVolOpportunities()));

    }

    public void changeVolAge(View v)
    {
        int newVolAge = Integer.parseInt(newVolAgeET.getText().toString());


        aMainActivity.firebaseHelper.updateUserInfo(new UserInfo (aMainActivity.firebaseHelper.getUserInfo().getAccountType(), aMainActivity.firebaseHelper.getUserInfo().getAllOrgPosts(),
                aMainActivity.firebaseHelper.getUserInfo().getName(), null, null, aMainActivity.firebaseHelper.getUserInfo().getSchool(), newVolAge,
                aMainActivity.firebaseHelper.getUserInfo().getUserEmail(), aMainActivity.firebaseHelper.getUserInfo().getUserPassword(), aMainActivity.firebaseHelper.getUserInfo().getUserUID(),
                aMainActivity.firebaseHelper.getUserInfo().getVolOpportunities()));


    }

    public void changeVolSchool(View v)
    {
        String newVolSchool = newVolSchoolET.getText().toString();


        aMainActivity.firebaseHelper.updateUserInfo(new UserInfo (aMainActivity.firebaseHelper.getUserInfo().getAccountType(), aMainActivity.firebaseHelper.getUserInfo().getAllOrgPosts(),
                aMainActivity.firebaseHelper.getUserInfo().getName(), null, null, newVolSchool, aMainActivity.firebaseHelper.getUserInfo().getUserAge(),
                aMainActivity.firebaseHelper.getUserInfo().getUserEmail(), aMainActivity.firebaseHelper.getUserInfo().getUserPassword(), aMainActivity.firebaseHelper.getUserInfo().getUserUID(),
                aMainActivity.firebaseHelper.getUserInfo().getVolOpportunities()));


    }

    public void goBackToVolProfileScreen(View v)
    {
        Intent p = new Intent(this, bVolViewProfile.class);
        startActivity(p);
    }
}