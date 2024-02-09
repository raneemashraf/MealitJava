package com.example.mealitjava.authantication.signup.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealitjava.Home.HomeActivity;
import com.example.mealitjava.R;
import com.example.mealitjava.authantication.signup.presenter.SignUpPresenter;
import com.example.mealitjava.authantication.signup.presenter.SignUpPresenterImpl;
import com.example.mealitjava.databinding.FragmentSignUpBinding;
import com.example.mealitjava.model.repository.authRepo.AuthRepositoryImpl;
import com.google.firebase.auth.FirebaseUser;

public class SignUpFragment extends Fragment implements SignUpViewInterface {
    FragmentSignUpBinding binding;
    private SignUpPresenter signUpPresenter;
    private static final String TAG = "SignUpFragment";

    public SignUpFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpPresenter = new SignUpPresenterImpl(AuthRepositoryImpl.getInstance(),this);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.signUpBtn.setOnClickListener(v -> signUp());
        binding.TxtLogin.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_signUpFragment_to_loginFragment);
        });
        binding.skipText.setOnClickListener(v -> {
            goToHomeActivity();
        });

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
        goToHomeActivity();
    }

    @Override
    public void OnFailure(String message) {
        Toast.makeText(getContext(),"fail",Toast.LENGTH_LONG).show();
    }
    private void signUp() {
        String userName = binding.etName.getText().toString();
        String pass = binding.etPassword.getText().toString();
        //if (isAllDataFilled() && checkValidation(userName, pass)) {
            signUpPresenter.signUp(userName, pass);
       // }
    }
    private void goToHomeActivity() {
        Intent intent = new Intent(binding.getRoot().getContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        binding.getRoot().getContext().startActivity(intent);
    }
}