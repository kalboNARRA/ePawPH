package com.example.epawph;

import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import android.widget.Button;

public class LostActivity extends ComponentActivity {

private Button btnCreatePost;

private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);


        btnCreatePost = findViewById(R.id.btnCreatePost); // note the correct ID

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LostActivity.this, "Thanks for sharing. We’ll keep an eye out and hope your dog comes home safely soon.", Toast.LENGTH_SHORT).show();
            }
        });
        btnBack = findViewById(R.id.btnBack); // note the correct ID

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LostActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
