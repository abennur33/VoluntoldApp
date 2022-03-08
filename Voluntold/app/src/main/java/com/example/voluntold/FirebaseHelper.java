package com.example.voluntold;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {

    public final String TAG = "Abhi";
    private static String uid = null;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public FirebaseHelper()
    {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public FirebaseAuth getmAuth()
    {
        return mAuth;
    }


    public void addVolunteerToFirestore(String name, String email, String password, String uid, int age)
    {
        UserInfo userInfoVol = new UserInfo(name, email, password, uid, age);

        db.collection(uid).document("UserInfo")
                .set(userInfoVol)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i(TAG, "Volunteer account added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error adding volunteer", e);
                    }
                });
    }

    public void addOrganizationToFirestore(String name, String email, String password, String uid)
    {
        UserInfo userInfoOrg = new UserInfo(name, email, password, uid);

        db.collection(uid).document("UserInfo")
                .set(userInfoOrg)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i(TAG, "Organization account added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error adding organization", e);
                    }
                });
    }

}
