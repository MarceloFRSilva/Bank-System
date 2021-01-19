package com.banksystem;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMain {

    private static int port = 6666;

    public static void main(final String[] args) {
        System.out.println("Aqui!!");

        ServerCommunication.receiveData();
        /*try {
            ServerSocket ss = new ServerSocket(port);
            Socket s = ss.accept(); //establishes connection
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String str = CommunicationLibrary.getMessage(dis);
            System.out.println("message= " + str);
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }*/
    }

}
