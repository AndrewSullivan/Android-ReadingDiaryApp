package com.example.readingdiaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditEntryActivity extends AppCompatActivity {

    Button UpdateEntryButton;
    EditText date, bookName, pagesRead, bookRating, comment, entryID;
    DatabaseAdapter dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
        date = findViewById(R.id.UpdatedBookNameInputField);
        bookName = findViewById(R.id.UpdatedBookNameInputField);
        pagesRead = findViewById(R.id.UpdatedPagesReadInputField);
        bookRating = findViewById(R.id.UpdatedBookRatingInputField);
        comment = findViewById(R.id.UpdatedCommentInputField);
        entryID = findViewById(R.id.EntryIdInputField);
        dbHelper = new DatabaseAdapter(this);
        UpdateEntryButton = (Button) findViewById(R.id.UpdateEntryButton);

        UpdateEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editEntry(v);
            }
        });
    }

    public void editEntry(View view){
        String chosenEntryID = entryID.getText().toString();
        String dateUpdated = date.getText().toString();
        String bookNameUpdated = bookName.getText().toString();
        String pagesReadUpdated = pagesRead.getText().toString();
        String bookRatingUpdated = bookRating.getText().toString();
        String commentUpdated = comment.getText().toString();

        if(chosenEntryID.isEmpty())
        {
            EntriesListMessage.message(getApplicationContext(), "You must enter a valid ID for the chosen entry to be edited!");
        }
        else{
            long eE = dbHelper.updateDatabaseEntry(dateUpdated,bookNameUpdated,pagesReadUpdated,bookRatingUpdated,commentUpdated,chosenEntryID);
            if(eE<=0){
                EntriesListMessage.message(getApplicationContext(), "Could not edit chosen ID's entry");
            }
            else
            {
                EntriesListMessage.message(getApplicationContext(), "Successfully edited entry!");
                date.setText("");
                bookName.setText("");
                pagesRead.setText("");
                bookRating.setText("");
                comment.setText("");
            }
        }
    }
}