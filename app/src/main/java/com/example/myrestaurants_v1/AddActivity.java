package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.databinding.AddBinding;
import com.google.android.material.snackbar.Snackbar;

public class AddActivity extends BaseActivity {
    private AddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = AddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        Snackbar.make(binding.getRoot(), "Restaurant Added", Snackbar.LENGTH_SHORT).show();
    }
}
