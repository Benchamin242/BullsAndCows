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

        currentPlayer.incrementCodesAttempted();

        code.generateCode();
        System.out.printf("The game has started with the code: %s%n", code.decipheredCode);

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your guess: ");
        String userGuess = scan.nextLine();
        code.makeGuess(userGuess);
    }
}
