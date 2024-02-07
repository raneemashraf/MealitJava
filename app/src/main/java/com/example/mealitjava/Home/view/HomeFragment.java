package com.example.mealitjava.Home.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealitjava.Home.presenter.HomePresenter;
import com.example.mealitjava.Home.presenter.HomePresenterImpl;
import com.example.mealitjava.Home.view.HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment;
import com.example.mealitjava.R;
import com.example.mealitjava.model.Category;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.remoteDataSource.api.MealsItemRemoteImpl;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeInterfaceView {
    HomePresenter homePresenter;
    MealsItemRemoteImpl mealsItemRemote;
    TextView textViewMealName,textViewMealCountry,textViewMealCategory;
    CategoryAdapter categoryAdapter;
    GridLayoutManager gridLayoutManager;
    RoundedImageView imageViewDishOfTheDay;
    RecyclerView recyclerViewCategory;
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
        homePresenter.getCategory();

        super.onViewCreated(view, savedInstanceState);
        textViewMealName = view.findViewById(R.id.txtViewMealNameDishOfTheDay);
        textViewMealCountry = view.findViewById(R.id.textViewCountryDishOfTheDay);
        imageViewDishOfTheDay = view.findViewById(R.id.imageViewDishOfTheDay);
        cardView = view.findViewById(R.id.cardViewRandomMeal);
        textViewMealCategory = view.findViewById(R.id.textViewCategoryRandom);
        recyclerViewCategory=view.findViewById(R.id.recycelrViewCategory);

        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        categoryAdapter = new CategoryAdapter(new ArrayList<>());
        recyclerViewCategory.setLayoutManager(gridLayoutManager);
        recyclerViewCategory.setAdapter(categoryAdapter);

    }
//
//    @Override
//    public void showCategories(Category category) {
//        binding.tvCategory.setText(category.strCategory);
//        //categoryAdapter.setCategoryList(cate);
//    }

    @Override
    public void showCategories(List<Category> category) {
        categoryAdapter.setCategoryList(category);

    }

    @Override
    public void showMeal(MealsItem mealsItem) {
        textViewMealName.setText(mealsItem.strMeal);
        textViewMealCountry.setText(mealsItem.strArea);
        textViewMealCategory.setText(mealsItem.strCategory);
        Glide.with(imageViewDishOfTheDay.getContext()).load(mealsItem.strMealThumb).into(imageViewDishOfTheDay);
        cardView.setOnClickListener(v -> {
            //Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_mealDetailsFragment);
            ActionHomeFragmentToMealDetailsFragment action =
                    HomeFragmentDirections.actionHomeFragmentToMealDetailsFragment(mealsItem);
            Navigation.findNavController(v).navigate(action);
        });
        Log.i(TAG, "showMeal: " + mealsItem.strCategory);
    }

    @Override
    public void showError(String err) {
        Log.i(TAG, "showError: "+ err.toString());
    }
}