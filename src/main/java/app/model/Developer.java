package main.java.app.model;

import main.java.app.repository.SkillRepository;


import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Developer extends BaseEntity {
    private String firstName;
    private String lastName;
    private Set<String> specialty;

    public Developer(Long id,String firstName, String lastName,Set<String> specialty) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getSpecialty() {
        return specialty;
    }

    /*public String getSpecialtyInString() {
        String returnLine = "";
        for(String s : this.specialty) {
            returnLine += s + "; ";
        }
        return returnLine;
    }*/

    public void setSpecialty(Set<String> specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return this.getId() + ". " + this.firstName + " " + this.lastName + ", " + this.specialty;
    }
}
