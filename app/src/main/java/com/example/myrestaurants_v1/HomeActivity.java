package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    public void onStart(){
        super.onStart();
        Log.d("activity_lifecycle", "activity started");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("activity_lifecycle", "activity resumed");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("activity_lifecycle", "activity paused");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("activity_lifecycle", "activity stopped");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d("activity_lifecycle", "activity restarted");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("activity_lifecycle", "activity destroyed");
    }

    @Override
    public void onSaveInstanceState( Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d("activity_lifecycle", "onSaveInstanceState");
    }

    @Override
    public void onRestoreInstanceState( Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("activity_lifecycle", "onRestoreInstanceState");
    }
}