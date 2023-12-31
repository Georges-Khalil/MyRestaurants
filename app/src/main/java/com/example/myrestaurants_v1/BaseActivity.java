package com.example.myrestaurants_v1;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

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
