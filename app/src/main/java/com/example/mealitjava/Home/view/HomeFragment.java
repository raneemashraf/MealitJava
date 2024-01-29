package com.example.mealitjava.Home.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mealitjava.Home.presenter.HomePresenter;
import com.example.mealitjava.Home.presenter.HomePresenterImpl;
import com.example.mealitjava.R;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.remotesource.api.MealsItemRemoteImpl;
import com.makeramen.roundedimageview.RoundedImageView;

public class HomeFragment extends Fragment implements HomeInterfaceView {
     HomePresenter homePresenter;
     MealsItemRemoteImpl mealsItemRemote;
     TextView textViewMealName;
     TextView textViewMealCountry;
     RoundedImageView imageViewDishOfTheDay;
     CardView cardView;

    public HomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mealsItemRemote = new MealsItemRemoteImpl();
        homePresenter = new HomePresenterImpl(this,
                MealsRepositoryImpl.getInstance(MealsItemRemoteImpl.getInstance()));
        homePresenter.getRandomMeals();

        super.onViewCreated(view, savedInstanceState);
        textViewMealName = view.findViewById(R.id.txtViewMealNameDishOfTheDay);
        textViewMealCountry = view.findViewById(R.id.textViewCountryDishOfTheDay);
        imageViewDishOfTheDay = view.findViewById(R.id.imageViewDishOfTheDay);
        cardView = view.findViewById(R.id.cardViewRandomMeal);

    }

    @Override
    public void showMeal(MealsItem mealsItem) {
        textViewMealName.setText(mealsItem.getStrMeal());
        textViewMealCountry.setText(mealsItem.getStrArea());
        Glide.with(imageViewDishOfTheDay.getContext()).load(mealsItem.getStrMealThumb()).into(imageViewDishOfTheDay);

        Log.i(TAG, "showMeal: " + mealsItem.getStrCategory());
    }

    @Override
    public void showError(String err) {
        Log.i(TAG, "showError: "+ err.toString());
    }
}