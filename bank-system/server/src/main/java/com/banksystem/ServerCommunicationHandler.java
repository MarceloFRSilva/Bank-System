package com.banksystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerCommunicationHandler extends Thread {

    private static final String LOGIN_ID = "Login request";
    private static Socket socket;
    private static DataInputStream dis;
    private static DataOutputStream dout;

    public ServerCommunicationHandler(Socket socket, DataInputStream dis, DataOutputStream dout) {
        this.socket = socket;
        this.dis = dis;
        this.dout = dout;
    }

    @Override
    public void run() {
        System.out.println("New thread launched. Thread count: " + Thread.activeCount());
        String received;
        while(!socket.isClosed()) {
            try {
                received = new CommunicationLibrary().getMessage(dis);
                handleRequest(received);
            } catch (IOException e) {
                System.out.println("Node exited.");
                break;
            } catch (Exception e) {
                System.out.println(e);
                break;
            }
        }
    }

    public void handleRequest(String received){
        if(received.equals(LOGIN_ID)){
            System.out.println("The login request was sent to the server");
            try {
                new CommunicationLibrary().sendMessage("Ok, Login Handled", dout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
