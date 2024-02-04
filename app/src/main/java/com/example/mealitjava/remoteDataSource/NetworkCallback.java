package com.example.mealitjava.remoteDataSource;

import com.example.mealitjava.model.Country;
import com.example.mealitjava.model.MealsItem;

import java.util.List;

public interface NetworkCallback {
     void onSuccessResult(MealsItem mealsItem);
     void onFailureResult(String errorMessage);
     void onSuccessCountry(List<Country> countries);
     void onSuccessMealByFilter(List<MealsItem> meals);
     public void onSuccessSearch(List<MealsItem> meals);


}
