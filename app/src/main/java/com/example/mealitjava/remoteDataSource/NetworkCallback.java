package com.example.mealitjava.remoteDataSource;

import com.example.mealitjava.model.Category;
import com.example.mealitjava.model.Country;
import com.example.mealitjava.model.IngredientList;
import com.example.mealitjava.model.MealResponse;
import com.example.mealitjava.model.MealsItem;

import java.util.List;

public interface NetworkCallback {
     void onSuccessResult(MealsItem mealsItem);
     void onFailureResult(String errorMessage);
     void onSuccessMealByFilter(MealResponse meals);
     public void onSuccessSearch(List<MealsItem> meals);
     void onSuccessCountry(List<Country> countries);
     void onSuccessCategory(List<Category> category);
     public void onSuccessIngredient(List<IngredientList> ingredientLists);
     public void onSuccessPlanner(List<MealsItem> meals);

     }
