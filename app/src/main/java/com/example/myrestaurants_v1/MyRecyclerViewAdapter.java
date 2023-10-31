package com.example.myrestaurants_v1;


import static com.example.myrestaurants_v1.MyApp.restaurantDBHelper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrestaurants_v1.Model.Restaurant;
import com.example.myrestaurants_v1.Model.RestaurantsListViewModel;
import com.example.myrestaurants_v1.databinding.RvItemBinding;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter{
    private Context context;
    private RestaurantsListViewModel restaurantList;

    public MyRecyclerViewAdapter(Context context) {
        this.context = context;
        restaurantList = new ViewModelProvider((ViewModelStoreOwner) context).get(RestaurantsListViewModel.class);
        restaurantList.setRestaurantList(restaurantDBHelper.getAllRestaurants());
    }

    public void refresh() {
        restaurantList.setRestaurantList(restaurantDBHelper.getAllRestaurants());
    }

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
        holder.binding.ratingBar.setRating(restaurant.getRating());

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

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            holder.binding.call.setBackgroundColor(ContextCompat.getColor(context, R.color.dark_grey));
        }
        else{
            holder.binding.call.setBackgroundColor(ContextCompat.getColor(context, R.color.pink));
        }

        holder.binding.browse.setOnClickListener(v -> browseWebsite(restaurant.getWeb()));
        holder.binding.call.setOnClickListener(v -> callPhone(restaurant.getPhone()));
        holder.binding.burgerMenu.setOnClickListener(v -> holder.binding.drawerLayout.openDrawer(GravityCompat.END));
        holder.binding.delete.setOnClickListener(v -> deleteRestaurant(restaurant, position));
        holder.binding.update.setOnClickListener(v -> editRestaurant(position));
    }

    private void editRestaurant(int position) {
        Intent intent = new Intent(context, EditRestaurantActivity.class);
        intent.putExtra("id_", restaurantList.getRestaurantList().get(position).getId_());
        context.startActivity(intent);
    }

    private void deleteRestaurant(Restaurant restaurant, int index) {
        restaurantList.remove(restaurant);
        restaurantDBHelper.deleteRestaurant(restaurant.getId_());
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, restaurantList.getRestaurantList().size() - index);
    }

    private void callPhone(String phone) {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        Uri uri = Uri.parse("tel:" + phone);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        context.startActivity(intent);
    }

    private void browseWebsite(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
}
