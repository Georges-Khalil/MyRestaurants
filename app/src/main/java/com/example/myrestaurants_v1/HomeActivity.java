package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantList;

import android.os.Bundle;

import com.example.myrestaurants_v1.databinding.HomeBinding;

public class HomeActivity extends BaseActivity {
    private HomeBinding homeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = HomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        String message = "Total Restaurants: " + restaurantList.size();
        homeBinding.size.setText(message);
    }
}