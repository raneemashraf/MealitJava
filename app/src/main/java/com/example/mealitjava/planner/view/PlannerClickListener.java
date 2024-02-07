package com.example.mealitjava.planner.view;

import com.example.mealitjava.model.MealsItem;

public interface PlannerClickListener {

    void addMealToDay(MealsItem meal);

    void deleteMealFromDay(MealsItem meal);
}
