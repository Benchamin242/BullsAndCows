package Tests;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import Classes.Game;
import Classes.Player;
import Codes.SecretCode;
import Codes.NumbersCode;
import Codes.LettersCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class GameTests {

    private Player player;
    private SecretCode code;
    private Game game;
    private String filePath;

//    @Before
//    public void before(){
//        player = new Player("Test Testerson", 0, 0, 0, 0,0); }

/*
    @Test
    public void testConstructorWithCodeType() {
        Game game = new Game(player, "Letters");

        assertNotNull(game);
    }
*/

//    @Test
//    public void testConstructorWithoutCodeType() {
//        Game game = new Game(player,new NumbersCode());
//
//        assertNotNull(game);
//    }

    @Before
    public void initSG1() {
        filePath = Paths.get("Bulls and Cows/src/testSave.txt").toAbsolutePath().toString();
    }

    //delete player name form the file before running test
    @Test
    public void saveGameAppend() throws FileNotFoundException {
        String playername = "dumb";
        String code_compare = null;
        String guess_compare = null;
        String guess = "3214";
        game = new Game(new Player(playername,0,0,0,0,0));
        game.saveGame(Paths.get("Bulls and Cows/src/testSave.txt").toAbsolutePath().toString(),guess);
        int b = -1; int c = -1;

        Scanner sc = new Scanner(new File(filePath));

        String line;
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            Scanner lines = new Scanner(line);
            if(lines.next().equals(playername)) {
                guess_compare = lines.next();
                code_compare = lines.next();
                b = lines.nextInt();
                c = lines.nextInt();
            }
        }

        assertEquals(guess_compare,guess);
        assertEquals(code_compare, game.getCode().decipheredCode);
        assertTrue(b >= 0 && c >= 0);

    }

}

