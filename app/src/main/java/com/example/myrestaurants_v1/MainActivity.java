package com.example.myrestaurants_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.databinding.ActivityMainBinding;
import com.example.myrestaurants_v1.databinding.HomeBinding;
import com.example.myrestaurants_v1.databinding.ViewListBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<Restaurant> restaurantList;
    ArrayAdapter<Restaurant> restaurantArrayAdapter;
    private ActivityMainBinding binding;
    private ViewListBinding listBinding;
    private HomeBinding homeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        listBinding = ViewListBinding.inflate(getLayoutInflater());
        homeBinding = HomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        restaurantList = new ArrayList<>();
        restaurantArrayAdapter = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1, restaurantList);
        listBinding.listView.setAdapter(restaurantArrayAdapter);

        binding.btAdd.setOnClickListener(v -> add_restaurant());
    }

    public void add_restaurant(){
        String name = binding.name.getText().toString();
        String address = binding.address.getText().toString();
        boolean onTable = binding.onTable.isChecked();
        boolean delivery = binding.delivery.isChecked();
        boolean takeAway = binding.takeaway.isChecked();

        if(name.length() < 3){
            binding.name.setText("");
            Toast.makeText(this,"Please enter valid name", Toast.LENGTH_SHORT).show();
            binding.name.requestFocus();
            return;
        }
        if(address.length() < 3){
            binding.address.setText("");
            Toast.makeText(this,"Please enter valid address", Toast.LENGTH_SHORT).show();
            binding.address.requestFocus();
            return;
        }
        if(onTable == false && delivery == false && takeAway == false){
            Toast.makeText(this,"Please choose at least one option", Toast.LENGTH_SHORT).show();
            return;
        }

        Restaurant restaurant = new Restaurant(name, address);
        restaurant.setOnTable(onTable);
        restaurant.setDelivery(delivery);
        restaurant.setTakeAway(takeAway);

        binding.name.setText("");
        binding.address.setText("");
        binding.onTable.setChecked(true);
        binding.delivery.setChecked(false);
        binding.takeaway.setChecked(false);
        binding.name.requestFocus();
        restaurantList.add(restaurant);
        listBinding.listView.setAdapter(restaurantArrayAdapter);
        Snackbar.make(binding.getRoot(), "Restaurant Added", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.listView){
            setContentView(listBinding.getRoot());
        }
        if(item.getItemId()==R.id.add){
            setContentView(binding.getRoot());
        }
        if(item.getItemId()==R.id.home){
            setContentView(homeBinding.getRoot());
            String message = "Total Restaurants: " + restaurantList.size();
            homeBinding.size.setText(message);
        }

        return true;
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