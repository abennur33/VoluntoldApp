package com.example.voluntold;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class aOrganizationSignUp extends AppCompatActivity {

    private EditText nameET, orgNameET, newEmailET, newPasswordET;
    String email, password;

    private static final String TAG = "Abhi";

    public static FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organization_signup);

        firebaseHelper = new FirebaseHelper();

        Intent intent = getIntent();

        email =  intent.getStringExtra(aSignUp.EXTRA_EMAIL) ;
        password = intent.getStringExtra(aSignUp.EXTRA_PASSWORD);

        nameET = findViewById(R.id.inputOrgNameET);
        orgNameET = findViewById(R.id.inputNameOfOrgET);
        newEmailET = findViewById(R.id.newOrgEmailET);
        newPasswordET = findViewById(R.id.newOrgPasswordET);
    }

    public void signUp(View v) {
        // Make references to EditText in xml
        String name = nameET.getText().toString();
        String orgName = orgNameET.getText().toString();
        String newEmail = newEmailET.getText().toString();
        String newPassword = newPasswordET.getText().toString();

        if (!email.equals(newEmail) || !password.equals(newPassword)) {
            Toast.makeText(getApplicationContext(), "Email or password does not match", Toast.LENGTH_SHORT).show();
            newEmail = "";
            newPassword = "";
            newEmailET.setText("");
            newPasswordET.setText("");
        }
        else {
            firebaseHelper.getmAuth().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // user account was created in firebase auth
                                Log.i(TAG, email + " account created");

                                FirebaseUser user = firebaseHelper.getmAuth().getCurrentUser();

                                // update the FirebaseHelper var uid to equal the uid of the currently signed in user
                                firebaseHelper.updateUid(user.getUid());

                                // add a collection to our database to represent this user
                                firebaseHelper.addUserToFirestore(name, email, password, user.getUid(), "Organization", null, orgName, 0);


                                // lets further investigate why this method call is needed
                                firebaseHelper.attachReadDataToUser();

                                // choose whatever actions you want - update UI, switch to a new screen, etc.
                                // take the user to the screen where they can enter their wishlist items
                                // get application context will get the activity we are currently in that
                                // is sending the intent. Similar to how we have said "this" in the past

                                Intent intent = new Intent(getApplicationContext(), cOrgCreateOpportunityPost.class);
                                startActivity(intent);
                            }
                            else {
                                // user WASN'T created
                                Log.d(TAG, email + " sign up failed");
                            }
                        }
                    });
        }
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, aSignUp.class);
        startActivity(intent);
    }
}