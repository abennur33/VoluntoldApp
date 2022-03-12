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

import com.example.voluntold.VolunteerSide.VolDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    public static FirebaseHelper firebaseHelper;
    private static final String TAG = "Luis";
    private EditText nameET, emailET, passwordET, ageET;
    private Button volunteerSignInButton, organizationSignInButton;
    private int buttonClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        // instantiate FirebaseHelper var
        firebaseHelper = new FirebaseHelper();

        // Make references to xml elements
        emailET = findViewById(R.id.emailTV);
        passwordET = findViewById(R.id.passwordTV);

    }

    @Override
    public void onStart() {
        super.onStart();
        // doesn't work with this for some reason: updateIfLoggedIn();

    }

    public void updateIfLoggedIn() {
        // Create reference to current user using firebaseHelper variable
        FirebaseUser user = firebaseHelper.getmAuth().getCurrentUser();

        if (user != null)
        {
            if (firebaseHelper.getAccountType().equals("Organization"))
            {
                Intent intent = new Intent(LogIn.this, OrgDashboard.class);
                startActivity(intent);
            }

            else if (firebaseHelper.getAccountType().equals("Volunteer"))
            {
                Intent intent = new Intent(LogIn.this, VolDashboard.class);
                startActivity(intent);
            }

        }

    }

    public void signIn(View v) {
        // Note we don't care what they entered for name here
        // it could be blank

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
                                updateIfLoggedIn();
                                // this is another way to create the intent from inside the OnCompleteListener
                            }
                            else {
                                //sign in failed
                                Log.d(TAG, email + " failed to log in");
                            }
                        }
                    });
        }

        /*
         * Enter Firebase Code here CODE here
         */

    }
    public void goHome(View v) {
        Intent intent = new Intent(LogIn.this, MainActivity.class);
        startActivity(intent);
    }
}