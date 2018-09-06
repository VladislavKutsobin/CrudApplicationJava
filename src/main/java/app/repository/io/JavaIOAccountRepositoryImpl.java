package main.java.app.repository.io;

import main.java.app.model.Account;
import main.java.app.model.Skill;
import main.java.app.repository.AccountRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaIOAccountRepositoryImpl implements AccountRepository {
    private static final String ACCOUNT_FILE_PATH = "src/main/resources/Accounts.txt";

    @Override
    public void save(Account account) throws IOException {
        File fn = new File(ACCOUNT_FILE_PATH);
        FileWriter fw = new FileWriter(fn, true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            String skillToStringRepresentation = account.getId() + "." + account.getLogin();
            bw.write(skillToStringRepresentation);
            bw.newLine();
        }
    }

    @Override
    public List<Account> findAll() throws IOException {
        File fileWithSkills = new File(ACCOUNT_FILE_PATH);
        List<String> list = new ArrayList<>();
        List<Account> AccountList = new ArrayList<>();
        String line;

        // read file and add to ArrayList:
        FileReader fr = new FileReader(fileWithSkills);
        try (BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            //creat AList of Skill's instances
            for (String aList : list) {
                String[] arrOfStr = aList.split("\\.");
                AccountList.add(new Account(Long.parseLong(arrOfStr[0]), arrOfStr[1]));
            }
            return AccountList;
        }
    }

    @Override
    public void update(Account account) throws IOException {
        File fileWithSkills = new File(ACCOUNT_FILE_PATH);
        List<String> list = new ArrayList<>();
        String forReplace = account.getLogin();
        String strIdForComparing = Long.toString(account.getId());
        String line;
        // read file and add to ArrayList:
        FileReader fr = new FileReader(fileWithSkills);
        try (BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            // iterating over ArrayList and replace:
            for (int i = 0; i < list.size(); i++) {
                String[] arrOfStr = list.get(i).split("\\.");
                if (strIdForComparing.equals(arrOfStr[0])) {
                    list.set(i, arrOfStr[0] + "." + forReplace);
                    break;
                }
            }
        }
        // write updated content from ArrayList to file:
        FileWriter fw = new FileWriter(fileWithSkills, false);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            for (String str : list) {
                bw.write(str + "\n");
            }
        }
    }

    @Override
    public void delete(Long id) throws IOException{
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
            // write updated content without deleted item from ArrayList to file:
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
        File file = new File(ACCOUNT_FILE_PATH);
        FileReader fr = new FileReader(file);
        BufferedReader bf = new BufferedReader(fr);
        String line;
        while ((line = bf.readLine()) != null) {
            String[] retLine = line.split("\\.");
            if (retLine[0].equals(Long.toString(id))) {
                return new Account(id, retLine[1]);
            }
        }
        return null;
    }
}
