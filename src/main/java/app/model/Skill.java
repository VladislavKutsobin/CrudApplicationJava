package main.java.app.model;

public class Skill extends NameEntity {

    @Override
    public String toString() {
        return  this.getId() + "." + this.getName();
    }

    public Skill() {
    }

    public Skill(Long id, String name) {
        super(id, name);
    }
}