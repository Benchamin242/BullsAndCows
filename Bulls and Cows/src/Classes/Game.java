package Classes;

import Codes.LettersCode;
import Codes.NumbersCode;
import Codes.SecretCode;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private Player currentPlayer;
    private String codeType;
    private String lastGuess;
    private final Players players;
    private SecretCode code;
    private final Path playersFilePath = Paths.get("players.txt").toAbsolutePath();

    public Game(String playerName, String codeType) {
       this.codeType = codeType;
       this.players = new Players(playersFilePath);
       currentPlayer = this.players.getPlayer(playerName);
       if(currentPlayer == null) {
           currentPlayer = new Player(playerName,0,0,0,0,0);
           this.players.addPlayer(currentPlayer);
       }
    }

    //anything added for the tests can be removed from final version(iteration 3 finished)
    //added for load game tests
    public Game(Player p) {
        currentPlayer = p;
        code = new NumbersCode(0,0);
        players = new Players(playersFilePath);
    }

    //read Game

    public void PlayGame() {

        //make code for user to load a previously saved game, but only if they have one
        System.out.print("Would you like to load a saved game? (yes/no): ");
        String loadOption = new Scanner(System.in).nextLine().trim().toLowerCase();

        if (loadOption.equals("yes")) {
            String guess = loadGame(codesFilePath.toString(), currentPlayer.getUsername());
            System.out.println("Your last guess was: " + guess);
        }



        this.code = codeType.equals("Letter") ? new LettersCode(0,0) : new NumbersCode(0,0);

        System.out.printf("The game started by %s with the code: %s%n",currentPlayer.getUsername(), code.decipheredCode);

        String userGuess;

        boolean codeDeciphered = false;

        currentPlayer.incrementCodesAttempted();

        Scanner scan = new Scanner(System.in);

        while (!codeDeciphered) {

            userGuess = getUserGuess();
            currentPlayer.incrementNumberOfGuesses();
            lastGuess = userGuess;

            try {
                code.makeGuess(userGuess);
            } catch (Exception e) {
                System.out.println("Invalid input! " + e.getMessage());
                continue;
            }

            if (code.currentNumOfBulls == 4) {
                codeDeciphered = true;
                currentPlayer.incrementCodesDeciphered();
                currentPlayer.updateBulls(code.currentNumOfBulls);
                currentPlayer.updateCows(code.currentNumOfCows);
                players.savePlayers(playersFilePath);
                System.out.printf("Congratulations %s! You deciphered the code! You have deciphered %d code(s).%n",currentPlayer.getUsername(), currentPlayer.getCodesDeciphered());
            } else {
                currentPlayer.updateBulls(code.currentNumOfBulls);
                currentPlayer.updateCows(code.currentNumOfCows);
                System.out.printf("%s Bulls, %s Cows.%n", code.currentNumOfBulls, code.currentNumOfCows);
            }

            System.out.print("Do you want to save your current progress? (yes/no): ");
            String saveOption = scan.nextLine().trim().toLowerCase();

            if (saveOption.equals("yes")) {
                String filepath = "Bulls and Cows/src/Codes.txt";
                saveGame(filepath, userGuess);
                System.out.println("Game saved successfully!");
            }
        }
    }

    private void displayAllTimeStatistics(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(playersFilePath.toString()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] playerData = line.split("\\|");
                if (playerData[0].equals(username)) {
                    // Extracts data from the line and display the statistics
                    String message = String.format("%s, Your all-time statistics are %d total bulls, %d total cows, %d codes attempted, %d codes deciphered and %d attempts",
                            username, Integer.parseInt(playerData[1]), Integer.parseInt(playerData[2]),
                            Integer.parseInt(playerData[3]), Integer.parseInt(playerData[4]), Integer.parseInt(playerData[5]));
                    System.out.println(message);
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the player file.");
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
            code = new NumbersCode(0,0);
        } else {
            code = new LettersCode(0,0);
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
                currentPlayer.incrementCodesDeciphered();
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
            BufferedReader read = new BufferedReader(new FileReader(f));
            String override = String.format("%s %s %s %d %d", currentPlayer.getUsername(), currGuess, code.decipheredCode, code.currentNumOfBulls, code.currentNumOfCows);
            StringBuilder fileContent = new StringBuilder();
            boolean found = false;

            //check if the player already has a saved game
            String line;
            while ((line = read.readLine()) != null) {
                //check for currentPlayer
                if (line.contains(currentPlayer.getUsername())) {
                    //comment/uncomment out what you need
                    //for prod
//                    System.out.print("do you want to overwrite your current saved game? y/n: ");
//                    String yes_no = new Scanner(System.in).nextLine();
//                    if(yes_no.charAt(0) == 'y') {
//                        fileContent.append(override).append(System.lineSeparator());
//                        found = true;
//                    } else System.out.println("aborting overwrite");
                    //for tests
                    fileContent.append(override).append(System.lineSeparator());
                    found = true;
                } else {
                    fileContent.append(line).append(System.lineSeparator());
                }
            }
            read.close();
            //append if not already in file
            if (!found)
                fileContent.append(override).append(System.lineSeparator());
            System.out.println(fileContent.toString());
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent.toString());
            fw.close();


            //delete and rename
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //returns the current guess
    public String loadGame(String filePath, String p_name) {
        //default guess if there isn't one
        String guess = "____";
        boolean found = false;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(new File(filePath)));

            String line; // of the form: %s %s %s %d %d
            while((line = bf.readLine()) != null) {
                System.out.println(line);
                Scanner contents = new Scanner(line);

                if(contents.next().equals(p_name)) {
                    this.currentPlayer = players.getPlayer(p_name);
                    guess = contents.next();
                    String code_lit = contents.next();
                    if(code_lit.charAt(0) > 47 && code_lit.charAt(0) < 59) {
                        code = new NumbersCode(code_lit,contents.nextInt(), contents.nextInt());
                    }
                    else { //                            bulls               cows
                        code = new LettersCode(code_lit, contents.nextInt(), contents.nextInt());
                    }
                    found = true;
                }
            }

            if(!found) {
                System.out.println("this player does not have a saved game");
            }

        } catch(FileNotFoundException e) {
            System.out.println("the save file is missing");
        } catch (IOException e) {
            System.out.println("the save file is corrupted");
        }
        return guess;
    }

    public void showSol() {
        System.out.println("The solution is " + code.decipheredCode);
    }

    //added for assertEquals comparisons
    public SecretCode getCode() {
        return code;
    }
}