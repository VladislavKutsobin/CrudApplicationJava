package main.java.app.repository.io;

import main.java.app.model.Account;
import main.java.app.repository.AccountRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaIOAccountRepositoryImpl implements AccountRepository {
    private static final String ACCOUNT_FILE_PATH = "src/main/resources/accounts.txt";

    @Override
    public void save(Account account) throws IOException {
        File fileWithAccounts = new File(ACCOUNT_FILE_PATH);
        FileWriter fw = new FileWriter(fileWithAccounts, true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            String accountToStringRepresentation = account.getId() + "." + account.getDeveloperData();
            bw.write(accountToStringRepresentation);
            bw.newLine();
        }
    }

    @Override
    public List<Account> findAll() throws IOException {

        File fileWithAccounts = new File(ACCOUNT_FILE_PATH);
        List<String> list = new ArrayList<>();
        List<Account> accountList = new ArrayList<>();
        String line;


        FileReader fr = new FileReader(fileWithAccounts);
        try (BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            for (String aList : list) {
                String[] arrOfStr = aList.split("\\.");
                accountList.add(new Account(Long.parseLong(arrOfStr[0]), arrOfStr[1]));
            }
            return accountList;
        }
    }

    @Override
    public void update(Account account) throws IOException {
        File fileWithAccounts = new File(ACCOUNT_FILE_PATH);
        List<String> list = new ArrayList<>();
        String forReplace = account.getDeveloperData();
        String strIdForComparing = Long.toString(account.getId());
        String line;

        FileReader fr = new FileReader(fileWithAccounts);
        try (BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            for (int i = 0; i < list.size(); i++) {
                String[] arrOfStr = list.get(i).split("\\.");
                if (strIdForComparing.equals(arrOfStr[0])) {
                    list.set(i, arrOfStr[0] + "." + forReplace);
                    break;
                }
            }
        }

        FileWriter fw = new FileWriter(fileWithAccounts, false);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            for (String str : list) {
                bw.write(str + "\n");
            }
        }

    }

    @Override
    public void delete(Long id) throws IOException {
        File fileWithAccounts = new File(ACCOUNT_FILE_PATH);
        List<String> list = new ArrayList<>();
        FileReader fr = new FileReader(fileWithAccounts);
        String line;

        try (BufferedReader bf = new BufferedReader(fr)) {
            while ((line = bf.readLine()) != null) {

                String[] arrOfStr = line.split("\\.");
                if(arrOfStr[0].equals(Long.toString(id))){
                    continue;
                }else { list.add(line);
                }
            }

            FileWriter fw = new FileWriter(fileWithAccounts, false);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for (String str : list) {
                    bw.write(str + "\n");
                }
            }
        }

    }

    @Override
    public Account getById(Long id) throws IOException {
        File fileWithAccounts = new File(ACCOUNT_FILE_PATH);
        FileReader fr = new FileReader(fileWithAccounts);
        try (BufferedReader bf = new BufferedReader(fr)) {
            String line;

            while ((line = bf.readLine()) != null) {
                String[] arrOfStr = line.split("\\.");
                if(arrOfStr[0].equals(Long.toString(id))){
                    return new Account(id, arrOfStr[1]);
                }
            }
        }
        return null;
    }
}