package com.example.mealitjava.remotesource.api;

import com.example.mealitjava.remotesource.api.MealsCallback;

public interface MealsItemRemote {
    public void makeNetworkCall(MealsCallback mealsCallback);
}
