package com.example.myrestaurants_v1;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myrestaurants_v1.databinding.ViewListBinding;

public class ViewActivity extends BaseActivity {
    ViewListBinding listBinding;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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
}
