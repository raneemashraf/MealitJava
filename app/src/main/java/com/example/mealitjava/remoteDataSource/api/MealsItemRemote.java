package com.example.mealitjava.remoteDataSource.api;

import com.example.mealitjava.remoteDataSource.CategoryCallBack;
import com.example.mealitjava.remoteDataSource.MealsCallback;

public interface MealsItemRemote {
    public void makeNetworkCall(MealsCallback mealsCallback);
    public void makeCategoryCall(CategoryCallBack categoryCallBack);

}
