package com.example.voluntold;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate FirebaseHelper var
        firebaseHelper = new FirebaseHelper();

        // Make references to xml elements
        nameET = findViewById(R.id.nameTV);
        emailET = findViewById(R.id.emailTV);
        passwordET = findViewById(R.id.passwordTV);
        ageET = findViewById(R.id.ageTV);

        signInButton = findViewById(R.id.signInButton);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        updateIfLoggedIn();
    }

    public void updateIfLoggedIn() {
        // Create reference to current user using firebaseHelper variable
        FirebaseUser user = firebaseHelper.getmAuth().getCurrentUser();

        Intent intent = new Intent(LogIn.this, Dashboard.class);
        startActivity(intent);

    }

    public void signIn(View v) {
        // Note we don't care what they entered for name here
        // it could be blank

        // Get user data
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String value = ageET.getText().toString();
        int age = Integer.parseInt(value);


        // verify all user data is entered
        if (email.length() == 0 || password.length() == 0 || value.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
        }

        // verify password is at least 6 char long (otherwise firebase will deny)
        else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be at least 6 char long", Toast.LENGTH_SHORT).show();
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
                                Intent intent = new Intent(LogIn.this, Dashboard.class);
                                startActivity(intent);
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