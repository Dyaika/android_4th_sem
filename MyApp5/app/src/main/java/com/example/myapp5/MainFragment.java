package com.example.myapp5;

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
import android.widget.EditText;

public class MainFragment extends Fragment {
    private NavController navController;
    private Button toSecond;
    private Button toThird;
    private EditText text;

    public MainFragment() {
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        toSecond = view.findViewById(R.id.button_to_second);
        toThird = view.findViewById(R.id.button_to_third);
        text = view.findViewById(R.id.text);
        Bundle args = getArguments();
        if (args != null){
            String s = args.getString("text");
            if (s != null){
                text.setText(s);
            }
        }
        toSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("text", text.getText().toString());
                navController.navigate(R.id.action_mainFragment_to_secondFragment, bundle);
            }
        });
        toThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("text", text.getText().toString());
                navController.navigate(R.id.action_mainFragment_to_thirdFragment, bundle);
            }
        });
    }
}