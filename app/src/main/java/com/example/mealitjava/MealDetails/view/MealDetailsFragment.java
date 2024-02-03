package com.example.mealitjava.MealDetails.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealitjava.MealDetails.presenter.MealDetailsPresenter;
import com.example.mealitjava.MealDetails.presenter.MealDetailsPresenterImpl;
import com.example.mealitjava.R;
import com.example.mealitjava.databinding.FragmentMealDetailsBinding;
import com.example.mealitjava.favorite.view.OnClickFavoriteMeal;
import com.example.mealitjava.localDataSource.MealLocalSourceImpl;
import com.example.mealitjava.model.GetIngredientsFromMeal;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.remoteDataSource.api.MealsItemRemoteImpl;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.ArrayList;

public class MealDetailsFragment extends Fragment {
    FragmentMealDetailsBinding binding;
    MealsIngredientsAdapter ingredientsAdapter;
    MealDetailsPresenter mealDetailsPresenter;
    MealsItemRemoteImpl mealsItemRemote;
    LinearLayoutManager linearLayoutManager;
    OnClickFavoriteMeal onClickFavoriteMeal;
    public MealDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMealDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
               mealDetailsPresenter = new MealDetailsPresenterImpl(
                       new MealsRepositoryImpl(new MealsItemRemoteImpl()
                               ,new MealLocalSourceImpl(getContext())));

        MealDetailsFragmentArgs args = MealDetailsFragmentArgs.fromBundle(getArguments());
        MealsItem mealsItem = args.getMealitem();

        binding.imageViewAddToFavITemDetails.setOnClickListener(view1 -> {
            mealDetailsPresenter.addMealToFavorite(mealsItem);
            Log.i(TAG, "onViewCreated: " + mealsItem.idMeal);
            Toast.makeText(getContext(), "Added to Favorite (;", Toast.LENGTH_SHORT).show();
            binding.imageViewAddToFavITemDetails
                    .setImageResource(R.drawable.baseline_favorite_24);

        });
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
//
//    @Override
//    public void showData(MealsItem meal) {}
//    @Override
//    public void showErrorMessage(String error) {
//        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
//
//    }
//    @Override
//    public void addMealsToFav(MealsItem mealsItem) {
//        mealDetailsPresenter.addMealToFavorite(mealsItem);
//        Toast.makeText(getContext(), "Added to fav ", Toast.LENGTH_SHORT).show();
//    }

}
