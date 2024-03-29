package com.example.voluntold;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bVolViewOpportunityFromSaved extends AppCompatActivity {

    OrgPost clickedOrgPost;
    String TAG = "Abhi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vol_view_opportunity_saved);

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
                aMainActivity.firebaseHelper.getmAuth().getUid(), clickedOrgPost.getDocID(), clickedOrgPost.getTitle(), clickedOrgPost.getMonth(),
                clickedOrgPost.getDate(), clickedOrgPost.getYear());

        UserInfo userInfoObjOfCurUser = aMainActivity.firebaseHelper.getUserInfo();
        // Log.i(TAG, userInfoObjOfCurUser.toString());

        aMainActivity.firebaseHelper.addPosttoVol(volOpportunity);
        aMainActivity.firebaseHelper.addVoltoPost(clickedOrgPost, userInfoObjOfCurUser);

        Intent intent = new Intent(bVolViewOpportunityFromSaved.this, bVolDashboard.class);
        startActivity(intent);
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, bVolSavedOpportunities.class);
        startActivity(intent);
    }
}