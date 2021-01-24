package com.banksystem;

import java.io.*;
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
            try {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress() + "  " + socket.getPort());
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream dis = new ObjectInputStream(inputStream);
                ObjectOutputStream dout = new ObjectOutputStream(socket.getOutputStream());
                CommunicationLibrary comm = new CommunicationLibrary();
                Thread t = new ServerCommunicationHandler(socket, dis, dout, comm);
                t.start();
            } catch (IOException e) {
                System.out.println("Something went wrong, try again.");
                System.exit(0);
            }
        }
    }
}
