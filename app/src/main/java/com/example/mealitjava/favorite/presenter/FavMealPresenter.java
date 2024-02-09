package com.example.mealitjava.favorite.presenter;

import com.example.mealitjava.model.MealsItem;

public interface FavMealPresenter {
    public void getMeals();
    public void deleteFavoriteMeal(MealsItem meal);
}
