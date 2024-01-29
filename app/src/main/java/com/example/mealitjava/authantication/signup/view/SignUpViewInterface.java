package com.example.mealitjava.authantication.signup.view;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpViewInterface {
    void onSuccess(FirebaseUser user);
    void OnFailure(String message);
}
