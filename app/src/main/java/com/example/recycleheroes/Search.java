package com.example.recycleheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Search extends AppCompatActivity implements NetworkService.OnTaskCompleted {

    ListView lv_searchResults;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String message = "ALL";
        NetworkService networkService = new NetworkService(this);
        networkService.execute(message);


        final ImageButton imageButton = findViewById(R.id.back_button);

        lv_searchResults = findViewById(R.id.lv_searchResult);
        EditText et_searchInput = findViewById(R.id.et_searchInput);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, MainActivity.class);


                // Start the activity
                startActivity(intent);
            }
        });





        LinearLayout linearLayout = findViewById(R.id.book_btn);

        // Set OnClickListener
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start CategoryResults activity
                Intent intent = new Intent(Search.this, CategoryResults.class);


                // Start the activity
                startActivity(intent);
            }
        });


        LinearLayout linearLayout2 = findViewById(R.id.clothes_btn);

        // Set OnClickListener
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start CategoryResults activity
                Intent intent = new Intent(Search.this, CategoryResults.class);

                // Add extra data if needed
                intent.putExtra(CategoryResults.CATEGORY_KEY, "CLOTHES");

                // Start the activity
                startActivity(intent);
            }
        });

        LinearLayout linearLayout3 = findViewById(R.id.devices_btn);

        // Set OnClickListener
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start CategoryResults activity
                Intent intent = new Intent(Search.this, CategoryResults.class);

                // Add extra data if needed
                intent.putExtra(CategoryResults.CATEGORY_KEY, "DEVICES");

                // Start the activity
                startActivity(intent);
            }
        });

        LinearLayout linearLayout4 = findViewById(R.id.miscs_btn);

        // Set OnClickListener
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start CategoryResults activity
                Intent intent = new Intent(Search.this, Map.class);

                // Add extra data if needed
                intent.putExtra(CategoryResults.CATEGORY_KEY, "SAMMELBOX");

                // Start the activity
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
