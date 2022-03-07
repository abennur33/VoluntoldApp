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

public class MainActivity extends AppCompatActivity {

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
        signUpResultTextView = findViewById(R.id.signUpResultTV);

        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);
        signOutButton = findViewById(R.id.signOutButton);
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
        else if (age < 0) {
            Toast.makeText(getApplicationContext(), "Age must be a real number", Toast.LENGTH_SHORT).show();
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
                                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
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

    public void signUp(View v) {
        // Make references to EditText in xml
        nameET = findViewById(R.id.nameTV);
        emailET = findViewById(R.id.emailTV);
        passwordET = findViewById(R.id.passwordTV);

        // Get user data
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        Log.i(TAG, name + " " + email + " " + password);

        // verify all user data is entered
        if (name.length() == 0 || email.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
        }

        // verify password is at least 6 char long (otherwise firebase will deny)
        else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be at least 6 char long", Toast.LENGTH_SHORT).show();
        }
        else {
            // code to sign up user
            firebaseHelper.getmAuth().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // user account was created in firebase auth
                                Log.i(TAG, email + " account created");

                                FirebaseUser user = firebaseHelper.getmAuth().getCurrentUser();

                                // update the FirebaseHelper var uid to equal to uid of currently signed in user
                                firebaseHelper.updateUid(user.getUid());

                                // add a document to our database to represent this user
                                firebaseHelper.addUserToFirestore(name, user.getUid());

                                // lets further investigate why this method call is needed
                                firebaseHelper.attachReadDataToUser();
                                // choose whatever actions you want - update UI, switch to a new screen, etc.
                                // take the user to the screen where they can enter wish list items
                                // getApplicationContext() will get the Activity we are currently in, that is sending
                                // the intent. Similar to how we have said "this" in the past
                                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                                startActivity(intent);
                            }
                            else {
                                // user WASN'T created
                                Log.d(TAG, email + "sign up failed");
                            }
                        }
                    });
        }
        updateIfLoggedIn();
    }
}