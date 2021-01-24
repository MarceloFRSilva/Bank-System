package com.banksystem;

import java.io.Serializable;

// must implement Serializable in order to be sent
public class Message implements Serializable{

    private final String text;
    private User user = null;

    public Message(String text) {
        this.text = text;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public String getText() {
        return text;
    }
}
