package com.example.voluntold;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.voluntold.VolunteerSide.VolDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class OrganizationSignUp extends AppCompatActivity {

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

        email =  intent.getStringExtra(SignUp.EXTRA_EMAIL) ;
        password = intent.getStringExtra(SignUp.EXTRA_PASSWORD);

        nameET = findViewById(R.id.inputOrgNameET);
        orgNameET = findViewById(R.id.inputNameOfOrgET);
        newEmailET = findViewById(R.id.newOrgEmailET);
        newPasswordET = findViewById(R.id.newOrgPasswordET);
    }

    public void signUp(View v) {
        // Make references to EditText in xml
        String name = nameET.getText().toString();
        String orgName = orgNameET.getText().toString();


        firebaseHelper.getmAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            // user account was created in firebase auth
                            Log.i(TAG, email +  " account created");

                            FirebaseUser user = firebaseHelper.getmAuth().getCurrentUser();

                            // update the FirebaseHelper var uid to equal the uid of the currently signed in user
                            firebaseHelper.updateUid(user.getUid());

                            // add a collection to our database to represent this user
                            firebaseHelper.addUserToFirestore(name, email, password, user.getUid(), orgName);

                            // lets further investigate why this method call is needed
                            firebaseHelper.attachReadDataToUser();

                            // choose whatever actions you want - update UI, switch to a new screen, etc.
                            // take the user to the screen where they can enter their wishlist items
                            // get application context will get the activity we are currently in that
                            // is sending the intent. Similar to how we have said "this" in the past

                            // Confirm email and password values
                            String newEmail = newEmailET.getText().toString();
                            String newPassword = newPasswordET.getText().toString();

                            if (!email.equals(newEmail))
                            {
                                Toast.makeText(getApplicationContext(), "Email does not match", Toast.LENGTH_SHORT).show();
                            }

                            if(!password.equals(newPassword))
                            {
                                Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                            }


                            if(!email.equals(newEmail) && !password.equals(newPassword))
                            {
                                Toast.makeText(getApplicationContext(), "Email and password does not match", Toast.LENGTH_SHORT).show();
                            }

                            if (email.equals(newEmail) && password.equals(newPassword))
                            {
                                Intent intent = new Intent(getApplicationContext(), OrgDashboard.class);
                                startActivity(intent);
                            }

                        }
                        else
                        {
                            // user WASN'T created
                            Log.d(TAG, email + " sign up failed");
                        }
                    }


                });


    }
}