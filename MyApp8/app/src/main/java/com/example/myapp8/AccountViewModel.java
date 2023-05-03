package com.example.myapp8;

import static com.example.myapp8.Repository.getEncryptedUserByLogin;
import static com.example.myapp8.Security.decrypt;
import static com.example.myapp8.Security.encrypt;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

    public static boolean checkNewLogin(String login){
        return Repository.getEncryptedUserByLogin(login) == null;
    }
    public static boolean checkNewPassword(String password1, String password2){
        return (password1.equals(password2) &&
                password1.length() >= 8 &&
                password1.length() <= 16);
    }
    public static boolean checkNewUserdata(String firstname, String lastname){
        return (firstname.length() > 1 &&
                firstname.length() < 20 &&
                lastname.length() > 1 &&
                lastname.length() < 20);
    }

    public static boolean checkRegistration(String firstname, String lastname, String login,
                                     String password1, String password2){
        if (checkNewLogin(login) &&
                checkNewPassword(password1, password2) &&
                checkNewUserdata(firstname, lastname)){
            String key = password1 + password1.toUpperCase();
            User user_encoded = new User(
                    Security.encrypt(firstname, key),
                    Security.encrypt(lastname, key),
                    login,
                    Security.encrypt(password1, key));
            Repository.addUser(user_encoded);
            return true;
        }
        return false;
    }
}
