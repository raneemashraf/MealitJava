package com.example.mealitjava.planner.presenter;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.PlannerModel;

import java.util.List;

public interface PlannerPresenterInterface {
    //public void showPlannedMeals();
    //LiveData<List<MealsItem>> showPlannedMealsByDate(String date);
    public void deletePlannerMeal(PlannerModel meal);
    LiveData<List<PlannerModel>> getPlanedMeals(String d);
}
