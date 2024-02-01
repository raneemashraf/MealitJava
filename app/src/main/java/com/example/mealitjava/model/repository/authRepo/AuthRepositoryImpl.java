package com.example.mealitjava.model.repository.authRepo;

import com.example.mealitjava.remoteDataSource.LogInCallBack;
import com.example.mealitjava.remoteDataSource.SignUpCallBack;
import com.example.mealitjava.remoteDataSource.SignUpGoogleCallBack;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthRepositoryImpl implements AuthRepository {
    private static AuthRepositoryImpl authRepositoryImpl;
    private FirebaseAuth firebaseAuth;

    public static synchronized AuthRepositoryImpl getInstance() {
        if (authRepositoryImpl == null) {
            authRepositoryImpl = new AuthRepositoryImpl();
        }
        return authRepositoryImpl;
    }

    private AuthRepositoryImpl() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signUp(String email, String password, SignUpCallBack signUpCallBack) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        signUpCallBack.onSuccess(task.getResult().getUser());
                    } else {
                        signUpCallBack.onFailure(task.getException().getMessage());
                    }
                });
    }
    @Override
    public void LogIn(String email, String password, LogInCallBack logInCallBack) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        logInCallBack.onSuccess(task.getResult().getUser());
                    } else {
                        logInCallBack.onFailure(task.getException().getMessage());
                    }
                });
    }

    @Override
    public void signUpWithGoogle(GoogleSignInAccount account , SignUpGoogleCallBack signUpGoogleCallBack) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        signUpGoogleCallBack.onSuccessGoogle(task.getResult().getUser());
                    } else {
                        signUpGoogleCallBack.onFailureGoogle(task.getException().getMessage());
                    }
                });
    }

}
