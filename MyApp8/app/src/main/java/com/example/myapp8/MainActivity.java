package com.example.myapp8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private AccountViewModel accountViewModel;
    private ItemsViewModel itemsViewModel;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private ViewModelProvider viewModelProvider;
    private boolean logged_in;
    private User user;

    public ViewModelProvider getViewModelProvider() {
        initViewModelProvider();
        return viewModelProvider;
    }
    private void initViewModelProvider() {
        if (viewModelProvider == null){
            viewModelProvider = new ViewModelProvider(this);
        }
    }
    private void prepareSharedPreferences(){
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
    }
    public void saveAuthorisation(String login, String password){
        if (sharedPreferences == null){
            prepareSharedPreferences();
        }
        editor = sharedPreferences.edit();
        editor.putString("LOGIN", login);
        editor.putString("PASSWORD", password);
        editor.apply();
    }
    public void removeAuthorisation(){
        if (sharedPreferences == null){
            prepareSharedPreferences();
        }
        editor = sharedPreferences.edit();
        editor.remove("LOGIN");
        editor.remove("PASSWORD");
        editor.apply();
    }
    public void authorise(){
        if (sharedPreferences == null){
            prepareSharedPreferences();
        }
        initViewModelProvider();
        accountViewModel = viewModelProvider.get(AccountViewModel.class);
        itemsViewModel = viewModelProvider.get(ItemsViewModel.class);
        String login = sharedPreferences.getString("LOGIN", null);
        String password = sharedPreferences.getString("PASSWORD", null);
        if (login != null && password != null){
            accountViewModel.checkAuthentication(login, password);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authorise();

        logged_in = Boolean.TRUE.equals(accountViewModel.isLoggedIn().getValue());
        accountViewModel.isLoggedIn().observe(this, aBoolean -> {
            logged_in = aBoolean;
        });
        user = accountViewModel.getUser().getValue();
        accountViewModel.getUser().observe(this, user1 -> {
            this.user = user1;
        });
    }

    public boolean isLoggedIn() {
        return logged_in;
    }
}