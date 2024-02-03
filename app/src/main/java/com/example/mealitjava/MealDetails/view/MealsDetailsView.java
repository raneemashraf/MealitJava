package com.example.mealitjava.MealDetails.view;

import com.example.mealitjava.model.MealsItem;

public interface MealsDetailsView {
    public void showData(MealsItem meal);
    public void showErrorMessage(String error);
    public void addMealsToFav(MealsItem mealsItem);
}
