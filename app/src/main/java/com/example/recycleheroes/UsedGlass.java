package com.example.recycleheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UsedGlass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_glass);

    final ImageButton imageButton = findViewById(R.id.back_button);
    final ImageButton imageButton1 = findViewById(R.id.imageButton4);
    final ImageButton imageButton2 = findViewById(R.id.imageButton6);

    imageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UsedGlass.this, Categories.class);
            startActivity(intent);
            }
        });

    imageButton1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UsedGlass.this, Map.class);
            startActivity(intent);
            }
        });

    imageButton2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UsedGlass.this, Map.class);
            startActivity(intent);
            }
        });
    }
}