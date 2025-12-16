package com.example.epawph;

import android.widget.LinearLayout;
import android.widget.ImageView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import android.widget.Button;

public class LoginGoogleActivity extends ComponentActivity {

    private ImageView btnClose;
    private LinearLayout accountsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);

        btnClose = findViewById(R.id.btnClose); // note the correct ID

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginGoogleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        accountsList = findViewById(R.id.item1); // note the correct ID

        accountsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginGoogleActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
