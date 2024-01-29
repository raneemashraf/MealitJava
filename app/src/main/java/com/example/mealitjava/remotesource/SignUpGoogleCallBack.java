package com.example.mealitjava.remotesource;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpGoogleCallBack {
    void onSuccessGoogle(FirebaseUser user);
    void onFailureGoogle(String message);
}
