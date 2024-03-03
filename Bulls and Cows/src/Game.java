import Codes.*;

import java.util.Scanner;

public class Game {
    private final Player currentPlayer;
    private final String codeType;

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
        code.generateCode();
        System.out.printf("The game has started with the code: %s%n", code.decipheredString);

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your guess: ");
        //should i take int here or string

    }
}
