package com.example.mealitjava.remoteDataSource.api;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static RetrofitApiMethods getApi(){
        return getInstance().create(RetrofitApiMethods.class);
    }

}
