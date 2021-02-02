package com.banksystem;

public class Display {

    protected static void firstBankWorkerMenuDisplay(){
        System.out.println("\nMain Menu");
        System.out.println("1 - Login to my Account");
        System.out.println("0 - Exit Application");
        System.out.print("Option: ");
    }

    protected static void bankWorkerMenuDisplay(){
        System.out.println("\nMain Menu");
        System.out.println("1 - Account list");
        System.out.println("2 - Manage account");
        System.out.println("3 - Online users list");
        System.out.println("0 - Exit Application");
        System.out.print("Option: ");
    }

    protected static void bankWorkerManageAccountMenuDisplay(){
        System.out.println("\nMain Menu");
        System.out.println("1 - See account details");
        System.out.println("2 - Delete account");
        System.out.println("3 - Add new user to account");
        System.out.println("4 - Remove user from account");
        System.out.println("0 - Exit Application");
        System.out.print("Option: ");
    }
}
