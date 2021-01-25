package com.banksystem;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterfaceMenu {

    private static int port = 6666;
    private static final String LOGIN_ID = "LOGIN_ID";
    private static final String REGISTER_ID = "REGISTER_ID";
    private static final String EXIT_ID = "EXIT_ID";
    private static final String OK_ID = "OK_ID";
    private static final String ERROR_ID = "ERROR_ID";
    private static Client client;

    public static void userInterfaceLauncher(){
        CommunicationLibrary commLibrary = new CommunicationLibrary();
        while(commLibrary.getSocket() == null || !commLibrary.getSocket().isConnected()) {
            try {
                commLibrary.createConnection("localhost", port);
            } catch (Exception e) {
                continue;
            }
        }
        System.out.println("Welcome to the New Bank.");
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
                System.out.print("Email: ");
                String email = getUserInput();
                Console console = System.console() ;
                char[] password = console.readPassword("Enter password: ");
                Message message = new Message(LOGIN_ID);
                User user = new User();
                user.setEmail(email);
                user.setPassword(String.valueOf(password));
                message.setUser(user);
                commLibrary.sendMessage(message);
                Message received = commLibrary.getMessage();
                System.out.println(received.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(option.equals("2")) {
            System.out.print("First Name: ");
            String first_name = getUserInput();
            System.out.print("Last Name: ");
            String last_name = getUserInput();
            System.out.print("Email: ");
            String email = getUserInput();
            System.out.print("Phone number: ");
            String phone_number = getUserInput();
            char[] password;
            while(true) {
                Console console = System.console() ;
                password = console.readPassword("Enter password: ");
                char[] password_confirmation = console.readPassword("Confirm password: ");
                if(String.valueOf(password).equals(String.valueOf(password_confirmation))){
                    break;
                }
                System.out.println("Passwords do not match, try again.");
            }
            try {
                User user = new User(first_name, last_name, email, phone_number, String.valueOf(password));
                Message message = new Message(REGISTER_ID);
                message.setUser(user);
                System.out.println("Sending messages to the Server");
                commLibrary.sendMessage(message);
                Message received = commLibrary.getMessage();
                System.out.println(received.getText());
                if(received.getText().equals(OK_ID)){
                    System.out.println("User created successfully");
                    System.out.println("Account ID: " + received.getUser().getAccountNumber());
                    client = new Client(first_name, last_name, email, phone_number, String.valueOf(password));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(option.equals("0")) {
            try {
                Message message = new Message(EXIT_ID);
                commLibrary.sendMessage(message);
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
