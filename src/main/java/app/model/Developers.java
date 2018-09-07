package main.java.app.model;

import main.java.app.repository.SkillRepository;


import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Developers {
    private Account account;
    private Set<Long> skillId;


    public Developers(Set<Long> skillId, Account account) throws IOException {
        this.skillId = skillId;
        this.account = account;
    }

    public Set<Long> getSkills() {
        return skillId;
    }

    public void setSkillId(Set<Long> skillId) {
        this.skillId = skillId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return skillId + " " + account;
    }
}
