package com.example.voluntold;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class bVolSavedOpportunities extends AppCompatActivity {

    private ArrayList<VolOpportunity> savedList;

    private FirebaseAuth mAuth;
    private FirebaseHelper.FirestoreCallback FirestoreCallback;
    public final String TAG = "Josh2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vol_saved_opportunities);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.saved);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId())
            {
                case R.id.discover:
                    startActivity(new Intent(getApplicationContext(), bVolDiscoverOpportunities.class));
                    return true;
                case R.id.saved:
                    return true;
                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), bVolViewProfile.class));
                    return true;
                case R.id.signout:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setTitle("Signing out")
                            .setMessage("Do you want to sign out?")
                            .setCancelable(true)
                            .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    aMainActivity.firebaseHelper.getmAuth().signOut();
                                    aMainActivity.firebaseHelper.getUserInfo().setAccountType("");
                                    aMainActivity.firebaseHelper.updateUid(null);
                                    startActivity(new Intent(getApplicationContext(), aMainActivity.class));
                                }
                            })
                            .show();

            }
            return false;
        });

        mAuth = FirebaseAuth.getInstance();
        savedList = aMainActivity.firebaseHelper.getVolOpps();

        ArrayList<OrgPost> savedPosts = new ArrayList<>();

        for (VolOpportunity v: savedList) {
           for (OrgPost o: aMainActivity.firebaseHelper.getPosts()) {
               if ((mAuth.getCurrentUser().getUid() + "-volOPP-" +o.getDocID()).equals(v.getDocID())) {
                    savedPosts.add(o);
               }
           }
        }

        Intent intent = getIntent();

        ArrayAdapter<OrgPost> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, savedPosts);
        // may change simple_list_item_1 to custom ListView layout

        ListView listView = (ListView) findViewById(R.id.postView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(bVolSavedOpportunities.this, bVolViewOpportunityFromDisc.class);

                // Sends the specific object at index i to the Edit activity
                // In this case, it is sending the particular WishListItem object
                intent.putExtra("ITEM_TO_EDIT", savedPosts.get(i));
                startActivity(intent);
            }
        });
    }
}

