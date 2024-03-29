package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.PrefFileName;
import static com.example.myrestaurants_v1.MyApp.PrefFile_key_lang;
import static com.example.myrestaurants_v1.MyApp.restaurantDBHelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import com.example.myrestaurants_v1.Model.Category;
import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.databinding.HomeBinding;

public class HomeActivity extends BaseActivity {
    HomeBinding homeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        SharedPreferences settings = this.getSharedPreferences(PrefFileName, Context.MODE_PRIVATE);
        String language = settings.getString(PrefFile_key_lang, null);
        Toast.makeText(this, "stored language: "+language, Toast.LENGTH_SHORT).show();
        if(language != null) {
            setLocalLanguage(language);
        }
        */

        if (restaurantDBHelper.restaurantCount() == 0) {
            addSomeRestaurants();
        }

        homeBinding = HomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        String message = "Total Restaurants: " + restaurantDBHelper.restaurantCount();
        homeBinding.size.setText(message);
    }

    private void setLocalLanguage(String language) {
        String localeName;
        if(language.equals("english")){
            localeName = "us-USA";
        }
        else{
            localeName = "ar-LB";
        }
        LocaleListCompat applocale = LocaleListCompat.forLanguageTags(localeName);
        AppCompatDelegate.setApplicationLocales(applocale);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.listView){
            Intent ViewActivity = new Intent(this, ViewActivity.class);
            startActivity(ViewActivity);
        }
        if(item.getItemId()==R.id.add){
            Intent AddActivity = new Intent(this, AddActivity.class);
            startActivity(AddActivity);
        }
        if(item.getItemId()==R.id.category){
            Intent CategoryActivity = new Intent(this, CategoryActivity.class);
            startActivity(CategoryActivity);
        }
        /*
        if(item.getItemId()==R.id.AR){
            setLocalLanguage("arabic");
            save_language("arabic");
        }
        if(item.getItemId()==R.id.EN){
            setLocalLanguage("english");
            save_language("english");
        }
        */
        return true;
    }

    public void save_language(String language){
        SharedPreferences settings = this.getSharedPreferences(PrefFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PrefFile_key_lang, language);
        editor.commit();
    }

    public void addSomeRestaurants() {
        Category category = new Category("not set");
        restaurantDBHelper.insertCategory(category);
        Restaurant restaurant;
        restaurant = new Restaurant("McDonalds", "Beirut Highway-LeMall, Dbayeh","01 492 703","https://mcdonalds.com.lb", 4);
        restaurant.setDelivery(true);
        restaurantDBHelper.insert(restaurant);
        restaurant = new Restaurant("KFC", "Tripoli, Boulvard, El Bahsas","06 438 632","https://global.kfc.com/", 3);
        restaurantDBHelper.insert(restaurant);
        restaurant.setTakeAway(true);restaurant.setTakeAway(true);
        restaurant = new Restaurant("Dip N' Dip", "Beirut"," 01 370 120","https://dipndip.com/", 5);
        restaurantDBHelper.insert(restaurant);
        restaurant.setDelivery(true);
        restaurant = new Restaurant("CrepAway", "Byblos, Main Highway Entrance","09 943 777","https://crepaway.com/", 4);
        restaurantDBHelper.insert(restaurant);
        restaurant = new Restaurant("Roadster", "Batroun, Main Road","06 740 982","https://roadsterdiner.com/",1);
        restaurantDBHelper.insert(restaurant);
    }

    @Override
    public void onResume(){
        super.onResume();
        String message = "Total Restaurants: " + restaurantDBHelper.restaurantCount();
        homeBinding.size.setText(message);
        Log.d("activity_lifecycle", "activity resumed");
    }
}