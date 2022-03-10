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
    private EditText emailET, passwordET;


    public static final String EXTRA_EMAIL = "com.example.SignUpActivity.EMAIL";
    public static final String EXTRA_PASSWORD = "com.example.SignUpActivity.PASSWORD";

    public static FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);

        emailET = findViewById(R.id.inputEmailTV);
        passwordET = findViewById(R.id.inputPassTV);

    }

    public void goToVolSignUp(View v)
    {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        Intent intent = new Intent (SignUp.this, VolunteerSignUp1.class);

        intent.putExtra(EXTRA_EMAIL, email);
        intent.putExtra(EXTRA_PASSWORD, password);

        startActivity(intent);
    }
    public void goToOrgSignUp()
    {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        Intent intent = new Intent (SignUp.this, OrganizationSignUp1.class);

        intent.putExtra(EXTRA_EMAIL, email);
        intent.putExtra(EXTRA_PASSWORD, password);

        startActivity(intent);
    }


    public void goHome(View v) {
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
    }
}