package com.example.recycleheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TechnicalDevices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical_devices);

    final ImageButton imageButton = findViewById(R.id.back_button);
    final ImageButton imageButton1 = findViewById(R.id.imageButton4);
    final ImageButton imageButton2 = findViewById(R.id.imageButton6);
    final Button button = findViewById(R.id.recyclingpoint_button);

    imageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Create an Intent to start CategoryResults activity
            Intent intent = new Intent(TechnicalDevices.this, Map.class);

            // Add extra data if needed
            intent.putExtra(CategoryResults.CATEGORY_KEY, "DEVICES");

            // Start the activity
            startActivity(intent);
            }
        });
    imageButton1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TechnicalDevices.this, Map.class);

            // Add extra data if needed
            intent.putExtra(CategoryResults.CATEGORY_KEY, "DEVICES");

            // Start the activity
            startActivity(intent);
            }
        });

    imageButton2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TechnicalDevices.this, Map.class);

            // Add extra data if needed
            intent.putExtra(CategoryResults.CATEGORY_KEY, "DEVICES");

            // Start the activity
            startActivity(intent);
            }
        });


    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TechnicalDevices.this, RecyclingPoint.class);

            // Add extra data if needed
            intent.putExtra(CategoryResults.CATEGORY_KEY, "DEVICES");

            // Start the activity
            startActivity(intent);
        }
    });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TechnicalDevices.this, Categories.class);


                // Start the activity
                startActivity(intent);
            }
        });
}
}