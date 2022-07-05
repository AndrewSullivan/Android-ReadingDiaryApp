package com.example.readingdiaryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {

    databaseHelper dbHelper;

    public DatabaseAdapter(Context context){
        dbHelper = new databaseHelper(context);
    }

    public long addEntryData(String date, String bookName, String pagesRead, String bookRating, String comment){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.DATE, date);
        contentValues.put(dbHelper.BOOKNAME, bookName);
        contentValues.put(dbHelper.PAGESREAD, pagesRead);
        contentValues.put(dbHelper.BOOKRATING, bookRating);
        contentValues.put(dbHelper.COMMENT, comment);
        long aED = database.insert(dbHelper.TABLE_NAME, null, contentValues);
        return aED;
    }

    public String retrieveDatabaseData(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String[] dbColumns = {dbHelper.UID,dbHelper.DATE,dbHelper.BOOKNAME,dbHelper.PAGESREAD,dbHelper.BOOKRATING,dbHelper.COMMENT};
        Cursor cursor = database.query(dbHelper.TABLE_NAME,dbColumns,null,null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            int colNum = cursor.getInt(cursor.getColumnIndexOrThrow(dbHelper.UID));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.DATE));
            String bookName = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.BOOKNAME));
            String pagesRead = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.PAGESREAD));
            String bookRating = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.BOOKRATING));
            String comment = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COMMENT));
            buffer.append(colNum+ " " + date + " " + bookName + " " + pagesRead + " " +
                    bookRating + " " + comment +"\n");
        }
        return buffer.toString();
    }

    public int removeDatabaseEntry(String chosenEntry){
        SQLiteDatabase database = dbHelper.getWritableDatabase(); // Gets the database
        String[] chosenEntries = {chosenEntry}; // String array for the book from a diary entry that's been chosen to be removed.

        int entryCount = database.delete(dbHelper.TABLE_NAME, dbHelper.UID+" = ?",chosenEntries); // Removes the inputted book name from the database.
        return entryCount;
    }

    public long updateDatabaseEntry(String date, String bookName, String pagesRead, String bookRating, String comment, String chosenEntryID){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String[] chosenEntry = {chosenEntryID};
        ContentValues contentValues = new ContentValues();

        contentValues.put(dbHelper.DATE,date);
        contentValues.put(dbHelper.BOOKNAME,bookName);
        contentValues.put(dbHelper.PAGESREAD,pagesRead);
        contentValues.put(dbHelper.BOOKRATING,bookRating);
        contentValues.put(dbHelper.COMMENT,comment);
        long uDE = database.update(dbHelper.TABLE_NAME,contentValues,dbHelper.UID+" = ?",chosenEntry);
        return uDE;
    }

    static class databaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "ReadingDiaryDatabase"; // Database Name
        private static final String TABLE_NAME = "ReadingDiaryTable"; // Table Name
        private static final int DATABASE_Version = 1;
        private static final String UID = "_id"; // First Column
        private static final String DATE = "Date"; // Second Column
        private static final String BOOKNAME = "BookName"; // Third Column
        private static final String PAGESREAD = "PagesRead"; // Fourth Column
        private static final String BOOKRATING = "BookRating"; // Fifth Column
        private static final String COMMENT = "Comment"; // Sixth Column
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+DATE+" VARCHAR(255) ,"+ BOOKNAME+" VARCHAR(255) ,"+ PAGESREAD+" VARCHAR(255) ,"+
                BOOKRATING+" VARCHAR(255) ,"+ COMMENT+" VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;



        public databaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }


        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(CREATE_TABLE);
            } catch (Exception e){
                EntriesListMessage.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                EntriesListMessage.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch(Exception e){
                EntriesListMessage.message(context,""+e);
            }
        }
    }
}
