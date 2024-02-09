package com.example.mealitjava.MealDetails.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealitjava.R;
import com.example.mealitjava.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class MealsIngredientsAdapter extends RecyclerView.Adapter<MealsIngredientsAdapter.ViewHolder> {
    private List<Ingredient> ingredient;

    public MealsIngredientsAdapter(List<Ingredient> ingredientList) {
        this.ingredient = ingredientList;
    }

    @NonNull
    @Override
    public MealsIngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ingredients_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsIngredientsAdapter.ViewHolder holder, int position) {
        //Ingredient ingredient = ingredient.get(position);
        holder.nameTextView.setText(ingredient.get(position).getIngredientName());
        holder.mesureTextView.setText(ingredient.get(position).getIngredientMeasure());

        Glide.with(holder.getView().getContext()).load("https://www.themealdb.com/images/ingredients/" +
                ingredient.get(position).getIngredientName() +
                "-Small.png").into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return ingredient.size();
    }

    public void setList(ArrayList<Ingredient> arrayList) {
        ingredient = arrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nameTextView, mesureTextView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewIngredientImage);
            nameTextView = itemView.findViewById(R.id.textViewIngredientNameItem_mealDetails);
            mesureTextView = itemView.findViewById(R.id.textViewIngredientMeasureItem);
            view = itemView;
        }

        public View getView() {
            return view;
        }
    }

}
