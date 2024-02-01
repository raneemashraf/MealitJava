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

    public static FavMealPresenterImp getInstance(MealsRepository mealsRepository, FavoriteView favView){
        if(FavProductPresenterImp==null){
            FavProductPresenterImp=new FavMealPresenterImp(mealsRepository,favView);
        }
        return FavProductPresenterImp;
    }

    public FavMealPresenterImp(MealsRepository mealsRepository, FavoriteView favView) {
        this.mealsRepository = mealsRepository;
        this.favView = favView;
    }

        //favProductView.showData(products);
    @Override
    public void getMeals() {
       LiveData<List<MealsItem>> result = mealsRepository.getFavProducts();
        favView.showFav(result);
    }
    @Override
    public void deleteFavoriteMeal(MealsItem meal) {
        mealsRepository.deleteFavoriteProduct(meal);
    }
}
