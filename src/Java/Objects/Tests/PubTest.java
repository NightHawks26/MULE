package Java.Objects.Tests;

import Java.Objects.Pub;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adam Lawrence on 10/29/15.
 */
public class PubTest {

    @Test
    public void testGambleBigNumber() {
        Pub p = new Pub();
        int result = p.gamble(49,11);
        assertEquals(250, result);
    }

    @Test
    public void testGambleLowNumber() {
        for (int i = 0; i < 1000; i++) {
            Pub p = new Pub();
            int result = p.gamble(0, 1);
            boolean bigger = false;
            if (result >= 0) {
                bigger = true;

            }
            assertTrue(bigger);
        }
    }
    @Test
    public void testGambleImpossible() {
        for (int i = 0; i < 1000; i++) {
            Pub p = new Pub();
            int result = p.gamble(-5, -1000);
            boolean bigger = false;
            if (result >= 0) {
                bigger = true;

            }
            assertTrue(bigger);
        }
    }
}