package com.example.mealitjava.localDataSource;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.PlannerModel;

import java.util.List;

public class MealLocalSourceImpl implements MealLocalSource {
    private MealDao mealDao;
    private LiveData<List<MealsItem>> favoriteMeals;
    private LiveData<List<PlannerModel>> plannedMeals;
    private static MealLocalSourceImpl mealLocalSource = null;

    public MealLocalSourceImpl(Context ctx,String d) {
        DataBase dataBase = DataBase.getInstance(ctx);
        mealDao = dataBase.mealDao();
        favoriteMeals = mealDao.getAllFavoriteMeals();
        plannedMeals = mealDao.getAllPlannerMeals(d);
    }

    public MealLocalSourceImpl(Context ctx) {
        DataBase dataBase = DataBase.getInstance(ctx);
        mealDao = dataBase.mealDao();
    }

    public static MealLocalSourceImpl getInstance(Context context,String d){
        if(mealLocalSource == null){
            mealLocalSource = new MealLocalSourceImpl(context,d);
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
    public void insertMealToPlanner(PlannerModel meal) {
        new Thread(() -> mealDao.insertMealToPlanner(meal)){
        }.start();
    }

    @Override
    public void deletePlannerMeal(PlannerModel meal) {
        new Thread(() -> mealDao.deleteMealFromPlanner(meal)){
        }.start();
    }

    @Override
    public LiveData<List<PlannerModel>> getPlannerMeals(String d) {
        return  mealDao.getAllPlannerMeals(d);
    }

    @Override
    public void deleteAllMeals() {
        new Thread(() -> mealDao.deleteAllMeals()){
        }.start();
    }

    @Override
    public void deleteAllPlanner() {
        new Thread(() -> mealDao.deleteAllPlanner()){
        }.start();
    }
}
