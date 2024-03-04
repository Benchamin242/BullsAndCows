package Classes;

import Codes.LettersCode;
import Codes.NumbersCode;
import Codes.SecretCode;

import java.util.Map;
import java.util.Scanner;

public class Game {
    private final Player currentPlayer;
    private final String codeType;
    private String lastGuess;

    public Game(Player currentPlayer, String codeType) {
        this.currentPlayer = currentPlayer;
        this.codeType = codeType;
    }

    public Game(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.codeType = "Numbers";
    }

    public void PlayGame() {
        SecretCode code;

        if (codeType.equals("Numbers")) {
            code = new NumbersCode();
        } else {
            code = new LettersCode("WordList.csv");
        }

        currentPlayer.incrementCodesAttempted();

        code.generateCode();

        //If there was an issue reading from the words list file
        if (code.decipheredCode == null){
            System.out.println("Error reading word file");
            System.exit(-1);
        }

        System.out.printf("The game has started with the code: %s%n", code.decipheredCode);

        String userGuess;

        boolean codeDeciphered=false;

        while (!codeDeciphered){
            userGuess = getUserGuess();
            lastGuess = userGuess;
            Map<String, Integer> bullsAndCows = null;

            try{
                bullsAndCows = code.makeGuess(userGuess);
            } catch (Exception e){
                System.out.println("Invalid input! Please try again.");
            }

            //If the user inputted a valid guess
            if (bullsAndCows != null){
                Integer numbersOfBulls = bullsAndCows.get("Bulls");
                Integer numbersOfCows = bullsAndCows.get("Cows");
                currentPlayer.incrementCodesAttempted();
                if(numbersOfBulls == 4){
                    codeDeciphered = true;
                    currentPlayer.incrementCodesDeciphered();
                    System.out.printf("Congratulations! You deciphered the code! You have deciphered %d code(s).%n",currentPlayer.getCodesDeciphered());
                }
                else {
                    System.out.printf("%s Bulls, %s Cows.%n", numbersOfBulls, numbersOfCows);
                }
            }
        }

    }

    private String getUserGuess(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your guess: ");
        return scan.nextLine();
    }
    public void undoGuess() {

        Map<String, Integer> bullsAndCows = null;
        Integer numbersOfBulls = bullsAndCows.get("Bulls");
        Integer numbersOfCows = bullsAndCows.get("Cows");

        Scanner scan2 = new Scanner(System.in);
        if (lastGuess == null) {
            System.out.println("You havent guessed yet");
            return;
        }
        System.out.println("Youre last guess was"+ lastGuess);
        System.out.println("Enter what position you want to change");
        int position = scan2.nextInt();
        if(position < 0 || position > lastGuess.length()) {
            System.out.println("Invalid position");
            return;
        }
        System.out.println("Enter the new number");
        int newNumber = scan2.nextInt();
        currentPlayer.incrementCodesAttempted();
        lastGuess = lastGuess.substring(0, position) + newNumber + lastGuess.substring(position + 1);
        System.out.println("Your new guess is " + lastGuess);
        System.out.printf("%s Bulls, %s Cows.%n", numbersOfBulls, numbersOfCows);

    }
}
