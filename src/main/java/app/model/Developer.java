package main.java.app.model;

import java.util.Set;

public class Developer extends BaseEntity{

    private String firstName;
    private String lastName;
    private Set<Skill> skills;
    private Account account;
    private String specialty;

    public Developer(Long id,
                     String firstName,
                     String lastName,
                     String specialty,
                     Set<Skill> skills,
                     Account account) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.account = account;
        this.specialty = specialty;
    }

    public Developer() {

    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
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

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "{" +
                "id = "+ getId() +
                ", name = '" + firstName + " " + lastName + '\'' +
                ", specialty = '" + specialty + '\'' +
                ", skills =" + skills +
                ", account id = " + account.getId() +
                ", food_preferences: " + account.getDeveloperData() +
                '}' + "\n";
    }
}