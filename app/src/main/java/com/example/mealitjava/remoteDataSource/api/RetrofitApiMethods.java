package com.example.mealitjava.remoteDataSource.api;

import com.example.mealitjava.model.CategoryResponse;
import com.example.mealitjava.model.CountryResponse;
import com.example.mealitjava.model.MealResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApiMethods {
    @GET("random.php")
    Observable<MealResponse> getRandomMeal();
    @GET("categories.php")
    Observable<CategoryResponse> getCategories();
    @GET("list.php?a=list")
    Observable<CountryResponse> getCountry();

//    @GET("api/json/v1/1/list.php?i=list")
//    Single<IngredientResponse> getAllIngredients();

    @GET("filter.php?")
    Single<MealResponse> getAllMealsByArea(@Query("a") String area);

    @GET("filter.php?")
    Single<MealResponse> getAllMealsByIngredient(@Query("i") String ingredient);

    @GET("filter.php?")
    Single<MealResponse> getAllMealsByCategory(@Query("c") String category);

    @GET("search.php?")
    Single<MealResponse> getAllMealsBySearch(@Query("s") String name);





}
