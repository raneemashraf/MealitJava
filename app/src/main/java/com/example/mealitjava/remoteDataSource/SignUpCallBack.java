package com.example.mealitjava.remoteDataSource;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpCallBack {
    void onSuccess(FirebaseUser user);

    void onFailure(String errorMessage);
}
