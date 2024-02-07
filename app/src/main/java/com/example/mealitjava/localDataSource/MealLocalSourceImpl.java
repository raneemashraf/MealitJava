package com.example.mealitjava.localDataSource;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class MealLocalSourceImpl implements MealLocalSource{
    private static MealLocalSourceImpl mealLocalSource = null;
    private MealDao mealDao;
    private LiveData<List<MealsItem>> favoriteMeals,
            saturdayMeals, sundayMeals, mondayMeals, tuesdayMeals,
            wednesdayMeals, thursdayMeals, fridayMeals;
    ;

    public MealLocalSourceImpl(Context ctx) {
        DataBase dataBase = DataBase.getInstance(ctx);
        mealDao = dataBase.mealDao();
        favoriteMeals = mealDao.getAllFavoriteMeals();

        saturdayMeals = mealDao.getMealsByDay("saturday");
        sundayMeals = mealDao.getMealsByDay("sunday");
        mondayMeals = mealDao.getMealsByDay("monday");
        tuesdayMeals = mealDao.getMealsByDay("tuesday");
        thursdayMeals = mealDao.getMealsByDay("thursday");
        wednesdayMeals = mealDao.getMealsByDay("wednesday");
        fridayMeals = mealDao.getMealsByDay("friday");
    }
    public static MealLocalSourceImpl getInstance(Context context){
        if(mealLocalSource == null){
            mealLocalSource = new MealLocalSourceImpl(context);
        }
        return mealLocalSource;
    }
    @Override
    public void insertMealToFavorite(MealsItem meal) {
        new Thread(() -> mealDao.insertMealToFavorite(meal)){
        }.start();
    }
    @Override
    public void deleteFavoriteMeal(MealsItem meal) {
        new Thread(() -> mealDao.deleteMealFromFavorite(meal)){
        }.start();
    }
    @Override
    public LiveData<List<MealsItem>> getFavoriteMeals(){
        return favoriteMeals;
    }

    @Override
    public Completable deletePlannedMeal(MealsItem meal) {
        return mealDao.deleteFromPlan(meal);
    }

    @Override
    public LiveData<List<MealsItem>> getStoredMealsByDay(String day) {
        switch (day) {
            case "saturday":
                return saturdayMeals;
            case "sunday":
                return sundayMeals;
            case "monday":
                return mondayMeals;
            case "tuesday":
                return tuesdayMeals;
            case "wednesday":
                return wednesdayMeals;
            case "thursday":
                return thursdayMeals;
            case "friday":
                return fridayMeals;
            default:
                return null;

        }
    }
}
