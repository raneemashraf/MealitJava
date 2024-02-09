package com.example.mealitjava.localDataSource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import androidx.room.OnConflictStrategy;

import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.PlannerModel;

import java.util.List;

@Dao
public interface MealDao {
       @Query("SELECT * FROM meal where dateModified = 'fav' ")
        LiveData<List<MealsItem>> getAllFavoriteMeals();
       @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insertMealToFavorite(MealsItem meal);
        @Delete
        void deleteMealFromFavorite(MealsItem meal);


    @Query("SELECT * FROM weekPlan where date =:date ")
    LiveData<List<PlannerModel>> getAllPlannerMeals(String date);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMealToPlanner(PlannerModel meal);
    @Delete
    void deleteMealFromPlanner(PlannerModel meal);

    @Query("DELETE FROM meal")
    void deleteAllMeals();
    @Query("DELETE FROM weekPlan")
    void deleteAllPlanner();

}
