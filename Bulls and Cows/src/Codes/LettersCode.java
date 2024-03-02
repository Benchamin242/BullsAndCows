package Codes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LettersCode extends SecretCode{
    public LettersCode(String fileLocation) {
        File wordsCSV = new File("src/"+fileLocation);

        if (!wordsCSV.exists()){
            throw new RuntimeException("Word file not found");
        }

        Scanner sc;

        try{
            sc = new Scanner(wordsCSV);
        } catch (FileNotFoundException e){
            throw new RuntimeException("Word file not found");
        }

        ArrayList<String> wordsList = new ArrayList<>();

        sc.useDelimiter("\\n");
        while (sc.hasNext())
        {
            wordsList.add(sc.next());
        }
        sc.close();

        Random rand = new Random();
        int index = rand.nextInt(wordsList.size());

        super.deciferedString = wordsList.get(index);
    }
}
