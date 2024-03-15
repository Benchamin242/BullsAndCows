package Tests;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import Classes.Game;
import Classes.Player;

public class GameTests {

    private static Player player;

    @Before
    public void before(){
        player = new Player("Test Testerson", 0, 0, 0, 0);
    }

    @Test
    public void testConstructorWithCodeType() {
        Game game = new Game(player, "Letters");

        assertNotNull(game);
    }

    @Test
    public void testConstructorWithoutCodeType() {
        Game game = new Game(player);

        assertNotNull(game);
    }
}

