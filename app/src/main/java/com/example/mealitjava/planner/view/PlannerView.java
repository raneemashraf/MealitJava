package com.example.mealitjava.planner.view;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.PlannerModel;

import java.util.List;

public interface PlannerView {
    public void showPlanner(LiveData<List<PlannerModel>> meal);
    public void deletePlannerMeal(PlannerModel meal);
}
