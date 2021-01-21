package com.banksystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerCommunicationHandler extends Thread {

    /****************
     * Request list *
     ****************/
    private static final String LOGIN_ID = "Login request";
    private static final String EXIT_ID = "Exit request";


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
                Thread.currentThread().interrupt();
                System.out.println("Node exited.");
                System.out.println("The new thread count is: " + getNewThreadCount());
                return;
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted. Exiting...");
                System.out.println("The new thread count is: " + getNewThreadCount());
                return;
            }
        }
    }

    private void handleRequest(String received) throws IOException {
        if(received.equals(LOGIN_ID)){
            System.out.println("The login request was sent to the server");
            try {
                DataBaseManagement.testDB();
                comm.sendMessage("Ok, Login Handled", dout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(received.equals(EXIT_ID)){
            System.out.println("The exit request was sent to the server");
            throw new IOException();
        }
    }

    private int getNewThreadCount(){
        int numberOfThreads = Thread.activeCount();
        return numberOfThreads -1;
    }
}
