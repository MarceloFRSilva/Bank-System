package com.banksystem;

public class UserInterfaceMenu {

    private static int port = 6666;

    public static void userInterfaceLauncher(String command){
        CommunicationLibrary commLibrary = new CommunicationLibrary();
        try{
            commLibrary.createConnection("localhost", port);
            commLibrary.sendMessage("Hello Server");
            String received = new CommunicationLibrary().getMessage();
            System.out.println(received);
            commLibrary.stopConnection();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
