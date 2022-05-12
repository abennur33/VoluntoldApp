package com.example.voluntold;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class cOrgPostedOpportunities extends AppCompatActivity {

    public ArrayList<OrgPost> postList = new ArrayList<>();
    public ArrayList<OrgPost> allPostList;
    private ArrayList<String> titles;
    public final String TAG = "Josh";
    public FirebaseHelper firebasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_posted_opportunities);
        firebasehelper = new FirebaseHelper();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.posts);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId())
            {
                case R.id.profileorg:
                    startActivity(new Intent(getApplicationContext(), cOrgViewProfile.class));
                    return true;
                case R.id.posts:
                    return true;
                case R.id.create:
                    startActivity(new Intent(getApplicationContext(), cOrgCreateOpportunityPost.class));
                    return true;
                case R.id.signoutorg:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setTitle("Signing out")
                            .setMessage("Do you want to sign out?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    aMainActivity.firebaseHelper.getmAuth().signOut();
                                    aMainActivity.firebaseHelper.getUserInfo().setAccountType("");
                                    aMainActivity.firebaseHelper.updateUid(null);
                                    startActivity(new Intent(getApplicationContext(), aMainActivity.class));
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .show();
            }
            return false;
        });

        // NEED FUNCTION TO GET ARRAYLIST OF ALL POSTS WHEN DONE
        Intent intent = getIntent();
        allPostList = aMainActivity.firebaseHelper.getPosts();
        String orgID = aMainActivity.firebaseHelper.getmAuth().getUid();

        // Log.i(TAG, allPostList.get(0).getTitle());

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
                Log.i("Aadit", "in postedopps" + postList.get(i).getVolunteers().toString());
                ArrayList<UserInfo> users = postList.get(i).getVolunteers();
                intent.putParcelableArrayListExtra("USERS_LIST", users);
                startActivity(intent);
            }
        });
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, cOrgDashboard.class);
        startActivity(intent);
    }
}