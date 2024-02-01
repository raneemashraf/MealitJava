package com.example.mealitjava.model.repository.mealsRepo;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.remoteDataSource.api.MealsCallback;

import java.util.List;

public interface MealsRepository {
    void getRandomMeal(MealsCallback mealsCallback);
    void insertProductToFavorite(MealsItem meal);
    void deleteFavoriteProduct(MealsItem meal);
    LiveData<List<MealsItem>> getFavProducts();
}
