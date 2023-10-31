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

        binding.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle edit action here
            }
        });

        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    restaurantDBHelper.deleteCategory(category.getId_());
                } catch (Exception e) {
                    Toast.makeText(context,"Category contains restaurants", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }

}
