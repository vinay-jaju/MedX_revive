package com.example.android.medx;

public class User {

    String name, username, password,email;
    String age;

    public User(String name, String age, String username, String email, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this("", "", username,"", password);
    }
}
