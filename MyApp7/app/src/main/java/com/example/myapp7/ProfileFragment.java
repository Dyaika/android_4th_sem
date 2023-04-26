package com.example.myapp7;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    private boolean logged_in = true;
    private NavController navController;
    private Button collection_button;
    private Button exit_button;
    private Button login_button;
    private Button signup_button;
    private TextView username_text;
    private AccountViewModel accountViewModel;
    public ProfileFragment() {
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        logged_in = Boolean.TRUE.equals(accountViewModel.isLoggedIn().getValue());
        if (logged_in){
            return inflater.inflate(R.layout.fragment_user_profile, container, false);
        } else {
            return inflater.inflate(R.layout.fragment_empty_profile, container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        if (logged_in){
            collection_button = view.findViewById(R.id.collection_button);
            exit_button = view.findViewById(R.id.exit_button);
            username_text = view.findViewById(R.id.username_text);
        } else {
            login_button = view.findViewById(R.id.login_button);
            signup_button = view.findViewById(R.id.signup_button);
        }

        contentInflation();
    }

    public void contentInflation(){

        if (logged_in){
            collection_button.setOnClickListener(view -> {
                navController.navigate(R.id.action_profileFragment_to_collectionFragment);
            });
            exit_button.setOnClickListener(view -> {
                accountViewModel.logout();
                navController.navigate(R.id.action_profileFragment_to_loginFragment);
            });
            username_text.setText(accountViewModel.getUser().getValue().getFirstname() + " " +
                    accountViewModel.getUser().getValue().getLastname());
        }
        else{
            login_button.setOnClickListener(view -> {
                navController.navigate(R.id.action_profileFragment_to_loginFragment);
            });
            signup_button.setOnClickListener(view -> {
                navController.navigate(R.id.action_profileFragment_to_signupFragment);
            });
        }
    }
}