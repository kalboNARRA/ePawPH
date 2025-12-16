package com.example.epawph;


import androidx.cardview.widget.CardView;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.ComponentActivity;

public class HomeActivity extends ComponentActivity {

    private ImageView fabAddPost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        // Initialize the plus button
        fabAddPost = findViewById(R.id.fabAddPost);

        // Show modal when plus button is clicked
        fabAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPetStatusModal();
            }
        });
    }

    private void showPetStatusModal() {
        // Create dialog
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Inflate the modal layout
        View view = LayoutInflater.from(this).inflate(R.layout.modal_pet_status, null);
        dialog.setContentView(view);

        // Make dialog background transparent and set proper size
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
            );
            // Add margin
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.horizontalMargin = 0.05f;
            window.setAttributes(layoutParams);
        }

        // Close button
        view.findViewById(R.id.btnCloseModal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // LOST button
        view.findViewById(R.id.btnLost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openCreatePostActivity("LOST");
                Intent intent = new Intent(HomeActivity.this, LostActivity.class);
                startActivity(intent);
            }

        });

        // ADOPTION button
        view.findViewById(R.id.btnAdoption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openCreatePostActivity("ADOPTION");
            }
        });

        // FOUND button
        view.findViewById(R.id.btnFound).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openCreatePostActivity("FOUND");
            }
        });

        // Show the dialog
        dialog.show();
    }

    private void openCreatePostActivity(String status) {
        // TODO: Create CreatePostActivity later
        // For now, just show a toast
        Toast.makeText(this, "Selected: " + status, Toast.LENGTH_SHORT).show();

        // Uncomment this when you create the CreatePostActivity
        // Intent intent = new Intent(this, CreatePostActivity.class);
        // intent.putExtra("PET_STATUS", status);
        // startActivity(intent);
    }
}