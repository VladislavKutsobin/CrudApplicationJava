package main.java.app.view;

import main.java.app.controller.SkillController;
import main.java.app.model.Skill;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class SkillView {
    SkillController skillController = new SkillController();
    static Scanner scanner = new Scanner(System.in);

    public void getSkillMenu() throws IOException {

        System.out.println("Enter:" +"\n"+
                "'add' for adding new skill" +"\n"+
                "'delete' for deleting skill" +"\n"+
                "'show' for showing All the skills"+"\n"+
                "'menu' for return to main menu" + "\n" +
                "'exit' for exit");

        String input = scanner.next();
        Skill skillToSave = new Skill();

        while(!input.equals("exit")){
            switch(input){
                case "add":
                    System.out.println("Enter id:");
                    input = scanner.next();
                    skillToSave.setId(Long.parseLong(input));

                    System.out.println("Enter name of the skill:");
                    input = scanner.next();
                    skillToSave.setName(input);
                    skillController.save(skillToSave);

                    System.out.println("Your skill was successfully added!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "delete":
                    System.out.println("Enter id of deleting skill:");
                    input = scanner.next();
                    Long idDeletingSkill = Long.parseLong(input);
                    skillController.delete(idDeletingSkill);
                    System.out.println("Your skill was successfully deleted!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;


                case "show":
                    System.out.println(skillController.findAll());
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