package main.java.app.model;

public class Skill extends NameEntity {
    public Skill(Long id, String name) {
        super(id, name);
    }

    public Skill() {

    }

    @Override
    public String toString() {
        return this.getId() + "." + this.getName();
    }
}
