package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantDBHelper;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.myrestaurants_v1.Model.Category;
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

        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, restaurantDBHelper.getAllCategories());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);

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
        float rating = binding.ratingBar.getRating();
        Category category = (Category) binding.spinner.getSelectedItem();

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
        if(rating == 0){
            Toast.makeText(this,"Please choose rating", Toast.LENGTH_SHORT).show();
            return;
        }

        Restaurant restaurant = new Restaurant(name, address, phone, web, rating);
        restaurant.setOnTable(onTable);
        restaurant.setDelivery(delivery);
        restaurant.setTakeAway(takeAway);
        restaurant.setCategory_id(category.getId_());

        binding.name.setText("");
        binding.address.setText("");
        binding.phone.setText("");
        binding.web.setText("");
        binding.onTable.setChecked(true);
        binding.delivery.setChecked(false);
        binding.takeaway.setChecked(false);
        binding.ratingBar.setRating(0);
        binding.spinner.setSelection(0);
        binding.name.requestFocus();

        restaurantDBHelper.insert(restaurant);
        Snackbar.make(binding.getRoot(), R.string.add_activity_toast, Snackbar.LENGTH_SHORT).show();
    }
}
