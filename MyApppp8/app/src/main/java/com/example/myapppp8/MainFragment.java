package com.example.myapppp8;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainFragment extends Fragment {

    private static final String TAG = "main fragment";
    private EditText editText;
    private Button save;
    private Button SP;
    private Button AS;
    private Button DE;
    private Button DB;
    private SharedPreferences sharedPreferences;

    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
        save = view.findViewById(R.id.save);
        SP = view.findViewById(R.id.SP);
        AS = view.findViewById(R.id.AS);
        DE = view.findViewById(R.id.DE);
        DB = view.findViewById(R.id.DB);
        buttonsConfig();
    }
    private void buttonsConfig(){
        save.setOnClickListener(view -> {
            String text = editText.getText().toString();
            saveText(text);
            Toast.makeText(getContext(), "saved", Toast.LENGTH_SHORT).show();
        });
        SP.setOnClickListener(view -> {
            String text = getFromSharedPrefs();
            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        });
        AS.setOnClickListener(view -> {
            String text = getFromAppSpec();
            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        });
        DE.setOnClickListener(view -> {
            String text = getFromDevice();
            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        });
        DB.setOnClickListener(view -> {
            String text = getFromDatabase();
            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        });
    }
    private void saveText(String text){

        SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        editor.putString("text", text + "_SP");
        editor.apply();
    }
    private String getFromSharedPrefs(){
        return sharedPreferences.getString("text", "empty");
    }
    private String getFromAppSpec(){
        return "AS";
    }
    private String getFromDevice(){
        return "DE";
    }
    private String getFromDatabase(){
        return "DB";
    }

}