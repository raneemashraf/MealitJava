package com.example.mealitjava.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.mealitjava.MainActivity;
import com.example.mealitjava.R;
import com.example.mealitjava.localDataSource.MealLocalSource;
import com.example.mealitjava.localDataSource.MealLocalSourceImpl;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity  {
    BottomNavigationView bottomNavigationView;
    MealLocalSource mealLocalSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_home_main);
        bottomNavigationView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        ImageView logout = findViewById(R.id.logoutButton2);
        mealLocalSource = new MealLocalSourceImpl(getBaseContext());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
//                homePresenter.logOut();
                mealLocalSource.deleteAllMeals();
                mealLocalSource.deleteAllPlanner();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

    }


}