package com.example.mealitjava.remoteDataSource;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpGoogleCallBack {
    void onSuccessGoogle(FirebaseUser user);
    void onFailureGoogle(String message);
}
