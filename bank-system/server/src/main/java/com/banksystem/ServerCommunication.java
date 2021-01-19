package com.banksystem;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerCommunication {

    private static ServerSocket serverSocket;
    private static int port = 6666;

    public static void receiveData(){
        try {
            serverSocket = new ServerSocket(port);
            Thread t = new ServerCommunicationHandler(serverSocket);
            //System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
            t.start();
        } catch (IOException e) {
            System.out.println("Something went wrong, try again.");
            System.exit(0);
            //e.printStackTrace();
        }
    }
}
