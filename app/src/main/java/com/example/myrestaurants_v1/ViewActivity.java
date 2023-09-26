package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantArrayAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myrestaurants_v1.databinding.ViewListBinding;

public class ViewActivity extends BaseActivity {
    private ViewListBinding listBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        listBinding = ViewListBinding.inflate(getLayoutInflater());
        setContentView(listBinding.getRoot());

        listBinding.listView.setAdapter(restaurantArrayAdapter);
    }
}
