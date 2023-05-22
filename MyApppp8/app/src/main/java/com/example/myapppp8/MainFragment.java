package com.example.myapppp8;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;


public class MainFragment extends Fragment {

    private static final String TAG = "main fragment";
    private EditText editText;
    private Button save;
    private Button SP;
    private Button AS;
    private Button DE;
    private Button DB;
    private SharedPreferences sharedPreferences;
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;

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
            getFromDatabase();
        });
    }
    private void saveText(String text){

        SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        editor.putString("text", text + "_SP");
        editor.apply();

        File file = new File(getContext().getFilesDir(), "file.txt");
        try {
            FileOutputStream fout = new FileOutputStream(file);
            fout.write((text + "_AS").getBytes());
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Разрешение уже предоставлено, выполните необходимые действия для чтения/записи во внешнее хранилище
            File externalStorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            file = new File(externalStorage, "file.txt");
            try {
                FileOutputStream fout = new FileOutputStream(file);
                fout.write((text + "_DE").getBytes());
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Запрос разрешения у пользователя
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 23);
        }


        TextDataBase db = TextDataBase.getDatabase(getContext().getApplicationContext());
        final TextDao textDao = db.textDao();
        new Thread(() -> {
            TextEntity textEntity = textDao.getText("text");
            if (textEntity == null) {
                textDao.saveText(new TextEntity("text", text + "_DB"));
            } else {
                TextEntity newTextEntity = new TextEntity("text", text+ "_DB");
                newTextEntity.setId(textEntity.getId());
                textDao.updateText(newTextEntity);
            }
        }).start();

    }
    private String getFromSharedPrefs(){
        return sharedPreferences.getString("text", "empty");
    }
    private String getFromAppSpec(){
        File file = new File(getContext().getFilesDir(), "file.txt");
        String text = "empty";
        try {
            FileInputStream fin = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fin.read(data);
            fin.close();
            text = new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
    private String getFromDevice(){
        String text = "empty";
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Разрешение уже предоставлено, выполните необходимые действия для чтения/записи во внешнее хранилище
        } else {
            // Запрос разрешения у пользователя
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 23);
        }
        File externalStorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(externalStorage, "file.txt");
        try {
            FileInputStream fin = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fin.read(data);
            fin.close();
            text = new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
    private void getFromDatabase(){
        TextDataBase db = TextDataBase.getDatabase(getContext().getApplicationContext());
        new Thread(() -> {
            TextDao textDao = db.textDao();
            TextEntity textEntity = textDao.getText("text");
            if (textEntity != null) {
                final String loadedText = textEntity.getText();
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), loadedText, Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

}