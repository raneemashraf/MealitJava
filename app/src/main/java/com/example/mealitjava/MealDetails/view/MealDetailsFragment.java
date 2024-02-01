package com.example.mealitjava.MealDetails.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealitjava.Home.view.GetIdFromYoutubeUrl;
import com.example.mealitjava.databinding.FragmentMealDetailsBinding;
import com.example.mealitjava.model.GetIngredientsFromMeal;
import com.example.mealitjava.model.MealsItem;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.ArrayList;

public class MealDetailsFragment extends Fragment {
    FragmentMealDetailsBinding binding;
    MealsIngredientsAdapter ingredientsAdapter;
    LinearLayoutManager linearLayoutManager;
    public MealDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_meal_details, container, false);
        binding = FragmentMealDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MealDetailsFragmentArgs args = MealDetailsFragmentArgs.fromBundle(getArguments());
        MealsItem mealsItem = args.getMealitem();
        ingredientsAdapter = new MealsIngredientsAdapter(new ArrayList<>());
        linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.recyclerViewIngredientsItemDetails.setLayoutManager(linearLayoutManager);

        binding.recyclerViewIngredientsItemDetails.setAdapter(ingredientsAdapter);

        showMealDetails(mealsItem);
    }

    private void showMealDetails(MealsItem mealsItem) {
        binding.txtViewMealNameItemDetails.setText(mealsItem.strMeal);
        binding.textViewMealCountryItemDetails.setText(mealsItem.strArea);
        binding.textViewMealCateItemDetails.setText(mealsItem.strCategory);
        Glide.with(binding.mealImage.getContext()).load(mealsItem.strMealThumb).into(binding.mealImage);
        binding.textViewProcedures.setText(mealsItem.strInstructions);
        binding.recyclerViewIngredientsItemDetails.setLayoutManager(linearLayoutManager);
        ingredientsAdapter.setList(GetIngredientsFromMeal.getArrayList(mealsItem));

        binding.ytPlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = GetIdFromYoutubeUrl.getId(mealsItem.strYoutube);
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
    }


}
