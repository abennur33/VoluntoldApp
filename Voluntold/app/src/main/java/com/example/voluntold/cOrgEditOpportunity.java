package com.example.voluntold;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class cOrgEditOpportunity extends AppCompatActivity {

    private ArrayList<OrgPost> postList;
    OrgPost clickedOrgPost;
    private static final String TAG = "Abhi3";

    private FirebaseAuth mAuth;
    private FirebaseHelper.FirestoreCallback FirestoreCallback;
    private EditText displayTitleET, monthET, dayET, yearET, displayContentET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_edit_opportunity_post);
        Intent intent = getIntent();
        clickedOrgPost = (OrgPost) intent.getParcelableExtra("ITEM_TO_EDIT2");

        EditText displayTitleET = findViewById(R.id.postTitleTV);
        EditText monthET = findViewById(R.id.monthET);
        EditText dayET = findViewById(R.id.dayET);
        EditText yearET = findViewById(R.id.yearET);
        EditText displayContentET = findViewById(R.id.contentOfPostTV);

        displayTitleET.setText(clickedOrgPost.getTitle());
        Log.i(TAG, clickedOrgPost.getTitle());
        monthET.setText(String.valueOf(clickedOrgPost.getMonth()));
        dayET.setText(String.valueOf(clickedOrgPost.getDate()));
        yearET.setText(String.valueOf(clickedOrgPost.getYear()));
        displayContentET.setText(clickedOrgPost.getBody());


    }

    public void updateData(View v) {
        String newTitle = findViewById(R.id.postTitleTV).toString(); // find what to put here;
        Integer newMonth = Integer.parseInt(monthET.getText().toString());
        Integer newDay= Integer.parseInt(monthET.getText().toString());
        Integer newYear = Integer.parseInt(monthET.getText().toString());
        String newContent = displayContentET.getText().toString();
        String docID = clickedOrgPost.getDocID();
        clickedOrgPost.setTitle(newTitle);
        clickedOrgPost.setMonth(newMonth);
        clickedOrgPost.setDate(newDay);
        clickedOrgPost.setYear(newYear);
        clickedOrgPost.setBody(newContent);
        // NEED TO DO ASAP
        // firebaseHelper code
        aMainActivity.firebaseHelper.editPost(clickedOrgPost);
        Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show();
    }

    public void goBack(View v)
    {
        Intent p = new Intent(this, cOrgViewOpportunity.class);
        startActivity(p);
    }

}