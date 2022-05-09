package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class cOrgCreateOpportunityPost extends AppCompatActivity {

    private String title, body;
    private int month, day, year, maxVols;
    private EditText titleET, bodyET, monthET, dayET, yearET, maxVolsET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_create_opportunity_post);

        titleET = findViewById(R.id.titleET);
        bodyET = findViewById(R.id.bodyET);
        monthET = findViewById(R.id.monthET);
        dayET = findViewById(R.id.dayET);
        yearET = findViewById(R.id.yearET);
        maxVolsET = findViewById(R.id.maxVolnsET);
    }

    public void Post(View v) {
        title = titleET.getText().toString();
        body = bodyET.getText().toString();
        month = Integer.parseInt(monthET.getText().toString());
        day = Integer.parseInt(dayET.getText().toString());
        year = Integer.parseInt(yearET.getText().toString());
        maxVols = Integer.parseInt(maxVolsET.getText().toString());

        String uid = aMainActivity.firebaseHelper.getUid();

        OrgPost orgPost = new OrgPost(uid, title, month, day, year, body, maxVols);

        aMainActivity.firebaseHelper.addPost(orgPost);

        Intent intent = new Intent(cOrgCreateOpportunityPost.this, cOrgDashboard.class);
        startActivity(intent);
    }

    public void goHome(View v) {
//        aMainActivity.firebaseHelper.attachReadDataToUser();
        Intent intent = new Intent(this, cOrgDashboard.class);
        startActivity(intent);
    }
}