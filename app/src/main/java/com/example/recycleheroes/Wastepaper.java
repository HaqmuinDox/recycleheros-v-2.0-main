package com.example.recycleheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Wastepaper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wastepaper);

    final ImageButton imageButton = findViewById(R.id.back_button);
    final ImageButton imageButton1 = findViewById(R.id.imageButton6);
    final ImageButton imageButton2 = findViewById(R.id.imageButton4);

    imageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Wastepaper.this, Categories.class);


            // Start the activity
            startActivity(intent);
            }
        });

    imageButton1.setOnClickListener(new View.OnClickListener() {
        @Override
          public void onClick(View v) {
            Intent intent = new Intent(Wastepaper.this, Map.class);

            // Add extra data if needed
            intent.putExtra(CategoryResults.CATEGORY_KEY, "PAPER");

            // Start the activity
            startActivity(intent);
            }
        });

    imageButton2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Wastepaper.this, Map.class);

            // Add extra data if needed
            intent.putExtra(CategoryResults.CATEGORY_KEY, "PAPER");

            // Start the activity
            startActivity(intent);
            }
        });
    }
}