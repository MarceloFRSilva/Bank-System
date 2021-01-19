package com.banksystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterfaceMenu {

    private static int port = 6666;

    public static void userInterfaceLauncher(){
        CommunicationLibrary commLibrary = new CommunicationLibrary();
        while(true) {
            Display.firstUserMenuDisplay();
            try {
                commLibrary.createConnection("localhost", port);
                commLibrary.sendMessage(getUserInput());
                String received = new CommunicationLibrary().getMessage();
                System.out.println(received);
                commLibrary.stopConnection();
            } catch (Exception e) {
                System.out.println(e);
            }
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
