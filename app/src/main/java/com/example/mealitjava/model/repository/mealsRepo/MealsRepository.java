package com.example.mealitjava.model.repository.mealsRepo;

import com.example.mealitjava.remotesource.api.MealsCallback;

public interface MealsRepository {
    void getRandomMeal(MealsCallback mealsCallback);
}
