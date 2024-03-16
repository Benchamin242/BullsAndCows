package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import Classes.Players;
import Classes.Player;

public class PlayersTests {

    private final Path playersFilePath = Paths.get("Bulls and Cows/src/Tests/playersTests.txt").toAbsolutePath();
    private Players players;

    @BeforeEach
    void setUp() {
        Player player1 = new Player("LEEEEERRRRROOOOOYYY JENKINS!!!", 2, 3, 4, 5, 6);
        Player player2 = new Player("sudo rm -rf /", 1, 4, 3, 6, 6);
        players = new Players(player1);
        players.addPlayer(player2);
    }

    @Test
    void testAddPlayer() {
        Player player3 = new Player("Electric Callboy", 3, 2, 1, 4, 5);
        players.addPlayer(player3);
        assertEquals(3, players.getAllPlayers().size());
        assertTrue(players.getAllPlayers().contains(player3));
    }

    @Test
    void testSaveAndLoadPlayers() {
        players.savePlayers(playersFilePath);

        Players loadedPlayers = new Players(playersFilePath);
        assertEquals(players.getAllPlayers().size(), loadedPlayers.getAllPlayers().size());
        for (int i = 0; i < players.getAllPlayers().size(); i++) {
            assertEquals(players.getAllPlayers().get(i).toString(), loadedPlayers.getAllPlayers().get(i).toString());
        }
    }

    @Test
    void testGetPlayer() {
        Player player = players.getPlayer("LEEEEERRRRROOOOOYYY JENKINS!!!");
        assertNotNull(player);
        assertEquals("LEEEEERRRRROOOOOYYY JENKINS!!!", player.getUsername());

        Player nonExistingPlayer = players.getPlayer("TheLegend27");
        assertNull(nonExistingPlayer);
    }

    @Test
    void testGetAllPlayersBulls() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(1);

        assertEquals(expected, players.getAllPlayersBulls());
    }

    @Test
    void testGetAllPlayersCows() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(4);

        assertEquals(expected, players.getAllPlayersCows());
    }

    @Test
    void testGetAllPlayersCodesAttempted() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(4);
        expected.add(3);

        assertEquals(expected, players.getAllPlayersCodesAttempted());
    }

    @Test
    void testGetAllPlayersCodesDeciphered() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(5);
        expected.add(6);

        assertEquals(expected, players.getAllPlayersCodesDeciphered());
    }
}

