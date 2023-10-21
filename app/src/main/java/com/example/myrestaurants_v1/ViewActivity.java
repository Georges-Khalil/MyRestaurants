package com.example.myrestaurants_v1;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myrestaurants_v1.databinding.ViewListBinding;
import android.Manifest;

public class ViewActivity extends BaseActivity {
    ViewListBinding listBinding;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
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
}
