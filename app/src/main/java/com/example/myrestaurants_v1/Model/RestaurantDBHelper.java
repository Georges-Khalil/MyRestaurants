package com.example.myrestaurants_v1.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RestaurantDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myrestaurants.db";
    private static final int SCHEMA_VERSION = 2;

    public RestaurantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        getWritableDatabase();
        Log.d("database", "calling constructor of open helper");
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE restaurants (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, address TEXT, onTable INTEGER, delivery INTEGER, takeAway INTEGER, phone TEXT, web TEXT);");
        Log.d("database", "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("database", "onUpgrade");
    }
}
