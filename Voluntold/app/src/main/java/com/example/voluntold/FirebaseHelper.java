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

        // attachReadDataToUser();
    }

    /*
    public void attachReadDataToUser() {
        if (mAuth.getCurrentUser() != null)
        {
            uid = mAuth.getUid();
            readData(new FirestoreCallback() {
                @Override
                public void onCallback(ArrayList<WishListItem> myList) {
                    Log.i(TAG, "Inside attatchReadDataToUser, onCallback");
                }
            });
        }
        else
        {
            Log.i(TAG, "No one is logged in");
        }
    }
    */

    public void addUserToFirestore(String name, String newUID) {
        // Create a new user with their name
        // Use a simple HashMap

        // how to add parameters instead?
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("uid", uid);


        db.collection(newUID).document(newUID)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i(TAG, name + " 's user account added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error adding user", e);
                    }
                });
    }

}
