import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LettersCode extends SecretCode {
    private String wordListFile;

    public LettersCode(String wordListFile) {
        this.wordListFile = wordListFile;
    }

    @Override
    public void generateCode() {
        List<String> wordList = readWordListFromFile(wordListFile);

        if (wordList != null && !wordList.isEmpty()) {

            Random random = new Random();
            int index = random.nextInt(wordList.size());
            decipheredCode = wordList.get(index);
        }
    }

    private List<String> readWordListFromFile(String filename) {
        List<String> wordList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/"+filename))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] words = line.split(",");
                for (String word : words) {
                    wordList.add(word.trim().toLowerCase());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading word list file: " + e.getMessage());
        }
        return wordList;
    }

    @Override
    public void makeGuess(String userInput) {

    }
}
