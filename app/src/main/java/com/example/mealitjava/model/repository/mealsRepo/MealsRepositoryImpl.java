package com.example.mealitjava.model.repository.mealsRepo;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.localDataSource.MealLocalSource;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.PlannerModel;
import com.example.mealitjava.remoteDataSource.NetworkCallback;
import com.example.mealitjava.remoteDataSource.api.MealsItemRemote;

import java.util.List;

public class MealsRepositoryImpl implements MealsRepository {
    static MealsRepositoryImpl mealsRepositoryObj;
    MealsItemRemote mealsItemRemote;
    MealLocalSource mealLocalSource;

    public MealsRepositoryImpl(MealsItemRemote mealsItemRemote ,MealLocalSource mealLocalSource) {
        this.mealsItemRemote = mealsItemRemote;
        this.mealLocalSource = mealLocalSource;
    }
    public MealsRepositoryImpl(MealsItemRemote mealsItemRemote ) {
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
    public void getCategory(NetworkCallback categoryCallBack) {
        mealsItemRemote.makeCategoryCall(categoryCallBack);
    }

    @Override
    public void getCountry(NetworkCallback countryCallBack) {
        mealsItemRemote.makeCountryCall(countryCallBack);
    }
    @Override
    public void getIngredients(NetworkCallback ingredientCallBack) {
        mealsItemRemote.makeIngredientCall(ingredientCallBack);
    }

    @Override
    public void getRandomMeal(NetworkCallback mealsCallback) {
       mealsItemRemote.makeNetworkCall(mealsCallback);
    }

    @Override
    public void insertMealToFavorite(MealsItem meal) {
        mealLocalSource.insertMealToFavorite(meal);
    }

    @Override
    public void deleteFavoriteMeal(MealsItem meal) {
        mealLocalSource.deleteFavoriteMeal(meal);
    }

    @Override
    public LiveData<List<MealsItem>> getFavMeals() {
        return mealLocalSource.getFavoriteMeals();
    }

    @Override
    public void insertMealToPlanner(PlannerModel meal) {
        mealLocalSource.insertMealToPlanner(meal);
    }

    @Override
    public void deletePlannerMeal(PlannerModel meal) {
        mealLocalSource.deletePlannerMeal(meal);
    }

    @Override
    public LiveData<List<PlannerModel>> getPlannerMeals(String d) {
        return mealLocalSource.getPlannerMeals(d);
    }

    @Override
    public void getFilteredMeals(NetworkCallback filterCallback, String name, char c) {
        mealsItemRemote.makeCallFilter(filterCallback,name, c);
    }

    @Override
    public void getSearchedMeals(NetworkCallback searchCallback, String search) {
        mealsItemRemote.makeCallBySearch(searchCallback,search);
    }

    @Override
    public void deleteAllMeals() {
        mealLocalSource.deleteAllMeals();
    }

}
