package com.example.voluntold;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class aLogIn extends AppCompatActivity {


    private static final String TAG = "Luis";
    private EditText nameET, emailET, passwordET, ageET;
    private Button volunteerSignInButton, organizationSignInButton;
    private int buttonClicked;
    private UserInfo currUserInfo;

    public static FirebaseHelper firebaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        // instantiate FirebaseHelper var
        firebaseHelper = new FirebaseHelper();

        // Make references to xml elements
        emailET = findViewById(R.id.emailTV);
        passwordET = findViewById(R.id.passwordTV);

        currUserInfo = firebaseHelper.getUserInfo();


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void takeToScreenUponLogIn() {

        if (firebaseHelper.getAccountType().equals("Organization"))
        {
            Intent intent = new Intent(aLogIn.this, cOrgCreateOpportunityPost.class);
            startActivity(intent);
        }

        else if (firebaseHelper.getAccountType().equals("Volunteer"))
        {
            Intent intent = new Intent(aLogIn.this, bVolDashboard.class);
            startActivity(intent);
        }

    }



    public void signIn(View v) {

        // Get user data
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();


        // verify all user data is entered
        if (email.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            // code to sign in user
            firebaseHelper.getmAuth().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // updating MY var for uid of current user
                                firebaseHelper.updateUid(firebaseHelper.getmAuth().getCurrentUser().getUid());
                                // we can do any other UI updating or change screens based on how our app
                                // should respond
                                Log.i(TAG, email + " is signed in");

                                // this will help us with the asych method calls
                                firebaseHelper.attachReadDataToUser();
                                // we can do any other UI updating or change screens based on how our app
                                // should respond
                                takeToScreenUponLogIn();
                                // this is another way to create the intent from inside the OnCompleteListener
                            }
                            else {
                                //sign in failed
                                Log.d(TAG, email + " failed to log in");
                            }
                        }
                    });
        }

    }
    public void goHome(View v) {
        Intent intent = new Intent(aLogIn.this, aMainActivity.class);
        startActivity(intent);
    }
}