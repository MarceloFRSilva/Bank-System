package com.banksystem;

public class Display {

    protected static void firstAdminMenuDisplay(){
        System.out.println("\nMain Menu");
        System.out.println("1 - Login to my Account");
        System.out.println("0 - Exit Application");
        System.out.print("Option: ");
    }

    protected static void adminMenuDisplay(){
        System.out.println("\nMain Menu");
        System.out.println("1 - Account list");
        System.out.println("2 - Manage account");
        System.out.println("3 - Online users list");
        System.out.println("0 - Exit Application");
        System.out.print("Option: ");
    }
}
