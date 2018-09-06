package main.java.app;

import main.java.app.model.Account;
import main.java.app.model.Skill;
import main.java.app.repository.AccountRepository;
import main.java.app.repository.SkillRepository;
import main.java.app.repository.io.JavaIOAccountRepositoryImpl;
import main.java.app.repository.io.JavaIOSkillRepositoryImpl;

import java.io.IOException;

public class AppRunner {
    public static void main(String[] args) throws IOException {

        AccountRepository ar = new JavaIOAccountRepositoryImpl();

        Account account = new Account();
        account.setId(1L);
        account.setLogin("Vladislav");

        Account account1 = new Account();
        account1.setId(2L);
        account1.setLogin("Vladislav");


        ar.save(account);
        ar.save(account1);

        Account account2 = new Account(1L,"Eugeniy");
        ar.update(account2);

        System.out.println(ar.getById(2L));
        System.out.println(ar.findAll());
        ar.delete(2L);

    }
}
