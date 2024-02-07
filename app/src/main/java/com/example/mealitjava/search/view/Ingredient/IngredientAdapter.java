package com.example.mealitjava.search.view.Ingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealitjava.R;
import com.example.mealitjava.model.IngredientList;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private final Context context;
    private List<IngredientList> ingredientList;

    private OnIngredientClickListener listener;

    public IngredientAdapter(Context context, List<IngredientList> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    public List<IngredientList> getingredientList() {
        return ingredientList;
    }

    public void setingredientList(List<IngredientList> ingredientList) {
        this.ingredientList = ingredientList;
        notifyDataSetChanged();
    }

    public IngredientAdapter(Context context, List<IngredientList> ingredientList, OnIngredientClickListener listener) {
        this.context = context;
        this.ingredientList = ingredientList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.ingredients_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //CheckSearchBy checkSearchBy = new CheckSearchBy();
        String name = ingredientList.get(position).getStrIngredient();
        holder.ingredientTextView.setText(ingredientList.get(position).getStrIngredient());
        Glide.with(holder.itemView.getContext()).load("https://www.themealdb.com/images/ingredients/"
                + name + "-Small.png")
                .into(holder.ingredientImage);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickIngredient(name);
            }
        });
//        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onClickItem(name);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientTextView;
        RoundedImageView ingredientImage;
        ConstraintLayout constraintLayout;
        View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            ingredientTextView = v.findViewById(R.id.textViewIngredientNameItem_mealDetails);
            ingredientImage = v.findViewById(R.id.imageViewIngredientImage);
            constraintLayout = v.findViewById(R.id.ingredientCard);
        }
    }
}

