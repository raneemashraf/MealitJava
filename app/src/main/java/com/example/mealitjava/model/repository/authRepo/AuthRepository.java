package com.example.mealitjava.model.repository.authRepo;

import com.example.mealitjava.remotesource.LogInCallBack;
import com.example.mealitjava.remotesource.SignUpCallBack;
import com.example.mealitjava.remotesource.SignUpGoogleCallBack;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;

public interface AuthRepository {
    void signUp(String email, String pass, SignUpCallBack signUpCallBack);
    void LogIn(String email, String pass, LogInCallBack signInCallBack);
    void signUpWithGoogle(GoogleSignInAccount account , SignUpGoogleCallBack signUpGoogleCallBack);


}
