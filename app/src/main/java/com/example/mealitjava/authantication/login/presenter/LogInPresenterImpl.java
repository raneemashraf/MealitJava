package com.example.mealitjava.authantication.login.presenter;

import com.example.mealitjava.authantication.login.view.LogInViewInterface;
import com.example.mealitjava.model.repository.authRepo.AuthRepositoryImpl;
import com.example.mealitjava.remoteDataSource.LogInCallBack;
import com.google.firebase.auth.FirebaseUser;

public class LogInPresenterImpl implements LogInPresenter, LogInCallBack {
        AuthRepositoryImpl authRepositoryImpl;
        LogInViewInterface logInView;

    public LogInPresenterImpl(AuthRepositoryImpl authRepositoryImpl, LogInViewInterface logInView) {
        this.authRepositoryImpl = authRepositoryImpl;
        this.logInView = logInView;
    }
    @Override
    public void logIn(String email, String pass) {
        authRepositoryImpl.LogIn(email,pass,this );
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        logInView.onSuccess(user);
    }

    @Override
    public void onFailure(String errorMessage) {
        logInView.OnFailure(errorMessage);
    }


}
