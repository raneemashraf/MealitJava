package com.example.mealitjava.MealDetails.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealitjava.InternetConnection;
import com.example.mealitjava.MainActivity;
import com.example.mealitjava.MealDetails.presenter.MealDetailsPresenter;
import com.example.mealitjava.MealDetails.presenter.MealDetailsPresenterImpl;
import com.example.mealitjava.R;
import com.example.mealitjava.databinding.FragmentMealDetailsBinding;
import com.example.mealitjava.favorite.view.OnClickFavoriteMeal;
import com.example.mealitjava.localDataSource.MealLocalSourceImpl;
import com.example.mealitjava.model.DateFormatter;
import com.example.mealitjava.model.GetIngredientsFromMeal;
import com.example.mealitjava.model.MealPlannerAndMealConverter;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.PlannerModel;
import com.example.mealitjava.model.repository.authRepo.AuthRepositoryImpl;
import com.example.mealitjava.model.repository.mealsRepo.MealsRepositoryImpl;
import com.example.mealitjava.remoteDataSource.api.MealsItemRemoteImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MealDetailsFragment extends Fragment {
    FragmentMealDetailsBinding binding;
    MealsIngredientsAdapter ingredientsAdapter;
    MealDetailsPresenter mealDetailsPresenter;
    LinearLayoutManager linearLayoutManager;
    OnClickFavoriteMeal onClickFavoriteMeal;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceFavorite;
    DatabaseReference databaseReferencePlanner;
    Calendar now;
    int year, month, dayOfMonth;
    Date date;
    MealsItem mealsItem;
    PlannerModel plannerModel;
    InternetConnection internetConnection;
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
        String date = DateFormatter.getString(new Date());
               mealDetailsPresenter = new MealDetailsPresenterImpl(
                       new MealsRepositoryImpl(new MealsItemRemoteImpl()
                               ,new MealLocalSourceImpl(getContext(),date)));

        MealDetailsFragmentArgs args = MealDetailsFragmentArgs.fromBundle(getArguments());
        this.mealsItem = args.getMealitem();
        ingredientsAdapter = new MealsIngredientsAdapter(new ArrayList<>());
        linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.recyclerViewIngredientsItemDetails.setLayoutManager(linearLayoutManager);
        binding.recyclerViewIngredientsItemDetails.setAdapter(ingredientsAdapter);

        showMealDetails(mealsItem);

        if (!AuthRepositoryImpl.getInstance().isAuthenticated()) {
            Toast.makeText(getContext(), "Please sign Up to access this feature", Toast.LENGTH_SHORT).show();
        } else {
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReferenceFavorite = firebaseDatabase.getReference()
                    .child("user")
                    .child(FirebaseAuth.getInstance().getUid())
                    .child("favourite");

            databaseReferencePlanner = firebaseDatabase.getReference()
                    .child("user")
                    .child(FirebaseAuth.getInstance().getUid())
                    .child("plan");

            binding.imageViewAddToFavITemDetails.setOnClickListener(view1 -> {
                if (!AuthRepositoryImpl.getInstance().isAuthenticated()) {
                    openGotoSignUpDialogue();
                }
                mealsItem.dateModified = "fav";
                mealDetailsPresenter.addMealToFavorite(mealsItem);
                Toast.makeText(getContext(), "Added to Favorite (;", Toast.LENGTH_SHORT).show();
                databaseReferenceFavorite.child(mealsItem.idMeal).setValue(mealsItem);

                binding.imageViewAddToFavITemDetails
                        .setImageResource(R.drawable.baseline_favorite_24);

            });
            binding.imageViewAddToFavITemDetails.setOnClickListener(new View.OnClickListener() {

                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    mealDetailsPresenter.addMealToFavorite(mealsItem);
                    Toast.makeText(getContext(), "Added to Favorite (;", Toast.LENGTH_SHORT).show();
                    databaseReferenceFavorite.child(mealsItem.idMeal).setValue(mealsItem);
                    binding.imageViewAddToFavITemDetails
                            .setImageResource(R.drawable.baseline_favorite_24);
                }
            });

            binding.imageViewAddToCalendarItemDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDatePickerDialog();
                    //databaseReferencePlanner.child(mealsItem.idMeal).setValue(mealsItem);
                }
            });
        }


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

    private void showDatePickerDialog() {
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String d = DateFormatter.getString(year, month, dayOfMonth);

                PlannerModel plannerModel = MealPlannerAndMealConverter
                        .getMealPlannerFromMealAndDate(mealsItem,DateFormatter
                                .getString(year, month, dayOfMonth),0);

                mealDetailsPresenter.addMealToPlanner(plannerModel);
                databaseReferencePlanner.child(mealsItem.idMeal).setValue(plannerModel);
                Toast.makeText(getContext(), "Added to Planner (;", Toast.LENGTH_SHORT).show();

//                performActionOnDateSet(date.toString());
            }
        };

        now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH);
        dayOfMonth = now.get(Calendar.DAY_OF_MONTH);

        // tripDate = getView().findViewById(R.id.calenderTv);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                onDateSetListener, year, month, dayOfMonth);
        datePickerDialog.setTitle("Please select a date.");
        datePickerDialog.show();
    }

    private void performActionOnDateSet(String date) {
        mealsItem.dateModified = date;

        Log.i("PLANNER", "performActionOnDateSet: " + mealsItem);
        Toast.makeText(getContext(), "Added to Planner (;", Toast.LENGTH_SHORT).show();

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
