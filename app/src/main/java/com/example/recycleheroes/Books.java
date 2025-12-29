package com.example.recycleheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Books extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

    final ImageButton imageButton = findViewById(R.id.back_button);
    final ImageButton imageButton1 = findViewById(R.id.imageButton4);
    final ImageButton imageButton2 = findViewById(R.id.imageButton6);
    final Button button10 = findViewById(R.id.recyclingpoint_button);
    imageButton.setOnClickListener(new View.OnClickListener() {
        @Override
         public void onClick(View v) {
            Intent intent = new Intent(Books.this, Categories.class);


            // Start the activity
            startActivity(intent);
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Books.this, Map.class);

                // Add extra data if needed
                intent.putExtra(CategoryResults.CATEGORY_KEY, "BOOKS");

                // Start the activity
                startActivity(intent);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Books.this, Map.class);

                // Add extra data if needed
                intent.putExtra(CategoryResults.CATEGORY_KEY, "BOOKS");

                // Start the activity
                startActivity(intent);
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Books.this, RecyclingPoint.class);

                // Add extra data if needed

                // Start the activity
                startActivity(intent);
            }
        });
    }
}