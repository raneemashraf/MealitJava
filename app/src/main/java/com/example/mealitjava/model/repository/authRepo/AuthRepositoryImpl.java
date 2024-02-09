package com.example.mealitjava.model.repository.authRepo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mealitjava.authantication.login.view.LoginFragment;
import com.example.mealitjava.localDataSource.DataBase;
import com.example.mealitjava.model.MealsItem;
import com.example.mealitjava.model.PlannerModel;
import com.example.mealitjava.remoteDataSource.LogInCallBack;
import com.example.mealitjava.remoteDataSource.SignUpCallBack;
import com.example.mealitjava.remoteDataSource.SignUpGoogleCallBack;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AuthRepositoryImpl implements AuthRepository {
    private static AuthRepositoryImpl authRepositoryImpl;
    private FirebaseAuth firebaseAuth;
    LoginFragment loginFragment = new LoginFragment();


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
    public FirebaseUser getUser() {
        return firebaseAuth.getCurrentUser();
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
                            DatabaseReference databaseReferenceFavorite = FirebaseDatabase
                                    .getInstance()
                                    .getReference().child("user")
                                    .child(FirebaseAuth.getInstance().getUid())
                                    .child("favourite");
                            databaseReferenceFavorite.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    List<MealsItem> mealModelList = new ArrayList<>();
                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                        // Assuming each child under "favorite" node is a MealModel object
                                        MealsItem mealModel = dataSnapshot.getValue(MealsItem.class);
                                        mealModelList.add(mealModel);
                                    }
                                    new Thread(()->{
                                        for(int i=0; i<mealModelList.size(); i++)
                                            DataBase.getInstance(loginFragment.getContextInFragment())
                                                    .mealDao()
                                                    .insertMealToFavorite(mealModelList.get(i));
                                    }).start();
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.i("TAG", "onCancelled: " + error.getMessage());
                                }
                            });

                        DatabaseReference databaseReferencePlanner = FirebaseDatabase
                                .getInstance()
                                .getReference().child("user")
                                .child(FirebaseAuth.getInstance().getUid())  // Ensure user is authenticated before accessing UID
                                .child("plan");
                        databaseReferencePlanner.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                List<PlannerModel> mealsItems = new ArrayList<>();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    // Assuming each child under "plan" node is a MealsItem object
                                    PlannerModel mealsItem = dataSnapshot.getValue(PlannerModel.class);
                                    if (mealsItem != null) {
                                        mealsItems.add(mealsItem);
                                    }
                                }
//                                new Thread(() -> {
//                                    // Insert mealsItems into the database
//                                    for (PlannerModel meal : mealsItems) {
//                                        DataBase.getInstance(loginFragment.getContextInFragment()).mealDao()
//                                                .insertMealToPlanner(mealsItems.get(i));
//                                    }
//                                }).start()
                                new Thread(()->{
                                    for(int i=0; i<mealsItems.size(); i++)
                                        DataBase.getInstance(loginFragment.getContextInFragment())
                                                .mealDao()
                                                .insertMealToPlanner(mealsItems.get(i));
                                    Log.i("TAG", "onDataChange: " + mealsItems);
                                }).start();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.i("TAG", "onCancelled: " + error.getMessage());
                            }
                        });

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


    public boolean isAuthenticated() {
        return AuthRepositoryImpl.getInstance().getUser() != null;
    }
}

