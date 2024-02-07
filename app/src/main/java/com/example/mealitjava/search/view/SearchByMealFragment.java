package com.example.mealitjava.search.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mealitjava.databinding.FragmentSearchByMealBinding;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.remoteDataSource.api.MealsItemRemoteImpl;
import com.example.mealitjava.search.presenter.SearchPresenterImpl;
import com.example.mealitjava.search.presenter.SearchPresenterInterface;

import java.util.List;

public class SearchByMealFragment extends Fragment implements SearchClickListener,SearchViewInterface {
    FragmentSearchByMealBinding binding;
    SearchedMealsAdapter searchedMealsAdapter;
    SearchPresenterInterface searchPresenterInterface;

    public SearchByMealFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchByMealBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchPresenterInterface = new SearchPresenterImpl(this,
                MealsRepositoryImpl.getInstance(MealsItemRemoteImpl.getInstance()));

        binding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i(TAG, "onTextChanged: " + charSequence);
                searchPresenterInterface.getSearchedMeals(charSequence + "");
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void showMeals(List<MealsItem> meals) {
        if(meals != null){
            Log.i(TAG, "showMeals: "+meals.get(0).strMeal);
            searchedMealsAdapter = new SearchedMealsAdapter(getContext(),
                    SearchByMealFragment.this);
            searchedMealsAdapter.setAllMeals(meals);
            binding.searchResultRecyclerView.setAdapter(searchedMealsAdapter);
        }
    }
}