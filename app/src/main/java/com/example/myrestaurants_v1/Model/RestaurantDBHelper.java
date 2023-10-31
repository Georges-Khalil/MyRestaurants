package com.example.myrestaurants_v1.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class RestaurantDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "restaurants.db";
    private static final int SCHEMA_VERSION = 2;

    public RestaurantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        getWritableDatabase();
        Log.d("database", "calling constructor of open helper");
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE restaurants (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, address TEXT, onTable INTEGER, delivery INTEGER, takeAway INTEGER, phone TEXT, web TEXT, rating REAL);");
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
        contentValues.put("rating", restaurant.getRating());

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
            float rating = cursor.getFloat(8);
            Restaurant restaurant = new Restaurant(name, address, phone, web, rating);
            restaurant.setDelivery(delivery);
            restaurant.setTakeAway(takeAway);
            restaurant.setOnTable(onTable);
            restaurant.setId_(id_);
            restaurantList.add(restaurant);
            cursor.moveToNext();
        }
        return restaurantList;
    }

    public void updateRestaurant(Restaurant restaurant){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", restaurant.getName());
        contentValues.put("address", restaurant.getAddress());
        contentValues.put("phone", restaurant.getPhone());
        contentValues.put("web", restaurant.getWeb());
        contentValues.put("onTable", restaurant.isOnTable());
        contentValues.put("delivery", restaurant.isDelivery());
        contentValues.put("takeAway", restaurant.isTakeAway());
        contentValues.put("rating", restaurant.getRating());
        getWritableDatabase().update("restaurants", contentValues, "_id=?", new String[]{restaurant.getId_()+""});
    }

    public void deleteRestaurant(long id){
        getWritableDatabase().delete("restaurants", "_id=?", new String[]{id+""});
    }

    public int restaurantCount(){
        String query = "select count(*) from restaurants";
        Cursor cursor = getReadableDatabase().rawQuery(query, null);

        cursor.moveToFirst();
        int count = cursor.getInt(0);
        return count;
    }

    public Restaurant getRestaurant(long id) {
        String query = "select * from restaurants where _id = " + id;
        Cursor cursor = getReadableDatabase().rawQuery(query, null);

        cursor.moveToFirst();

        long id_ = cursor.getLong(0);
        String name = cursor.getString(1);
        String address = cursor.getString(2);
        boolean onTable = cursor.getInt(3) == 1;
        boolean delivery = cursor.getInt(4) == 1;
        boolean takeAway = cursor.getInt(5) == 1;
        String phone = cursor.getString(6);
        String web = cursor.getString(7);
        float rating = cursor.getFloat(8);
        Restaurant restaurant = new Restaurant(name, address, phone, web, rating);
        restaurant.setDelivery(delivery);
        restaurant.setTakeAway(takeAway);
        restaurant.setOnTable(onTable);
        restaurant.setId_(id_);
        return restaurant;
    }
}
