package com.example.mealitjava.model;

import java.util.List;

public class CountryResponse {
    private List<Country> meals;

    public CountryResponse(List<Country> meals) {
        this.meals = meals;
    }

    public CountryResponse() {}

    public List<Country> getCountries() {
        return meals;
    }

    public void setCountries(List<Country> meals) {
        this.meals = meals;
    }
}
