package com.example.mealitjava.planner.presenter;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.MealsItem;

import java.util.List;

public interface PlannerPresenterInterface {

    void getSearchedMeals(String search);

    void addMealPlanner(MealsItem meal);

    void deletePlannerMeal(MealsItem meal);

    LiveData<List<MealsItem>> getPlannedDayMeals(String day);
}
