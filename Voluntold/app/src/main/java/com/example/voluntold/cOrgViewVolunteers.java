package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class cOrgViewVolunteers extends AppCompatActivity {

    private ArrayList<UserInfo> volList;
    private ArrayList<String> volListNames = new ArrayList<>();
    OrgPost clickedOrgPost;
    private final String TAG = "Josh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_view_volunteers);

        Intent intent = getIntent();
        clickedOrgPost = (OrgPost) intent.getParcelableExtra("ITEM_TO_EDIT");
        clickedOrgPost.setVolunteers(intent.getParcelableArrayListExtra("USERS_LIST"));
        Log.i("Aadit", clickedOrgPost.getVolunteers().toString());
        Log.i(TAG, clickedOrgPost.getVolunteers().toString());

        volList = clickedOrgPost.getVolunteers();
//        Log.i(TAG, volList.get(0).toString());

        for (int i = 0; i < volList.size(); i++) {
            volListNames.add(volList.get(i).toString());
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, volListNames);
        // may change simple_list_item_1 to custom ListView layout

        ListView listView = (ListView) findViewById(R.id.postView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(cOrgViewVolunteers.this, cOrgVerifyVolunteer.class);

                // Sends the specific object at index i to the Edit activity
                // In this case, it is sending the particular WishListItem object
                intent.putExtra("USER_TO_EDIT", volList.get(i));
                intent.putExtra("ITEM_TO_EDIT", clickedOrgPost);
                ArrayList<UserInfo> users = clickedOrgPost.getVolunteers();
                intent.putParcelableArrayListExtra("USERS_LIST", users);
                startActivity(intent);
            }
        });
    }

    public void verifyAll(View v) {
        for (UserInfo u: volList) {
            VolOpportunity volOpportunity = new VolOpportunity(clickedOrgPost.getOrgID(),
                    aMainActivity.firebaseHelper.getmAuth().getUid(), clickedOrgPost.getDocID(), clickedOrgPost.getTitle(), clickedOrgPost.getMonth(),
                    clickedOrgPost.getDate(), clickedOrgPost.getYear());
            volOpportunity.setCompleted(true);
            aMainActivity.firebaseHelper.verifyUserforVolOpp(u, clickedOrgPost, volOpportunity);
        }

        Intent intent = new Intent(cOrgViewVolunteers.this, cOrgViewOpportunity.class);

        intent.putExtra("ITEM_TO_EDIT", clickedOrgPost);
        Log.i("Aadit", "in postedopps" + clickedOrgPost.getVolunteers().toString());
        ArrayList<UserInfo> users = clickedOrgPost.getVolunteers();
        intent.putParcelableArrayListExtra("USERS_LIST", users);
        startActivity(intent);
    }

    public void goBack(View v) {
        Intent p = new Intent(this, cOrgViewOpportunity.class);
        p.putExtra("ITEM_TO_EDIT", clickedOrgPost);
        startActivity(p);
    }
}