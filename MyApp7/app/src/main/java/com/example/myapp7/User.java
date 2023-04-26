package com.example.myapp7;

public class User {
    private String firstname;
    private String lastname;
    private String login;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLogin() {
        return login;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public User(String firstname, String lastname, String login, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

}
