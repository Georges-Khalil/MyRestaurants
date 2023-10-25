package com.example.myrestaurants_v1.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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

    public long insert(Restaurant restaurant){
        ContentValues contentValues;

        contentValues = new ContentValues();
        contentValues.put("name", restaurant.getName());
        contentValues.put("address", restaurant.getAddress());
        contentValues.put("phone", restaurant.getPhone());
        contentValues.put("web", restaurant.getWeb());
        contentValues.put("onTable", restaurant.isOnTable());
        contentValues.put("delivery", restaurant.isDelivery());
        contentValues.put("takeAway", restaurant.isTakeAway());

        long id = getWritableDatabase().insert("restaurants", null, contentValues);
        return id;
    }

    public ArrayList<Restaurant> getAllRestaurants(){
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        String query = "select * from restaurants";
        Cursor cursor = getReadableDatabase().rawQuery(query, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            long id_ = cursor.getLong(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            boolean onTable = cursor.getInt(3) == 1;
            boolean delivery = cursor.getInt(4) == 1;
            boolean takeAway = cursor.getInt(5) == 1;
            String phone = cursor.getString(6);
            String web = cursor.getString(7);
            Restaurant restaurant = new Restaurant(name, address, phone, web);
            restaurant.setDelivery(delivery);
            restaurant.setTakeAway(takeAway);
            restaurant.setOnTable(onTable);
            restaurant.setId_(id_);
            restaurantList.add(restaurant);
        }
        return restaurantList;
    }
}
