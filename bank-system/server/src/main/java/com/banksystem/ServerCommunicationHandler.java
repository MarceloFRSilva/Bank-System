package com.banksystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCommunicationHandler extends Thread {

    private static String LOGIN_ID = "1";
    private static Socket socket;

    public ServerCommunicationHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String received;
        DataInputStream dis;
        System.out.println("Chega aqui");
        try{
            dis = new DataInputStream(socket.getInputStream());
            received = new CommunicationLibrary().getMessage(dis);
            System.out.println("Aqui estamos 3");
            handleRequest(received);
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            new CommunicationLibrary().sendMessage("Message", dout);
            dout.close();
        } catch(IOException e){
            System.out.println("Node exited.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleRequest(String received){
        if(received.equals(LOGIN_ID)){
            System.out.println("The login request was sent to the server");
        }
    }
}
