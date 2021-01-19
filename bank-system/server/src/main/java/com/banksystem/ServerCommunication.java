package com.banksystem;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCommunication {

    private static ServerSocket serverSocket;
    private static int port = 6666;

    public static void receiveData(){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress() + "  " + socket.getPort());
                System.out.println("Server is online and read to receive requests.");
                Thread t = new ServerCommunicationHandler(socket);
                System.out.println("New thread 3");
                //System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
                t.start();
            } catch (IOException e) {
                System.out.println("Something went wrong, try again.");
                System.exit(0);
            }
        }
    }
}
