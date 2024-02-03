package com.example.mealitjava.favorite.view;

import com.example.mealitjava.model.MealsItem;

public interface OnClickFavoriteMeal {
    void onClickItem(MealsItem meal);
    void onClickDeleteItem(MealsItem meal);
}
