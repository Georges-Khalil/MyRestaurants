package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantDBHelper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.myrestaurants_v1.Model.Category;
import com.example.myrestaurants_v1.databinding.FilterBinding;

import java.util.ArrayList;

public class FilterRestaurants extends BaseActivity{
    FilterBinding binding;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = FilterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Category> categoryList = restaurantDBHelper.getAllCategories();
        categoryList.add(0, new Category("All"));

        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.filterLayout.spinner.setAdapter(adapter);
        binding.filterLayout.btAdd.setText("Filter");

        binding.filterLayout.btAdd.setOnClickListener(v -> filterRestaurant());
    }

    private void filterRestaurant() {
        String name = binding.filterLayout.name.getText().toString();
        String address = binding.filterLayout.address.getText().toString();
        String phone = binding.filterLayout.phone.getText().toString();
        String web = binding.filterLayout.web.getText().toString();
        boolean onTable = binding.filterLayout.onTable.isChecked();
        boolean delivery = binding.filterLayout.delivery.isChecked();
        boolean takeAway = binding.filterLayout.takeaway.isChecked();
        float rating = binding.filterLayout.ratingBar.getRating();
        Category category = (Category) binding.filterLayout.spinner.getSelectedItem();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("name", name);
        resultIntent.putExtra("address", address);
        resultIntent.putExtra("phone", phone);
        resultIntent.putExtra("web", web);
        resultIntent.putExtra("onTable", onTable);
        resultIntent.putExtra("delivery", delivery);
        resultIntent.putExtra("takeAway", takeAway);
        resultIntent.putExtra("rating", rating);
        resultIntent.putExtra("category_speciality", category.getSpecialty());
        resultIntent.putExtra("category_id", category.getId_());
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
