package com.example.myapp7;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class LoginFragment extends Fragment {

    private View close_button;
    private Button login_button;
    private View signup_text_button;
    private NavController navController;
    private TextInputEditText login_text;
    private TextInputEditText password_text;
    private AccountViewModel accountViewModel;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountViewModel = ((MainActivity)requireActivity())
                .getViewModelProvider()
                .get(AccountViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        close_button = view.findViewById(R.id.close_img_button);
        login_button = view.findViewById(R.id.login_button);
        signup_text_button = view.findViewById(R.id.signup_text_button);
        login_text = view.findViewById(R.id.login_text);
        password_text = view.findViewById(R.id.password_text);
        buttonsConfig();
    }
    public void buttonsConfig(){
        close_button.setOnClickListener(view -> {
            navController.navigate(R.id.action_loginFragment_to_profileFragment);
        });
        login_button.setOnClickListener(view -> {
            String password = password_text.getText().toString();
            String login = login_text.getText().toString();
            accountViewModel.checkAuthentication(login, password);
            if (Boolean.TRUE.equals(accountViewModel.isLoggedIn().getValue())){
                navController.navigate(R.id.action_loginFragment_to_profileFragment);
            }
        });
        signup_text_button.setOnClickListener(view -> {
            navController.navigate(R.id.action_loginFragment_to_signupFragment);
        });
    }




}