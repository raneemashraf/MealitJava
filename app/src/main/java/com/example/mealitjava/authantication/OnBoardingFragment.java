package com.example.mealitjava.authantication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealitjava.R;
import com.example.mealitjava.databinding.FragmentOnBoardingBinding;



public class OnBoardingFragment extends Fragment {
    FragmentOnBoardingBinding fragmentonboarding;
    public OnBoardingFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentonboarding = FragmentOnBoardingBinding.inflate(inflater, container, false);
        return fragmentonboarding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentonboarding.loginBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_OnBoardingFragment_to_loginFragment);
        });
        fragmentonboarding.signUpBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_OnBoardingFragment_to_signUpFragment);
        });

    }
}