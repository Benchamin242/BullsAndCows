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
import java.util.Scanner;

public class GameTests {

    private Player player;
    private SecretCode code;
    private Game game;
    private String filePath;

    @Before
    public void before(){
        player = new Player("Test Testerson", 0, 0, 0, 0,0);
    }

/*
    @Test
    public void testConstructorWithCodeType() {
        Game game = new Game(player, "Letters");

        assertNotNull(game);
    }
*/

    @Test
    public void testConstructorWithoutCodeType() {
        Game game = new Game(player,new NumbersCode());

        assertNotNull(game);
    }

    @Before
    public void initSG1() {
        code = new NumbersCode();
        player = new Player("idiot", 0, 0, 0,0,0);
        game = new Game(player,code);
        filePath = "saveGametest.csv";

    }

    //delete player name form the file before running test
    @Test
    public void saveGameAppend() throws FileNotFoundException {
         //testfile
        game.saveGame("src/Tests/saveGametest.csv", "1_0_"); //save with current guess
        Scanner sc = new Scanner(new File("src/Tests/saveGametest.csv"));
        String code_check = "", guess_check = "";
        String playerName = "idiot";
        int b = -1, c = -1;

        while(sc.hasNext()) {
            Scanner line_s = new Scanner(sc.nextLine());
            if(line_s.next().equals(playerName)) {
                guess_check = line_s.next();
                code_check = line_s.next();
                b = line_s.nextInt();
                c = line_s.nextInt();
            }
        }
        assertEquals(code_check,code.decipheredCode);
        assertEquals(code.currentNumOfBulls,b);
        assertEquals(code.currentNumOfCows,c);
        assertEquals(guess_check,"1_0_");

    }

}

