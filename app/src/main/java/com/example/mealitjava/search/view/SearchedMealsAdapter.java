package com.example.mealitjava.search.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealitjava.R;
import com.example.mealitjava.model.MealsItem;

import java.util.List;

public class SearchedMealsAdapter extends RecyclerView.Adapter<SearchedMealsAdapter.ViewHolder> {

    private static final String TAG = "SearchedMealsAdapter";
    private List<MealsItem> allMeals;
    private Context context;
    private SearchClickListener searchClickListener;

    public SearchedMealsAdapter(Context context, SearchClickListener searchClickListener) {
        super();
        this.context = context;
        this.searchClickListener = searchClickListener;
    }

    public void setAllMeals(List<MealsItem> allMeals) {
        this.allMeals = allMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_meal_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsItem meal = allMeals.get(position);
        holder.mealSearchedNameCardTextView.setText(allMeals.get(position).strMeal);
        holder.categoryTextview.setText(allMeals.get(position).strCategory);
        Glide.with(context).load(allMeals.get(position).strMealThumb)
                .apply(new RequestOptions().override(200, 160))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealSearchedCardImageView);

        holder.searchCardConstraintLayout.setOnClickListener(v -> {
            SearchByMealFragmentDirections.ActionSearchFragmentToMealDetailsFragment action =
                    SearchByMealFragmentDirections.actionSearchFragmentToMealDetailsFragment(meal);
            Navigation.findNavController(v).navigate(action);

        });

    }

    @Override
    public int getItemCount() {
        return allMeals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTextview, mealSearchedNameCardTextView;
        ImageView mealSearchedCardImageView;
        ConstraintLayout searchCardConstraintLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            categoryTextview =itemView.findViewById(R.id.categorySearchCard);
            mealSearchedCardImageView = itemView.findViewById(R.id.mealSearchedCardImageView);
            mealSearchedNameCardTextView = itemView.findViewById(R.id.mealSearchedNameCardTextView);
            searchCardConstraintLayout = itemView.findViewById(R.id.searchCardConstraintLayout);


        }
    }
}