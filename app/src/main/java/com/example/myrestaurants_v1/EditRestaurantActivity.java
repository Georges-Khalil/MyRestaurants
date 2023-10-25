package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.databinding.EditBinding;

public class EditRestaurantActivity extends BaseActivity{
    EditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = EditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int position = intent.getIntExtra("index", -1);
        Restaurant restaurant = restaurantList.getRestaurantList().get(position);

        binding.editLayout.name.setText(restaurant.getName());
        binding.editLayout.address.setText(restaurant.getAddress());
        binding.editLayout.phone.setText(restaurant.getPhone());
        binding.editLayout.web.setText(restaurant.getWeb());
        binding.editLayout.onTable.setChecked(restaurant.isOnTable());
        binding.editLayout.delivery.setChecked(restaurant.isDelivery());
        binding.editLayout.takeaway.setChecked(restaurant.isTakeAway());
        binding.editLayout.btAdd.setText("Update");

        binding.editLayout.btAdd.setOnClickListener(v -> updateRestaurant(position));
    }

    private void updateRestaurant(int position) {
        String name = binding.editLayout.name.getText().toString();
        String address = binding.editLayout.address.getText().toString();
        String phone = binding.editLayout.phone.getText().toString();
        String web = binding.editLayout.web.getText().toString();
        boolean onTable = binding.editLayout.onTable.isChecked();
        boolean delivery = binding.editLayout.delivery.isChecked();
        boolean takeAway = binding.editLayout.takeaway.isChecked();

        if(name.length() < 3){
            binding.editLayout.name.setText("");
            Toast.makeText(this,"Please enter valid name", Toast.LENGTH_SHORT).show();
            binding.editLayout.name.requestFocus();
            return;
        }
        if(address.length() < 3){
            binding.editLayout.address.setText("");
            Toast.makeText(this,"Please enter valid address", Toast.LENGTH_SHORT).show();
            binding.editLayout.address.requestFocus();
            return;
        }
        if(!onTable && !delivery && !takeAway){
            Toast.makeText(this,"Please choose at least one type", Toast.LENGTH_SHORT).show();
            return;
        }

        Restaurant restaurant = restaurantList.getRestaurantList().get(position);
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setPhone(phone);
        restaurant.setWeb(web);
        restaurant.setOnTable(onTable);
        restaurant.setDelivery(delivery);
        restaurant.setTakeAway(takeAway);
    }
}
