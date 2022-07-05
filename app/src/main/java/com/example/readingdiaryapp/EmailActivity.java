package com.example.readingdiaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmailActivity extends AppCompatActivity {

    Button sendEmail, getEntries;
    EditText recipientEmail;
    DatabaseAdapter dbHelper;
    TextView entryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        sendEmail = findViewById(R.id.SendEmailButton);
        getEntries = findViewById(R.id.ViewEntriesButton);
        entryList = findViewById(R.id.EntryList);
        recipientEmail = findViewById(R.id.RecipientEmailAddress);
        dbHelper = new DatabaseAdapter(this);
        getEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEntriesList(v);
            }
        });

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailEntered = recipientEmail.getText().toString();
                String subject = "Reading Diary Entries";
                String entries = entryList.getText().toString();
                String body = "The student's current reading diary entries are below:\n\n";
                body += (entries + "\n");
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailEntered});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, body);
                emailIntent.setType("message/rfc822");
                if(emailIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(emailIntent);
                }
                else{
                    Toast errorMessage = Toast.makeText(EmailActivity.this, "No Email App!", Toast.LENGTH_LONG);
                    errorMessage.show();
                }
            }
        });

    }

    public void getEntriesList(View view){
        String getEntries = dbHelper.retrieveDatabaseData();
        entryList.setText(getEntries);
    }
}