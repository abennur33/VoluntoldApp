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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FirebaseHelper {

    public final String TAG = "Abhi";
    private static String uid = null;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private ArrayList<UserInfo> allOrgs = new ArrayList<>();
    private ArrayList<OrgPost> allPosts = new ArrayList<>();

    public static UserInfo myInfo;

    public FirebaseHelper()
    {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        myInfo = new UserInfo();

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
                    Log.i(TAG, "Inside attachReadDataToUser, onCallback " + userInfo.getAccountType());
                }
            });

            getAllOrgs(new OrganizationCallback() {
                @Override
                public void onCallBackOrgs(ArrayList<UserInfo> allOrgs) {

                }
            });

            
            Log.i(TAG, mAuth.getCurrentUser().getEmail() + " test");

            if (myInfo != null) {
                Log.i(TAG, "after" + myInfo.getAccountType());
            }
            else {
                Log.i(TAG, "nobody logged in");
            }

            getAllPosts(new PostCallback() {
                @Override
                public void onCallBackPost(OrgPost post) {

                }

                @Override
                public void onCallBackPosts(ArrayList<OrgPost> posts) {

                }
            });
        }
        else{
                Log.i(TAG, "No one is logged in");
            }
    }

    public void addUserToFirestore(String accountType, ArrayList<OrgPost> allPosts, String name, String orgType, String organizationName, String school, int age, String email,
                                   String password, String uid, ArrayList<VolOpportunity> allOpportunities)
    {
        UserInfo userInfo = new UserInfo(accountType, allPosts, name, orgType, organizationName, school, age, email, password, uid, allOpportunities);

        db.collection(uid).document("UserInfo: " + uid)
                .set(userInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        updateUid(uid);
                        attachReadDataToUser();
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

            addOrg(userInfo);
        }
    }

    public void addPost(OrgPost o) {
        addPost(o, new FirestoreCallback() {

            @Override
            public void onCallBack(UserInfo userInfo) {

            }
        });
    }

    private void addPost(OrgPost o, FirestoreCallback firestoreCallback) {
        db.collection("AllPosts")
                .add(o)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        db.collection("AllPosts")
                                .document(documentReference.getId())
                                .update("docID", documentReference.getId());
                        Log.i(TAG, "just added " + o.getTitle());
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

    public void addOrg(UserInfo u) {

        // add WishListItem w to the database

        // this method is overloaded and incorporates the interface to handle the asynch calls

        addOrg(u, new FirestoreCallback() {

            @Override
            public void onCallBack(UserInfo userInfo) {

            }
        });

    }

    private void addOrg(UserInfo u, FirestoreCallback firestoreCallback) {

        db.collection("Organizations").document("orgList-" + u.getUserUID())

                .set(u)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i(TAG, u.getOrganizationName() + " added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error adding", e);
                    }
                });
    }

    public void updateUid(String uid) {
        this.uid = uid;
    }

    private void readData(FirestoreCallback firestoreCallback) {
//        myInfo = null;

        db.collection(uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for(DocumentSnapshot doc: task.getResult()) {
                                myInfo = doc.toObject(UserInfo.class);
                               String email = myInfo.getUserEmail();
                               Log.i(TAG, "in readData " + email);

//                                Log.i(TAG, "user school: " + doc.get("school"));
                                // this above line is correctly getting data from firestore, not sure why setSchool is not working
                                myInfo.setSchool(getUserInfo().getSchool());
                                Log.i(TAG, "user school from myInfo: " + myInfo.getSchool());
                            }
                            Log.i(TAG, "success reading user data");
                            firestoreCallback.onCallBack(myInfo);
                        }
                        else {
                            Log.d(TAG, "Error getting documents", task.getException());
                        }
                    }
                });
    }


    private void getAllOrgs(OrganizationCallback organizationCallback) {
        allOrgs.clear();

        db.collection("Organizations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for(DocumentSnapshot doc: task.getResult()) {
                                Log.i(TAG, doc.getData().toString());
                                allOrgs.add(doc.toObject(UserInfo.class));
                            }
                            Log.i(TAG, "success grabbing orgs");
                            Log.i(TAG, allOrgs.toString());
                            organizationCallback.onCallBackOrgs(allOrgs);
                        }
                    }
                });
        Log.i(TAG, "returning form getallorgs" + allOrgs.toString());
    }

    private void getAllPosts(PostCallback postCallback) {
        allPosts.clear();

        db.collection("AllPosts").orderBy("comparisonDate")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for(DocumentSnapshot doc: task.getResult()) {
                                Log.i(TAG, doc.getData().toString());
                                OrgPost post = doc.toObject(OrgPost.class);
                                db.collection("AllPosts").document(doc.getId()).collection("Volunteers").orderBy("Name")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                ArrayList<UserInfo> users = new ArrayList<>();
                                                for(DocumentSnapshot udoc: task.getResult()) {
                                                    users.add(udoc.toObject(UserInfo.class));
                                                }
                                                post.setVolunteers(users);
                                            }
                                        });
                                allPosts.add(post);
                            }
                            Log.i(TAG, "success grabbing orgs");
                            Log.i(TAG, allPosts.toString());
                            postCallback.onCallBackPosts(allPosts);
                        }
                    }
                });
        Log.i(TAG, "returning form getallorgs" + allPosts.toString());
    }


    public UserInfo getUserInfo()
    {
        Log.i(TAG, myInfo.getUserEmail());
        return myInfo;
    }

    public void addVoltoPost(OrgPost o, UserInfo u) {
        addVoltoPost(o, u, new PostCallback() {
            @Override
            public void onCallBackPost(OrgPost post) {

            }

            @Override
            public void onCallBackPosts(ArrayList<OrgPost> posts) {

            }
        });
    }

    private void addVoltoPost(OrgPost o, UserInfo u, PostCallback postCallback) {
        String docId = o.getDocID();
        db.collection("AllPosts")
                .document(docId)
                .collection("Volunteers")
                .add(u)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i(TAG, "just added " + o.getTitle());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error adding document", e);
                    }
                });
    }

    public void addPosttoVol(VolOpportunity v) {
        addPosttoVol(v, new FirestoreCallback() {
            @Override
            public void onCallBack(UserInfo userInfo) {

            }
        });
    }

    private void addPosttoVol(VolOpportunity v, FirestoreCallback firestoreCallback) {
        String docId = "UserInfo: " + uid;
        db.collection(uid)
                .document(docId)
                .collection("savedPosts")
                .document(v.getDocID())
                .set(v)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i(TAG, v.getTitle() + " added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error adding", e);
                    }
                });
    }

//    public String getAccountType()
//    {
//        return accountType;
//    }

    public void updateUserInfo(UserInfo u) {
        // edit WishListItem w to the database
        // this method is overloaded and incorporates the interface to handle the asynch calls
        updateUserInfo(u, new FirestoreCallback() {
            @Override
            public void onCallBack(UserInfo userInfo) {

            }
        });
    }

    private void updateUserInfo(UserInfo u, FirestoreCallback firestoreCallback)
    {
        db.collection(uid).document("UserInfo: " + uid)
                .set(u)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i(TAG, "Success updating document");
                        readData(firestoreCallback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error updating document", e);
                    }
                });
    }

    private void editPost(OrgPost o, FirestoreCallback firestoreCallback) {
        String docId = o.getDocID();
        db.collection("AllPosts")
                .document(docId)
                .set(o)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i(TAG, "Success updating document");
                        readData(firestoreCallback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error updating document", e);
                    }
                });
    }

    public void editPost(OrgPost o) {
        // edit WishListItem w to the database
        // this method is overloaded and incorporates the interface to handle the asynch calls
        editPost(o, new FirestoreCallback() {
            @Override
            public void onCallBack(UserInfo userInfo) {
            }
        });
    }

    public void verifyUserforVolOpp(UserInfo u, OrgPost o, VolOpportunity v) {
        verifyUserforVolOpp(u, o, v, new FirestoreCallback() {
            @Override
            public void onCallBack(UserInfo userInfo) {

            }
        });
    }

    private void verifyUserforVolOpp(UserInfo u, OrgPost o, VolOpportunity v, FirestoreCallback firestoreCallback)
    {
        db.collection(u.getUserUID()).document("UserInfo: " + u.getUserUID()).collection("savedPosts")
                .document(u.getUserUID() + "-volOPP-" + o.getDocID())
                .set(v)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i(TAG, "Success updating document");
                        readData(firestoreCallback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error updating document", e);
                    }
                });
    }


    public static String getUid() {
        return uid;
    }

    public ArrayList<OrgPost> getPosts() {
        return allPosts;
    }

    public ArrayList<UserInfo> getOrgs() {
        return allOrgs;
    }

    public interface FirestoreCallback {
        void onCallBack(UserInfo userInfo);
    }

    public interface OrganizationCallback {
        void onCallBackOrgs(ArrayList<UserInfo> allOrgs);
    }

    public interface PostCallback {
        void onCallBackPost(OrgPost post);
        void onCallBackPosts(ArrayList<OrgPost> posts);
    }

//    public void clearAccType()
//    {
//        accountType = "";
//    }
}
