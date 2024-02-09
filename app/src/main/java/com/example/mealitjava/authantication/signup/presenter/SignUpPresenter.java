package com.example.mealitjava.authantication.signup.presenter;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface SignUpPresenter {
    void signUp(String email , String pass);
    void signUpWithGoogle(GoogleSignInAccount account);

}
