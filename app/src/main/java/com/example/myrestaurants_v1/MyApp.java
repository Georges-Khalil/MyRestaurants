package com.example.myrestaurants_v1;

import android.app.Application;
import android.widget.ArrayAdapter;

import com.example.myrestaurants_v1.Model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {
    static List<Restaurant> restaurantList;
    static ArrayAdapter<Restaurant> restaurantArrayAdapter;

    @Override
    public void onCreate(){
        super.onCreate();
        restaurantList = new ArrayList<>();
        restaurantArrayAdapter = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1, restaurantList);
    }
}
