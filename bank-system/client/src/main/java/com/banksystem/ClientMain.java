package com.banksystem;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMain {

    private static int port = 6666;

    public static void main(final String[] args) {
        UserInterfaceMenu.userInterfaceLauncher();
    }
}
