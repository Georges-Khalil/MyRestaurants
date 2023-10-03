package com.example.myrestaurants_v1.Model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsListViewModel extends ViewModel {
    private List<Restaurant> restaurantList = new ArrayList<>();

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
