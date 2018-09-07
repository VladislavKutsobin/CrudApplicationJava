package main.java.app.model;

public class Account extends BaseEntity {
    private String login;
    private String password;

    public Account(Long id, String login) {
        super(id);
        this.login = login;
        //this.password = password;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.getId() + "/" + this.getLogin();
    }
}
