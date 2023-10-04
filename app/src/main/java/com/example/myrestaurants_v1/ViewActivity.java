package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantList;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.databinding.ViewListBinding;

public class ViewActivity extends BaseActivity {
    ViewListBinding listBinding;
    ArrayAdapter<Restaurant> restaurantArrayAdapter;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        listBinding = ViewListBinding.inflate(getLayoutInflater());
        setContentView(listBinding.getRoot());
        //restaurantArrayAdapter = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1, restaurantList.getRestaurantList());
        myRecyclerViewAdapter = new MyRecyclerViewAdapter((MyApp) getApplication());
        listBinding.recycleView.setLayoutManager(new LinearLayoutManager(this));


        listBinding.recycleView.setAdapter(myRecyclerViewAdapter);
    }
}
