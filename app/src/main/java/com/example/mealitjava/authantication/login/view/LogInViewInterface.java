package com.example.mealitjava.authantication.login.view;

import com.google.firebase.auth.FirebaseUser;

public interface LogInViewInterface {
    void onSuccess(FirebaseUser user);
    void OnFailure(String message);
}
