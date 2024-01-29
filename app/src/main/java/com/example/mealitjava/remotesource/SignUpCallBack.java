package com.example.mealitjava.remotesource;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpCallBack {
    void onSuccess(FirebaseUser user);

    void onFailure(String errorMessage);
}
