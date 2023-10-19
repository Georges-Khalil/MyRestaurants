package com.example.myrestaurants_v1;

import android.app.Application;

import com.example.myrestaurants_v1.Model.RestaurantsListViewModel;

public class MyApp extends Application {
    public static RestaurantsListViewModel restaurantList;

    @Override
    public void onCreate(){
        super.onCreate();
    }
}
