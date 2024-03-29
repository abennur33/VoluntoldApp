package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class cOrgVerifyVolunteer extends AppCompatActivity {

    private UserInfo clickedUser;
    private OrgPost clickedOrgPost;
    TextView displayVolNameTV;
    TextView displayVolAgeTV;
    TextView displayVolSchoolTV;

    UserInfo currUserInfoObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_verify_volunteer);

        Intent intent = getIntent();
        clickedUser = (UserInfo) intent.getParcelableExtra("USER_TO_EDIT");
        clickedOrgPost = (OrgPost) intent.getParcelableExtra("ITEM_TO_EDIT");
        clickedOrgPost.setVolunteers(intent.getParcelableArrayListExtra("USERS_LIST"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.vol_view_profile);

        currUserInfoObj = clickedUser;

        displayVolNameTV = findViewById(R.id.displayVolNameTV);
        displayVolAgeTV = findViewById(R.id.dislpayVolAgeTV);
        displayVolSchoolTV = findViewById(R.id.displayVolSchoolTV);

        displayVolNameTV.setText("Name: " + currUserInfoObj.getName());
        displayVolAgeTV.setText("Age: " + Integer.toString(currUserInfoObj.getUserAge()));
        displayVolSchoolTV.setText("School: " + currUserInfoObj.getSchool());

    }

    public void verifyVolunteer(View v) {
        VolOpportunity volOpportunity = new VolOpportunity(clickedOrgPost.getOrgID(),
                clickedUser.getUserUID(), clickedOrgPost.getDocID(), clickedOrgPost.getTitle(), clickedOrgPost.getMonth(),
                clickedOrgPost.getDate(), clickedOrgPost.getYear());
        volOpportunity.setCompleted(true);
        aMainActivity.firebaseHelper.verifyUserforVolOpp(clickedUser, clickedOrgPost, volOpportunity);

        Intent intent = new Intent(cOrgVerifyVolunteer.this, cOrgViewOpportunity.class);

        intent.putExtra("ITEM_TO_EDIT", clickedOrgPost);
        Log.i("Aadit", "in postedopps" + clickedOrgPost.getVolunteers().toString());
        ArrayList<UserInfo> users = clickedOrgPost.getVolunteers();
        intent.putParcelableArrayListExtra("USERS_LIST", users);
        startActivity(intent);
    }

    public void goBack(View v) {
        Intent p = new Intent(this, cOrgViewVolunteers.class);
        startActivity(p);
    }
}