package com.example.mealitjava.remotesource.api;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.mealitjava.model.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsItemRemoteImpl implements MealsItemRemote{
    static MealsItemRemoteImpl mealsItemRemoteObj;
    RetrofitApiMethods apiMethods;

    public MealsItemRemoteImpl() {
        this.apiMethods = RetrofitApi.getApi();
    }
    public static MealsItemRemoteImpl getInstance(){
        if(mealsItemRemoteObj == null){
            mealsItemRemoteObj = new MealsItemRemoteImpl();
        }
        return mealsItemRemoteObj;
    }
    @Override
    public void makeNetworkCall(MealsCallback mealsCallback) {
        apiMethods.getRandomMeal().enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                mealsCallback.onSuccessResult(response.body().getMeals().get(0));
                Log.i(TAG, "onResponse: " + response);
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                mealsCallback.onFailureResult(t.getMessage());
            }
        });
    }
}
