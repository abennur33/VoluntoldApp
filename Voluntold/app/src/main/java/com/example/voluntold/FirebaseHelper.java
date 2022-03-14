package com.example.voluntold;

import android.util.Log;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
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

    private String accountType;

    public FirebaseHelper()
    {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

//        uid = mAuth.getUid();

        attachReadDataToUser();
//        readData(new FirestoreCallback() {
//            @Override
//            public void onCallBack(UserInfo userInfo) {
//
//            }
//        });
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

                @Override
                public void onCallBack(Organization organization) {

                }
            });
        }
        else {
            Log.i(TAG, "No one is logged in");
        }
    }

    public void addUserToFirestore(String name, String email, String password, String uid, String accountType,
                                   String school, String organizationName, int age)
    {
        UserInfo userInfo = new UserInfo(name, email, password, uid, accountType, school, organizationName, age);

        db.collection(uid).document("UserInfo: " + uid)
                .set(userInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i(TAG, "account added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error adding account", e);
                    }
                });

        if (accountType.equals("Organization")) {
            Organization org = new Organization(organizationName, uid);

            addOrg(org);
        }

    }
    public void addOrg(Organization o) {

        // add WishListItem w to the database

        // this method is overloaded and incorporates the interface to handle the asynch calls

        addOrg(o, new FirestoreCallback() {

            @Override
            public void onCallBack(UserInfo userInfo) {

            }

            @Override
            public void onCallBack(Organization organization) {

            }

        });

    }

    private void addOrg(Organization o, FirestoreCallback firestoreCallback) {

        db.collection("Organizations")

                .add(o)

                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                    @Override

                    public void onSuccess(DocumentReference documentReference) {

                        // This will set the docID key for the WishListItem that was just added.

                        db.collection(uid)

                                .document(documentReference.getId()).update("docID", documentReference.getId());

                        Log.i(TAG, "just added " + o.getName());

                        readData(firestoreCallback);

                    }

                })

                .addOnFailureListener(new OnFailureListener() {

                    @Override

                    public void onFailure(@NonNull Exception e) {

                        Log.i(TAG, "Error adding document", e);

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
                                myInfo = doc.toObject(UserInfo.class);
                                accountType = myInfo.getAccountType();
                            }
                            Log.i(TAG, "success reading all data " + myInfo.toString());
                            firestoreCallback.onCallBack(myInfo);
                        }
                    }
                });
    }


    public UserInfo getUserInfo()
    {
        return myInfo;
    }

    public String getAccountType()
    {
        return accountType;
    }


    public interface FirestoreCallback {
        void onCallBack(UserInfo userInfo);
        void onCallBack(Organization organization);
    }




}
