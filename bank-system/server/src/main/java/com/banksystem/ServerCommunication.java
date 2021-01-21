package com.banksystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCommunication {

    private static ServerSocket serverSocket;
    private static final int port = 6666;

    public static void receiveData(){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server is online and read to receive requests.");
        while(true) {
            System.out.println("Loop antes da thread");
            try {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress() + "  " + socket.getPort());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                CommunicationLibrary comm = new CommunicationLibrary();
                Thread t = new ServerCommunicationHandler(socket, dis, dout, comm);
                //System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
                t.start();
            } catch (IOException e) {
                System.out.println("Something went wrong, try again.");
                System.exit(0);
            }
            System.out.println("The new thread count is: " + Thread.activeCount());
        }
    }
}
