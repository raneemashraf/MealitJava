package com.example.mealitjava.MealDetails.presenter;

import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.PlannerModel;

public interface MealDetailsPresenter {
    public void getMeals();
    public void addMealToFavorite(MealsItem meal);

    public void addMealToPlanner(PlannerModel meal);
}
