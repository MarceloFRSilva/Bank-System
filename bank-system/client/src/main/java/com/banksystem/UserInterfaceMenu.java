package com.banksystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterfaceMenu {

    private static int port = 6666;
    private static final String LOGIN_ID = "Login request";
    private static final String EXIT_ID = "Exit request";

    public static void userInterfaceLauncher(){
        CommunicationLibrary commLibrary = new CommunicationLibrary();
        while(commLibrary.getSocket() == null || !commLibrary.getSocket().isConnected()) {
            try {
                commLibrary.createConnection("localhost", port);
            } catch (Exception e) {
                continue;
            }
        }
        Display.firstUserMenuDisplay();
        String option = getUserInput();
        while(true) {
            processRequest(option, commLibrary);
            Display.firstUserMenuDisplay();
            option = getUserInput();
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

    private static void processRequest(String option, CommunicationLibrary commLibrary){
        if(option.equals("1")) {
            try {
                commLibrary.sendMessage(LOGIN_ID);
                String received = commLibrary.getMessage();
                System.out.println(received);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(option.equals("0")) {
            try {
                commLibrary.sendMessage(EXIT_ID);
                try {
                    commLibrary.stopConnection();
                    System.out.println("Closing application. Thanks for using New Bank.");
                    System.exit(0);
                } catch (IOException e) {
                    System.out.println("Connection closed. Exiting...");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
