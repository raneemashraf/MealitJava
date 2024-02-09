package com.example.mealitjava.search.presenter;

public interface GeneralSearchPresenterInterface {
    public void getCategory();
    public void getCountry();
    public void getIngredient();
    public void getFilteredMeals(String name, char c);
}
