package com.example.voluntold;

import android.util.Log;

import androidx.annotation.NonNull;

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

    private ArrayList<Organization> allOrgs = new ArrayList<>();
    private ArrayList<OrgPost> allPosts = new ArrayList<>();

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

                }
            });
            getAllOrgs(new OrganizationCallback() {
                @Override
                public void onCallBackOrgs(ArrayList<Organization> allOrgs) {

                }
            });

            for(Organization o: allOrgs) {
                Log.i(TAG, o.getName());
                allPosts.addAll(getPostsbyOrg(o.getOrgID(), new PostCallback() {
                    @Override
                    public void onCallBackPosts(ArrayList<OrgPost> posts) {

                    }
                }));
            }
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

    public void addPost(OrgPost o) {
        addPost(o, new FirestoreCallback() {

            @Override
            public void onCallBack(UserInfo userInfo) {

            }
        });
    }

    private void addPost(OrgPost o, FirestoreCallback firestoreCallback) {
        db.collection(uid).document("UserInfo: " + uid).collection("myPosts")
                .add(o)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
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

    public void addOrg(Organization o) {

        // add WishListItem w to the database

        // this method is overloaded and incorporates the interface to handle the asynch calls

        addOrg(o, new FirestoreCallback() {

            @Override
            public void onCallBack(UserInfo userInfo) {

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
                            Log.i(TAG, "success reading all data ");
                            firestoreCallback.onCallBack(myInfo);
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
                                allOrgs.add(doc.toObject(Organization.class));
                            }
                            Log.i(TAG, "success grabbing orgs");
                            Log.i(TAG, allOrgs.toString());
                            organizationCallback.onCallBackOrgs(allOrgs);
                        }
                    }
                });
        Log.i(TAG, "returning form getallorgs" + allOrgs.toString());
    }

    public ArrayList<OrgPost> getPostsbyOrg(String orgID, PostCallback postCallback) {
        ArrayList<OrgPost> posts = new ArrayList<>();

        db.collection(orgID). document("UserInfo: " + orgID).collection("myPosts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for(DocumentSnapshot doc: task.getResult()) {
                                OrgPost post = doc.toObject(OrgPost.class);
                                //Date currentdate = new Date();
                                //Date postDate = new Date(post.getYear(), post.getMonth() - 1, post.getDate());
                                /*if(postDate.after(currentdate)) {
                                    posts.add(post);
                                }
                                 */
                                posts.add(post);
                            }
                            Log.i(TAG, "success getting post");
                            postCallback.onCallBackPosts(posts);
                        }
                        else Log.i(TAG, "failed to grab post");
                    }
                });

        return posts;
    }

    public UserInfo getUserInfo()
    {
        return myInfo;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public static String getUid() {
        return uid;
    }

    public ArrayList<OrgPost> getPosts() {
        return allPosts;
    }

    public interface FirestoreCallback {
        void onCallBack(UserInfo userInfo);
    }

    public interface OrganizationCallback {
        void onCallBackOrgs(ArrayList<Organization> allOrgs);
    }

    public interface PostCallback {
        void onCallBackPosts(ArrayList<OrgPost> posts);
    }
}
