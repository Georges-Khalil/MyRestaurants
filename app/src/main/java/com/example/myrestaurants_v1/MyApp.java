package com.example.myrestaurants_v1;

import android.app.Application;
import android.widget.ArrayAdapter;

import androidx.lifecycle.ViewModelProvider;

import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.Model.RestaurantsListViewModel;

public class MyApp extends Application {
    static RestaurantsListViewModel restaurantList;
    static ArrayAdapter<Restaurant> restaurantArrayAdapter;

    @Override
    public void onCreate(){
        super.onCreate();
        restaurantList = new ViewModelProvider.AndroidViewModelFactory(this).create(RestaurantsListViewModel.class);;
        restaurantArrayAdapter = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1, restaurantList.getRestaurantList());
    }
}
