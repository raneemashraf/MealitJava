package com.example.mealitjava.localDataSource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealitjava.model.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MealDao {
    @Query("SELECT * FROM meal")
    LiveData<List<MealsItem>> getAllFavoriteMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMealToFavorite(MealsItem meal);
    @Delete
    void deleteMealFromFavorite(MealsItem meal);


    @Query("SELECT * FROM meal where day = :day")
    LiveData<List<MealsItem>> getMealsByDay(String day);
    @Delete
    Completable deleteFromPlan(MealsItem meal);


//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void insertMealToPlanner(MealsItem meal);

}
