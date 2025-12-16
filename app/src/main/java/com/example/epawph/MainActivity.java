package com.example.epawph;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.util.Log;
import androidx.activity.ComponentActivity;
import android.widget.LinearLayout;

public class MainActivity extends ComponentActivity {
    private EditText emailField, passwordField;
    private Button loginButton;
    private Button signup_button;
    private LinearLayout accountItem1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        signup_button = findViewById(R.id.signup_button_fr_login);
        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);
        loginButton = findViewById(R.id.login_button);
        accountItem1 = findViewById(R.id.google_login_button);

        // Check if views are null
        if (emailField == null || passwordField == null || loginButton == null) {
            Log.e("MainActivity", "One or more views are null!");
            Toast.makeText(this, "Error: Views not found", Toast.LENGTH_LONG).show();
            return;
        }

        // Signup button click listener
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        // Google login button click listener
        accountItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginGoogleActivity.class);
                startActivity(intent);
            }
        });

        // Login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Login button clicked!");

                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                Log.d("MainActivity", "Email entered: [" + email + "]");
                Log.d("MainActivity", "Password entered: [" + password + "]");

                String staticEmail = "daveancheta@gmail.com";
                String staticPassword = "daveancheta123";

                if (email.isEmpty() || password.isEmpty()) {
                    Log.d("MainActivity", "Fields are empty!");
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.equals(staticEmail) && password.equals(staticPassword)) {
                    Log.d("MainActivity", "Login successful!");
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("MainActivity", "Invalid credentials!");
                    Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}