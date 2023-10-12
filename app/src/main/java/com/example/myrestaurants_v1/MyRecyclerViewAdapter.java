package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.databinding.RvItemBinding;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter{

    class RestaurantListVH extends RecyclerView.ViewHolder{
        RvItemBinding binding;
        RestaurantListVH(RvItemBinding binding){
            super(binding.getRoot());
            this.binding=binding;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemBinding binding;
        binding = RvItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RestaurantListVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder_, int position) {
        RestaurantListVH holder = (RestaurantListVH) holder_;
        Restaurant restaurant = restaurantList.getRestaurantList().get(position);
        holder.binding.name.setText(restaurant.getName());
        holder.binding.address.setText(restaurant.getAddress());
        holder.binding.phone.setText(restaurant.getPhone());
        holder.binding.web.setText(restaurant.getWeb());
        if(restaurant.isOnTable()){
            holder.binding.onTable.setImageResource(R.drawable.baseline_table_restaurant_24);
        }
        else{
            holder.binding.onTable.setImageResource(R.drawable.baseline_table_restaurant_24_false);
        }
        if(restaurant.isTakeAway()){
            holder.binding.takeAway.setImageResource(R.drawable.baseline_takeout_dining_24);
        }
        else{
            holder.binding.takeAway.setImageResource(R.drawable.baseline_takeout_dining_24_false);
        }
        if(restaurant.isDelivery()){
            holder.binding.delivery.setImageResource(R.drawable.baseline_delivery_dining_24);
        }
        else{
            holder.binding.delivery.setImageResource(R.drawable.baseline_delivery_dining_24_false);
        }
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
}
