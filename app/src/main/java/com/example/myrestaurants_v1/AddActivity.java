package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantList;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.databinding.AddBinding;
import com.google.android.material.snackbar.Snackbar;

public class AddActivity extends BaseActivity {
    AddBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = AddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.onTable.setChecked(true);

        binding.btAdd.setOnClickListener(v -> add_restaurant());
    }

    public void add_restaurant(){
        String name = binding.name.getText().toString();
        String address = binding.address.getText().toString();
        String phone = binding.phone.getText().toString();
        String web = binding.web.getText().toString();
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
        if(!onTable && !delivery && !takeAway){
            Toast.makeText(this,"Please choose at least one type", Toast.LENGTH_SHORT).show();
            return;
        }

        Restaurant restaurant = new Restaurant(name, address, phone, web);
        restaurant.setOnTable(onTable);
        restaurant.setDelivery(delivery);
        restaurant.setTakeAway(takeAway);

        binding.name.setText("");
        binding.address.setText("");
        binding.phone.setText("");
        binding.web.setText("");
        binding.onTable.setChecked(true);
        binding.delivery.setChecked(false);
        binding.takeaway.setChecked(false);
        binding.name.requestFocus();
        restaurantList.add(restaurant);
        Snackbar.make(binding.getRoot(), "Restaurant Added", Snackbar.LENGTH_SHORT).show();
    }
}
