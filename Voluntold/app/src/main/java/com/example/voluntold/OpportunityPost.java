package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OpportunityPost extends AppCompatActivity {

    OrgPost clickedOrgPost;

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
        displayDateTV.setText(clickedOrgPost.getDate());
        displayContentTV.setText(clickedOrgPost.getBody());
    }

    public void signUpUserForOrganizationPost(View v)
    {
        VolOpportunity volOpportunity = new VolOpportunity(clickedOrgPost.getOrgID(),
                aMainActivity.firebaseHelper.getmAuth().getUid(), clickedOrgPost.getTitle(), clickedOrgPost.getMonth(),
                clickedOrgPost.getDate(), clickedOrgPost.getYear());

        aMainActivity.firebaseHelper.getUserInfo().addVolOpportunity(volOpportunity);

        clickedOrgPost.decrementMaxVolunteers();

        Intent intent = new Intent(OpportunityPost.this, bVolDashboard.class);

    }
}