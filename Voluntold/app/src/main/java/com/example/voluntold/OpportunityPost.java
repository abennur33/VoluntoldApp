package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class OpportunityPost extends AppCompatActivity {

    OrgPost clickedOrgPost;
    String TAG = "Abhi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opportunity_post);

        Intent intent = getIntent();

        clickedOrgPost = (OrgPost) intent.getParcelableExtra("ITEM_TO_EDIT");

        TextView displayTitleTV = findViewById(R.id.postTitleTV);
        TextView displayDateTV = findViewById(R.id.postDateTV);
        TextView displayContentTV = findViewById(R.id.contentOfPostTV);

        displayTitleTV.setText(clickedOrgPost.getTitle());
        displayDateTV.setText(clickedOrgPost.getMonth() + "-" + clickedOrgPost.getDate() + "-" + clickedOrgPost.getYear());
        displayContentTV.setText(clickedOrgPost.getBody());
    }

    public void signUpUserForOrganizationPost(View v)
    {
        VolOpportunity volOpportunity = new VolOpportunity(clickedOrgPost.getOrgID(),
                aMainActivity.firebaseHelper.getmAuth().getUid(), clickedOrgPost.getTitle(), clickedOrgPost.getMonth(),
                clickedOrgPost.getDate(), clickedOrgPost.getYear());

        UserInfo userInfoObjOfCurUser = aMainActivity.firebaseHelper.getUserInfo();
        // Log.i(TAG, userInfoObjOfCurUser.toString());

        userInfoObjOfCurUser.addVolOpportunity(volOpportunity);

        clickedOrgPost.decrementMaxVolunteers();

        Intent intent = new Intent(OpportunityPost.this, bVolDashboard.class);
        startActivity(intent);

    }
}