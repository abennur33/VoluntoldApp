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

public class SignUp extends AppCompatActivity {

    private static final String TAG = "Denna";
    private EditText nameET, emailET, passwordET, ageET;

    public static FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);
    }
    public void signUp(View v) {
        // Make references to EditText in xml
        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        ageET = findViewById(R.id.ageET);

        // Get user data
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String value = ageET.getText().toString();
        int age = Integer.parseInt(value);

        firebaseHelper = new FirebaseHelper();

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
                                firebaseHelper.addUserToFirestore(name, email, password, user.getUid(), age);

                                // lets further investigate why this method call is needed
                                // firebaseHelper.attachReadDataToUser();
                                // choose whatever actions you want - update UI, switch to a new screen, etc.
                                // take the user to the screen where they can enter wish list items
                                // getApplicationContext() will get the Activity we are currently in, that is sending
                                // the intent. Similar to how we have said "this" in the past
                                Intent intent = new Intent(getApplicationContext(), VolDashboard.class);
                                startActivity(intent);
                            }
                            else {
                                // user WASN'T created
                                Log.d(TAG, email + "sign up failed");
                            }
                        }
                    });
        }
    }

    public void goHome(View v) {
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
    }
}