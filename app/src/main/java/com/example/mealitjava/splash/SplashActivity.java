package com.example.mealitjava.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealitjava.MainActivity;
import com.example.mealitjava.R;

public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_DELAY = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);

            // Start the new activity
            startActivity(intent);

            // Finish the SplashActivity so the user can't navigate back to it
            finish();
        }, SPLASH_DELAY);
    }
}