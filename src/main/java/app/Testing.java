package main.java.app;

import main.java.app.model.Account;
import main.java.app.model.Developer;
import main.java.app.model.Skill;
import main.java.app.repository.AccountRepository;
import main.java.app.repository.DeveloperRepository;
import main.java.app.repository.SkillRepository;
import main.java.app.repository.io.JavaIOAccountRepositoryImpl;
import main.java.app.repository.io.JavaIODeveloperRepositoryImpl;
import main.java.app.repository.io.JavaIOSkillRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Testing {
    public static void main(String[] args) throws IOException {
        SkillRepository sr = new JavaIOSkillRepositoryImpl();
        AccountRepository ar = new JavaIOAccountRepositoryImpl();
        DeveloperRepository dv = new JavaIODeveloperRepositoryImpl();

        //-----------------SkillsMethodsTest-----------------------------------------


        Skill skill1 = new Skill(1L,"SQL");
        Skill skill2 = new Skill(2L,"CPP");
        Skill skill3 = new Skill(3L,"Java");

        Skill skill4 = new Skill(4L,"Python");
        Skill skill5 = new Skill(5L,"Math");

        Skill skill6 = new Skill(6L,"JavaScript");
        Skill skill7 = new Skill(7L,"HTML");

       /* sr.save(skill1);
        sr.save(skill2);
        sr.save(skill3);
        sr.save(skill4);
        sr.save(skill5);
        sr.save(skill6);
        sr.save(skill7);
*/
        //-----------------AccountsMethodsTest----------------------------------------

        Account ac1 = new Account(10L,"Maya likes milk");
        Account ac2 = new Account(20L,"Vlad likes beer");

        /*    ar.save(ac1);
        ar.save(ac2);
        ar.save(ac3);
        System.out.println(ar.findAll());
        System.out.println(ar.getById(10L));
        ar.delete(30L);
        Account ac4 = new Account(10L,"Maya likes fish"); // id is as the same as ac1
        ar.update(ac4);*/

        //-----------------DevelopersMethodsTest---------------------------------------

        Set<Skill>  skillSet1 = new HashSet<>();
        skillSet1.add(skill1);
        skillSet1.add(skill2);

        Set<Skill> skillSet2 = new HashSet<>();
        skillSet2.add(skill3);
        skillSet2.add(skill4);
        skillSet2.add(skill5);
        skillSet2.add(skill6);
        skillSet2.add(skill7);

        Developer dev1 = new Developer(100L,"Maya","Podolskaya","Cat",skillSet1,ac1);
        Developer dev2 = new Developer(200L,"Vladislav","Kutsobin","Java developer",skillSet2,ac2);
        /*dv.save(dev1);
        dv.save(dev2);*/

//......................Testing update.................................
        Set<Skill> skillSet3 = new HashSet<>();
        skillSet3.add(skill6);
        skillSet3.add(skill7);
        Account ac3 = new Account(30L,"Tom likes apples");
        Developer devTest = new Developer(200L,"Tom","Sawyer","literary hero",skillSet3,ac3);
//        dv.update(dev2);
//.....................................................................

        System.out.println(dv.findAll());
//        dv.delete(200L);
        System.out.println(dv.getById(200L));


    }
}