package com.example.mealitjava.planner.view;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealitjava.R;
import com.example.mealitjava.databinding.FragmentPlannerBinding;
import com.example.mealitjava.localDataSource.MealLocalSourceImpl;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.planner.presenter.PlannerPresenterImpl;
import com.example.mealitjava.planner.presenter.PlannerPresenterInterface;
import com.example.mealitjava.remoteDataSource.api.MealsItemRemoteImpl;

import java.util.ArrayList;
import java.util.List;

public class PlannerFragment extends Fragment implements PlannerClickListener,PlannerViewInterface{
    FragmentPlannerBinding binding;
    private DayAdapter
            wednesdayAdapter, saturdayAdapter,
            sundayAdapter, mondayAdapter, tuesdayAdapter, thursdayAdapter, fridayAdapter;

    private List<MealsItem> allMeals;
    public static Dialog searchDialog;
    private String day;
    private PlannerPresenterInterface planPresenterInterface;

    public PlannerFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPlannerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        planPresenterInterface = new PlannerPresenterImpl(this,
                new MealsRepositoryImpl(new MealsItemRemoteImpl(), new MealLocalSourceImpl(getContext())));
        allMeals = new ArrayList<>();
        day = null;
        saturdayAdapter = new DayAdapter(getContext(), PlannerFragment.this);
        sundayAdapter = new DayAdapter(getContext(), PlannerFragment.this);
        mondayAdapter = new DayAdapter(getContext(), PlannerFragment.this);
        tuesdayAdapter = new DayAdapter(getContext(), PlannerFragment.this);
        thursdayAdapter = new DayAdapter(getContext(), PlannerFragment.this);
        wednesdayAdapter = new DayAdapter(getContext(), PlannerFragment.this);
        fridayAdapter = new DayAdapter(getContext(), PlannerFragment.this);


        planPresenterInterface.getPlannedDayMeals("saturday")
                .observe((LifecycleOwner) getContext(), new Observer<List<MealsItem>>() {
                    @Override
                    public void onChanged(List<MealsItem> meals) {
                        saturdayAdapter.setAllMeals(meals);
                        binding.saturdayRecyclerView.setAdapter(saturdayAdapter);
                        saturdayAdapter.notifyDataSetChanged();

                    }
                });

        planPresenterInterface.getPlannedDayMeals("sunday").observe((LifecycleOwner) getContext(), new Observer<List<MealsItem>>() {
            @Override
            public void onChanged(List<MealsItem> meals) {
                sundayAdapter.setAllMeals(meals);
              binding.sundayRecyclerView.setAdapter(sundayAdapter);
                sundayAdapter.notifyDataSetChanged();

            }
        });

        planPresenterInterface.getPlannedDayMeals("monday").observe((LifecycleOwner) getContext(),
                new Observer<List<MealsItem>>() {
            @Override
            public void onChanged(List<MealsItem> meals) {
                mondayAdapter.setAllMeals(meals);
             binding.mondayRecyclerView.setAdapter(mondayAdapter);
                mondayAdapter.notifyDataSetChanged();

            }
        });

        planPresenterInterface.getPlannedDayMeals("tuesday").observe((LifecycleOwner) getContext(), new Observer<List<MealsItem>>() {
            @Override
            public void onChanged(List<MealsItem> meals) {
                tuesdayAdapter.setAllMeals(meals);
              binding.tuesdayRecyclerView.setAdapter(tuesdayAdapter);
                tuesdayAdapter.notifyDataSetChanged();

            }
        });
        planPresenterInterface.getPlannedDayMeals("wednesday").observe((LifecycleOwner) getContext(), new Observer<List<MealsItem>>() {
            @Override
            public void onChanged(List<MealsItem> meals) {
                wednesdayAdapter.setAllMeals(meals);
               binding.wednesdayRecyclerView.setAdapter(wednesdayAdapter);
                wednesdayAdapter.notifyDataSetChanged();

            }
        });
        planPresenterInterface.getPlannedDayMeals("thursday")
                .observe((LifecycleOwner) getContext(), new Observer<List<MealsItem>>() {
                    @Override
                    public void onChanged(List<MealsItem> meals) {
                        thursdayAdapter.setAllMeals(meals);
                      binding.thursdayRecyclerView.setAdapter(thursdayAdapter);
                        thursdayAdapter.notifyDataSetChanged();

                    }
                });

        planPresenterInterface.getPlannedDayMeals("friday")
                .observe((LifecycleOwner) getContext(), new Observer<List<MealsItem>>() {
                    @Override
                    public void onChanged(List<MealsItem> meals) {
                        fridayAdapter.setAllMeals(meals);
                       binding.fridayRecyclerView.setAdapter(fridayAdapter);
                        fridayAdapter.notifyDataSetChanged();

                    }
                });

        binding.saturdayAddMaterialButton.setOnClickListener(v -> {
            day = "saturday";
            showDialog(day);
        });
        binding.sundayAddMaterialButton.setOnClickListener(v -> {
            day = "sunday";
            showDialog(day);
        });
        binding.mondayAddMaterialButton.setOnClickListener(v -> {
            day = "monday";
            showDialog(day);

        });
        binding.tuesdayAddMaterialButton.setOnClickListener(v -> {
            day = "tuesday";
            showDialog(day);

        });
        binding.wednesdayAddMaterialButton.setOnClickListener(v -> {
            day = "wednesday";
            showDialog(day);
        });
        binding.thursdayAddMaterialButton.setOnClickListener(v -> {
            day = "thursday";
            showDialog(day);

        });
        binding.fridayAddMaterialButton.setOnClickListener(v -> {
            day = "friday";
            showDialog(day);

        });

    }

    public void showDialog(String day) {
        View dialogLayout = LayoutInflater.from(getContext()).inflate(R.layout.dialog_search, null);
        searchDialog = new Dialog(getContext());
        searchDialog.setContentView(dialogLayout);
        searchDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        searchDialog.show();
        RecyclerView searchResultCardRecyclerView = searchDialog.findViewById(R.id.searchResultCardRecyclerView);
        DialogSearchAdapter dialogSearchAdapter = new DialogSearchAdapter(getContext(), PlannerFragment.this, day);

        EditText searchDialogEditText = searchDialog.findViewById(R.id.searchDialogEditText);

        searchDialogEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planPresenterInterface.getSearchedMeals(charSequence + "");
                try {
                    Thread.sleep(700);

                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                dialogSearchAdapter.setAllMeals(allMeals);
                searchResultCardRecyclerView.setAdapter(dialogSearchAdapter);
                searchResultCardRecyclerView.setVisibility(View.VISIBLE);
                dialogSearchAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void showPlannerMeals(List<MealsItem> meals) {
        if (meals != null) {
            allMeals.clear();
            for (MealsItem meal : meals)
                allMeals.add(meal);
        }
    }
    @Override
    public void addMealToDay(MealsItem meal) {
        planPresenterInterface.addMealPlanner(meal);
    }

    @Override
    public void deleteMealFromDay(MealsItem meal) {
        planPresenterInterface.deletePlannerMeal(meal);
        saturdayAdapter.notifyDataSetChanged();
        sundayAdapter.notifyDataSetChanged();
        mondayAdapter.notifyDataSetChanged();
        tuesdayAdapter.notifyDataSetChanged();
        wednesdayAdapter.notifyDataSetChanged();
        thursdayAdapter.notifyDataSetChanged();
        fridayAdapter.notifyDataSetChanged();
    }

}