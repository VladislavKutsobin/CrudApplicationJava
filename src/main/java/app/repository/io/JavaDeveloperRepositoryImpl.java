package main.java.app.repository.io;

import main.java.app.model.Account;
import main.java.app.model.Developer;
import main.java.app.repository.DeveloperRepository;

import java.io.*;
import java.util.*;

public class JavaDeveloperRepositoryImpl implements DeveloperRepository {
    private static final  String DEVELOPER_FILE_PATH = "src/main/resources/developers.txt";

    @Override
    // TODO: Переписал метод, изменения связаны с изменениями в классе Developer, смотри его
    public void save(Developer developer) throws IOException {
        File fn = new File(DEVELOPER_FILE_PATH);
        FileWriter fw = new FileWriter(fn, true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            String developerToStringRepresentation = developer.getId() + "." + developer.getFirstName() + " " +
                    developer.getLastName() + "; " + developer.getSpecialty();
            bw.write(developerToStringRepresentation);
            bw.newLine();
        }
    }

    @Override
    // TODO: Return Developer(ID, firstname, lastname, Set<String> specialty)
    public List<Developer> findAll() throws IOException {
        File fileWithDevelopers = new File(DEVELOPER_FILE_PATH);
        List<Developer> RepresentationDevelopersList = new ArrayList<>();
        List<String> listWithSplitLines = new ArrayList<>();
        Set<String> setWithSkills = new HashSet<>();
        String lineWithDeveloperInformationData;

        FileReader fr = new FileReader(fileWithDevelopers);
        try(BufferedReader br = new BufferedReader(fr)) {
            while ((lineWithDeveloperInformationData = br.readLine()) != null) {
                listWithSplitLines.add(lineWithDeveloperInformationData);
            }
            for(String s : listWithSplitLines) {
                String[] arrayWithSplitByDot = s.split("\\."); // разбиваем на id и фио + скилы
                String[] arrayWithSplitComa = arrayWithSplitByDot[1].split("; ");   // ращбиваем на фио и скилы
                String[] arrayWithSplitByFirstLastName = arrayWithSplitComa[0].split(" ");  // разбиваем фио на имя и фамилию
                // Вытягиваем строку со скилами без [...]
                String skillsWithoutBrackets = arrayWithSplitComa[1].substring(1, arrayWithSplitComa[1].length()-1);
                String[] arrayWithSplitByComaInSkills = skillsWithoutBrackets.split(", ");  // бьем строку со скилами по запятой
                for(String skillsLine : arrayWithSplitByComaInSkills) {
                    setWithSkills.add(skillsLine);  // создаем множество со скилами String в виде id + ) + skillName
                }

                Long id = Long.parseLong(arrayWithSplitByDot[0]);
                String firstName = arrayWithSplitByFirstLastName[0];
                String lastName = arrayWithSplitByFirstLastName[1];
                RepresentationDevelopersList.add(new Developer(id, firstName, lastName, setWithSkills));
            }
        }
        return RepresentationDevelopersList; // TODO: Method is DONE(посмотришь, может, что можно переписать)
    }

    @Override
    public void update(Developer developer) throws IOException {

        /*File fileWithDev = new File(DEVELOPER_FILE_PATH);
        List<String> list = new ArrayList<>();

        Account account= developer.getAccount();
        Set<Long> setSkills = developers.getSkills();
        String strIdForComparing = Long.toString(account.getId());
        String line;

        FileReader fr = new FileReader(fileWithDev);
        try (BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            for (int i = 0; i < list.size(); i++) {
                String[] arrOfStr = list.get(i).split("/");
                if (strIdForComparing.equals(arrOfStr[0])) {
                    list.set(i,account + "." + setSkills);
                    break;
                }
            }

            FileWriter fw = new FileWriter(fileWithDev, false);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for (String str : list) {
                    bw.write(str + "\n");
                }
            }
        }*/
    }

    @Override
    public void delete(Long aLong) throws IOException {
        /*File fileWithDevelopers = new File(DEVELOPER_FILE_PATH);
        List<String> list = new ArrayList<>();
        FileReader fr = new FileReader(fileWithDevelopers);
        String line;

        try (BufferedReader bf = new BufferedReader(fr)) {
            while ((line = bf.readLine()) != null) {

                String[] arrOfStr = line.split("/");
                if(arrOfStr[0].equals(Long.toString(aLong))){
                    continue;
                }else { list.add(line);
                }
            }
            // write updated content without deleted item from ArrayList to file:
            FileWriter fw = new FileWriter(fileWithDevelopers, false);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for (String str : list) {
                    bw.write(str + "\n");
                }
            }
        }
        */
    }

    @Override
    public Developer getById(Long aLong) throws FileNotFoundException, IOException {
        /*File file = new File(DEVELOPER_FILE_PATH);
        FileReader fr = new FileReader(file);
        String line;

        try (BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("/");
                System.out.println(arrOfStr[0].equals((Long.toString(aLong))));
                if(arrOfStr[0].equals(Long.toString(aLong))){
                    String [] arrOfStr2 =  arrOfStr[1].split("\\.");
                    String delBracket = arrOfStr2[1].substring(1,arrOfStr2[1].length()-1);
                    String [] arrOfStr3 = delBracket.split(", ");
                    Set<String> setOfStrId = new HashSet<>(Arrays.asList(arrOfStr3));

                // Converting Set<String> to Set<Long>
                    Set<Long> setOfId = new HashSet<>();
                    for(String s : setOfStrId){
                        Long g = Long.parseLong(s);
                        setOfId.add(g);
                    }

                    return new Developers(setOfId, new Account(Long.parseLong(arrOfStr[0]),arrOfStr2[0]));
                }
            }
        }*/
        return null;
    }
}
