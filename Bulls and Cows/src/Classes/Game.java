package Classes;

import Codes.LettersCode;
import Codes.NumbersCode;
import Codes.SecretCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private final Player currentPlayer;
    private final String codeType;
    private String lastGuess;
    private SecretCode code;

    public Game(Player currentPlayer, String codeType) {
        this.currentPlayer = currentPlayer;
        this.codeType = codeType;
    }

    public Game(Player currentPlayer) {
        this(currentPlayer, "Numbers");
    }

    public void PlayGame() {

        if (codeType.equals("Numbers")) {
            code = new NumbersCode();
        } else {
            code = new LettersCode("WordList.csv");
        }

        currentPlayer.incrementCodesAttempted();

        try {
            code.generateCode();
        } catch (IOException e) {
            System.err.println("Error reading word file");
            System.exit(-1);
        }

        System.out.printf("The game has started with the code: %s%n", code.decipheredCode);

        String userGuess;

        boolean codeDeciphered = false;

        while (!codeDeciphered) {
            userGuess = getUserGuess();
            lastGuess = userGuess;

            try {
                code.makeGuess(userGuess);
            } catch (Exception e) {
                System.out.println("Invalid input! " + e.getMessage());
                continue;
            }

            currentPlayer.incrementCodesAttempted();
            if (code.currentNumOfBulls == 4) {
                codeDeciphered = true;
                currentPlayer.incrementCodesDeciphered();
                System.out.printf("Congratulations! You deciphered the code! You have deciphered %d code(s).%n", currentPlayer.getCodesDeciphered());
            } else {
                System.out.printf("%s Bulls, %s Cows.%n", code.currentNumOfBulls, code.currentNumOfCows);
            }
        }
    }

    private String getUserGuess() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your guess: ");
        return scan.nextLine();
    }

    public void undoGuess() {
        Scanner scan2 = new Scanner(System.in);
        if (lastGuess == null) {
            System.out.println("You haven't guessed yet");
            return;
        }
        System.out.println("Your last guess was: " + lastGuess);
        System.out.println("Enter the position you want to change:");
        int position = scan2.nextInt();
        if (position < 0 || position >= lastGuess.length()) {
            System.out.println("Invalid position");
            return;
        }
        System.out.println("Enter the new number:");
        char newNumber = scan2.next().charAt(0);
        currentPlayer.incrementCodesAttempted();
        lastGuess = lastGuess.substring(0, position) + newNumber + lastGuess.substring(position + 1);
        System.out.println("Your new guess is: " + lastGuess);

        SecretCode code;
        if (codeType.equals("Numbers")) {
            code = new NumbersCode();
        } else {
            code = new LettersCode("WordList.csv");
        }

        Map<String, Integer> bullsAndCows = null;

        try {
            code.makeGuess(lastGuess);
        } catch (Exception e) {
            System.out.println("Invalid input! Please try again.");
            return;
        }

        if (bullsAndCows != null) {
            Integer numbersOfBulls = bullsAndCows.get("Bulls");
            Integer numbersOfCows = bullsAndCows.get("Cows");
            if (numbersOfBulls == 4) {
                System.out.printf("Congratulations! You deciphered the code! You have deciphered %d code(s).%n", currentPlayer.getCodesDeciphered());
            } else {
                System.out.printf("%s Bulls, %s Cows.%n", numbersOfBulls, numbersOfCows);
            }
        }
    }

    public void saveGame(String filepath, String currGuess) {
        // TODO add b & c to SecretCode and write to this fill, le string format plus getters
        try {
            File f = new File(filepath);
            File tmp = new File("src/temp.txt");
            Scanner sc = new Scanner(f);
            FileWriter fw = new FileWriter(tmp);
            String override = String.format("%s %s %s", currentPlayer.getUsername(), currGuess, code.decipheredCode + System.lineSeparator());
            boolean found = false;

            //check if the player already has a saved game
            while (sc.hasNext()) {
                String line = sc.nextLine();
                //check for currentPlayer
                if (line.contains(currentPlayer.getUsername())) {
                    //comment/uncomment out what you need
                    //for prod
//          System.out.print("do you want to overwrite your current saved game? y/n: ");
//          String yes_no = new Scanner(System.in).nextLine();
//          if(yes_no.charAt(0) == 'y') {
//            fw.write(override);
//            found = true;
//          } else System.out.println("aborting overwrite");
                    //for tests
                    fw.write(override);
                    found = true;
                } else {
                    fw.write(line + System.lineSeparator());
                }
            }
            sc.close();
            //append if not already in file
            if (!found)
                fw.write(override);
            fw.close();

            //delete and rename
            if (!f.delete()) {
                System.out.println("failed to delete original file");
            }
            if (!tmp.renameTo(f)) {
                System.out.println("failed to rewrite file");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}