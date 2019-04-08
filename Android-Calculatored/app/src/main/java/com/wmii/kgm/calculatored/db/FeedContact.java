package com.wmii.kgm.calculatored.db;

import android.provider.BaseColumns;

public final class FeedContact {

    private FeedContact() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "history_table";
        public static final String TABLE_ID = "ID";
        public static final String TABLE_CONTEXT = "EXPRESSION";
    }
    // SQLite create table string command
    static final String SQL_CREATE_TABLE =  "CREATE TABLE " + FeedEntry.TABLE_NAME + "("
                                                            + FeedEntry.TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                            + FeedEntry.TABLE_CONTEXT + " TEXT)";

}
