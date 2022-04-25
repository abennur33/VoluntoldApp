package com.example.voluntold;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class cEditOpportunity extends AppCompatActivity {

    private ArrayList<OrgPost> postList;
    OrgPost clickedOrgPost;

    private FirebaseAuth mAuth;
    private FirebaseHelper.FirestoreCallback FirestoreCallback;
    private EditText displayTitleET, monthET, dayET, yearET, displayContentET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        clickedOrgPost = (OrgPost) intent.getParcelableExtra("ITEM_TO_EDIT");

        EditText displayTitleET = findViewById(R.id.postTitleTV);
        EditText monthET = findViewById(R.id.monthET);
        EditText dayET = findViewById(R.id.dayET);
        EditText yearET = findViewById(R.id.yearET);
        EditText displayContentET = findViewById(R.id.contentOfPostTV);

        displayTitleET.setText(clickedOrgPost.getTitle());
        monthET.setText(clickedOrgPost.getMonth());
        dayET.setText(clickedOrgPost.getDate());
        yearET.setText(clickedOrgPost.getYear());
        displayContentET.setText(clickedOrgPost.getBody());

    }

    public void editOpportunity(View v) {
        Intent p = new Intent(cEditOpportunity.this, cEditOpportunity.class);
        p.putExtra("ITEM_TO_EDIT", clickedOrgPost);
        startActivity(p);
    }

    public void updateData(View v) {
        String newTitle = displayTitleET.getText().toString();
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
        MainActivity.firebaseHelper.editData(w);
        Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show();
    }


}