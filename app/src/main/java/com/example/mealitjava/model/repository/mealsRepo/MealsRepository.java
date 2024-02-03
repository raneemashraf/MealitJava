package com.example.mealitjava.model.repository.mealsRepo;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.remoteDataSource.CategoryCallBack;
import com.example.mealitjava.remoteDataSource.MealsCallback;

import java.util.List;

public interface MealsRepository {
    void getCategory(CategoryCallBack categoryCallBack);
    void getRandomMeal(MealsCallback mealsCallback);
    void insertMealToFavorite(MealsItem meal);
    void deleteFavoriteMeal(MealsItem meal);
    LiveData<List<MealsItem>> getFavMeals();
}
