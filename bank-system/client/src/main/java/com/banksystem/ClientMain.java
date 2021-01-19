package com.banksystem;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMain {

    public static void main(final String[] args) {
        Display.firstUserMenuDisplay();
        UserInterfaceMenu.userInterfaceLauncher(getUserInput());

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
