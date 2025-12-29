package com.example.recycleheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
public class Categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


    final ImageButton imageButton = findViewById(R.id.back_button);
    final Button button = findViewById(R.id.technicaldevices_button);
    final Button button1 = findViewById(R.id.clothes_button);
    final Button button2 = findViewById(R.id.food_button);
    final Button button3 = findViewById(R.id.books_button);
    final Button button4 = findViewById(R.id.usedglass_button);
    final Button button5 = findViewById(R.id.wastepaper_button);


    imageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Categories.this, MainActivity.class);
            startActivity(intent);
            }
        });

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Categories.this, TechnicalDevices.class);
            startActivity(intent);
            }
        });

    button1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Categories.this, Clothes.class);
            startActivity(intent);
            }
        });

    button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Categories.this, Food.class);
            startActivity(intent);
            }
        });

    button3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Categories.this, Books.class);
            startActivity(intent);
            }
        });

    button4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Categories.this, UsedGlass.class);
            startActivity(intent);
            }
        });

    button5.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Categories.this, Wastepaper.class);
            startActivity(intent);
            }
        });

    }
}