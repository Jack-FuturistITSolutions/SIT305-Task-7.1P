package com.example.task71p;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button buttonCreateAdvert, buttonViewAdverts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Bind the buttons to variables
        buttonCreateAdvert = findViewById(R.id.buttonCreateAdvert);
        buttonViewAdverts = findViewById(R.id.buttonViewAdverts);

        // Set listener to transition to CreateAdvertActivity
        buttonCreateAdvert.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CreateAdvertActivity.class));
        });

        // Set listener to transition to AdvertsListActivity
        buttonViewAdverts.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AdvertsListActivity.class));
        });

        // Handle system window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}