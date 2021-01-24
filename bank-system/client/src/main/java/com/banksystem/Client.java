package com.banksystem;

public class Client {

    private final String last_name;
    private final String first_name;
    private final String email;
    private final String phone_number;
    private final String password;

    public Client(String first_name, String last_name, String email, String phone_number, String password){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
    }
}
