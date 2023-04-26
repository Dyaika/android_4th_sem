package com.example.myapp7;

import static com.example.myapp7.Repository.getEncryptedUserByLogin;
import static com.example.myapp7.Security.decrypt;
import static com.example.myapp7.Security.encrypt;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class AccountViewModel extends ViewModel {
    private static final String TAG = "account view-model";
    private MutableLiveData<User> user = new MutableLiveData<>(null);
    private MutableLiveData<Boolean> logged_in = new MutableLiveData<>(false);

    public MutableLiveData<Boolean> isLoggedIn() {
        return logged_in;
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void logout(){
        user.setValue(null);
        logged_in.setValue(false);
    }

    public void checkAuthentication(String login, String password){
        if (password.length() >= 8 && password.length() <= 16){
            String key = password + password.toUpperCase();
            String encryptedPassword = encrypt(password, key);
            User encryptedUser = getEncryptedUserByLogin(login);
            if (encryptedUser != null){
                logged_in.setValue(encryptedPassword.equals(encryptedUser.getPassword()));
                if (Boolean.TRUE.equals(logged_in.getValue())){
                    user.setValue(new User(
                            decrypt(encryptedUser.getFirstname(), key),
                            decrypt(encryptedUser.getLastname(), key),
                            login,
                            password));
                }
            }
        }
    }

}
