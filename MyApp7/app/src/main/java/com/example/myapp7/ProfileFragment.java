package com.example.myapp7;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProfileFragment extends Fragment {
    private NavController navController;
    private Button collection_button;
    private Button login_button;
    private Button signup_button;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        collection_button = view.findViewById(R.id.collection_button);
        login_button = view.findViewById(R.id.login_button);
        signup_button = view.findViewById(R.id.signup_button);
        buttonsConfig();
    }

    public void buttonsConfig(){
        collection_button.setOnClickListener(view -> {
            navController.navigate(R.id.action_profileFragment_to_collectionFragment);
        });
        login_button.setOnClickListener(view -> {
            navController.navigate(R.id.action_profileFragment_to_loginFragment);
        });
        signup_button.setOnClickListener(view -> {
            navController.navigate(R.id.action_profileFragment_to_signupFragment);
        });
    }
}