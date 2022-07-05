package com.example.readingdiaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button YourDiary, Email;
    Intent YourDiaryScreen, EmailScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YourDiary = (Button) findViewById(R.id.YourDiaryButton);
        Email = (Button) findViewById(R.id.EmailButton);
        // If the "Your Diary" button is pressed, it will load the diary screen.
        YourDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YourDiaryScreen = new Intent(getApplicationContext(), YourDiaryActivity.class);
                startActivity(YourDiaryScreen);
            }
        });

        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailScreen = new Intent(getApplicationContext(), EmailActivity.class);
                startActivity(EmailScreen);
            }
        });
    }
}