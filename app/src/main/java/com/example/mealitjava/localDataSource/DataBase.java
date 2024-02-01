package com.example.mealitjava.localDataSource;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealitjava.model.MealsItem;
    @Database(entities = {MealsItem.class } , version = 1,exportSchema = false)
    public abstract class DataBase extends RoomDatabase {
        public static final String DATABASE_NAME="databaseMeal";
        private static DataBase instance = null;
        public abstract MealDao mealDao();
        public static synchronized DataBase getInstance(Context context){
            if(instance == null){
                instance = Room.databaseBuilder(context.getApplicationContext(),DataBase.class,DATABASE_NAME).build();
            }
            return instance;
        }
    }
