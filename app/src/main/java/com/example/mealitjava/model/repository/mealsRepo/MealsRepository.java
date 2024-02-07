package com.example.mealitjava.model.repository.mealsRepo;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.remoteDataSource.NetworkCallback;

import java.util.List;

public interface MealsRepository {
    void getCategory(NetworkCallback categoryCallBack);
    void getCountry(NetworkCallback countryCallBack);
    void getIngredients(NetworkCallback ingredientCallBack);
    void getRandomMeal(NetworkCallback mealsCallback);
    void insertMealToFavorite(MealsItem meal);
    void deleteFavoriteMeal(MealsItem meal);
    LiveData<List<MealsItem>> getFavMeals();
    void getFilteredMeals(NetworkCallback filterCallback, String name, char c);

    void getSearchedMeals(NetworkCallback searchCallback, String search);

    void removeMealPlanned(MealsItem meal);
    LiveData<List<MealsItem>> getMealsByDayDB(String day);


}
