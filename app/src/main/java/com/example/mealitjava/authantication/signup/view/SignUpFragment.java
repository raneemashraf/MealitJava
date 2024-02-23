package com.example.mealitjava.authantication.signup.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealitjava.Home.HomeActivity;
import com.example.mealitjava.R;
import com.example.mealitjava.authantication.signup.presenter.SignUpPresenter;
import com.example.mealitjava.authantication.signup.presenter.SignUpPresenterImpl;
import com.example.mealitjava.databinding.FragmentSignUpBinding;
import com.example.mealitjava.model.repository.authRepo.AuthRepositoryImpl;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignUpFragment extends Fragment implements SignUpViewInterface {
    FragmentSignUpBinding binding;
    private SignUpPresenter signUpPresenter;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth firebaseAuth;
    private static final String TAG = "SignUpFragment";

    public SignUpFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpPresenter = new SignUpPresenterImpl(AuthRepositoryImpl.getInstance(),this);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.signUpBtn.setOnClickListener(v -> signUp());
        binding.TxtLogin.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_signUpFragment_to_loginFragment);
        });
        binding.skipText.setOnClickListener(v -> {
            goToHomeActivity();
        });

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(requireContext(), googleSignInOptions);

        binding.iconGoogle.setOnClickListener(v -> {
            Intent intent = googleSignInClient.getSignInIntent();
            someActivityResultLauncher.launch(intent);
        });

        firebaseAuth = FirebaseAuth.getInstance();

    }
    ActivityResultLauncher<Intent> someActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
                                if (signInAccountTask.isSuccessful()) {
                                    try {
                                        GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                                        if (googleSignInAccount != null) {
                                            AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                                            signUpPresenter.signUpWithGoogle(googleSignInAccount);
                                        }
                                    } catch (ApiException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    });
    //    private void initUi(){
//        etName = findViewById(R.id.etName);
//        etPass = findViewById(R.id.etPassword);
//        signUpBtn = findViewById(R.id.SignUpBtn);
//
//        signUpBtn.setOnClickListener(view -> {
//            signUpPresenter.signUp(etName.getText().toString(),etPass.getText().toString());
//        });
//    }
    @Override
    public void onSuccess(FirebaseUser user) {
        goToHomeActivity();
    }

    @Override
    public void OnFailure(String message) {
        Toast.makeText(getContext(),"Signup fail",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessGoogle(FirebaseUser user) {
        Toast.makeText(getContext(), "onSuccessGoogle", Toast.LENGTH_LONG).show();

    }

    private void signUp() {
        String userName = binding.etName.getText().toString();
        String pass = binding.etPassword.getText().toString();
        //if (isAllDataFilled() && checkValidation(userName, pass)) {
            signUpPresenter.signUp(userName, pass);
       // }
    }
    private void goToHomeActivity() {
        Intent intent = new Intent(binding.getRoot().getContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        binding.getRoot().getContext().startActivity(intent);
    }
}