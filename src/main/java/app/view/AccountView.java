package main.java.app.view;

import main.java.app.controller.AccountController;
import main.java.app.model.Account;


import java.io.IOException;
import java.util.Scanner;

public class AccountView {
    Scanner scanner = new Scanner(System.in);
    AccountController accountController = new AccountController();


    public void getAccountMenu() throws IOException {
        System.out.println("Enter:" +"\n"+
                "'add' for adding new account" +"\n"+
                "'delete' for deleting account" +"\n"+
                "'show' for showing all the account"+"\n"+
                "'menu' for return to main menu" + "\n" +
                "'exit' for exit");

        String input = scanner.nextLine();
        Account accountToSave = new Account();

        while(!input.equals("exit")){
            switch(input){
                case "add":
                    System.out.println("Enter id:");
                    input = scanner.next();
                    accountToSave.setId(Long.parseLong(input));

                    System.out.println("Enter some developer's info for saving:");
                    scanner.nextLine();
                    input = scanner.nextLine();
                    // https://stackoverflow.com/questions/19335420/scan-nextline-does-not-work
                    accountToSave.setDeveloperData(input);
                    accountController.save(accountToSave);
                    System.out.println("Your account was successfully added!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "delete":
                    System.out.println("Enter id of deleting account:");
                    input = scanner.next();
                    Long idDeletingAccount = Long.parseLong(input);
                    accountController.delete(idDeletingAccount);
                    System.out.println("Your account was successfully deleted!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "show":
                    System.out.println(accountController.findAll());
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "menu":
                    ConsoleHelper ch = new ConsoleHelper();
                    ch.getMenu();

                default:
                    System.out.println("Please,make your choice!");
                    input = scanner.next();
            }
        }
    }
}
