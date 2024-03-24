package Codes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LettersCode extends SecretCode {

    //mostly init with 0, parameters for reading
    public LettersCode(int b, int c) {
        super(generateCode(),b,c);
    }

    //added for reading in codes
    public LettersCode(String code, int b , int c) {
        super(code,b,c);
    }

    private static String generateCode() {
        String wordListFile = "WordList.csv";
        try {
            List<String> wordList = readWordListFromFile(wordListFile);

            if (!wordList.isEmpty()) {
                Random random = new Random();
                int index = random.nextInt(wordList.size());
                return wordList.get(index);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static List<String> readWordListFromFile(String filename) throws IOException{
        List<String> wordList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(filename).toAbsolutePath().toString()))) {
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
    public void makeGuess(String userInput) {
        for (char c : userInput.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("Input is not a word");
            }
        }

        super.getBullsAndCows(userInput);
    }
}
