package com.example.mealitjava.remotesource.api;

import com.example.mealitjava.model.MealsItem;

import java.util.List;

public interface MealsCallback {
     void onSuccessResult(MealsItem mealsItem);
     void onFailureResult(String errorMessage);
}
