package com.banksystem;

import java.io.Serializable;

public class User implements Serializable{

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }

    private final String last_name;
    private final String first_name;
    private final String email;
    private final String phone_number;
    private final String password;

    public User(String first_name, String last_name, String email, String phone_number, String password){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
    }
}
