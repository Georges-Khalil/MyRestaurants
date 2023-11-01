package com.example.myrestaurants_v1;

import static com.example.myrestaurants_v1.MyApp.restaurantDBHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.myrestaurants_v1.Model.Category;
import com.example.myrestaurants_v1.databinding.CategoryItemBinding;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<Category> {
    Context context;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        super(context, 0, categories);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Category category = getItem(position);

        CategoryItemBinding binding;

        if (convertView == null) {
            binding = CategoryItemBinding.inflate(LayoutInflater.from(getContext()), parent, false);
            convertView = binding.getRoot();
        }
        else{
            binding = CategoryItemBinding.bind(convertView);
        }

        binding.categorySpecialty.setText(category.getSpecialty());

        binding.editButton.setOnClickListener(v -> edit_category());

        binding.deleteButton.setOnClickListener(v -> delete_category(category));

        return convertView;
    }

    private void edit_category() {
    }

    private void delete_category(Category category){
        try {
            restaurantDBHelper.deleteCategory(category.getId_());
            remove(category);
            notifyDataSetChanged();
        } catch (Exception e) {
            Toast.makeText(context,"Category contains restaurants", Toast.LENGTH_SHORT).show();
        }
    }

}
