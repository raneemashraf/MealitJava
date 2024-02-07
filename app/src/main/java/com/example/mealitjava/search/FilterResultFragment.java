package com.example.mealitjava.search;

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
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mealitjava.databinding.FragmentFilterResultBinding;
import com.example.mealitjava.model.MealResponse;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.remoteDataSource.api.MealsItemRemoteImpl;
import com.example.mealitjava.search.presenter.SearchPresenterImpl;
import com.example.mealitjava.search.presenter.SearchPresenterInterface;
import com.example.mealitjava.search.view.FilterResultAdapter;
import com.example.mealitjava.search.view.SearchViewInterface;

import java.util.List;

public class FilterResultFragment extends Fragment implements SearchViewInterface {
    FragmentFilterResultBinding binding;
    FilterResultAdapter filterResultAdapter;
    SearchPresenterInterface searchPresenterInterface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilterResultBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FilterResultFragmentArgs args = FilterResultFragmentArgs.fromBundle(getArguments());
        MealResponse mealResponse = args.getListMeal();

        filterResultAdapter = new FilterResultAdapter(mealResponse.getMeals());


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.mealResultsRecyclerView.setLayoutManager(gridLayoutManager);
        //layoutManager.setOrientation(RecyclerView.VERTICAL);
        filterResultAdapter = new FilterResultAdapter(mealResponse.getMeals());
        binding.mealResultsRecyclerView.setAdapter(filterResultAdapter);
        filterResultAdapter.notifyDataSetChanged();



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
            Log.i(TAG, "showMeals: " + meals.get(0).strMeal);
            filterResultAdapter = new FilterResultAdapter(meals);
            filterResultAdapter.setAllMeals(meals);
            binding.mealResultsRecyclerView.setAdapter(filterResultAdapter);
        }
    }
}
