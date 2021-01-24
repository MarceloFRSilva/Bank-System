package com.banksystem;

import java.io.*;
import java.net.Socket;

public class ServerCommunicationHandler extends Thread {

    /****************
     * Request list *
     ****************/
    private static final String LOGIN_ID = "LOGIN_ID";
    private static final String REGISTER_ID = "REGISTER_ID";
    private static final String EXIT_ID = "EXIT_ID";
    private static final String OK_ID = "OK_ID";


    private Socket socket;
    private ObjectInputStream dis;
    private ObjectOutputStream dout;
    private CommunicationLibrary comm;

    public ServerCommunicationHandler(Socket socket, ObjectInputStream dis, ObjectOutputStream dout, CommunicationLibrary comm) {
        this.socket = socket;
        this.dis = dis;
        this.dout = dout;
        this.comm = comm;
    }

    @Override
    public void run() {
        System.out.println("New thread launched. Thread count: " + Thread.activeCount());
        Message received;
        while(!socket.isClosed()) {
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

    private void handleRequest(Message received) throws IOException {
        if(received.getText().equals(LOGIN_ID)){
            System.out.println("The login request was sent to the server");
            try {
                Message message = new Message("Ok, Login Handled");
                comm.sendMessage(message, dout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(received.getText().equals(REGISTER_ID)){
            System.out.println("The register request was sent to the server");
            try {
                String temp = received.getUser().getFirst_name();
                System.out.println(temp);
                Message message = new Message(OK_ID);
                comm.sendMessage(message, dout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(received.getText().equals(EXIT_ID)){
            System.out.println("The exit request was sent to the server");
            throw new IOException();
        }
    }

    private int getNewThreadCount(){
        int numberOfThreads = Thread.activeCount();
        return numberOfThreads -1;
    }
}
