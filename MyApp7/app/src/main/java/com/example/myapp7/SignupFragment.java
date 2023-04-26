package com.example.myapp7;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SignupFragment extends Fragment {
    private View close_button;
    private Button signup_button;
    private View login_text_button;
    private NavController navController;
    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        close_button = view.findViewById(R.id.close_img_button);
        signup_button = view.findViewById(R.id.signup_button);
        login_text_button = view.findViewById(R.id.login_text_button);
        buttonsConfig();
    }

    public void buttonsConfig(){
        close_button.setOnClickListener(view -> {
            navController.navigate(R.id.action_signupFragment_to_profileFragment);
        });
        signup_button.setOnClickListener(view -> {
            navController.navigate(R.id.action_signupFragment_to_profileFragment);
        });
        login_text_button.setOnClickListener(view -> {
            navController.navigate(R.id.action_signupFragment_to_loginFragment);
        });
    }
}