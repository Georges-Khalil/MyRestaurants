package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myrestaurants_v1.databinding.HomeBinding;

public class HomeActivity extends BaseActivity {
    HomeBinding homeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        if(item.getItemId()==R.id.home) {
            Intent MainActivity = new Intent(this, HomeActivity.class);
            startActivity(MainActivity);
        }
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        String message = "Total Restaurants: " + restaurantList.size();
        homeBinding.size.setText(message);
        Log.d("activity_lifecycle", "activity resumed");
    }
}