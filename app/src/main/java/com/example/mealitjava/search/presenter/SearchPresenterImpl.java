package com.example.mealitjava.search.presenter;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.mealitjava.model.Category;
import com.example.mealitjava.model.Country;
import com.example.mealitjava.model.IngredientList;
import com.example.mealitjava.model.MealResponse;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepository;
import com.example.mealitjava.remoteDataSource.NetworkCallback;
import com.example.mealitjava.search.view.SearchViewInterface;

import java.util.List;

public class SearchPresenterImpl implements SearchPresenterInterface , NetworkCallback {
    private SearchViewInterface searchViewInterface;
    private MealsRepository repositoryInterface;

    public SearchPresenterImpl(SearchViewInterface searchViewInterface, MealsRepository repositoryInterface) {
        this.searchViewInterface = searchViewInterface;
        this.repositoryInterface = repositoryInterface;
    }
    @Override
    public void getSearchedMeals(String search) {
        Log.d(TAG, "onSuccess: ");
        repositoryInterface.getSearchedMeals(this,search);
    }
    @Override
    public void onSuccessSearch(List<MealsItem> meals) {
        searchViewInterface.showMeals(meals);
    }
    @Override
    public void onFailureResult(String errorMessage) {
        Log.d(TAG, "onFailure: " + errorMessage);
    }
    @Override
    public void onSuccessResult(MealsItem mealsItem) {

    }

    @Override
    public void onSuccessCountry(List<Country> countries) {

    }

    @Override
    public void onSuccessCategory(List<Category> category) {

    }

    @Override
    public void onSuccessIngredient(List<IngredientList> ingredientLists) {

    }
    @Override
    public void onSuccessMealByFilter(MealResponse meals) {

    }


}
