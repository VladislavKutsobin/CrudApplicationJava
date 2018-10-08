package main.java.app.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import main.java.app.controller.AccountController;
import main.java.app.controller.DeveloperController;
import main.java.app.controller.SkillController;
import main.java.app.model.Account;
import main.java.app.model.Developer;
import main.java.app.model.Skill;



public class DeveloperView {
    Scanner scanner = new Scanner(System.in);
    DeveloperController developerController = new DeveloperController();
    SkillController skillController = new SkillController();
    AccountController accountController = new AccountController();

    public void getDeveloperMenu() throws IOException {
        System.out.println("Enter:" +"\n"+
                "'add' for adding new skill" +"\n"+
                "'delete' for deleting skill" +"\n"+
                "'show' for showing All the skills"+"\n"+
                "'menu' for return to main menu" + "\n" +
                "'exit' for exit");

        String input = scanner.next();

        while(!input.equals("exit")){
            switch(input){
                case "add":
                    Developer developerToSave = new Developer();

                    System.out.println("Enter id:");
                    input = scanner.next();
                    developerToSave.setId(Long.parseLong(input));

                    System.out.println("Enter the first name:");
                    input = scanner.next();
                    developerToSave.setFirstName(input);

                    System.out.println("Enter the last name:");
                    input = scanner.next();
                    developerToSave.setLastName(input);

                    System.out.println("Enter the speciality:");
                    input = scanner.next();
                    developerToSave.setSpecialty(input);
                    //--------Set of skills' logic---------------------------
                    SkillController skillController = new SkillController();
                    System.out.println("Choose some skills from this list and enter its id:" + "\n"
                            + skillController.findAll());
                    scanner.nextLine();
                    input = scanner.nextLine();
                    // 1) Separate user's input by comma:
                    String [] idSkills = input.split(",");
                    Set <String> idSkillsSetString = new HashSet<>(Arrays.asList(idSkills));
                    //  2) Converting Set<String> to Set<Long>
                    Set<Long> idSkillsSetLong = new HashSet<>();
                    for(String id : idSkillsSetString){
                        idSkillsSetLong.add(Long.parseLong(id));
                    }
                    // 3) getting instance of Skill and adding to Set:
                    Set<Skill> skillSet = new HashSet<>();
                    for(Long id: idSkillsSetLong){
                        Skill skillToSave = skillController.getById(id);
                        skillSet.add(skillToSave);
                    }
                    System.out.println("Now your skills are: " + skillSet);
                    developerToSave.setSkills(skillSet);
                    //-------------------------------------------------------
                    System.out.println("Choose your accout from this list and enter its id:" + "\n"
                            + accountController.findAll());
                    input = scanner.next();
                    Account accountToSave = accountController.getById(Long.parseLong(input));
                    developerToSave.setAccount(accountToSave);

                    developerController.save(developerToSave);

                    System.out.println("Your developer was successfully added!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "delete":
                    System.out.println("Enter id of deleting developer:");
                    input = scanner.next();
                    Long idDeletedDeveloper = Long.parseLong(input);
                    developerController.delete(idDeletedDeveloper);
                    System.out.println("Your developer was successfully deleted!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "show":
                    System.out.println(developerController.findAll());
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
