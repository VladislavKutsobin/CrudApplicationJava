package main.java.app.controller;

import main.java.app.model.Account;
import main.java.app.repository.AccountRepository;
import main.java.app.repository.io.JavaIOAccountRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class AccountController {
    AccountRepository accountRepository = new JavaIOAccountRepositoryImpl();

    public void save(Account account) throws IOException {
        if( account==null){
            throw new IllegalArgumentException();
        }else{
            accountRepository.save(account);
        }
    }

    public List<Account> findAll() throws IOException {
        return accountRepository.findAll();
    }


    public void update(Account account) throws IOException {
    }


    public void delete(Long id) throws IOException {
        if(id == null || id < 0) {
            throw new IllegalArgumentException();
        }else {

            accountRepository.delete(id);
        }
    }


    public Account getById(Long id) throws IOException {
        if(id == null || id < 0) {
            throw new IllegalArgumentException();
        }else {
            return accountRepository.getById(id);
        }
    }
}