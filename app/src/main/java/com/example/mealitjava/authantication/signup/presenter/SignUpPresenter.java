package com.example.mealitjava.authantication.signup.presenter;

import com.google.firebase.auth.AuthCredential;

public interface SignUpPresenter {
    void signUp(String email , String pass);
    void signUpWithGoogle(AuthCredential authCredential);

}
