package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class bDiscoverOpportunities extends AppCompatActivity {

    private ArrayList<OrgPost> postList;
    private ArrayList<Organization> orgList;
    private ArrayList<OrgPost> sortedList;
    private Spinner s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_discover_opportunities);

        String[] arraySpinner = new String[] {
                "Date", "Agriculture/Food", "Environment", "Education", "Health", "Community", "Other"
        };
        s = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        postList = aMainActivity.firebaseHelper.getPosts();

        orgList = aMainActivity.firebaseHelper.getOrgs();



        ArrayAdapter<OrgPost> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, postList);
        // may change simple_list_item_1 to custom ListView layout

        ListView listView = (ListView) findViewById(R.id.postView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(bDiscoverOpportunities.this, bVolOpportunityPost.class);

                // Sends the specific object at index i to the Edit activity
                // In this case, it is sending the particular WishListItem object
                intent.putExtra("ITEM_TO_EDIT", postList.get(i));
                startActivity(intent);

//                sortBy(s.getSelectedItem().toString());
            }
        });
    }

    public void dateSort(ArrayList<OrgPost> sortedList) {

    }

//    public void sortBy(String field)
//    {
//
//       for (int i = 0; i < postList.size(); i++)
//       {
//           String uidOfOrgWhoPostedCurrentPost = postList.get(i).getOrgID();
//           for (int j = 0; j < orgList.size(); j++)
//           {
//               if (orgList.get(j).get)
//           }
//       }
//
//
//
//    }

    public void goBack(View v) {
        Intent intent = new Intent(this, bVolDashboard.class);
        startActivity(intent);
    }
}