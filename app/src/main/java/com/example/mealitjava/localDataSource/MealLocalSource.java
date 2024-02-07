package com.example.mealitjava.localDataSource;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public interface MealLocalSource {
    void insertMealToFavorite(MealsItem meal);
    void deleteFavoriteMeal(MealsItem meal);
    LiveData<List<MealsItem>> getFavoriteMeals();

    Completable deletePlannedMeal(MealsItem meal);
    LiveData<List<MealsItem>> getStoredMealsByDay(String day);

}
