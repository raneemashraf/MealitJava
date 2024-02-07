package com.example.mealitjava.planner.presenter;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.Category;
import com.example.mealitjava.model.Country;
import com.example.mealitjava.model.IngredientList;
import com.example.mealitjava.model.MealResponse;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepository;
import com.example.mealitjava.planner.view.PlannerViewInterface;
import com.example.mealitjava.remoteDataSource.NetworkCallback;

import java.util.List;

public class PlannerPresenterImpl implements PlannerPresenterInterface, NetworkCallback {
    private PlannerViewInterface plannerViewInterface;
    private MealsRepository repositoryInterface;

    public PlannerPresenterImpl(PlannerViewInterface plannerViewInterface, MealsRepository repositoryInterface) {
        this.plannerViewInterface = plannerViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void getSearchedMeals(String search) {
        repositoryInterface.getSearchedMeals(this, search);
    }

    @Override
    public void addMealPlanner(MealsItem meal) {
        repositoryInterface.insertMealToFavorite(meal);
    }

    @Override
    public void deletePlannerMeal(MealsItem meal) {
        repositoryInterface.removeMealPlanned(meal);
    }

    @Override
    public LiveData<List<MealsItem>> getPlannedDayMeals(String day) {
        return repositoryInterface.getMealsByDayDB(day);
    }

    @Override
    public void onSuccessPlanner(List<MealsItem> meals) {
        plannerViewInterface.showPlannerMeals(meals);
    }

    @Override
    public void onSuccessResult(MealsItem mealsItem) {

    }

    @Override
    public void onFailureResult(String errorMessage) {

    }

    @Override
    public void onSuccessMealByFilter(MealResponse meals) {

    }

    @Override
    public void onSuccessSearch(List<MealsItem> meals) {

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


}
