package com.example.mealitjava.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealitjava.R;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.search.FilterResultFragmentDirections;

import java.util.List;

public class FilterResultAdapter extends RecyclerView.Adapter<FilterResultAdapter.ViewHolder> {
    private static final String TAG = "MealResultAdapter";
    private List<MealsItem> mealsList;

    public FilterResultAdapter(List<MealsItem> mealsList){
        this.mealsList = mealsList;
    }

    private OnClickMealResult listener;


    public FilterResultAdapter(Context context, List<MealsItem> mealsList, OnClickMealResult listener) {
        this.mealsList = mealsList;
        this.listener = listener;
    }
    public void setAllMeals(List<MealsItem> allMeals) {
        this.mealsList = allMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_main_meal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsItem meal = mealsList.get(position);
        holder.mealNameTextView.setText(mealsList.get(position).strMeal);
        Glide.with(holder.mealImage.getContext())
                .load(mealsList.get(position).strMealThumb)
                .into(holder.mealImage);

        holder.cardView.setOnClickListener(v -> {
            FilterResultFragmentDirections.ActionFilterResultFragmentToMealDetailsFragment action =
                    FilterResultFragmentDirections.actionFilterResultFragmentToMealDetailsFragment(meal);
            Navigation.findNavController(v).navigate(action);

        });

     //   holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onClickItem(mealsList.get(holder.getAbsoluteAdapterPosition()).idMeal);
//            }
      //  });

    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mealNameTextView;
        ImageView mealImage;
        CardView cardView;
        View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            mealNameTextView = v.findViewById(R.id.textView_meal_title_item_main);
            mealImage = v.findViewById(R.id.imageView_item_main);
            cardView = v.findViewById(R.id.cardView_item_main);
        }
    }
}

