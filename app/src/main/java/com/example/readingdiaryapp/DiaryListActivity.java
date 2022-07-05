package com.example.readingdiaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DiaryListActivity extends AppCompatActivity {

    Button EntryListButton;
    DatabaseAdapter dbHelper;
    TextView readingDiaryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        EntryListButton = (Button) findViewById(R.id.GetEntryListButton);
        dbHelper = new DatabaseAdapter(this);
        readingDiaryList = findViewById(R.id.ReadingDiaryList);

        EntryListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDiaryList(v);
            }
        });
    }

    public void viewDiaryList(View view){
        String entries = dbHelper.retrieveDatabaseData();
        readingDiaryList.setText(entries);
    }
}