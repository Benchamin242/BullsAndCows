package Classes;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner =  new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter username: ");

        String userName = scanner.nextLine().trim();

        userName = userName.replace("|","<pipe>");

        //ask the user if they would like to play with letters or numbers, and start the game accordingly
        //if user enters 1 start game with numbers, if user enters 2 start game with letters
        System.out.println("Would you like to play with numbers or letters?");
        System.out.println("1. Numbers");
        System.out.println("2. Letters");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim();

        if(choice.equals("1")){
            Game game = new Game(userName,"Number");

            boolean startNewGame = true;
            while (startNewGame){
                game.PlayGame();

                System.out.print("New game? (y/n): ");

                String newGame = scanner.nextLine();

                if(newGame.trim().equalsIgnoreCase("n")){
                    startNewGame = false;
                }
            }

        }else if(choice.equals("2")){
            Game game = new Game(userName,"Letter");

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

        scanner.close();
    }
}
