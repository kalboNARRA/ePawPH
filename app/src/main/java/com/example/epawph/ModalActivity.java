package com.example.epawph;

import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import android.widget.Button;

public class ModalActivity extends ComponentActivity {
    private CardView btnLost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modal_pet_status);

        btnLost = findViewById(R.id.btnLost); // note the correct ID

        btnLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModalActivity.this, LostActivity.class);
                startActivity(intent);
            }
        });
    }
}
