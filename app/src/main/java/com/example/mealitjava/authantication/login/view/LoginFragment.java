package com.example.mealitjava.authantication.login.view;

import android.content.Context;
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

import com.example.mealitjava.Home.HomeActivity;
import com.example.mealitjava.authantication.login.presenter.LogInPresenter;
import com.example.mealitjava.authantication.login.presenter.LogInPresenterImpl;
import com.example.mealitjava.databinding.FragmentLoginBinding;
import com.example.mealitjava.model.repository.authRepo.AuthRepositoryImpl;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment implements LogInViewInterface {
    FragmentLoginBinding binding;
    private LogInPresenter logInPresenter;
    private static final String TAG = "LoginFragment";
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logInPresenter = new LogInPresenterImpl(AuthRepositoryImpl.getInstance(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.loginBtn.setOnClickListener(v -> login());

    }

    //    private void initUi(){
//        etName = findViewById(R.id.etName);
//        etPass = findViewById(R.id.etPassword);
//        signUpBtn = findViewById(R.id.SignUpBtn);
//
//        signUpBtn.setOnClickListener(view -> {
//            signUpPresenter.signUp(etName.getText().toString(),etPass.getText().toString());
//        });
//    }
    @Override
    public void onSuccess(FirebaseUser user) {
        Toast.makeText(getContext(), "Login Success", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onSuccess: ");
        goToHomeActivity();
    }

    @Override
    public void OnFailure(String message) {
        Toast.makeText(getContext(), "login fail", Toast.LENGTH_LONG).show();
        Log.i(TAG, "OnFailure: ");
    }

    private void login() {
        String userName = binding.emailET.getText().toString();
        String pass = binding.passwordET.getText().toString();

        logInPresenter.logIn(userName, pass);


    }
    private void goToHomeActivity() {
        Intent intent = new Intent(binding.getRoot().getContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        binding.getRoot().getContext().startActivity(intent);
    }
    public Context getContextInFragment(){
        return getContext();
    }

}