package com.example.voluntold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class aSignUp extends AppCompatActivity {

    private static final String TAG = "Abhi";
    private EditText emailET, passwordET;


    public static final String EXTRA_EMAIL = "com.example.SignUpActivity.EMAIL";
    public static final String EXTRA_PASSWORD = "com.example.SignUpActivity.PASSWORD";


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

        if (email.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
        }

        // verify password is at least 6 char long (otherwise firebase will deny)
        else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be at least 6 char long", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Intent intent = new Intent (aSignUp.this, aVolunteerSignUp.class);

            intent.putExtra(EXTRA_EMAIL, email);
            intent.putExtra(EXTRA_PASSWORD, password);

            startActivity(intent);
        }


    }
    public void goToOrgSignUp(View v)
    {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if (email.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
        }

        // verify password is at least 6 char long (otherwise firebase will deny)
        else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be at least 6 char long", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Intent intent = new Intent (aSignUp.this, aOrganizationSignUp.class);

            intent.putExtra(EXTRA_EMAIL, email);
            intent.putExtra(EXTRA_PASSWORD, password);

            startActivity(intent);
        }
    }


    public void goHome(View v) {
        Intent intent = new Intent(aSignUp.this, aMainActivity.class);
        startActivity(intent);
    }
}