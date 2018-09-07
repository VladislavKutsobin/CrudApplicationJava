package main.java.app.repository.io;

import main.java.app.model.Account;
import main.java.app.model.Developers;
import main.java.app.repository.DeveloperRepository;

import java.io.*;
import java.util.*;

public class JavaDeveloperRepositoryImpl implements DeveloperRepository {
    private static final  String DEVELOPER_FILE_PATH = "src/main/resources/developers.txt";

    @Override
    public void save(Developers developers) throws IOException {
        File fn = new File(DEVELOPER_FILE_PATH);
        FileWriter fw = new FileWriter(fn, true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            String developerToStringRepresentation = developers.getAccount() + "." + developers.getSkills();
            bw.write(developerToStringRepresentation);
            bw.newLine();
        }
    }

    @Override
    public List<Developers> findAll() throws IOException {
        File fileWithDev = new File(DEVELOPER_FILE_PATH);
        List<String> list = new ArrayList<>();
        List<Developers> DevList = new ArrayList<>();
        String line;

        FileReader fr = new FileReader(fileWithDev);
        try (BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            // parsing String like "2.Bob-[4, 5, 6]"
            for (String aList : list) {
                String[] arrOfStr = aList.split("/");
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
                DevList.add(new Developers(setOfId,new Account(Long.parseLong(arrOfStr[0]),arrOfStr2[0])));
            }
        }
        return DevList;
    }

    @Override
    public void update(Developers developers) throws IOException {

    }

    @Override
    public void delete(Long aLong) throws IOException {

    }

    @Override
    public Developers getById(Long aLong) throws FileNotFoundException, IOException {
        return null;
    }
}