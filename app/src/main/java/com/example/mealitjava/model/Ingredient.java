package com.example.mealitjava.model;

public class Ingredient {
        String ingredientName;
        String ingredientMeasure;

        public Ingredient(String ingredientName, String ingredientMeasure) {
            this.ingredientName = ingredientName;
            this.ingredientMeasure = ingredientMeasure;
        }

        public String getIngredientName() {
            return ingredientName;
        }

        public String getIngredientMeasure() {
            return ingredientMeasure;
        }
    }

