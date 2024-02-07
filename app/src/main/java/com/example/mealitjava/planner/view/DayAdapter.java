package com.example.mealitjava.planner.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealitjava.R;
import com.example.mealitjava.model.MealsItem;

import java.util.List;



public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {

    private static final String TAG = "DayAdapter";
    private List<MealsItem> allMeals;
    private Context context;
    private PlannerClickListener plannerClickListener;

    public DayAdapter(Context context, PlannerClickListener allMealsClickListener) {
        super();
        this.context = context;
        this.plannerClickListener = allMealsClickListener;
    }

    public void setAllMeals(List<MealsItem> allMeals) {
        this.allMeals = allMeals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_fav_meal, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsItem meal = allMeals.get(position);
        holder.mealNameTextView.setText(allMeals.get(position).strMeal);
        Glide.with(context).load(allMeals.get(position).strMealThumb)
                .apply(new RequestOptions().override(200, 160))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealImageViewPlannerCard);

        Log.d(TAG, "onBindViewHolder: dddddddd " + meal.day);
        holder.deleteMealImageViewPlannerCard.setOnClickListener(e -> {
            Log.d(TAG, "onBindViewHolder: delete " + meal.strMeal);
            plannerClickListener.deleteMealFromDay(meal);
            notifyDataSetChanged();
        });
//        holder.planCardConstraintLayout.setOnClickListener(e -> {
//            Intent intent = new Intent(context, DetailsActivity.class);
//            intent.putExtra("meal", meal);
//            context.startActivity(intent);
//        });
//        holder.addMealToCalenderImageView.setOnClickListener(e -> {
//            if (!meal.getDay().equals("temp")) {
//                Intent i = new Intent(Intent.ACTION_INSERT);
//                i.setData(CalendarContract.Events.CONTENT_URI);
//                i.putExtra(CalendarContract.Events.TITLE, meal.getStrMeal() + " is the meal of the day");
//                i.putExtra(CalendarContract.Events.DESCRIPTION, " ");
//                SharedPreferences sharedPreferences = context.getSharedPreferences(LoginActivity.PREF, Context.MODE_PRIVATE);
//                i.putExtra(Intent.EXTRA_EMAIL, sharedPreferences.getString(LoginActivity.EMAIL, " "));
//                if (i.resolveActivity(context.getPackageManager()) != null)
//                    context.startActivity(i);
//                else Toast.makeText(context, "Can't save the event..", Toast.LENGTH_SHORT).show();
//
//            } else {
//                Toast.makeText(context, "Error to save this Event", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return allMeals.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mealNameTextView;
        ImageView deleteMealImageViewPlannerCard, mealImageViewPlannerCard, addMealToCalenderImageView;
        ConstraintLayout planCardConstraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            deleteMealImageViewPlannerCard = itemView.findViewById(R.id.icon_remove_item_fav);
            mealImageViewPlannerCard = itemView.findViewById(R.id.imageView_item_fav);
            mealNameTextView = itemView.findViewById(R.id.textView_meal_title_item_fav);
            planCardConstraintLayout = itemView.findViewById(R.id.cardViewRandomMeal);
            //addMealToCalenderImageView = itemView.findViewById(R.id.addMealToCalenderImageView);

        }
    }


}
