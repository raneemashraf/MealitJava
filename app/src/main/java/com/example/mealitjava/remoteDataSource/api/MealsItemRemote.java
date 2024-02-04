package com.example.mealitjava.remoteDataSource.api;

import com.example.mealitjava.remoteDataSource.CategoryCallBack;
import com.example.mealitjava.remoteDataSource.NetworkCallback;

public interface MealsItemRemote {
    public void makeNetworkCall(NetworkCallback mealsCallback);
    public void makeCategoryCall(CategoryCallBack categoryCallBack);
    public void makeCountryCall(NetworkCallback countryCallBack);

    void makeCallFilter(NetworkCallback filterCallBack, String name, char c);
    void makeCallBySearch(NetworkCallback searchCallBack, String search);

}
