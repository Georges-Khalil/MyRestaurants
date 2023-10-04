package com.example.myrestaurants_v1;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.Model.RestaurantsListViewModel;
import com.example.myrestaurants_v1.databinding.RvItemBinding;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter{
    RestaurantsListViewModel listViewModel;
    class RestaurantListVH extends RecyclerView.ViewHolder{
        RvItemBinding binding;
        RestaurantListVH(RvItemBinding binding){
            super(binding.getRoot());
            this.binding=binding;
        }
    }

    public MyRecyclerViewAdapter(MyApp app){
        this.listViewModel = app.restaurantList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RvItemBinding binding;
        binding = RvItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RestaurantListVH(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder_, int position) {
        RestaurantListVH holder = (RestaurantListVH) holder_;
        Restaurant restaurant = listViewModel.getRestaurantList().get(position);
        holder.binding.rowName.setText(restaurant.getName());
    }

    @Override
    public int getItemCount() {
        return listViewModel.size();
    }
}
