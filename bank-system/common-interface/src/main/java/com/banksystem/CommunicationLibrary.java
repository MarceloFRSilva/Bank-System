package com.banksystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicationLibrary {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private static DataOutputStream out;
    private static DataInputStream in;

    public CommunicationLibrary(){
        super();
    }

    public void createConnection(String IP, int port) throws Exception {
        clientSocket = new Socket(IP, port);
        out = new DataOutputStream(clientSocket.getOutputStream());
        in = new DataInputStream(clientSocket.getInputStream());
    }

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

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public Socket getSocket() {
        return clientSocket;
    }
}
