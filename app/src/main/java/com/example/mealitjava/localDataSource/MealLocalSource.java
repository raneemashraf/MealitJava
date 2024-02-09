package com.example.mealitjava.localDataSource;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.PlannerModel;

import java.util.List;

public interface MealLocalSource {
    void insertMealToFavorite(MealsItem meal);
    void deleteFavoriteMeal(MealsItem meal);
    LiveData<List<MealsItem>> getFavoriteMeals();

    void insertMealToPlanner(PlannerModel meal);
    void deletePlannerMeal(PlannerModel meal);
    LiveData<List<PlannerModel>> getPlannerMeals(String d);

    void deleteAllMeals();
    void deleteAllPlanner();
}
