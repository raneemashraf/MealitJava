package com.example.mealitjava.MealDetails.presenter;

import com.example.mealitjava.model.MealsItem;

public interface MealDetailsPresenter {
    public void getMeals();
    public void addMealToFavorite(MealsItem meal);
}
