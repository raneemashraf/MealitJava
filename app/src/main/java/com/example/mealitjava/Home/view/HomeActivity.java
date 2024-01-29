package com.example.mealitjava.Home.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.mealitjava.Home.presenter.HomePresenter;
import com.example.mealitjava.Home.presenter.HomePresenterImpl;
import com.example.mealitjava.R;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.remotesource.api.MealsItemRemoteImpl;

public class HomeActivity extends AppCompatActivity implements HomeInterfaceView{
    HomePresenter homePresenter;
    MealsItemRemoteImpl mealsItemRemote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mealsItemRemote = new MealsItemRemoteImpl();
        homePresenter = new HomePresenterImpl(this,
                MealsRepositoryImpl.getInstance(MealsItemRemoteImpl.getInstance()));
        homePresenter.getRandomMeals();
    }

    @Override
    public void showMeal(MealsItem mealsItem) {
        Log.i(TAG, "showMeal: " + mealsItem.getStrCategory());
    }

    @Override
    public void showError(String err) {
        Log.i(TAG, "showError: "+ err.toString());
    }
}