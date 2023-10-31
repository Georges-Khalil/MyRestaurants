package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantDBHelper;

import android.os.Bundle;

import com.example.myrestaurants_v1.Model.Category;
import com.example.myrestaurants_v1.databinding.CategoryBinding;

import java.util.ArrayList;

public class CategoryActivity extends BaseActivity{
    CategoryBinding binding;
    ArrayList<Category> categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = CategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        categoryList = restaurantDBHelper.getAllCategories();
        CategoryAdapter adapter = new CategoryAdapter(this, categoryList);
        binding.categoryList.setAdapter(adapter);
    }
}
