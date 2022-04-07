package com.example.voluntold;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class cPostedOpportunities extends AppCompatActivity {

    private ArrayList<OrgPost> postList;

    private FirebaseAuth mAuth;
    private FirebaseHelper.FirestoreCallback FirestoreCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_dashboard);

        mAuth = FirebaseAuth.getInstance();
         //NEED FUNCTION TO GET ARRAYLIST OF ALL POSTS WHEN DONE
        postList = aMainActivity.firebaseHelper.getPostsbyOrg(mAuth.getUid());
        Intent intent = getIntent();

        ArrayAdapter<OrgPost> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, postList);
        // may change simple_list_item_1 to custom ListView layout

        ListView listView = (ListView) findViewById(R.id.postView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(cPostedOpportunities.this, cOrgOpportunityPost.class);

                // Sends the specific object at index i to the Edit activity
                // In this case, it is sending the particular WishListItem object
                intent.putExtra("ITEM_TO_EDIT", postList.get(i));
                startActivity(intent);
            }
        });
    }

    public void signOut(View v) {
        mAuth.signOut();
        Intent p = new Intent(this, aMainActivity.class);
        startActivity(p);
    }
}