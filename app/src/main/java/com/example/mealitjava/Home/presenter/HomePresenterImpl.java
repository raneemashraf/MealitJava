package com.example.mealitjava.Home.presenter;

import com.example.mealitjava.Home.view.HomeInterfaceView;
import com.example.mealitjava.model.Category;
import com.example.mealitjava.model.Country;
import com.example.mealitjava.model.IngredientList;
import com.example.mealitjava.model.MealResponse;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepository;
import com.example.mealitjava.remoteDataSource.NetworkCallback;

import java.util.List;

public class HomePresenterImpl implements HomePresenter, NetworkCallback {
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
    public void getCategory() {
        mealsRepository.getCategory(this);
    }
    @Override
    public void onSuccessResult(MealsItem mealsItem) {
        homeInterfaceView.showMeal(mealsItem);
    }
    @Override
    public void onFailureResult(String errorMessage) {
        homeInterfaceView.showError(errorMessage);
    }

    @Override
    public void onSuccessCountry(List<Country> countries) {

    }
    @Override
    public void onSuccessIngredient(List<IngredientList> ingredientLists) {
    }

    @Override
    public void onSuccessPlanner(List<MealsItem> meals) {

    }

    @Override
    public void onSuccessMealByFilter(MealResponse meals) {

    }
    @Override
    public void onSuccessSearch(List<MealsItem> meals) {

    }

    @Override
    public void onSuccessCategory(List<Category> category) {
        homeInterfaceView.showCategories(category);
    }

}
