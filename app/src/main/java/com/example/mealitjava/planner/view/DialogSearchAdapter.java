package com.example.mealitjava.planner.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealitjava.R;
import com.example.mealitjava.model.MealsItem;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;


public class DialogSearchAdapter extends RecyclerView.Adapter<DialogSearchAdapter.ViewHolder> {

    private static final String TAG = "DialogSearchAdapter";
    private List<MealsItem> allMeals;
    private Context context;
    private PlannerClickListener allMealsClickListener;
    private String day;

    public DialogSearchAdapter(Context context, PlannerClickListener allMealsClickListener, String day) {
        super();
        this.context = context;
//        this.allMeals = new ArrayList<>();
        this.day = day;
        this.allMealsClickListener = allMealsClickListener;
    }

    public void setAllMeals(List<MealsItem> allMeals) {
        this.allMeals = allMeals;
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
        Glide.with(context).load(allMeals.get(position).strMealThumb).apply(new RequestOptions().override(200, 160)).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(holder.mealSearchedCardImageView);

        holder.searchCardConstraintLayout.setOnClickListener(e -> {
            LocalDate localDate = LocalDate.now();
            Log.d(TAG, "onBindViewHolder: localDate " + localDate.getDayOfWeek());
            Calendar calendar = Calendar.getInstance();
//            Set<DayOfWeek> days = EnumSet.range(localDate.getDayOfWeek(), DayOfWeek.FRIDAY);
//            Log.d(TAG, "onBindViewHolder: "+days.size());
//            calendar.add(Calendar.DATE,days.size() );
//
//            switch (localDate.getDayOfWeek()) {
//                case SATURDAY:
//                    calendar.add(Calendar.DATE, numberOfDays("saturday"));
//                    break;
//                case SUNDAY:
//                    calendar.add(Calendar.DATE, numberOfDays("sunday"));
//                    break;
//
//                case MONDAY:
//                    calendar.add(Calendar.DATE, numberOfDays("monday"));
//                    break;
//
//                case TUESDAY:
//                    calendar.add(Calendar.DATE, numberOfDays("tuesday"));
//                    break;
//
//                case WEDNESDAY:
//                    calendar.add(Calendar.DATE, numberOfDays("wednesday"));
//                    break;
//
//                case THURSDAY:
//                    calendar.add(Calendar.DATE, numberOfDays("thursday"));
//                    break;
//
//                case FRIDAY:
//                    calendar.add(Calendar.DATE, numberOfDays("friday"));
//                    break;
//
//            }
//            Log.d(TAG, "onBindViewHolder: calender " + calendar.getTime());
//            meal.setDate();
           // meal.idMeal(Integer.parseInt(meal.getIdMeal()));
            meal.setDay(day);
            allMealsClickListener.addMealToDay(meal);
            Toast.makeText(context, meal.strMeal + " is added to " + meal.getDay().toUpperCase(), Toast.LENGTH_LONG).show();
            PlannerFragment.searchDialog.dismiss();
        });

    }

    @Override
    public int getItemCount() {
        return allMeals.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mealSearchedNameCardTextView;
        ImageView mealSearchedCardImageView;
        ConstraintLayout searchCardConstraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mealSearchedCardImageView = itemView.findViewById(R.id.mealSearchedCardImageView);
            mealSearchedNameCardTextView = itemView.findViewById(R.id.mealSearchedNameCardTextView);
            searchCardConstraintLayout = itemView.findViewById(R.id.searchCardConstraintLayout);


        }
    }

    public int numberOfDays(String dayy) {
        Log.d(TAG, "numberOfDays: " + day);
        int number = 0;
        if (day.equals("saturday")) {

            if (dayy.equals("saturday")) {
                number = 0;
            } else if (dayy.equals("sunday")) {
                number = 1;
            } else if (dayy.equals("monday")) {
                number = 2;
            } else if (dayy.equals("tuesday")) {
                number = 3;
            } else if (dayy.equals("wednesday")) {
                number = 4;
            } else if (dayy.equals("thursday")) {
                number = 5;
            } else if (dayy.equals("friday")) {
                number = 6;
            }
        } else if (day.equals("sunday")) {
            if (dayy.equals("saturday")) {
                number = 6;
            } else if (dayy.equals("sunday")) {
                number = 0;
            } else if (dayy.equals("monday")) {
                number = 1;
            } else if (dayy.equals("tuesday")) {
                number = 2;
            } else if (dayy.equals("wednesday")) {
                number = 3;
            } else if (dayy.equals("thursday")) {
                number = 4;
            } else if (dayy.equals("friday")) {
                number = 5;
            }
        } else if (day.equals("monday")) {
            if (dayy.equals("saturday")) {
                number = 5;
            } else if (dayy.equals("sunday")) {
                number = 6;
            } else if (dayy.equals("monday")) {
                number = 0;
            } else if (dayy.equals("tuesday")) {
                number = 1;
            } else if (dayy.equals("wednesday")) {
                number = 2;
            } else if (dayy.equals("thursday")) {
                number = 3;
            } else if (dayy.equals("friday")) {
                number = 4;
            }
        } else if (day.equals("tuesday")) {
            if (dayy.equals("saturday")) {
                number = 4;
            } else if (dayy.equals("sunday")) {
                number = 5;
            } else if (dayy.equals("monday")) {
                number = 6;
            } else if (dayy.equals("tuesday")) {
                number = 0;
            } else if (dayy.equals("wednesday")) {
                number = 1;
            } else if (dayy.equals("thursday")) {
                number = 2;
            } else if (dayy.equals("friday")) {
                number = 3;
            }
        } else if (day.equals("wednesday")) {
            if (dayy.equals("saturday")) {
                number = 3;
            } else if (dayy.equals("sunday")) {
                number = 4;
            } else if (dayy.equals("monday")) {
                number = 5;
            } else if (dayy.equals("tuesday")) {
                number = 6;
            } else if (dayy.equals("wednesday")) {
                number = 0;
            } else if (dayy.equals("thursday")) {
                number = 1;
            } else if (dayy.equals("friday")) {
                number = 2;
            }
        } else if (day.equals("thursday")) {
            if (dayy.equals("saturday")) {
                number = 2;
            } else if (dayy.equals("sunday")) {
                number = 3;
            } else if (dayy.equals("monday")) {
                number = 4;
            } else if (dayy.equals("tuesday")) {
                number = 5;
            } else if (dayy.equals("wednesday")) {
                number = 6;
            } else if (dayy.equals("thursday")) {
                number = 0;
            } else if (dayy.equals("friday")) {
                number = 1;
            }
        } else if (day.equals("friday")) {
            if (dayy.equals("saturday")) {
                number = 1;
            } else if (dayy.equals("sunday")) {
                number = 2;
            } else if (dayy.equals("monday")) {
                number = 3;
            } else if (dayy.equals("tuesday")) {
                number = 4;
            } else if (dayy.equals("wednesday")) {
                number = 5;
            } else if (dayy.equals("thursday")) {
                number = 6;
            } else if (dayy.equals("friday")) {
                number = 0;
            }
        }
        Log.d(TAG, "numberOfDays: " + number);
        return number;
    }
}