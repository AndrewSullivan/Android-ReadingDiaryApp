package com.example.readingdiaryapp;

import android.content.Context;
import android.widget.Toast;

public class EntriesListMessage {
    public static void message(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
