package com.example.mealitjava.planner.view;

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
import com.example.mealitjava.favorite.view.OnClickFavoriteMeal;
import com.example.mealitjava.model.MealPlannerAndMealConverter;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.PlannerModel;
import com.example.mealitjava.planner.presenter.PlannerPresenterInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class PlannerAdapter extends RecyclerView.Adapter<PlannerAdapter.ViewHolder>{

    private final Context context;
    private List<PlannerModel> PlannerMealsList = new ArrayList<>();
    PlannerPresenterInterface plannerPresenterInterface;
    public OnDeleteClickListener onDeleteClickListener;
    DatabaseReference databaseReferencePlanner = FirebaseDatabase
            .getInstance()
            .getReference().child("user")
            .child(FirebaseAuth.getInstance().getUid())  // Ensure user is authenticated before accessing UID
            .child("plan");

    public void setPlannerMealsList(List<PlannerModel> PlannerMealsList) {
        this.PlannerMealsList = PlannerMealsList;
        notifyDataSetChanged();
    }

    private OnClickFavoriteMeal listener;

    public PlannerAdapter(Context context, List<PlannerModel> favMealsList ) {
        this.context = context;
        this.PlannerMealsList = favMealsList;
        //plannerPresenterInterface = new PlannerPresenterImpl(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_fav_meal,parent,false);
        PlannerAdapter.ViewHolder vh = new PlannerAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlannerModel mealsItem = PlannerMealsList.get(position);
        holder.mealNameTextView.setText(mealsItem.strMeal);
        Glide.with(context)
                .load(PlannerMealsList.get(position).strMealThumb)
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
            databaseReferencePlanner.child(mealsItem.idMeal).removeValue();
        });

        holder.cardView.setOnClickListener(v -> {
            MealsItem meal = MealPlannerAndMealConverter.getMealFromMealPlanner(mealsItem);
            PlannerFragmentDirections.ActionPlannerFragmentToMealDetailsFragment action =
                    PlannerFragmentDirections.actionPlannerFragmentToMealDetailsFragment(meal);
            Navigation.findNavController(v).navigate(action);

        });
    }

    @Override
    public int getItemCount() {
        return PlannerMealsList.size();
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
        void onItemClickListener(PlannerModel mealsItem);
    }

}
