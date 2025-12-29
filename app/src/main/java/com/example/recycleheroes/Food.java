package com.example.recycleheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Food extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

    final ImageButton imageButton = findViewById(R.id.back_button);
    final ImageButton imageButton1 = findViewById(R.id.imageButton8);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Food.this, Categories.class);


                // Start the activity
                startActivity(intent);
            }
        });

    imageButton1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Food.this, Map.class);

            // Add extra data if needed
            intent.putExtra(CategoryResults.CATEGORY_KEY, "FOOD");

            // Start the activity
            startActivity(intent);
            }
        });
    }
}