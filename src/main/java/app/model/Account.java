package main.java.app.model;

public class Account extends BaseEntity {
    private String developerData;

    public Account() {
    }

    public String getDeveloperData() {
        return developerData;
    }

    public void setDeveloperData(String developerData) {
        this.developerData = developerData;
    }

    public Account(Long id, String developerData) {
        super(id);
        this.developerData = developerData;
    }


    @Override
    public String toString() {
        return super.getId() + " " + developerData;
    }
}