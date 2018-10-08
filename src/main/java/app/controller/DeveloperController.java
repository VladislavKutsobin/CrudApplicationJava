package main.java.app.controller;

import main.java.app.model.Developer;
import main.java.app.repository.DeveloperRepository;
import main.java.app.repository.io.JavaIODeveloperRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class DeveloperController {
    DeveloperRepository developerRepository = new JavaIODeveloperRepositoryImpl();


    public void save(Developer developer) throws IOException {
        if(developer==null){
            throw new IllegalArgumentException();
        }else{
            developerRepository.save(developer);
        }

    }
    public List<Developer> findAll() throws IOException {
        return developerRepository.findAll();
    }


    public void update(Developer developer) throws IOException {

    }

    public void delete(Long id) throws IOException {
        if(id == null || id < 0) {
            throw new IllegalArgumentException();
        }else {

            developerRepository.delete(id);
        }
    }

    public Developer getById(Long id) throws IOException {
        if(id == null || id < 0) {
            throw new IllegalArgumentException();
        }else {

            developerRepository.getById(id);
        }
        return null;
    }
}