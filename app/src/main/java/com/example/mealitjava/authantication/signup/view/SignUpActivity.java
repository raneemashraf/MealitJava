package com.example.mealitjava.authantication.signup.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealitjava.R;
import com.example.mealitjava.authantication.signup.presenter.SignUpPresenter;
import com.example.mealitjava.authantication.signup.presenter.SignUpPresenterImpl;
import com.example.mealitjava.model.repository.authRepo.AuthRepositoryImpl;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements SignUpViewInterface {
    private SignUpPresenter signUpPresenter;
    EditText etName;
    EditText etPass;
    Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpBtn = findViewById(R.id.signUpBtn);

        signUpPresenter = new SignUpPresenterImpl(AuthRepositoryImpl.getInstance(),this);

        initUi();
    }
    private void initUi(){
        etName = findViewById(R.id.etName);
        etPass = findViewById(R.id.etPassword);
        signUpBtn = findViewById(R.id.signUpBtn);

        signUpBtn.setOnClickListener(view -> {
            signUpPresenter.signUp(etName.getText().toString(),etPass.getText().toString());
        });
    }
    @Override
    public void onSuccess(FirebaseUser user) {
        Toast.makeText(this,"login",Toast.LENGTH_LONG);
    }
    @Override
    public void OnFailure(String message) {
        Toast.makeText(this,"fail",Toast.LENGTH_LONG);

    }

    @Override
    public void onSuccessGoogle(FirebaseUser user) {

    }
}