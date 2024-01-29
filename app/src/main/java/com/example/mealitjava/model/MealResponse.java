package com.example.mealitjava.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MealResponse {
	@SerializedName("meals")
	private List<MealsItem> meals;
	public List<MealsItem> getMeals(){
		return meals;
	}
}