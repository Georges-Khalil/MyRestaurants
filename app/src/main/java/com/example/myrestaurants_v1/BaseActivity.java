package com.example.myrestaurants_v1;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

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
        if(item.getItemId()==R.id.home) {
            Intent MainActivity = new Intent(this, HomeActivity.class);
            startActivity(MainActivity);
        }
        return true;
    }
}
