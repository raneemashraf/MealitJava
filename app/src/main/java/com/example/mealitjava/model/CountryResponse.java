package com.example.mealitjava.model;

import java.util.ArrayList;

public class CountryResponse {
    public ArrayList<Country> countries;

    public CountryResponse(){

    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }
}
