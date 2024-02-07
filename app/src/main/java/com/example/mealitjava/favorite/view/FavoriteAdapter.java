package com.example.mealitjava.favorite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealitjava.R;
import com.example.mealitjava.model.MealsItem;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{

    private final Context context;
    private List<MealsItem> favMealsList;
    public OnDeleteClickListener onDeleteClickListener;

    public void setFavMealsListList(List<MealsItem> favMealsList) {
        this.favMealsList = favMealsList;
    }

    private OnClickFavoriteMeal listener;

    public FavoriteAdapter(Context context, List<MealsItem> favMealsList ) {
        this.context = context;
        this.favMealsList = favMealsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_fav_meal,parent,false);
        FavoriteAdapter.ViewHolder vh = new FavoriteAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsItem mealsItem = favMealsList.get(position);
        holder.mealNameTextView.setText(favMealsList.get(position).strMeal);
        Glide.with(context)
                .load(favMealsList.get(position).strMealThumb)
                .into(holder.mealImage);
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onClickItem(favMealsList.get(holder.getAbsoluteAdapterPosition()).idMeal);
//            }
//        });
//        holder.deleteMealIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onClickDeleteItem(favMealsList.get(holder.getAdapterPosition()));
//            }
//        });

        holder.deleteMealIcon.setOnClickListener(view -> {
            onDeleteClickListener.onItemClickListener(mealsItem);
        });

        holder.cardView.setOnClickListener(v -> {
            FavoriteFragmentDirections.ActionFavoriteFragmentToMealDetailsFragment action =
                    FavoriteFragmentDirections.actionFavoriteFragmentToMealDetailsFragment(mealsItem);
            Navigation.findNavController(v).navigate(action);

        });
    }

    @Override
    public int getItemCount() {
        return favMealsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mealNameTextView;
        RoundedImageView mealImage;
        RoundedImageView deleteMealIcon;
        CardView cardView;
        View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;

            mealNameTextView = v.findViewById(R.id.textView_meal_title_item_fav);
            mealImage = v.findViewById(R.id.imageView_item_fav);
            deleteMealIcon = v.findViewById(R.id.icon_remove_item_fav);
            cardView = v.findViewById(R.id.cardViewRandomMeal);
        }
    }
    public interface OnDeleteClickListener{
        void onItemClickListener(MealsItem mealsItem);
    }

}
