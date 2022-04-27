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
    private ArrayList<String> volListNames = new ArrayList<>();
    OrgPost clickedOrgPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_view_volunteers);

        Intent intent = getIntent();
        clickedOrgPost = (OrgPost) intent.getParcelableExtra("ITEM_TO_EDIT");

        volList = clickedOrgPost.getVolunteers();

        for (int i = 0; i < volList.size(); i++) {
            volListNames.add(volList.get(i).getName());
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, volListNames);
        // may change simple_list_item_1 to custom ListView layout

        ListView listView = (ListView) findViewById(R.id.postView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(cViewVolunteers.this, cOrgVerifyVolunteer.class);

                // Sends the specific object at index i to the Edit activity
                // In this case, it is sending the particular WishListItem object
                intent.putExtra("ITEM_TO_EDIT", volListNames.get(i));
                startActivity(intent);
            }
        });
    }

    public void goBack(View v) {
        Intent p = new Intent(this, cOrgViewOpportunity.class);
        startActivity(p);
    }
}