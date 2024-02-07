package com.example.mealitjava.search.view;

import com.example.mealitjava.model.Category;
import com.example.mealitjava.model.Country;
import com.example.mealitjava.model.IngredientList;
import com.example.mealitjava.model.MealResponse;

import java.util.List;

public interface GeneralSearchViewInterface {
    public void showCountries(List<Country> countries);

    public void showCategories(List<Category> categories);

    public void showIngredients(List<IngredientList> ingredient);

    public void showFilterResult(MealResponse mealResponse);
}
