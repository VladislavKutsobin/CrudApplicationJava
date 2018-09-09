package main.java.app;

import main.java.app.model.Account;
import main.java.app.model.Developer;
import main.java.app.model.Skill;
import main.java.app.repository.AccountRepository;
import main.java.app.repository.DeveloperRepository;
import main.java.app.repository.SkillRepository;
import main.java.app.repository.io.JavaDeveloperRepositoryImpl;
import main.java.app.repository.io.JavaIOAccountRepositoryImpl;
import main.java.app.repository.io.JavaIOSkillRepositoryImpl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class AppRunner {
    public static void main(String[] args) throws IOException {
        /*SkillRepository sr = new JavaIOSkillRepositoryImpl();
        AccountRepository ar = new JavaIOAccountRepositoryImpl();
        DeveloperRepository dv = new JavaDeveloperRepositoryImpl();

        Account ac1 = new Account(1L,"Maya");
        Account ac2 = new Account(2L,"Bob");


        Skill skill1 = new Skill(1L,"SQL");
        Skill skill2 = new Skill(2L,"C");
        Skill skill3 = new Skill(3L,"Java");

        Skill skill4 = new Skill(4L,"Python");
        Skill skill5 = new Skill(5L,"Django");

        Skill skill6 = new Skill(6L,"JavaScript");
        Skill skill7 = new Skill(7L,"HTML");

        Set<Long>  skillSet1 = new HashSet<>();
        skillSet1.add(skill1.getId());
        skillSet1.add(skill2.getId());

        Set<Long > skillSet2 = new HashSet<>();
        skillSet2.add(skill3.getId());
        skillSet2.add(skill4.getId());
        skillSet2.add(skill5.getId());

        Developers dev1 = new Developers(skillSet1,ac1);
        Developers dev2 = new Developers(skillSet2,ac2);

        dv.save(dev1);
        dv.save(dev2);

        Set<Long> skillSet3 = new HashSet<>();
        skillSet3.add(skill6.getId());
        skillSet3.add(skill7.getId());

        Account acTest = new Account(1L,"TestSkill"); //id = 1 ==> is the same as in ac1
        Developers devTest = new Developers(skillSet3,acTest);
        dv.update(devTest);

        System.out.println(dv.findAll());
        System.out.println(dv.getById(2L));
        */
        SkillRepository sr = new JavaIOSkillRepositoryImpl();
        DeveloperRepository dr = new JavaDeveloperRepositoryImpl();

        Skill sk1 = new Skill(1L, "Java");
        Skill sk2 = new Skill(2L, "Python");

        Set<String> skillSet = new HashSet<>();
        skillSet.add(sk1.getId()+ ")" + sk1.getName());
        skillSet.add(sk2.getId()+ ")" + sk2.getName());

        //Developer dev = new Developer(1L, "Vladislav", "Kutsobin", skillSet);
        //dr.save(dev);
        //Developer dev1 = new Developer(2L, "Vladimir", "Javovich", skillSet);
        //dr.save(dev1);

        System.out.println(dr.findAll());


    }
}
