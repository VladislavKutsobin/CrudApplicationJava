package main.java.app;

import main.java.app.model.Account;
import main.java.app.model.Developers;
import main.java.app.model.Skill;
import main.java.app.repository.DeveloperRepository;
import main.java.app.repository.SkillRepository;
import main.java.app.repository.io.JavaDeveloperRepositoryImpl;
import main.java.app.repository.io.JavaIOSkillRepositoryImpl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class AppRunner {
    public static void main(String[] args) throws IOException {
        DeveloperRepository dr = new JavaDeveloperRepositoryImpl();

        Account acc = new Account(2L, "Vladimir");
        Account acc1 = new Account(3L, "Vladislav");
        Skill skill1 = new Skill(1L, "SQL");
        Skill skill2 = new Skill(2L, "Java");

        Set<Long> skillSet1 = new HashSet<>();
        skillSet1.add(skill1.getId());
        skillSet1.add(skill2.getId());
        Set<Long> skillSe12 = new HashSet<>();
        skillSe12.add(skill1.getId());


        Developers dev1 = new Developers(skillSet1, acc);
        dr.save(dev1);
        Developers dev2 = new Developers(skillSe12, acc1);
        dr.save(dev2);

        System.out.println(dr.findAll());

    }
}
