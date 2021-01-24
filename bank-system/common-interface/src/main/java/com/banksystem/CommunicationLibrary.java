package com.banksystem;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class CommunicationLibrary {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private static ObjectOutputStream objectOutput;
    private static ObjectInputStream objectInput;

    public CommunicationLibrary(){
        super();
    }

    public void createConnection(String IP, int port) throws Exception {
        clientSocket = new Socket(IP, port);
        objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInput = new ObjectInputStream(clientSocket.getInputStream());
    }

    /*
    public void sendMessage(String msg) throws Exception {
        out.writeUTF(msg);
        out.flush();
    }

    public void sendMessage(String msg, DataOutputStream out) throws Exception {
        out.writeUTF(msg);
        out.flush();
    }

    public String getMessage(DataInputStream in) throws IOException {
        return in.readUTF();
    }

    public String getMessage() throws IOException {
        return in.readUTF();
    }
    */
    public void sendMessage(Message message, ObjectOutputStream objectOutput){
        try {
            objectOutput.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message){
        try {
            objectOutput.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message getMessage(ObjectInputStream objectInput){
        try {
            Message message = (Message) objectInput.readObject();
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Message getMessage(){
        try {
            Message message = (Message) objectInput.readObject();
            return message;
        } catch (IOException e) {
            System.out.println("The socket for reading the object has problem");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void stopConnection() throws IOException {
        objectInput.close();
        objectOutput.close();
        clientSocket.close();
    }

    public Socket getSocket() {
        return clientSocket;
    }
}
