package com.example.mealitjava.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MealResponse implements Serializable {
	@SerializedName("meals")
	private List<MealsItem> meals;
	public List<MealsItem> getMeals(){
		return meals;
	}
}