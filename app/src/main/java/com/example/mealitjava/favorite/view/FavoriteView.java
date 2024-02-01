package com.example.mealitjava.favorite.view;

import androidx.lifecycle.LiveData;

import com.example.mealitjava.model.MealsItem;

import java.util.List;

public interface FavoriteView {
    public void showFav(LiveData<List<MealsItem>> meal);
    public void deleteFavProduct(MealsItem meal);
}
