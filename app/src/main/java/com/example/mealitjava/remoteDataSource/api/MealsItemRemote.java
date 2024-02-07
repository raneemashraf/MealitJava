package com.example.mealitjava.remoteDataSource.api;

import com.example.mealitjava.remoteDataSource.NetworkCallback;

public interface MealsItemRemote {
    public void makeNetworkCall(NetworkCallback mealsCallback);
    public void makeCategoryCall(NetworkCallback categoryCallBack);
    public void makeCountryCall(NetworkCallback countryCallBack);
    public void makeIngredientCall(NetworkCallback ingredientCallBack);
    void makeCallFilter(NetworkCallback filterCallBack, String name, char c);
    void makeCallBySearch(NetworkCallback searchCallBack, String search);

}
