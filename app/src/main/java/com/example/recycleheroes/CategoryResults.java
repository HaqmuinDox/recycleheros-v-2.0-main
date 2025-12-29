package com.example.recycleheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

public class CategoryResults extends AppCompatActivity implements NetworkService.OnTaskCompleted {

    public static final String CATEGORY_KEY = "category";
    private String category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_results);
        final ImageButton imageButton = findViewById(R.id.back_button);

        // Get the category from the intent
        Intent intent = getIntent();
        if (intent.hasExtra(CATEGORY_KEY)) {
            category = intent.getStringExtra(CATEGORY_KEY);
        } else {
            // Default category if not provided
            category = "ALL";
        }

        // Use the category in your network request or other logic
        NetworkService networkService = new NetworkService(this);
        networkService.execute(category);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryResults.this, Search.class);
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
