package com.example.myapp7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private AccountViewModel accountViewModel;
    private ItemsViewModel itemsViewModel;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModelProvider();
        accountViewModel = viewModelProvider.get(AccountViewModel.class);
        itemsViewModel = viewModelProvider.get(ItemsViewModel.class);

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