package Tests;

import Classes.Player;
import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerTests {

    @Test
    public void testParameterizedConstructor() {
        Player player = new Player("Test Testerson", 2, 1, 5, 3,5);

        assertEquals("Test Testerson", player.getUsername());
        assertEquals(2, player.getBulls());
        assertEquals(1, player.getCows());
        assertEquals(5, player.getCodesAttempted());
        assertEquals(3, player.getCodesDeciphered());
    }

    @Test
    public void testDefaultConstructor() {
        Player player = new Player();

        assertEquals("No username entered", player.getUsername());
        assertEquals(0, player.getBulls());
        assertEquals(0, player.getCows());
        assertEquals(0, player.getCodesAttempted());
        assertEquals(0, player.getCodesDeciphered());
    }

    @Test
    public void testUpdateBulls() {
        Player player = new Player();
        player.updateBulls(3);

        assertEquals(3, player.getBulls());
    }

    @Test
    public void testUpdateCows() {
        Player player = new Player();
        player.updateCows(2);

        assertEquals(2, player.getCows());
    }

    @Test
    public void testIncrementCodesAttempted() {
        Player player = new Player();
        player.incrementCodesAttempted();

        assertEquals(1, player.getCodesAttempted());
    }

    @Test
    public void testIncrementCodesDeciphered() {
        Player player = new Player();
        player.incrementCodesDeciphered();

        assertEquals(1, player.getCodesDeciphered());
    }
}
