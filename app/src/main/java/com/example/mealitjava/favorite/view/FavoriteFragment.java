package com.example.mealitjava.favorite.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealitjava.InternetConnection;
import com.example.mealitjava.MainActivity;
import com.example.mealitjava.R;
import com.example.mealitjava.favorite.presenter.FavMealPresenter;
import com.example.mealitjava.favorite.presenter.FavMealPresenterImp;
import com.example.mealitjava.localDataSource.MealLocalSourceImpl;
import com.example.mealitjava.model.DateFormatter;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.repository.authRepo.AuthRepositoryImpl;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.remoteDataSource.api.MealsItemRemoteImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteView {
    FavoriteAdapter favoriteAdapter;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    FavMealPresenter favMealPresenter;
    private InternetConnection internetConnection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        internetConnection = new InternetConnection(requireContext());

        if (!AuthRepositoryImpl.getInstance().isAuthenticated()) {
            openGotoSignUpDialogue();
        } else {
            String date = DateFormatter.getString(new Date());
            favMealPresenter = new FavMealPresenterImp(
                    new MealsRepositoryImpl(new MealsItemRemoteImpl(), new MealLocalSourceImpl(getContext(), date)),
                    this
            );

            Log.e("TAG", "onCreate: ");

            favoriteAdapter = new FavoriteAdapter(getContext(), new ArrayList<>());
            recyclerView = view.findViewById(R.id.favoriteMeals_recyclerView);
            gridLayoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(favoriteAdapter);

            favMealPresenter.getMeals();
            favoriteAdapter.onDeleteClickListener = this::deleteFavProduct;
        }

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
        if (internetConnection.isConnectedWifi() || internetConnection.isConnectedMobile()){
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.Delete_Meal)
                    .setMessage(R.string.Are_you_sure_you_want_to_delete_this_meal)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            favMealPresenter.deleteFavoriteMeal(meal);
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
        } else {
            Toast.makeText(requireContext(), "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
        }

    }
    private void openGotoSignUpDialogue() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Sign Up Required")
                .setMessage("Please sign Up to access this feature.")
                .setPositiveButton(R.string.singup, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        startActivity(new Intent(requireActivity(), MainActivity.class));
                    }
                })
                .setNegativeButton(android.R.string.cancel, null).show();
    }
}