package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantDBHelper;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.myrestaurants_v1.Model.Category;
import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.databinding.FilterBinding;

import java.util.ArrayList;
import java.util.Objects;

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

        ArrayList<Restaurant> restaurantList = restaurantDBHelper.getAllRestaurants();

        if(name.length() > 0){
            for(int i=0; i<restaurantList.size(); i++){
                if(!restaurantList.get(i).getName().contains(name)){
                    restaurantList.remove(i);
                    i--;
                }
            }
        }

        if(address.length() > 0){
            for(int i=0; i<restaurantList.size(); i++){
                if(!restaurantList.get(i).getAddress().contains(address)){
                    restaurantList.remove(i);
                    i--;
                }
            }
        }

        if(phone.length() > 0){
            for(int i=0; i<restaurantList.size(); i++){
                if(!restaurantList.get(i).getPhone().contains(phone)){
                    restaurantList.remove(i);
                    i--;
                }
            }
        }

        if(web.length() > 0){
            for(int i=0; i<restaurantList.size(); i++){
                if(!restaurantList.get(i).getWeb().contains(web)){
                    restaurantList.remove(i);
                    i--;
                }
            }
        }

        if(onTable){
            for(int i=0; i<restaurantList.size(); i++){
                if(!restaurantList.get(i).isOnTable()){
                    restaurantList.remove(i);
                    i--;
                }
            }
        }

        if(delivery){
            for(int i=0; i<restaurantList.size(); i++){
                if(!restaurantList.get(i).isDelivery()){
                    restaurantList.remove(i);
                    i--;
                }
            }
        }

        if(takeAway){
            for(int i=0; i<restaurantList.size(); i++){
                if(!restaurantList.get(i).isTakeAway()){
                    restaurantList.remove(i);
                    i--;
                }
            }
        }

        if(rating > 0){
            for(int i=0; i<restaurantList.size(); i++){
                if(restaurantList.get(i).getRating() < rating){
                    restaurantList.remove(i);
                    i--;
                }
            }
        }

        if(!Objects.equals(category.getSpecialty(), "All")){
            for(int i=0; i<restaurantList.size(); i++){
                if(restaurantList.get(i).getCategory_id() != category.getId_()){
                    restaurantList.remove(i);
                    i--;
                }
            }
        }

        finish();
    }
}
