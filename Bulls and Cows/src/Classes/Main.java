package Classes;

import Codes.LettersCode;

public class Main {

    public static void main(String[] args) {
        Player player = new Player("mainMan",0,0,0,0);
        Game game = new Game(player,new LettersCode("src/WordList.csv"));
        game.saveGame("Tests/saveGametest.csv", "1010");
        //Game game = new Classes.Game(player);
//        while (true){
//            game.PlayGame();
//        }
    }
}
