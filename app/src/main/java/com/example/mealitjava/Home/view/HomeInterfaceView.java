package com.example.mealitjava.Home.view;

import com.example.mealitjava.model.Category;
import com.example.mealitjava.model.MealsItem;

import java.util.List;

public interface HomeInterfaceView {
    public void showCategories(List<Category> category);
    public void showMeal(MealsItem mealsItem);
    public void showError(String err);

}
