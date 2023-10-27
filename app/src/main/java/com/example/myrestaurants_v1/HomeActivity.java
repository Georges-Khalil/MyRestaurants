package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.Model.RestaurantDBHelper;
import com.example.myrestaurants_v1.Model.RestaurantsListViewModel;
import com.example.myrestaurants_v1.databinding.HomeBinding;

public class HomeActivity extends BaseActivity {
    HomeBinding homeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restaurantList = new ViewModelProvider(this).get(RestaurantsListViewModel.class);
        if (restaurantList.size() == 0) {
            addSomeRestaurants();
        }

        RestaurantDBHelper restaurantDBHelper = new RestaurantDBHelper(this);
        /*
        Restaurant restaurant;
        restaurant = new Restaurant("McDonalds1", "Beirut Highway-LeMall, Dbayeh1","01 492 7031","https://mcdonalds.com.lb1");
        restaurant.setDelivery(true);
        restaurant.setId_(2);
        restaurantDBHelper.deleteRestaurant(2);
         */
        Toast.makeText(this, restaurantDBHelper.getAllRestaurants().size()+"", Toast.LENGTH_LONG).show();

        homeBinding = HomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        String message = "Total Restaurants: " + restaurantList.size();
        homeBinding.size.setText(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.listView){
            Intent ViewActivity = new Intent(this, ViewActivity.class);
            startActivity(ViewActivity);
        }
        if(item.getItemId()==R.id.add){
            Intent AddActivity = new Intent(this, AddActivity.class);
            startActivity(AddActivity);
        }
        return true;
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

    @Override
    public void onResume(){
        super.onResume();
        String message = "Total Restaurants: " + restaurantList.size();
        homeBinding.size.setText(message);
        Log.d("activity_lifecycle", "activity resumed");
    }
}