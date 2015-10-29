package Java.Objects.Tests;

import Java.Objects.Map;
import Java.Objects.MuleGame;
import Java.Objects.Player;
import Java.Objects.Store;
import io.github.jgkamat.JayLayer.JayLayer;

import static org.junit.Assert.*;

/**
 * Created by Brian on 10/29/2015.
 */
public class StoreControllerTest {

    private Player[] testPlayers;
    private MuleGame testMuleGame;
    private JayLayer sound;
    private Map map;
    private Store store;

    @org.junit.Test
    public void testInitialize() throws Exception {
    }

    @org.junit.Test
    public void testStart() throws Exception {

        // create test players
        testPlayers = new Player[2];
        testPlayers[0] = new Player("Marco", "Human", "Beginner", "#FFFF99");
        testPlayers[1] = new Player("Polo", "Human", "Beginner", "#FF66FF");

        // create map
        map = new Map("default");

        // create mule game
        testMuleGame = new MuleGame("Beginner", map, testPlayers, sound);

        // create store
        store = new Store(10, 10, 10, 10, 10, 500, 500, 500, 500);

        // test with plenty of money
        testPlayers[0].setMoney(1000);
        testMuleGame.getStore().buyMule(testPlayers[0], "energy", 150);
        assertEquals(850, testPlayers[0].getMoney());
        assertEquals(1, testPlayers[0].getNumberOfMules());

        // test with exact amount of money
        testPlayers[0].setMoney(500);
        testMuleGame.getStore().buyMule(testPlayers[0], "energy", 500);
        assertEquals(0, testPlayers[0].getMoney());
        assertEquals(2, testPlayers[0].getNumberOfMules());

        // test with insufficient money
        testPlayers[0].setMoney(100);
        testMuleGame.getStore().buyMule(testPlayers[0], "energy", 150);
        assertEquals(100, testPlayers[0].getMoney());
        assertEquals(2, testPlayers[0].getNumberOfMules());


    }
}