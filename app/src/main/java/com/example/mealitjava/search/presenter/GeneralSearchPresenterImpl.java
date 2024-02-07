package com.example.mealitjava.search.presenter;

import com.example.mealitjava.model.Category;
import com.example.mealitjava.model.Country;
import com.example.mealitjava.model.IngredientList;
import com.example.mealitjava.model.MealResponse;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepository;
import com.example.mealitjava.remoteDataSource.NetworkCallback;
import com.example.mealitjava.search.view.GeneralSearchViewInterface;

import java.util.List;

public class GeneralSearchPresenterImpl implements GeneralSearchPresenterInterface, NetworkCallback {
    GeneralSearchViewInterface generalSearchView;
    MealsRepository repositoryInter;

    public GeneralSearchPresenterImpl(GeneralSearchViewInterface generalSearchView, MealsRepository repositoryInter) {
        this.generalSearchView = generalSearchView;
        this.repositoryInter = repositoryInter;
    }

    @Override
    public void getCategory() {
        repositoryInter.getCategory(this);
    }
    @Override
    public void getCountry() {
        repositoryInter.getCountry(this);
    }
    @Override
    public void getIngredient() {
        repositoryInter.getIngredients(this);
    }

    @Override
    public void getFilteredMeals(String name, char c) {
        repositoryInter.getFilteredMeals(this,name,c);
    }
    @Override
    public void onSuccessMealByFilter(MealResponse meals) {
        generalSearchView.showFilterResult(meals);
    }

    @Override
    public void onSuccessCountry(List<Country> countries) {
        generalSearchView.showCountries(countries);
    }
    @Override
    public void onSuccessCategory(List<Category> category) {
        generalSearchView.showCategories(category);
    }
    @Override
    public void onSuccessIngredient(List<IngredientList> ingredientLists) {
        generalSearchView.showIngredients(ingredientLists);
    }

    @Override
    public void onSuccessPlanner(List<MealsItem> meals) {

    }

    @Override
    public void onSuccessResult(MealsItem mealsItem) {
    }

    @Override
    public void onFailureResult(String errorMessage) {
    }



    @Override
    public void onSuccessSearch(List<MealsItem> meals) {

    }

}
