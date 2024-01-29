package com.example.mealitjava.model.repository.mealsRepo;

import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.remotesource.api.MealsCallback;
import com.example.mealitjava.remotesource.api.MealsItemRemote;
import com.example.mealitjava.remotesource.api.MealsItemRemoteImpl;

import java.util.List;

public class MealsRepositoryImpl implements MealsRepository{
    static MealsRepositoryImpl mealsRepositoryObj;
    MealsItemRemote mealsItemRemote;

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
}
