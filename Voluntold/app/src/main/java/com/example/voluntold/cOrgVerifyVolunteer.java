package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class cOrgVerifyVolunteer extends AppCompatActivity {

    private UserInfo clickedUser;
    private OrgPost clickedOrgPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_verify_volunteer);

        Intent intent = getIntent();
        clickedUser = (UserInfo) intent.getParcelableExtra("USER_TO_EDIT");
        clickedOrgPost = (OrgPost) intent.getParcelableExtra("ITEM_TO_EDIT");
    }

    public void verifyVolunteer() {
        VolOpportunity volOpportunity = new VolOpportunity(clickedOrgPost.getOrgID(),
                aMainActivity.firebaseHelper.getmAuth().getUid(), clickedOrgPost.getDocID(), clickedOrgPost.getTitle(), clickedOrgPost.getMonth(),
                clickedOrgPost.getDate(), clickedOrgPost.getYear());
        aMainActivity.firebaseHelper.verifyUserforVolOpp(clickedUser, clickedOrgPost, volOpportunity);

        Intent intent = new Intent(cOrgVerifyVolunteer.this, cOrgViewOpportunity.class);

        intent.putExtra("ITEM_TO_EDIT", clickedOrgPost);
        Log.i("Aadit", "in postedopps" + clickedOrgPost.getVolunteers().toString());
        ArrayList<UserInfo> users = clickedOrgPost.getVolunteers();
        intent.putParcelableArrayListExtra("USERS_LIST", users);
        startActivity(intent);
    }

    public void goBack(View v) {
        Intent p = new Intent(this, cViewVolunteers.class);
        startActivity(p);
    }
}