package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class cViewVolunteers extends AppCompatActivity {

    private ArrayList<UserInfo> volList;
    OrgPost clickedOrgPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_view_volunteers);

        Intent intent = getIntent();
        clickedOrgPost = (OrgPost) intent.getParcelableExtra("ITEM_TO_EDIT");

        volList = clickedOrgPost.getVolunteers();

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, volList);
        // may change simple_list_item_1 to custom ListView layout

        ListView listView = (ListView) findViewById(R.id.postView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(cViewVolunteers.this, bVolOpportunityPost.class); // .class needs to change

                // Sends the specific object at index i to the Edit activity
                // In this case, it is sending the particular WishListItem object
                intent.putExtra("ITEM_TO_EDIT", volList.get(i));
                startActivity(intent);
            }
        });
    }
}