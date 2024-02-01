package com.example.mealitjava.authantication.signup.presenter;

import com.example.mealitjava.authantication.signup.view.SignUpViewInterface;
import com.example.mealitjava.model.repository.authRepo.AuthRepository;
import com.example.mealitjava.remoteDataSource.SignUpCallBack;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPresenterImpl implements SignUpPresenter, SignUpCallBack {
    private AuthRepository authRepository;
    private SignUpViewInterface signUpView;
    private static SignUpPresenterImpl signUpPresenter;

//    public static SignUpPresenter getInstance(SignUpViewInterface signUpViewInterface) {
//        if (signUpPresenter == null) {
//            signUpPresenter = new SignUpPresenterImpl(authRepository,signUpViewInterface);
//        }
//        return signUpPresenter;
//    }
    public SignUpPresenterImpl(AuthRepository authRepository, SignUpViewInterface signUpViewInterface) {
        this.authRepository = authRepository;
        this.signUpView = signUpViewInterface;
    }

    @Override
    public void signUp(String email, String password) {
        authRepository.signUp( email, password,this);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        signUpView.onSuccess(user);
    }
    @Override
    public void onFailure(String errorMessage) {
        signUpView.OnFailure(errorMessage);
    }
}
