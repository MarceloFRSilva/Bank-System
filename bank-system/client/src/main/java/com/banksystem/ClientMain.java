package com.banksystem;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientMain {

    private int port = 6666;

    public static void main(final String[] args) {
        System.out.println("Welcome to the New Bank.");
        System.out.println("\nMain Menu");
        System.out.println("1 - Login to my Account");
        System.out.println("2 - New Account");
        System.out.println("3 - Delete Account");
        System.out.println("4 - Exit Application");
        System.out.println(getUserInput());
        CommunicationLibrary commLibrary = new CommunicationLibrary();
        try{
            commLibrary.createConnection("localhost", 6666);
            commLibrary.sendMessage("Hello Server");
            commLibrary.stopConnection();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static String getUserInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = "";
        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            System.out.println("Something went wrong. \nPlease verify your network connectivity and try again later.");
        }
        return userInput;
    }
}
