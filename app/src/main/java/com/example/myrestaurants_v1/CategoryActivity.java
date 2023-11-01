package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantDBHelper;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myrestaurants_v1.Model.Category;
import com.example.myrestaurants_v1.databinding.CategoryBinding;

public class CategoryActivity extends BaseActivity{
    CategoryBinding binding;
    CategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = CategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new CategoryAdapter(this, restaurantDBHelper.getAllCategories(), binding);
        binding.categoryList.setAdapter(adapter);

        binding.addCategoryButton.setOnClickListener(v -> add_category());
    }

    private void add_category() {
        String specialty = binding.newCategoryField.getText().toString();
        if(specialty.length() < 3){
            binding.newCategoryField.setText("");
            binding.newCategoryField.requestFocus();
            Toast.makeText(this, "Enter a valid speciality", Toast.LENGTH_SHORT).show();
            return;
        }
        Category category = new Category(specialty);
        restaurantDBHelper.insertCategory(category);
        binding.newCategoryField.setText("");
        adapter = new CategoryAdapter(this, restaurantDBHelper.getAllCategories(), binding);
        binding.categoryList.setAdapter(adapter);
    }
}
