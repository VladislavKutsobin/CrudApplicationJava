package main.java.app.controller;

import main.java.app.model.Skill;

import main.java.app.repository.SkillRepository;
import main.java.app.repository.io.JavaIOSkillRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class SkillController {

    SkillRepository skillRepository = new JavaIOSkillRepositoryImpl();

    public void save(Skill skill) throws IOException {
        if( skill==null){
            throw new IllegalArgumentException();
        }else{
            skillRepository.save(skill);
        }
    }

    public List<Skill> findAll() throws IOException {
        return skillRepository.findAll();
    }

    public void update(Skill skill) throws IOException {
        if(skill==null){
            throw new IllegalArgumentException();
        }
    }

    public void delete(Long id) throws IOException {
        if(id == null || id < 0) {
            throw new IllegalArgumentException();
        }else {

            skillRepository.delete(id);
        }
    }

    public Skill getById(Long id) throws IOException {
        if(id == null || id < 0) {
            throw new IllegalArgumentException();
        }else {
            return skillRepository.getById(id);
        }
    }
}