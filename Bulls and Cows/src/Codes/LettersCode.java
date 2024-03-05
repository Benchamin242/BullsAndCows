package Codes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LettersCode extends SecretCode {
    private String wordListFile;

    public LettersCode(String wordListFile) {
        this.wordListFile = wordListFile;
    }

    @Override
    public void generateCode() throws IOException{
        List<String> wordList = readWordListFromFile(wordListFile);

        if (!wordList.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(wordList.size());
            decipheredCode = wordList.get(index);
        }
    }

    private List<String> readWordListFromFile(String filename) throws IOException{
        List<String> wordList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/"+filename))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] words = line.split(",");
                for (String word : words) {
                    wordList.add(word.trim().toLowerCase());
                }
            }
        }

        return wordList;
    }

    @Override
    public Map<String,Integer> makeGuess(String userInput) {
        for (char c : userInput.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("Input is not a word");
            }
        }

        return super.getBullsAndCows(userInput);
    }
}
