package com.example.voluntold;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class cOrgPostedOpportunities extends AppCompatActivity {

    private ArrayList<OrgPost> postList;
    private ArrayList<OrgPost> allPostList;
    private ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_posted_opportunities);

        // NEED FUNCTION TO GET ARRAYLIST OF ALL POSTS WHEN DONE
        Intent intent = getIntent();
        allPostList = aMainActivity.firebaseHelper.getPosts();
        String orgID = aMainActivity.firebaseHelper.getmAuth().getUid();

        for (int i = 0; i < allPostList.size(); i++) {
            if (allPostList.get(i).getOrgID().equals(orgID)) {
                postList.add(allPostList.get(i));
            }
        }

        ArrayAdapter<OrgPost> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, postList);
        // may change simple_list_item_1 to custom ListView layout

        ListView listView = (ListView) findViewById(R.id.postView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(cOrgPostedOpportunities.this, cOrgViewOpportunity.class);

                // Sends the specific object at index i to the Edit activity
                // In this case, it is sending the particular WishListItem object
                intent.putExtra("ITEM_TO_EDIT", postList.get(i));
                startActivity(intent);
            }
        });
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, bVolDashboard.class);
        startActivity(intent);
    }
}