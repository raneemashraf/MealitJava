package com.example.mealitjava.remoteDataSource.api;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.example.mealitjava.model.MealResponse;
import com.example.mealitjava.remoteDataSource.CategoryCallBack;
import com.example.mealitjava.remoteDataSource.NetworkCallback;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
    public void makeNetworkCall(NetworkCallback mealsCallback) {
        apiMethods.getRandomMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealResponse->{
                            mealsCallback.onSuccessResult(mealResponse.getMeals().get(0));
                            Log.i(TAG, "makeCategoryCall: " + mealResponse.getMeals());
                        },
                        error -> Log.i(TAG, "Error: " + error.toString())
                );

//        .enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                mealsCallback.onSuccessResult(response.body().getMeals().get(0));
//                Log.i("TAG", "onResponse: " + response);
//            }
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                mealsCallback.onFailureResult(t.getMessage());
//            }
//        });
    }
    @Override
    public void makeCategoryCall(CategoryCallBack categoryCallBack) {
        Disposable d1 = apiMethods.getCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryResponse -> {
                            categoryCallBack.onSuccessCategory(categoryResponse.getCategories());
                            Log.i(TAG, "makeCategoryCall: " + categoryResponse.getCategories());
                        },
                        error -> Log.i(TAG, "Error: " + error.toString())
                );
    }
    @Override
    public void makeCountryCall(NetworkCallback countryCallBack) {
        Disposable d2 = apiMethods.getCountry().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(countryResponse -> {
                            countryCallBack.onSuccessCountry(countryResponse.getCountries());
                            Log.i(TAG, "makeCountryCall: " + countryResponse.getCountries());
                        },
                        throwable -> countryCallBack.onFailureResult(throwable.getMessage()));

    }

    @Override
    public void makeCallFilter(NetworkCallback filterCallBack, String name, char c) {
            if(c == 'a') {
                Single<MealResponse> allMealsByArea = apiMethods.getAllMealsByArea(name);
                Disposable d2 = allMealsByArea.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(MealResponse -> {
                                    filterCallBack.onSuccessMealByFilter(MealResponse.getMeals());
                                    Log.i(TAG, "makeCountryCall: " + MealResponse.getMeals().get(0).strArea);},
                                throwable -> filterCallBack.onFailureResult(throwable.getMessage()));

            } else if (c == 'i') {
                Single<MealResponse> allMealsByIngredient = apiMethods.getAllMealsByIngredient(name);
                Disposable d2 = allMealsByIngredient.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(MealResponse -> filterCallBack.onSuccessMealByFilter(MealResponse.getMeals()),
                                throwable -> filterCallBack.onFailureResult(throwable.getMessage()));

            } else if (c == 'c') {
                Single<MealResponse> allMealsByCategory = apiMethods.getAllMealsByCategory(name);
                Disposable d2 = allMealsByCategory.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(MealResponse -> filterCallBack.onSuccessMealByFilter(MealResponse.getMeals()),
                                throwable -> filterCallBack.onFailureResult(throwable.getMessage()));
            }
    }

    @Override
    public void makeCallBySearch(NetworkCallback searchCallBack, String search) {

        Single<MealResponse> allMealsBySearch = apiMethods.getAllMealsBySearch(search);
        Disposable d = allMealsBySearch.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myResponse -> {
                            Log.d(TAG, "enqueueCall: search ");
                            searchCallBack.onSuccessSearch(myResponse.getMeals());
                        },
                        throwable -> searchCallBack.onFailureResult(throwable.getMessage()));
    }

}
