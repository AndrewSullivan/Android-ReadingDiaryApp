package com.example.readingdiaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RemoveEntryActivity extends AppCompatActivity {

    Button RemoveEntryButton;
    DatabaseAdapter dbHelper;
    EditText entryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_entry);

        entryID = findViewById(R.id.ChosenEntryInputField);
        dbHelper = new DatabaseAdapter(this);
        RemoveEntryButton = (Button) findViewById(R.id.RemoveEntryButton);
        RemoveEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeEntry(v);
            }
        });
    }

    public void removeEntry(View view){
        String chosenEntry = entryID.getText().toString();
        if(chosenEntry.isEmpty())
        {
            EntriesListMessage.message(getApplicationContext(), "You must enter the book name from the entry you wish to remove!");
        }
        else{
            int removeCount = dbHelper.removeDatabaseEntry(chosenEntry);
            if(removeCount<=0){
                EntriesListMessage.message(getApplicationContext(), "Could not remove entry containing entered book name!");
            }
            else
            {
                EntriesListMessage.message(getApplicationContext(), "Entry with entered book name has been removed!");
                entryID.setText("");
            }
        }
    }
}