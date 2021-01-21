package com.banksystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerCommunicationHandler extends Thread {

    private static final String LOGIN_ID = "Login request";
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dout;
    private CommunicationLibrary comm;

    public ServerCommunicationHandler(Socket socket, DataInputStream dis, DataOutputStream dout, CommunicationLibrary comm) {
        this.socket = socket;
        this.dis = dis;
        this.dout = dout;
        this.comm = comm;
    }

    @Override
    public void run() {
        System.out.println("New thread launched. Thread count: " + Thread.activeCount());
        String received;
        while(!socket.isClosed()) {
            System.out.println("Loop na thread");
            try {
                received = comm.getMessage(dis);
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
                comm.sendMessage("Ok, Login Handled", dout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
