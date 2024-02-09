package com.example.mealitjava.MealDetails.presenter;

import com.example.mealitjava.MealDetails.view.MealsDetailsView;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.PlannerModel;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepository;

public class MealDetailsPresenterImpl implements MealDetailsPresenter {
    MealsRepository mealsRepository;
    MealsDetailsView mealsDetailsView;
    MealDetailsPresenterImpl mealDetailsPresenterImpl;

    MealDetailsPresenterImpl getInstance(){
        if(mealDetailsPresenterImpl == null){
            mealDetailsPresenterImpl = new MealDetailsPresenterImpl(mealsRepository);
        }
        return mealDetailsPresenterImpl;
    }
    public MealDetailsPresenterImpl(MealsRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }
    @Override
    public void getMeals(){}
    @Override
    public void addMealToFavorite(MealsItem meal) {
        mealsRepository.insertMealToFavorite(meal);
    }
    @Override
    public void addMealToPlanner(PlannerModel meal) {
        mealsRepository.insertMealToPlanner(meal);
    }
}
