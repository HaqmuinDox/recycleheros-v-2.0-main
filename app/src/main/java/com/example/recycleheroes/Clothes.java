package com.example.recycleheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Clothes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);

    final ImageButton imageButton = findViewById(R.id.back_button);
    final ImageButton imageButton1 = findViewById(R.id.imageButton4);
    final ImageButton imageButton2 = findViewById(R.id.imageButton5);
    final Button button = findViewById(R.id.button);

    imageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Create an Intent to start CategoryResults activity
            Intent intent = new Intent(Clothes.this, Categories.class);


            // Start the activity
            startActivity(intent);
            }
        });

    imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clothes.this, Map.class);

                // Add extra data if needed
                intent.putExtra(CategoryResults.CATEGORY_KEY, "CLOTHES");

                // Start the activity
                startActivity(intent);
            }
        });

    imageButton2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Clothes.this, Map.class);

            // Add extra data if needed
            intent.putExtra(CategoryResults.CATEGORY_KEY, "CLOTHES");

            // Start the activity
            startActivity(intent);
            }
        });
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Clothes.this, RecyclingPoint.class);

            // Add extra data if needed

            // Start the activity
            startActivity(intent);
        }
    });

    }
}