package com.example.mealitjava.remotesource;

import com.google.firebase.auth.FirebaseUser;

public interface LogInCallBack {
    void onSuccess(FirebaseUser user);
    void onFailure(String message);
}
