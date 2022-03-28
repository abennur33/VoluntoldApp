package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class cOrgDashboard extends AppCompatActivity {

    private ArrayList<OrgPost> postList;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opportunities_view);

        // NEED FUNCTION TO GET ARRAYLIST OF ALL POSTS WHEN DONE
        postList = aMainActivity.firebaseHelper.();
        Intent intent = getIntent();

        ArrayAdapter<OrgPost> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, postList);
        // may change simple_list_item_1 to custom ListView layout

        ListView listView = (ListView) findViewById(R.id.postView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(OpportunitiesView.this, OpportunityPost.class);

                // Sends the specific object at index i to the Edit activity
                // In this case, it is sending the particular WishListItem object
                intent.putExtra("ITEM_TO_EDIT", postList.get(i));
                startActivity(intent);
            }
        mAuth = FirebaseAuth.getInstance();
    }

    public void signOut(View v) {
        mAuth.signOut();
        Intent p = new Intent(this, aMainActivity.class);
        startActivity(p);
    }
}