package com.banksystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterfaceMenu {

    private static int port = 6666;
    private static final String LOGIN_ID = "Login request";

    public static void userInterfaceLauncher(){
        CommunicationLibrary commLibrary = new CommunicationLibrary();
        while(commLibrary.getSocket() == null || !commLibrary.getSocket().isConnected()) {
            try {
                commLibrary.createConnection("localhost", port);
            } catch (Exception e) {
                continue;
            }
        }
        while(true) {
            Display.firstUserMenuDisplay();
            try {
                String option = getUserInput();
                processRequest(option, commLibrary);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        /*try {
            commLibrary.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
    }
}
