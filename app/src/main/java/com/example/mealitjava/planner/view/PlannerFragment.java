package com.example.mealitjava.planner.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealitjava.InternetConnection;
import com.example.mealitjava.MainActivity;
import com.example.mealitjava.R;
import com.example.mealitjava.localDataSource.MealLocalSourceImpl;
import com.example.mealitjava.model.DateFormatter;
import com.example.mealitjava.model.PlannerModel;
import com.example.mealitjava.model.repository.authRepo.AuthRepositoryImpl;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.planner.presenter.PlannerPresenterImpl;
import com.example.mealitjava.planner.presenter.PlannerPresenterInterface;
import com.example.mealitjava.remoteDataSource.api.MealsItemRemoteImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlannerFragment extends Fragment implements PlannerView{
    PlannerPresenterInterface plannerPresenterInterface;
    PlannerAdapter plannerAdapter;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    CardView calendarCard;
    TextView dateTextView;
    Calendar now;
    int year, month, datOfMonth;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    private InternetConnection internetConnection;

    public PlannerFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        internetConnection = new InternetConnection(requireContext());

        if (!AuthRepositoryImpl.getInstance().isAuthenticated()) {
            openGotoSignUpDialogue();
        } else {

            calendarCard = view.findViewById(R.id.card_date);
            dateTextView = view.findViewById(R.id.tv_clendar);
            recyclerView = view.findViewById(R.id.mealWeekPlan_recyclerView_weekplan);

            String date = DateFormatter.getString(new Date());
            plannerPresenterInterface = new PlannerPresenterImpl(
                    new MealsRepositoryImpl(new MealsItemRemoteImpl(),
                            new MealLocalSourceImpl(getContext(), date)), this);

            plannerAdapter = new PlannerAdapter(getContext(), new ArrayList<>());
            gridLayoutManager = new GridLayoutManager(getContext(), 2);
            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(plannerAdapter);

            plannerAdapter.onDeleteClickListener = this::deletePlannerMeal;
            dateTextView.setText(date);

            plannerPresenterInterface.getPlanedMeals(date).observe(getViewLifecycleOwner(),
                    new Observer<List<PlannerModel>>() {
                        @Override
                        public void onChanged(List<PlannerModel> plannerModels) {
                            plannerAdapter.setPlannerMealsList(plannerModels);
                        }
                    });
            calendarCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDatePickerDialog();
                }
            });
        }
    }

    private void showDatePickerDialog() {
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String d= DateFormatter.getString(year,month,dayOfMonth);
                dateTextView.setText(d);
                plannerPresenterInterface.getPlanedMeals(d).observe(getViewLifecycleOwner(),
                        new Observer<List<PlannerModel>>() {
                    @Override
                    public void onChanged(List<PlannerModel> planDtos) {
                        plannerAdapter.setPlannerMealsList(planDtos);

                    }
                });
            }
        };

        now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH);
        datOfMonth = now.get(Calendar.DAY_OF_MONTH);

        // tripDate = getView().findViewById(R.id.calenderTv);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                onDateSetListener, year, month, datOfMonth);
        datePickerDialog.setTitle("Please select a date.");
        datePickerDialog.show();
    }


    private void openGotoSignUpDialogue() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Sign Up Required")
                .setMessage("Please sign Up to access this feature.")
                .setPositiveButton(R.string.singup, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        startActivity(new Intent(requireActivity(), MainActivity.class));
                    }
                })
                .setNegativeButton(android.R.string.cancel, null).show();
    }

    @Override
    public void showPlanner(LiveData<List<PlannerModel>> meal) {
//        meal.observe(getViewLifecycleOwner(), new Observer<List<PlannerModel>>() {
//            @Override
//            public void onChanged(List<PlannerModel> plannerModels) {
//
//            }
//        });
    }


    @Override
    public void deletePlannerMeal(PlannerModel meal) {
        if (internetConnection.isConnectedWifi() || internetConnection.isConnectedMobile()){
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.Delete_Meal)
                    .setMessage(R.string.Are_you_sure_you_want_to_delete_this_meal)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            plannerPresenterInterface.deletePlannerMeal(meal);
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
        } else {
            Toast.makeText(requireContext(), "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
        }
    }

}