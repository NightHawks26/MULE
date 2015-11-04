package Java.Objects.Tests;

import Java.Objects.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by anthonybonitatibus on 11/4/15.
 */
public class MapTest {
    private Map defaultMap;
    private Map randomMap;
    private String[] defaultLevel = {
            "p", "p", "m1", "p", "r", "p", "m3", "p", "p",
            "p", "m1", "p", "p", "r", "p", "p", "p", "m3",
            "m3", "p", "p", "p", "t", "p", "p", "p", "m1",
            "p", "m2", "p", "p", "r", "p", "m2", "p", "p",
            "p", "p", "m2", "p", "r", "p", "p", "p", "m2"};

    //Tests to make sure the default map generates the right tiles
    @Test
    public void defaultMapTest() {
        defaultMap = new Map("default");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(defaultLevel[(i * 9) + j], defaultMap.getTile(i, j).getTerrain().getName());
            }
        }
    }

    //Tests to make sure the random map puts the town in the middle of the map
    @Test
    public void randomMapTest() {
        randomMap = new Map("random");
        assertEquals("t", randomMap.getTile(2, 4).getTerrain().getName());
    }

}
