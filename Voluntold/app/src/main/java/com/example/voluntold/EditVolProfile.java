package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditVolProfile extends AppCompatActivity {

    EditText newVolNameET;
    EditText newVolAgeET;
    EditText newVolSchoolET;

    Button changeVolNameBT;
    Button changeVolAgeBT;
    Button changeVolSchoolBT;

    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vol_profile);

        newVolNameET = findViewById(R.id.newVolNameET);
        newVolAgeET = findViewById(R.id.newVolAgeET);
        newVolSchoolET = findViewById(R.id.newVolSchoolET);

        changeVolNameBT = findViewById(R.id.newVolNameBT);
        changeVolAgeBT = findViewById(R.id.newVolAgeBT);
        changeVolSchoolBT = findViewById(R.id.newVolSchoolBT);

        userInfo = aMainActivity.firebaseHelper.getUserInfo();
    }

    public void changeVolName(View v)
    {
        String newVolName = newVolNameET.getText().toString();

        // Changes the name but the school to "no school"
        aMainActivity.firebaseHelper.updateUserInfo(new UserInfo (newVolName, userInfo.getUserEmail(),
                userInfo.getUserPassword(), userInfo.getUserUID(), userInfo.getAccountType(), userInfo.getSchool(), null
                , null, userInfo.getUserAge()));



    }
}