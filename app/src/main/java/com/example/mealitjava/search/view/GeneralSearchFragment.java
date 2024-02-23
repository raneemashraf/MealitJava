package com.example.mealitjava.search.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealitjava.R;
import com.example.mealitjava.databinding.FragmentGeneralSearchBinding;
import com.example.mealitjava.model.Category;
import com.example.mealitjava.model.Country;
import com.example.mealitjava.model.IngredientList;
import com.example.mealitjava.model.MealResponse;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.remoteDataSource.api.MealsItemRemoteImpl;
import com.example.mealitjava.search.presenter.GeneralSearchPresenterImpl;
import com.example.mealitjava.search.presenter.GeneralSearchPresenterInterface;
import com.example.mealitjava.search.view.Category.CategoryAdapter;
import com.example.mealitjava.search.view.Category.OnCategoryClickListener;
import com.example.mealitjava.search.view.Country.CountryAdapter;
import com.example.mealitjava.search.view.Country.OnCountryClickListener;
import com.example.mealitjava.search.view.Ingredient.IngredientAdapter;
import com.example.mealitjava.search.view.Ingredient.OnIngredientClickListener;

import java.util.ArrayList;
import java.util.List;

public class GeneralSearchFragment extends Fragment implements GeneralSearchViewInterface, OnCountryClickListener, OnIngredientClickListener, OnCategoryClickListener {
    EditText searchBarEditText;
    CountryAdapter countryAdapter;
    IngredientAdapter ingredientAdapter;
    CategoryAdapter categoryAdapter;
    FragmentGeneralSearchBinding binding;
    GeneralSearchPresenterInterface generalSearchPresenter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGeneralSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();

        //return inflater.inflate(R.layout.fragment_general_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        
        generalSearchPresenter = new GeneralSearchPresenterImpl(this,
                MealsRepositoryImpl.getInstance(MealsItemRemoteImpl.getInstance()));

        generalSearchPresenter.getCountry();
        generalSearchPresenter.getIngredient();
        generalSearchPresenter.getCategory();


        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        binding.countiesRecyclerView.setLayoutManager(layoutManager2);
        countryAdapter = new CountryAdapter(getContext(), new ArrayList<>(),this );


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.ingredientsRecyclerView.setLayoutManager(layoutManager);
        ingredientAdapter = new IngredientAdapter(getContext(), new ArrayList<>(), this);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(RecyclerView.HORIZONTAL);
        binding.categoriesRecyclerView.setLayoutManager(layoutManager3);
        categoryAdapter = new CategoryAdapter(getContext(), new ArrayList<>(), this);

        binding.searchGeneralEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //GeneralSearchFragmentDirections.actionSearchFragmentToSearchMealFragment();
                Navigation.findNavController(v).navigate(R.id.action_searchFragment_to_searchMealFragment);
            }
        });
    }

    @Override
    public void showCountries(List<Country> countries) {
        binding.countiesRecyclerView.setAdapter(countryAdapter);
        countryAdapter.setCountryList(countries);
    }
    @Override
    public void showCategories(List<Category> categories) {
        binding.categoriesRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.setCategoryList(categories);
    }

    @Override
    public void showIngredients(List<IngredientList> ingredient) {
        binding.ingredientsRecyclerView.setAdapter(ingredientAdapter);
        ingredientAdapter.setingredientList(ingredient);
    }

    @Override
    public void showFilterResult(MealResponse mealResponse) {

        GeneralSearchFragmentDirections.ActionSearchFragmentToFilterResultFragment action =
                GeneralSearchFragmentDirections.actionSearchFragmentToFilterResultFragment(mealResponse);
        Navigation.findNavController(view).navigate(action);

        Log.i("TAG", "showFilterResult: " + mealResponse.getMeals().get(0).strMeal);
    }

    @Override
    public void onClickCountry(String country) {
        generalSearchPresenter.getFilteredMeals(country,'a');
    }

    @Override
    public void onClickCategory(String id) {
        generalSearchPresenter.getFilteredMeals(id,'c');

    }

    @Override
    public void onClickIngredient(String name) {
        generalSearchPresenter.getFilteredMeals(name,'i');

    }
}