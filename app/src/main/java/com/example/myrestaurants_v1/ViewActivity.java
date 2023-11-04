package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantDBHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.databinding.ViewListBinding;
import android.Manifest;
import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

public class ViewActivity extends BaseActivity {
    ViewListBinding listBinding;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    ActivityResultLauncher<Intent> resultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
        listBinding = ViewListBinding.inflate(getLayoutInflater());
        setContentView(listBinding.getRoot());

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            listBinding.recycleView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else {
            listBinding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        }

        listBinding.recycleView.setAdapter(myRecyclerViewAdapter);


        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                String name = data.getStringExtra("name");
                                String address = data.getStringExtra("address");
                                String phone = data.getStringExtra("phone");
                                String web = data.getStringExtra("web");
                                boolean onTable = data.getBooleanExtra("onTable", false);
                                boolean delivery = data.getBooleanExtra("delivery", false);
                                boolean takeAway = data.getBooleanExtra("takeAway", false);
                                float rating = data.getFloatExtra("rating", 0);
                                String category_speciality = data.getStringExtra("category_speciality");
                                long category_id = data.getLongExtra("category_id", 0);
                                filterApply(name, address, phone, web, onTable, delivery, takeAway, rating, category_speciality, category_id);
                            }
                        }
                    }
                }
        );


        listBinding.filter.setOnClickListener(v -> filter());
    }

    private void filterApply(String name, String address, String phone, String web, boolean onTable, boolean delivery, boolean takeAway, float rating, String category_speciality, long category_id) {
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

        if(!Objects.equals(category_speciality, "All")){
            for(int i=0; i<restaurantList.size(); i++){
                if(restaurantList.get(i).getCategory_id() != category_id){
                    restaurantList.remove(i);
                    i--;
                }
            }
        }

        myRecyclerViewAdapter.setRestaurantList(restaurantList);
        listBinding.recycleView.setAdapter(myRecyclerViewAdapter);
    }

    private void filter() {
        Intent intent = new Intent(this, FilterRestaurants.class);
        resultLauncher.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                myRecyclerViewAdapter = new MyRecyclerViewAdapter(this);
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    listBinding.recycleView.setLayoutManager(new GridLayoutManager(this, 2));
                }
                else {
                    listBinding.recycleView.setLayoutManager(new LinearLayoutManager(this));
                }

                listBinding.recycleView.setAdapter(myRecyclerViewAdapter);
            }
        }
    }

    public void onResume(){
        super.onResume();
        //myRecyclerViewAdapter.refresh();
        listBinding.recycleView.setAdapter(myRecyclerViewAdapter);
    }
}
