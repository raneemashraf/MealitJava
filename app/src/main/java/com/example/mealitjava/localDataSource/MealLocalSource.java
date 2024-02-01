package com.example.mealitjava.localDataSource;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.MealsItem;

import java.util.List;

public interface MealLocalSource {
    void insertMealToFavorite(MealsItem meal);
    void deleteFavoriteMeal(MealsItem meal);
    LiveData<List<MealsItem>> getFavoriteMeals();
}
