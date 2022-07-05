package com.example.readingdiaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class YourDiaryActivity extends AppCompatActivity {
    Button SubmitEntryButton, ViewDiaryButton, RemoveEntryButton, EditEntryButton;
    DatabaseAdapter dbHelper;
    EditText date, bookName, pagesRead, bookRating, comment;
    Intent DiaryListScreen, RemoveEntryScreen, EditEntryScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_diary);
        date = findViewById(R.id.DateInputField);
        bookName = findViewById(R.id.BookNameInputField);
        pagesRead = findViewById(R.id.PagesReadInputField);
        bookRating = findViewById(R.id.BookRatingInputField);
        comment = findViewById(R.id.CommentInputField);
        dbHelper = new DatabaseAdapter(this);
        ViewDiaryButton = (Button) findViewById(R.id.ViewDiaryButton);
        RemoveEntryButton = (Button) findViewById(R.id.RemoveDiaryEntryButton);
        EditEntryButton = (Button) findViewById(R.id.EditEntryButton);
        SubmitEntryButton = (Button) findViewById(R.id.SubmitEntryButton);
        SubmitEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewEntry(v);
            }
        });
        ViewDiaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryListScreen = new Intent(getApplicationContext(), DiaryListActivity.class);
                startActivity(DiaryListScreen);
            }
        });
        RemoveEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveEntryScreen = new Intent(getApplicationContext(), RemoveEntryActivity.class);
                startActivity(RemoveEntryScreen);
            }
        });
        EditEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditEntryScreen = new Intent(getApplicationContext(), EditEntryActivity.class);
                startActivity(EditEntryScreen);
            }
        });
    }

    public void addNewEntry(View view){
        String p1 = date.getText().toString();
        String p2 = bookName.getText().toString();
        String p3 = pagesRead.getText().toString();
        String p4 = bookRating.getText().toString();
        String p5 = comment.getText().toString();

        if(p1.isEmpty() || p2.isEmpty() || p3.isEmpty() || p4.isEmpty() || p5.isEmpty()){
            EntriesListMessage.message(getApplicationContext(), "You are missing part of the entry details!");
        }
        else{
            long aED = dbHelper.addEntryData(p1,p2,p3,p4,p5);
            if(aED<=0){
                EntriesListMessage.message(getApplicationContext(), "Data could not be added to the database!");
                date.setText("");
                bookName.setText("");
                pagesRead.setText("");
                bookRating.setText("");
                comment.setText("");
            }else{
                EntriesListMessage.message(getApplicationContext(), "Data was successfully added to the database!");
                date.setText("");
                bookName.setText("");
                pagesRead.setText("");
                bookRating.setText("");
                comment.setText("");
            }
        }
    }

}
