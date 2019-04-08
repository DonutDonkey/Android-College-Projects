package com.wmii.kgm.calculatored.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.wmii.kgm.calculatored.db.FeedContact.FeedEntry.TABLE_CONTEXT;
import static com.wmii.kgm.calculatored.db.FeedContact.FeedEntry.TABLE_NAME;
import static com.wmii.kgm.calculatored.db.FeedContact.SQL_CREATE_TABLE;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "history.db";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  db.execSQL(SQL_CREATE_TABLE); }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(String string) {
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(TABLE_CONTEXT, string);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME, null, values);

        if(newRowId != -1){
            return true;
        } else {
            return false;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }

    public void DeleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = 'history_table'");

    }
}
