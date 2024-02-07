package com.example.mealitjava.favorite.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealitjava.R;
import com.example.mealitjava.favorite.presenter.FavMealPresenter;
import com.example.mealitjava.favorite.presenter.FavMealPresenterImp;
import com.example.mealitjava.localDataSource.MealLocalSourceImpl;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.remoteDataSource.api.MealsItemRemoteImpl;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteView {
    FavoriteAdapter favoriteAdapter;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    FavMealPresenter favMealPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favMealPresenter = new FavMealPresenterImp(
                new MealsRepositoryImpl(new MealsItemRemoteImpl(), new MealLocalSourceImpl(getContext())),
                this
        );

        Log.e("TAG", "onCreate: ");

        favoriteAdapter = new FavoriteAdapter(getContext() , new ArrayList<>());
        recyclerView = view.findViewById(R.id.favoriteMeals_recyclerView);
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(favoriteAdapter);

        favMealPresenter.getMeals();
        favoriteAdapter.onDeleteClickListener = this::deleteFavProduct;


    }
    @Override
    public void showFav(LiveData<List<MealsItem>> meal) {
        meal.observe(this, new Observer<List<MealsItem>>() {
            @Override
            public void onChanged(List<MealsItem> mealsItems) {
                favoriteAdapter.setFavMealsListList(mealsItems);
                Log.d("TAG", "onChanged: " + mealsItems.size());
                favoriteAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void deleteFavProduct(MealsItem meal) {
        favMealPresenter.deleteFavoriteMeal(meal);
    }


}