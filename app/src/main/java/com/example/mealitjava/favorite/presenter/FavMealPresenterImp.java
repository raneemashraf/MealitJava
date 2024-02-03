package com.example.mealitjava.favorite.presenter;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.favorite.view.FavoriteView;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepository;

import java.util.List;

public class FavMealPresenterImp implements FavMealPresenter {
    static FavMealPresenterImp FavProductPresenterImp;
    MealsRepository mealsRepository;
    FavoriteView favView;

    public static FavMealPresenterImp getInstance(MealsRepository mealsRepository){
        if(FavProductPresenterImp==null){
            FavProductPresenterImp=new FavMealPresenterImp(mealsRepository);
        }
        return FavProductPresenterImp;
    }

    public FavMealPresenterImp(MealsRepository mealsRepository, FavoriteView favView) {
        this.mealsRepository = mealsRepository;
        this.favView = favView;
    }

    public FavMealPresenterImp(MealsRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    @Override
    public void getMeals() {
       LiveData<List<MealsItem>> result = mealsRepository.getFavMeals();
        favView.showFav(result);
    }
    @Override
    public void deleteFavoriteMeal(MealsItem meal) {
        mealsRepository.deleteFavoriteMeal(meal);
    }
}
