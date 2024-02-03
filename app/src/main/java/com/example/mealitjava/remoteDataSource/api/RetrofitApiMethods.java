package com.example.mealitjava.remoteDataSource.api;

import com.example.mealitjava.model.CategoryResponse;
import com.example.mealitjava.model.CountryResponse;
import com.example.mealitjava.model.MealResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface RetrofitApiMethods {
    @GET("random.php")
    Observable<MealResponse> getRandomMeal();
    @GET("categories.php")
    Observable<CategoryResponse> getCategories();
    @GET("list.php?a=list")
    Observable<CountryResponse> getCuisines();
}
