package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class VolProfile extends AppCompatActivity {

    Button changeVolNameBT;
    Button changeVolAgeBT;
    Button changeVolSchoolBT;
    Button confirmChangeVolNameBT;
    Button confirmChangeVolAgeBT;
    Button confirmChangeVolSchoolBT;

    EditText editVolNameET;
    EditText editVolAgeET;
    EditText editVolSchoolET;

    UserInfo userInfoOfCurrentUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_profile);

        changeVolNameBT = (Button) findViewById(R.id.editVolNameBT);
        changeVolAgeBT = (Button) findViewById(R.id.editVolAgeBT);
        changeVolSchoolBT = (Button) findViewById(R.id.editVolSchoolBT);

        confirmChangeVolNameBT = (Button) findViewById(R.id.confirmEditVolNameBT);
        confirmChangeVolAgeBT = (Button) findViewById(R.id.confirmEditVolAgeBT);
        confirmChangeVolSchoolBT = (Button) findViewById(R.id.confirmEditVolSchoolBT);

        editVolNameET = (EditText) findViewById(R.id.enterNewVolNameET);
        editVolAgeET = (EditText) findViewById(R.id.enterNewVolAgeET);
        editVolSchoolET = (EditText) findViewById(R.id.enterNewVolSchoolET);

        confirmChangeVolNameBT.setVisibility(View.GONE);
        confirmChangeVolAgeBT.setVisibility(View.GONE);
        confirmChangeVolSchoolBT.setVisibility(View.GONE);

        TextView volNameTV = findViewById(R.id.volNameTV);
        TextView volAgeTV = findViewById(R.id.volAgeTV);
        TextView volSchoolTV = findViewById(R.id.volSchoolTV);

        userInfoOfCurrentUser = aMainActivity.firebaseHelper.getUserInfo();

        volNameTV.setText(aMainActivity.firebaseHelper.getUserInfo().getName());
        volAgeTV.setText(aMainActivity.firebaseHelper.getUserInfo().getUserAge());
        volSchoolTV.setText(aMainActivity.firebaseHelper.getUserInfo().getSchool());
    }

    public void updateUIForNameChange(View v)
    {
        confirmChangeVolNameBT.setVisibility(View.VISIBLE);
        editVolName(newName);
    }
    public void editVolName(String newName)
    {
        aMainActivity.firebaseHelper.updateUserInfo(new UserInfo(newName, userInfoOfCurrentUser.getUserEmail()
                , userInfoOfCurrentUser.getUserPassword(), userInfoOfCurrentUser.getUserUID(), userInfoOfCurrentUser.getUserPassword(), userInfoOfCurrentUser.getSchool(),
                userInfoOfCurrentUser.getAccountType(), userInfoOfCurrentUser.getOrgType(), userInfoOfCurrentUser.getUserAge()));
    }

    public void editVolAge(int newAge)
    {
        aMainActivity.firebaseHelper.updateUserInfo(new UserInfo(userInfoOfCurrentUser.getName(), userInfoOfCurrentUser.getUserEmail()
                , userInfoOfCurrentUser.getUserPassword(), userInfoOfCurrentUser.getUserUID(), userInfoOfCurrentUser.getUserPassword(), userInfoOfCurrentUser.getSchool(),
                userInfoOfCurrentUser.getAccountType(), userInfoOfCurrentUser.getOrgType(), newAge));
    }
    public void editVolSchool(String newSchool)
    {
        aMainActivity.firebaseHelper.updateUserInfo(new UserInfo(userInfoOfCurrentUser.getName(), userInfoOfCurrentUser.getUserEmail()
                , userInfoOfCurrentUser.getUserPassword(), userInfoOfCurrentUser.getUserUID(), userInfoOfCurrentUser.getUserPassword(), newSchool,
                userInfoOfCurrentUser.getAccountType(), userInfoOfCurrentUser.getOrgType(), userInfoOfCurrentUser.getUserAge()));
    }

}