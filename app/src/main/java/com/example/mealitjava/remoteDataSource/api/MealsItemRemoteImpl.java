package com.example.mealitjava.remoteDataSource.api;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.example.mealitjava.remoteDataSource.CategoryCallBack;
import com.example.mealitjava.remoteDataSource.MealsCallback;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
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
    public void makeNetworkCall(MealsCallback mealsCallback) {
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
        apiMethods.getCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryResponse->{
                    categoryCallBack.onSuccessCategory(categoryResponse.getCategories());
                            Log.i(TAG, "makeCategoryCall: " + categoryResponse.getCategories());
                },
                   error -> Log.i(TAG, "Error: " + error.toString())
                        );
    }
}
