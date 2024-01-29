package com.example.mealitjava.Home.presenter;

import com.example.mealitjava.Home.view.HomeInterfaceView;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepository;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.remotesource.api.MealsCallback;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.remotesource.api.MealsItemRemote;

public class HomePresenterImpl implements HomePresenter, MealsCallback {
    private static final String TAG = "HomePresenter";
    HomeInterfaceView homeInterfaceView;
    MealsRepository mealsRepository;
    HomePresenterImpl homePresenterObj;

    HomePresenterImpl getInstance(){
        if(homePresenterObj == null){
            homePresenterObj = new HomePresenterImpl(homeInterfaceView,mealsRepository);
        }
        return homePresenterObj;
    }
    public HomePresenterImpl(HomeInterfaceView homeInterfaceView, MealsRepository mealsRepository) {
        this.homeInterfaceView = homeInterfaceView;
        this.mealsRepository = mealsRepository;
    }
    @Override
    public void getRandomMeals() {
        mealsRepository.getRandomMeal(this);
    }

    @Override
    public void onSuccessResult(MealsItem mealsItem) {
        homeInterfaceView.showMeal(mealsItem);
    }
    @Override
    public void onFailureResult(String errorMessage) {
        homeInterfaceView.showError(errorMessage);
    }
}
