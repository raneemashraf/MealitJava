package com.example.mealitjava.search.view.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealitjava.R;
import com.example.mealitjava.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private final Context context;
    private List<Category> categoryList;


    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    private final OnCategoryClickListener listener;



    public CategoryAdapter(Context context, List<Category> categoryList , OnCategoryClickListener listener) {
        this.context = context;
        this.categoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.country_search_row_layout,parent,false);
        CategoryAdapter.ViewHolder vh = new CategoryAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = categoryList.get(position).getStrCategory();
        holder.categoryTextView.setText(categoryList.get(position).getStrCategory());
//        Glide.with(context)
//                .load(categoryList.get(position).getStrCategoryThumb())
//                .into(holder.categoryImage);

        holder.categoryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickCategory(name);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTextView;
        ImageView categoryImage;
        CardView cardView;
        View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            categoryTextView = v.findViewById(R.id.country_TextView_search);
            //categoryImage = v.findViewById(R.id.category_imageView_card);
            //cardView = v.findViewById(R.id.country_row_layout);
        }
    }
}

