package com.example.mealitjava.Home.view;

import com.example.mealitjava.model.MealsItem;

public interface HomeInterfaceView {
    public void showMeal(MealsItem mealsItem);
    public void showError(String err);
}
