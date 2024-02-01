package com.example.mealitjava.localDataSource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import androidx.room.OnConflictStrategy;

import com.example.mealitjava.model.MealsItem;

import java.util.List;

@Dao
public interface MealDao {
       @Query("SELECT * FROM meal")
        LiveData<List<MealsItem>> getAllFavoriteMeals();
       @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insertMealToFavorite(MealsItem meal);
        @Delete
        void deleteMealFromFavorite(MealsItem meal);


}
