package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class OrgCreateOpportunityPost extends AppCompatActivity {

    private String title, body;
    private int month, day, year;
    private EditText titleET, bodyET, monthET, dayET, yearET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_create_opportunity_post);

        titleET = findViewById(R.id.titleET);
        bodyET = findViewById(R.id.bodyET);
        monthET = findViewById(R.id.monthET);
        dayET = findViewById(R.id.dayET);
        yearET = findViewById(R.id.yearET);
    }

    public void Post(View v) {
        title = titleET.getText().toString();
        body = bodyET.getText().toString();
        month = Integer.parseInt(monthET.getText().toString());
        day = Integer.parseInt(dayET.getText().toString());
        year = Integer.parseInt(yearET.getText().toString());

        String uid = LogIn.firebaseHelper.getmAuth().getUid();

        OrgPost orgPost = new OrgPost(uid, title, month, day, year, body);

        LogIn.firebaseHelper.addPost(orgPost);
    }
}