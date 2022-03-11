package com.example.voluntold;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {

    public final String TAG = "Abhi";
    private static String uid = null;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private UserInfo myInfo;

    public FirebaseHelper()
    {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public FirebaseAuth getmAuth()
    {
        return mAuth;
    }


    public void attachReadDataToUser() {
        if (mAuth.getCurrentUser() != null) {
            uid = mAuth.getUid();
            readData(new FirestoreCallback() {
                @Override
                public void onCallBack(UserInfo userInfo) {
                    Log.i(TAG, "Inside attachReadDataToUser, onCallBack");
                }
            });
        }
        else {
            Log.i(TAG, "No one is logged in");
        }
    }

    public void addUserToFirestore(String name, String email, String password, String uid, String school, int age)
    {
        UserInfo userInfoVol = new UserInfo(name, email, password, uid, school, age);

        db.collection(uid).document("UserInfo/" + uid)
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

    public void addUserToFirestore(String name, String email, String password, String uid, String organizationName)
    {
        UserInfo userInfoOrg = new UserInfo(name, email, password, uid, organizationName);

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

    public void updateUid(String uid) {
        this.uid = uid;
    }

    private void readData(FirestoreCallback firestoreCallback) {
        myInfo = null;

        db.collection(uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for(DocumentSnapshot doc: task.getResult()) {
                                UserInfo u = doc.toObject(UserInfo.class);
                                myInfo = u;
                            }
                            Log.i(TAG, "success reading all data");
                            firestoreCallback.onCallBack(myInfo);
                        }
                    }
                });
    }

    public interface FirestoreCallback {
        void onCallBack(UserInfo userInfo);
    }

}
