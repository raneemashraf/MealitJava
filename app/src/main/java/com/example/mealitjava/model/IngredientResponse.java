package com.example.mealitjava.model;

import java.util.List;

public class IngredientResponse {
    private List<IngredientList> meals;

    public IngredientResponse() {
    }

    public IngredientResponse(List<IngredientList> meals) {
        this.meals = meals;
    }

    public List<IngredientList> getMeals() {
        return meals;
    }

    public void setMeals(List<IngredientList> meals) {
        this.meals = meals;
    }
}
