package Java.Objects.Tests;

import Java.Objects.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by AveryDingler on 10/29/15.
 */
public class PlayerTest {
    private Player testPlayer;

    @Before
    public void setUp() throws Exception {
        testPlayer = new Player("Marco", "Human", "Beginner", "#FFFF99");

    }
    @Test
    public void testCalculateTimeForTurn() throws Exception {
        assertEquals(8, testPlayer.getFood());
        testPlayer.setFood(0);
        for (int x = 1; x < 13; x++) {
            assertEquals(5, testPlayer.calculateTimeForTurn(x));
        }
        for (int x = 1; x < 5; x++) {
            testPlayer.setFood(1);
            assertEquals(30, testPlayer.calculateTimeForTurn(x));
            testPlayer.setFood(2);
            assertEquals(30, testPlayer.calculateTimeForTurn(x));
            testPlayer.setFood(3);
            assertEquals(50, testPlayer.calculateTimeForTurn(x));
        }
        for (int x = 5; x < 9; x++) {
            testPlayer.setFood(1);
            assertEquals(30, testPlayer.calculateTimeForTurn(x));
            testPlayer.setFood(2);
            assertEquals(30, testPlayer.calculateTimeForTurn(x));
            testPlayer.setFood(3);
            assertEquals(30, testPlayer.calculateTimeForTurn(x));
            testPlayer.setFood(4);
            assertEquals(50, testPlayer.calculateTimeForTurn(x));
        }
        for (int x = 9; x < 13; x++) {
            testPlayer.setFood(1);
            assertEquals(30, testPlayer.calculateTimeForTurn(x));
            testPlayer.setFood(2);
            assertEquals(30, testPlayer.calculateTimeForTurn(x));
            testPlayer.setFood(3);
            assertEquals(30, testPlayer.calculateTimeForTurn(x));
            testPlayer.setFood(4);
            assertEquals(30, testPlayer.calculateTimeForTurn(x));
            testPlayer.setFood(5);
            assertEquals(50, testPlayer.calculateTimeForTurn(x));
        }
    }
}