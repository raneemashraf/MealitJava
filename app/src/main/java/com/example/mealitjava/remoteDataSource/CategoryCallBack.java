package com.example.mealitjava.remoteDataSource;

import com.example.mealitjava.model.Category;

import java.util.List;

public interface CategoryCallBack {
    void onSuccessCategory(List<Category> category);
    void onFailureCategory(String errorMessage);
}
