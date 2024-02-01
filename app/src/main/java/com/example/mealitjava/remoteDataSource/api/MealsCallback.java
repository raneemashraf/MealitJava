package com.example.mealitjava.remoteDataSource.api;

import com.example.mealitjava.model.MealsItem;

public interface MealsCallback {
     void onSuccessResult(MealsItem mealsItem);
     void onFailureResult(String errorMessage);
}
