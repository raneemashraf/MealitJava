package com.example.mealitjava.planner.presenter;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.PlannerModel;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepository;
import com.example.mealitjava.planner.view.PlannerView;

import java.util.List;

public class PlannerPresenterImpl implements PlannerPresenterInterface{
    MealsRepository mealsRepository;
    PlannerView plannerView;

    public PlannerPresenterImpl(MealsRepository mealsRepository ,PlannerView plannerView) {
        this.mealsRepository = mealsRepository;
        this.plannerView = plannerView;
    }

//    @Override
//    public void showPlannedMeals() {
//        LiveData<List<PlannerModel>> result = mealsRepository.getPlannerMeals();
//        plannerView.showPlanner(result);
//    }

    @Override
    public void deletePlannerMeal(PlannerModel meal) {
        mealsRepository.deletePlannerMeal(meal);
    }

    @Override
    public LiveData<List<PlannerModel>> getPlanedMeals(String d) {
        return mealsRepository.getPlannerMeals(d);
    }

}
