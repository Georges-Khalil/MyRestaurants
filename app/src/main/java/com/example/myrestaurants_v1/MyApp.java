package com.example.myrestaurants_v1;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.Model.RestaurantsListViewModel;

public class MyApp extends Application {
    static RestaurantsListViewModel restaurantList;

    @Override
    public void onCreate(){
        super.onCreate();
        restaurantList = new ViewModelProvider.AndroidViewModelFactory(this).create(RestaurantsListViewModel.class);
        addSomeRestaurants();
    }

    public void addSomeRestaurants() {
        Restaurant restaurant;
        restaurant = new Restaurant("McDonalds", "Beirut Highway-LeMall, Dbayeh","01 492 703","https://mcdonalds.com.lb");
        restaurant.setDelivery(true);
        restaurantList.add(restaurant);
        restaurant = new Restaurant("KFC", "Tripoli, Boulvard, El Bahsas","06 438 632","https://global.kfc.com/");
        restaurantList.add(restaurant);
        restaurant.setTakeAway(true);restaurant.setTakeAway(true);
        restaurant = new Restaurant("Dip N' Dip", "Beirut"," 01 370 120","https://dipndip.com/");
        restaurantList.add(restaurant);
        restaurant.setDelivery(true);
        restaurant = new Restaurant("CrepAway", "Byblos, Main Highway Entrance","09 943 777","https://crepaway.com/");
        restaurantList.add(restaurant);
        restaurant = new Restaurant("Roadster", "Batroun, Main Road","06 740 982","https://roadsterdiner.com/");
        restaurantList.add(restaurant);
    }
}
