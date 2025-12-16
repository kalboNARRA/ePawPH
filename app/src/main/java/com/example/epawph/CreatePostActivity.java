package com.example.epawph;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import java.util.Calendar;

public class CreatePostActivity extends ComponentActivity {

    private ImageView btnBack;
    private EditText etName, etBreed, etAge, etColor, etLastSeenDate, etLastSeenLocation, etMessage;
    private TextView tvGender, tvPetType, tvFileName;
    private Button btnCreatePost;
    private RelativeLayout btnChooseFile, genderSelector, petTypeSelector;

    private String selectedGender = "";
    private String selectedPetType = "";
    private String petStatus = "LOST"; // Default
    private Uri selectedImageUri = null;

    private ActivityResultLauncher<String> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        // Get pet status from intent
        Intent intent = getIntent();
        if (intent.hasExtra("PET_STATUS")) {
            petStatus = intent.getStringExtra("PET_STATUS");
        }

        // Initialize views
        initializeViews();

        // Setup image picker
        setupImagePicker();

        // Setup listeners
        setupListeners();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        etName = findViewById(R.id.etName);
        etBreed = findViewById(R.id.etBreed);
        etAge = findViewById(R.id.etAge);
        etColor = findViewById(R.id.etColor);
        etLastSeenDate = findViewById(R.id.etLastSeenDate);
        etLastSeenLocation = findViewById(R.id.etLastSeenLocation);
        etMessage = findViewById(R.id.etMessage);
        tvGender = findViewById(R.id.tvGender);
        tvPetType = findViewById(R.id.tvPetType);
        tvFileName = findViewById(R.id.tvFileName);
        btnCreatePost = findViewById(R.id.btnCreatePost);
        btnChooseFile = findViewById(R.id.btnChooseFile);
        genderSelector = findViewById(R.id.genderSelector);
        petTypeSelector = findViewById(R.id.petTypeSelector);
    }

    private void setupImagePicker() {
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        selectedImageUri = uri;
                        tvFileName.setText("Image selected");
                        tvFileName.setVisibility(View.VISIBLE);
                    }
                }
        );
    }

    private void setupListeners() {
        // Back button
        btnBack.setOnClickListener(v -> finish());

        ((View) tvGender.getParent()).setOnClickListener(v -> showGenderDialog());
        ((View) tvPetType.getParent()).setOnClickListener(v -> showPetTypeDialog());


        // Date picker
        etLastSeenDate.setOnClickListener(v -> showDatePicker());

        // File chooser
        btnChooseFile.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));

        // Create post button
        btnCreatePost.setOnClickListener(v -> createPost());
    }

    private void showGenderDialog() {
        String[] genders = {"Male", "Female"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Gender")
                .setItems(genders, (dialog, which) -> {
                    selectedGender = genders[which];
                    tvGender.setText(selectedGender);
                    tvGender.setTextColor(0xFF000000);
                })
                .show();
    }

    private void showPetTypeDialog() {
        String[] petTypes = {"Dog", "Cat", "Bird", "Rabbit", "Other"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Pet Type")
                .setItems(petTypes, (dialog, which) -> {
                    selectedPetType = petTypes[which];
                    tvPetType.setText(selectedPetType);
                    tvPetType.setTextColor(0xFF000000);
                })
                .show();
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    etLastSeenDate.setText(date);
                },
                year, month, day
        );

        datePickerDialog.show();
    }

    private void createPost() {
        // Get all values
        String name = etName.getText().toString().trim();
        String breed = etBreed.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        String color = etColor.getText().toString().trim();
        String lastSeenDate = etLastSeenDate.getText().toString().trim();
        String lastSeenLocation = etLastSeenLocation.getText().toString().trim();
        String message = etMessage.getText().toString().trim();

        // Validate required fields
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter pet name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedGender.isEmpty()) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedPetType.isEmpty()) {
            Toast.makeText(this, "Please select pet type", Toast.LENGTH_SHORT).show();
            return;
        }

        if (lastSeenDate.isEmpty()) {
            Toast.makeText(this, "Please select last seen date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (lastSeenLocation.isEmpty()) {
            Toast.makeText(this, "Please enter last seen location", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Save the post to database/server
        // For now, just show success message
        Toast.makeText(this, "Post created successfully! Status: " + petStatus, Toast.LENGTH_LONG).show();

        // Go back to home
        finish();
    }
}