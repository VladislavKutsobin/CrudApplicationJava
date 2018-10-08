package main.java.app.model;

public class NameEntity extends BaseEntity {
    private String name;

    public NameEntity() {
    }

    public String getName() {
        return name;
    }

    public NameEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
