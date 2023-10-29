package com.example.myrestaurants_v1;

import android.app.Application;

import com.example.myrestaurants_v1.Model.RestaurantDBHelper;

public class MyApp extends Application {
    public static RestaurantDBHelper restaurantDBHelper;

    @Override
    public void onCreate(){
        super.onCreate();
        restaurantDBHelper = new RestaurantDBHelper(this);
    }
}
