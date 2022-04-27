package com.example.voluntold;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class cOrgViewOpportunity extends AppCompatActivity {

    private ArrayList<OrgPost> postList;
    OrgPost clickedOrgPost;

    private FirebaseAuth mAuth;
    private FirebaseHelper.FirestoreCallback FirestoreCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.org_view_opportunity_post);

            Intent intent = getIntent();

            clickedOrgPost = (OrgPost) intent.getParcelableExtra("ITEM_TO_EDIT");

            TextView displayTitleTV = findViewById(R.id.postTitleTV);
            TextView displayDateTV = findViewById(R.id.postDateTV);
            TextView displayContentTV = findViewById(R.id.contentOfPostTV);

            displayTitleTV.setText(clickedOrgPost.getTitle());
            displayDateTV.setText(clickedOrgPost.getMonth() + "-" + clickedOrgPost.getDate() + "-" + clickedOrgPost.getYear());
            displayContentTV.setText(clickedOrgPost.getBody());
    }

    public void editOpportunity(View v) {
        Intent p = new Intent(cOrgViewOpportunity.this, cEditOpportunity.class);
        p.putExtra("ITEM_TO_EDIT", clickedOrgPost);
        startActivity(p);
    }
    public void viewVolunteers(View v) {
        Intent p = new Intent(this, cViewVolunteers.class);
        p.putExtra("ITEM_TO_EDIT", clickedOrgPost);
        startActivity(p);
    }

    public void goBack(View v) {
        Intent p = new Intent(this, cOrgPostedOpportunities.class);
        startActivity(p);
    }
}