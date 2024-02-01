package com.example.mealitjava.model.repository.mealsRepo;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.localDataSource.MealLocalSource;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.remoteDataSource.api.MealsCallback;
import com.example.mealitjava.remoteDataSource.api.MealsItemRemote;

import java.util.List;

public class MealsRepositoryImpl implements MealsRepository{
    static MealsRepositoryImpl mealsRepositoryObj;
    MealsItemRemote mealsItemRemote;
    MealLocalSource mealLocalSource;

    public MealsRepositoryImpl(MealsItemRemote mealsItemRemote) {
        this.mealsItemRemote = mealsItemRemote;
    }

    public static MealsRepositoryImpl getInstance(MealsItemRemote mealsItemRemote)
    {
        if(mealsRepositoryObj == null){
            mealsRepositoryObj = new MealsRepositoryImpl(mealsItemRemote);
        }
        return mealsRepositoryObj;
    }

    @Override
    public void getRandomMeal(MealsCallback mealsCallback) {
       mealsItemRemote.makeNetworkCall(mealsCallback);
    }

    @Override
    public void insertProductToFavorite(MealsItem meal) {
        mealLocalSource.insertMealToFavorite(meal);
    }

    @Override
    public void deleteFavoriteProduct(MealsItem meal) {
        mealLocalSource.deleteFavoriteMeal(meal);
    }

    @Override
    public LiveData<List<MealsItem>> getFavProducts() {
        return mealLocalSource.getFavoriteMeals();
    }
}
