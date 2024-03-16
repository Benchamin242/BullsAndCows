import Classes.Game;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Driver {

  public static void main(String[] args) {
    Game game = new Game("playername","numbesr");
    Path player = Paths.get("Bulls and Cows/src/testSave.txt");
    game.saveGame(player.toString(), "1234");
  }
}
