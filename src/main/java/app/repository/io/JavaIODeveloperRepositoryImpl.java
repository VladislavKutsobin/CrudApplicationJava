package main.java.app.repository.io;

import main.java.app.model.Account;
import main.java.app.model.Developer;
import main.java.app.model.Skill;
import main.java.app.repository.AccountRepository;
import main.java.app.repository.DeveloperRepository;
import main.java.app.repository.SkillRepository;

import java.io.*;
import java.util.*;

//https://stackoverflow.com/questions/8846173/how-to-remove-first-and-last-character-of-a-string

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    private static final String DEV_FILE_PATH = "src/main/resources/developers.txt";

    @Override
    public void save(Developer developer) throws IOException {
        File fileWithDev = new File(DEV_FILE_PATH);
        FileWriter fw = new FileWriter(fileWithDev,true);

        // getting Skill's id and adding to list:
        Set<Skill> skillsFromThisDeveloper = developer.getSkills();
        List<Long> listWithSkillId = new ArrayList<>();
        for(Skill skill: skillsFromThisDeveloper){
            listWithSkillId.add(skill.getId());
        }

        try (BufferedWriter bw = new BufferedWriter(fw)) {
            String devToStringRepresentation = developer.getId() +
                    "." + developer.getFirstName() + " " + developer.getLastName() +
                    "." + developer.getSpecialty() +
                    "." + listWithSkillId +
                    "." + developer.getAccount().getId();
            bw.write(devToStringRepresentation);
            bw.newLine();  // ==> 100.Maya Podolskaya.Cat.[1, 2].10
        }
    }

    @Override
    public List<Developer> findAll() throws IOException {
        File fileWithDev = new File(DEV_FILE_PATH);
        List<String> developerRecordsList = new ArrayList<>();
        List<Developer> developerArrayList = new ArrayList<>();

        FileReader fr = new FileReader(fileWithDev);
        try (BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                developerRecordsList.add(line);
            }
            // parsing String as "100.Maya Podolskaya.Cat.[1, 2].10":
            for (String aList : developerRecordsList) {
                String[] developerRecordListSplitted = aList.split("\\."); // ==> [100,Maya Podolskaya,Cat,[1, 2],10]

                String developerIdStr = developerRecordListSplitted[0];
                Long developerId = Long.parseLong(developerIdStr);

                // -----parsing first/last name by space (Maya Podolskaya)------------
                String developerFirstLastNames = developerRecordListSplitted[1];
                String [] developerFirstLastNamesSplited = developerFirstLastNames.split(" ");
                String developerFirstName = developerFirstLastNamesSplited[0];
                String developerLastName = developerFirstLastNamesSplited[1];
                //--------------------------------------------------------------------
                String developerSpecialty = developerRecordListSplitted[2];
                String developerSkillsId = developerRecordListSplitted[3];

                //------------- instance of Account:-----------------------
                String developerAccountIdString = developerRecordListSplitted[4];
                Long developerAccountIdLong = Long.parseLong(developerAccountIdString);
                AccountRepository ar = new JavaIOAccountRepositoryImpl();
                Account account = ar.getById(developerAccountIdLong);

                //----------------Set of Skill's instance:----------------
                //  1) parsing String developerSkills from "[1, 2]" to 1,2 :
                String delBracketsFromDeveloperSkillsId = developerSkillsId.substring(1,developerSkillsId.length()-1);
                String [] developerSkillsIdArray = delBracketsFromDeveloperSkillsId.split(", ");
                Set <String> developerSkillsIdSetString = new HashSet<>(Arrays.asList(developerSkillsIdArray));

                //  2) Converting Set<String> to Set<Long>
                Set<Long> developerSkillsIdSetLong = new HashSet<>();
                for(String id : developerSkillsIdSetString){
                    developerSkillsIdSetLong.add(Long.parseLong(id));
                }

                // 3) getting instance of Skill and adding to Set:
                Set<Skill> developerSkills = new HashSet<>();
                for(Long id: developerSkillsIdSetLong){
                    SkillRepository sr = new JavaIOSkillRepositoryImpl();
                    Skill skill = sr.getById(id);
                    developerSkills.add(skill);
                }

                // ==============ArrayList od Developer's instance:======
                developerArrayList.add(new Developer(
                        developerId,
                        developerFirstName,
                        developerLastName,
                        developerSpecialty,
                        developerSkills,
                        account));
            }
        }
        return developerArrayList;
    }



    @Override
    public void update(Developer developer) throws IOException {
        File fileWithDev = new File(DEV_FILE_PATH);
        List<String> developerRecordsList = new ArrayList<>();
        String strIdForComparing = Long.toString(developer.getId());
        //------- getting Id from Skill--------------:
        Set<Skill> developerSkills = developer.getSkills();
        List<Long> developerSkillsId = new ArrayList<>();
        for(Skill skill : developerSkills){
            developerSkillsId.add(skill.getId());
        }

        FileReader fr = new FileReader(fileWithDev);
        try (BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                developerRecordsList.add(line);
            }

            for (int i = 0; i < developerRecordsList.size(); i++) {
                String[] developerRecordListSplitted = developerRecordsList.get(i).split("\\.");
                if (strIdForComparing.equals(developerRecordListSplitted[0])) {
                    developerRecordsList.set(i,
                            developer.getId() + "." +
                                    developer.getFirstName() + " " +
                                    developer.getLastName() + "." +
                                    developer.getSpecialty() + "." +
                                    developerSkillsId + "." +
                                    developer.getAccount().getId());
                    break;
                }
            }
            FileWriter fw = new FileWriter(fileWithDev, false);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for (String str : developerRecordsList) {
                    bw.write(str + "\n");
                }
            }
        }
    }

    @Override
    public void delete(Long id) throws IOException {
        File fileWithSkills = new File(DEV_FILE_PATH);
        List<String> developerRecordsList = new ArrayList<>();
        FileReader fr = new FileReader(fileWithSkills);

        try (BufferedReader bf = new BufferedReader(fr)) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] developerRecordListSplitted = line.split("\\.");
                if(developerRecordListSplitted[0].equals(Long.toString(id))){
                    continue;
                }else { developerRecordsList.add(line);
                }
            }
            // write updated content without deleted item from developerRecordsList to file:
            FileWriter fw = new FileWriter(fileWithSkills, false);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for (String str : developerRecordsList) {
                    bw.write(str + "\n");
                }
            }
        }
    }

    @Override
    public Developer getById(Long id) throws IOException {
        File fileWithDev = new File(DEV_FILE_PATH);
        FileReader fr = new FileReader(fileWithDev);
//        List<String> list = new ArrayList<>();

        try (BufferedReader bf = new BufferedReader(fr)) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] lineDeveloperInfo = line.split("\\.");
                String developerIdStr = lineDeveloperInfo[0];

                if(developerIdStr.equals(Long.toString(id))){

                    Long developerId = Long.parseLong(developerIdStr);

                    // -----parsing first/last name by space (Maya Podolskaya)------------
                    String developerFirstLastNames = lineDeveloperInfo[1];
                    String [] developerFirstLastNamesSplited = developerFirstLastNames.split(" ");
                    String developerFirstName = developerFirstLastNamesSplited[0];
                    String developerLastName = developerFirstLastNamesSplited[1];
                    //--------------------------------------------------------------------
                    String developerSpecialty = lineDeveloperInfo[2];
                    String developerSkillsId = lineDeveloperInfo[3];

                    //------------- instance of Account:-----------------------
                    String developerAccountIdString = lineDeveloperInfo[4];
                    Long developerAccountIdLong = Long.parseLong(developerAccountIdString);
                    AccountRepository ar = new JavaIOAccountRepositoryImpl();
                    Account account = ar.getById(developerAccountIdLong);

                    //----------------Set of Skill's instance:----------------
                    //  1) parsing String developerSkills from "[1, 2]" to 1,2 :
                    String delBracketsFromDeveloperSkillsId = developerSkillsId.substring(1,developerSkillsId.length()-1);
                    String [] developerSkillsIdArray = delBracketsFromDeveloperSkillsId.split(", ");
                    Set <String> developerSkillsIdSetString = new HashSet<>(Arrays.asList(developerSkillsIdArray));

                    //  2) Converting Set<String> to Set<Long>
                    Set<Long> developerSkillsIdSetLong = new HashSet<>();
                    for(String skillId : developerSkillsIdSetString){
                        developerSkillsIdSetLong.add(Long.parseLong(skillId ));
                    }

                    // 3) getting instance of Skill and adding to Set:
                    Set<Skill> developerSkills = new HashSet<>();
                    for(Long skillId : developerSkillsIdSetLong){
                        SkillRepository sr = new JavaIOSkillRepositoryImpl();
                        Skill skill = sr.getById(skillId );
                        developerSkills.add(skill);
                    }
                    return new Developer(
                            developerId,
                            developerFirstName,
                            developerLastName,
                            developerSpecialty,
                            developerSkills,
                            account);
                }
            }
        }
        return null;
    }
}
