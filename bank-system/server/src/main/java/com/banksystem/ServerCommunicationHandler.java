package com.banksystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCommunicationHandler extends Thread {

    private static ServerSocket serverSocket;

    public ServerCommunicationHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        String received;
        DataInputStream dis;
        while(true){
            try{
                Socket socket = serverSocket.accept();
                dis = new DataInputStream(socket.getInputStream());
                received = new CommunicationLibrary().getMessage(dis);
                System.out.println(received);
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                new CommunicationLibrary().sendMessage("Message", dout);
            } catch(IOException e){
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
