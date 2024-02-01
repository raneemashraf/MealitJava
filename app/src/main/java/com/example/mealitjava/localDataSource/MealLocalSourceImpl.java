package com.example.mealitjava.localDataSource;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.MealsItem;

import java.util.List;

public class MealLocalSourceImpl implements MealLocalSource{
    private MealDao mealDao;
    private LiveData<List<MealsItem>> favoriteMeals;
    private static MealLocalSourceImpl mealLocalSource = null;

    public MealLocalSourceImpl(Context ctx) {
        DataBase dataBase = DataBase.getInstance(ctx);
        mealDao = dataBase.mealDao();
        favoriteMeals = mealDao.getAllFavoriteMeals();
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
    public LiveData<List<MealsItem>> getFavoriteMeals() {
        return favoriteMeals;
    }
}
