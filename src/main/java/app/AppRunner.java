package main.java.app;

import main.java.app.model.Skill;
import main.java.app.repository.SkillRepository;
import main.java.app.repository.io.JavaIOSkillRepositoryImpl;

import java.io.IOException;

public class AppRunner {
    public static void main(String[] args) throws IOException {

        SkillRepository sr = new JavaIOSkillRepositoryImpl();

        Skill skill1 = new Skill();
        skill1.setId(1L);
        skill1.setName("Python");

        Skill skill2 = new Skill();
        skill2.setId(2L);
        skill2.setName("Java");

        Skill skill3 = new Skill();
        skill3.setId(3L);
        skill3.setName("CPP");

        Skill skill4 =new Skill();
        skill4.setId(4L);
        skill4.setName("CSS");


        sr.save(skill1);
        sr.save(skill2);
        sr.save(skill3);
        sr.save(skill4);

        Skill skill5 = new Skill(1L,"JavaSript");
        sr.update(skill5);

        Skill skill6 = new Skill(2L,"HTML");
        sr.update(skill6);

        System.out.println(sr.getById(2L));
        System.out.println(sr.findAll());
        sr.delete(3L);

    }
}
