package com.example.mealitjava.remoteDataSource.api;

import com.example.mealitjava.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiMethods {
    @GET("random.php")
    Call<MealResponse> getRandomMeal();
}
