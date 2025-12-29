package com.example.recycleheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class Profilpage extends AppCompatActivity implements NetworkService.OnTaskCompleted {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilpage);

        NetworkService networkService = new NetworkService(this);
        networkService.execute("USERuser5");


    ImageButton imageButton = findViewById(R.id.imageButton9);
    progressBar = findViewById(R.id.progressBar);

    /*
    String name = datenbank.getString(NameausderDatenbank);
    String email = datenbank.getString(emailausderDatenbank);
    int telefon = datenbank.getInteger(TelefonnummerausderDatenbank);
    progressStatus = datenbank.getInteger(StatusausderDatenbank);

    TextView nameTextView = findViewById(R.id.nameTextView);
    TextView nameTextView1 = findViewById(R.id.nameTextView1);
    TextView emailTextView = findViewById(R.id.emailTextView);
    TextView telefonTextView = findViewById(R.id.telefonTextView);

    nameTextView.setText(name);
    emailTextView.setText(email);
    nameTextView1.setText(name);
    telefonTextView.setText(telefon);
    */



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Profilpage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        new Thread(() -> {

                handler.post(() -> progressBar.setProgress(progressStatus));

        }).start();
    }

    @Override
    public void onTaskCompleted(String result) {


        UserModel user = UserService.parseJsonResponse(result);

        TextView nameTextView1 = findViewById(R.id.nameTextView1);
        nameTextView1.setText(user.username);

        TextView nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText(user.username);

        TextView emailTextView = findViewById(R.id.emailTextView);
        emailTextView.setText((user.email));
/*
        TextView telefonTextView = findViewById(R.id.telefonTextView);
        telefonTextView.setText(user.telefonnr);

*/
        progressBar.setProgress(user.score);





    }
}