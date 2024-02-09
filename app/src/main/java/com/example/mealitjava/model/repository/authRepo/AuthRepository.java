package com.example.mealitjava.model.repository.authRepo;

import com.example.mealitjava.remoteDataSource.LogInCallBack;
import com.example.mealitjava.remoteDataSource.SignUpCallBack;
import com.example.mealitjava.remoteDataSource.SignUpGoogleCallBack;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseUser;

public interface AuthRepository {
    void signUp(String email, String pass, SignUpCallBack signUpCallBack);
    void LogIn(String email, String pass, LogInCallBack signInCallBack);
    void signUpWithGoogle(GoogleSignInAccount account , SignUpGoogleCallBack signUpGoogleCallBack);

    public FirebaseUser getUser();


}
