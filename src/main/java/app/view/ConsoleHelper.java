package main.java.app.view;

import main.java.app.controller.SkillController;
import main.java.app.model.Skill;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleHelper {
    Scanner scanner = new Scanner(System.in);

    SkillView sv = new SkillView();
    AccountView av = new AccountView();
    DeveloperView dv = new DeveloperView();

    public void start() throws IOException {
        getMenu ();
    }

    public void getMenu() throws IOException {
        // some logic for choosing sv||av||dv
        System.out.println("Enter S - for Skill, A - for Account, D - for Developer");
        String choosingEntity = scanner.next();
        switch (choosingEntity){
            case "S":
                sv.getSkillMenu();
                break;
            case "A":
                av.getAccountMenu();
                break;
            case "D":
                dv.getDeveloperMenu();
                break;
            default:
                System.out.println("You entered not correct letter!See ya!");
        }

    }
}