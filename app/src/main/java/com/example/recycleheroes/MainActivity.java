package com.example.recycleheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkService.OnTaskCompleted {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    final Button button = findViewById((R.id.button_getRidOf));
    final Button button1 = findViewById((R.id.button_discover));
    final ImageButton button2 = findViewById((R.id.imageButton10));

        String message = "ALL";
        NetworkService networkService = new NetworkService(this);
        networkService.execute(message);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Categories.class);
            startActivity(intent);

            }
        });

    button1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Search.class);
            startActivity(intent);

            }
        });

    button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Profilpage.class);
            startActivity(intent);

            }
        });


    }
    @Override
    public void onTaskCompleted(String result) {

        DataService dataService = new DataService();
        List<RecyclingPointModel> recyclingPoints = dataService.parseJsonResponse(result);

        RecyclingPointListAdapter adapter = new RecyclingPointListAdapter(this, recyclingPoints);


        // Get the ListView from the layout
        ListView listView = findViewById(R.id.lv_searchResult);

        // Set the adapter for the ListView
        listView.setAdapter(adapter);

    }
}