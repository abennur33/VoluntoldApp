package com.example.voluntold;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.util.ArrayList;

public class bVolDiscoverOpportunities extends AppCompatActivity {

    private ArrayList<OrgPost> postList;
    private ArrayList<UserInfo> orgList;
    private ArrayList<OrgPost> sortedList;
    private Spinner s;
    String TAG = "Abhi2";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vol_discover_opportunities);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.discover);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId())
            {
                case R.id.discover:
                    return true;
                case R.id.saved:
                    startActivity(new Intent(getApplicationContext(), bVolSavedOpportunities.class));
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

        String[] arraySpinner = new String[] {
                "Date", "Agriculture/Food", "Environment", "Education", "Health", "Community", "Other"
        };
        s = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        postList = aMainActivity.firebaseHelper.getPosts();
        Log.i(TAG, "1" + postList.toString());

        orgList = aMainActivity.firebaseHelper.getOrgs();

        // code that filers out old posts

        LocalDate localDate = LocalDate.now(); // this error doesn't do anything
        String[] dateElements = localDate.toString().split("-");

        int currDateYear = Integer.parseInt(dateElements[0]);
        int currDateMonth = Integer.parseInt(dateElements[1]);
        int currDateDay = Integer.parseInt(dateElements[2]);

        int currDateToCompare = (int) (10000*(currDateYear + ((double) currDateMonth/100) + ((double) currDateDay/10000)));

        for (int i = 0; i < postList.size(); i++)
        {
            if ((postList.get(i).getComparisonDate() < currDateToCompare) || (postList.get(i).getVolunteers().size() >= postList.get(i).getMaxVolunteers()))
            {
                postList.remove(i);
                i--;
            }
        }

        dateSortDefault();

        ArrayAdapter<OrgPost> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, postList);
        // may change simple_list_item_1 to custom ListView layout

        ListView listView = (ListView) findViewById(R.id.postView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(bVolDiscoverOpportunities.this, bVolViewOpportunityFromDisc.class);

                // Sends the specific object at index i to the Edit activity
                // In this case, it is sending the particular WishListItem object
                intent.putExtra("ITEM_TO_EDIT", postList.get(i));
                startActivity(intent);

            }
        });
    }

    public void dateSortDefault() {
        ArrayList<OrgPost> filteredOrgPostArr = new ArrayList<>();

        int k, l;
        for (k = 1; k < postList.size(); k++)
        {
            OrgPost key = postList.get(k);
            l = k - 1;

            while (l >= 0 && postList.get(l).getComparisonDate() > key.getComparisonDate()) {
                postList.set(l + 1, postList.get(l));
                l = l - 1;
            }
            postList.set(l + 1, key);
        }
        Log.i(TAG, "2" + postList.toString());

        for (int i = 0; i < postList.size(); i++) {
            filteredOrgPostArr.add(postList.get(i));
        }
    }

    public void sortBy(View v)
    {
        String field = s.getSelectedItem().toString();

        ArrayList<OrgPost> filteredOrgPostArr = new ArrayList<>();

       for (int i = 0; i < postList.size(); i++)
       {
           String uidOfOrgWhoPostedCurrentPost = postList.get(i).getOrgID();
           for (int j = 0; j < orgList.size(); j++)
           {
               if (orgList.get(j).getUserUID().equals(uidOfOrgWhoPostedCurrentPost))
               {
                   if (s.getSelectedItem().equals("Date")) {
                       int k, l;
                       for (k = 1; k < postList.size(); k++)
                       {
                           OrgPost key = postList.get(k);
                           l = k - 1;

                           while (l >= 0 && postList.get(l).getComparisonDate() > key.getComparisonDate()) {
                               postList.set(l + 1, postList.get(l));
                               l = l - 1;
                           }
                           postList.set(l + 1, key);
                       }
                       Log.i(TAG, "In Sort By" + postList.toString());
                       filteredOrgPostArr.add(postList.get(i));
                   }
                   else if (orgList.get(j).getOrgType().equals(field))
                   {
                       filteredOrgPostArr.add(postList.get(i));
                   }
               }
           }
       }

        ArrayAdapter<OrgPost> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, filteredOrgPostArr);

        ListView listView = (ListView) findViewById(R.id.postView);
        listView.setAdapter(listAdapter);
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, bVolDashboard.class);
        startActivity(intent);
    }
}