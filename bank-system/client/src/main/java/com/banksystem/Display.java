package com.banksystem;

public class Display {

    protected static void firstUserMenuDisplay(){
        System.out.println("\nMain Menu");
        System.out.println("1 - Login to my Account");
        System.out.println("2 - Create New Account");
        System.out.println("3 - Delete Account");
        System.out.println("0 - Exit Application");
        System.out.print("Option: ");
    }

    protected static void userMenuDisplay(){
        System.out.println("\nUser Menu");
        System.out.println("1 - Account balance");
        System.out.println("2 - Transfer money");
        System.out.println("3 - Deposit money");
        System.out.println("4 - See savings accounts");
        System.out.println("5 - Open savings account");
        System.out.println("6 - Account holder list");
        System.out.println("7 - Close account");
        System.out.println("8 - Read statement");
        System.out.println("0 - Exit Application");
        System.out.print("Option: ");
    }
}
