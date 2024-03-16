package Classes;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner =  new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter username: ");

        String userName = scanner.nextLine().trim();

        userName = userName.replace("|","<pipe>");

        Game game = new Game(userName,"Numbers");

        boolean startNewGame = true;
        while (startNewGame){
            game.PlayGame();

            System.out.print("New game? (y/n): ");

            String newGame = scanner.nextLine();

            if(newGame.trim().equalsIgnoreCase("n")){
                startNewGame = false;
            }
        }
    }
}
