package Java.Objects.Tests;

import Java.Objects.RandomEventGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adam Lawrence on 10/29/2015.
 */
public class RandomEventGeneratorTest {

    @Test
    public void RandomEventGeneratorTestLastPlace() {
        RandomEventGenerator reg= new RandomEventGenerator();
        Java.Objects.Player jim = new Java.Objects.Player();
        jim.addMoney(500);
        reg.getRandomEvent(jim, true, 4);
        assertEquals(500,jim.getMoney());

    }
    @Test
    public void RandomEventGeneratorTestNormal() {
        RandomEventGenerator reg= new RandomEventGenerator();
        Java.Objects.Player jim = new Java.Objects.Player();
        jim.addMoney(50);
        jim.setEnergy(50);
        jim.setCrystite(50);
        jim.setFood(50);
        jim.setOre(50);
        String result = reg.getRandomEvent(jim, false, 4);
        boolean changed = false;
        if (jim.getMoney() > 50 || jim.getMoney() < 50) {
            changed = true;
        } else if (jim.getOre() > 50 || jim.getOre() < 50) {
            changed = true;
        } else if (jim.getEnergy() > 50 || jim.getEnergy() < 50) {
            changed = true;
        } else if (jim.getFood() > 50 || jim.getFood() < 50) {
            changed = true;
        } else if (jim.getCrystite() > 50 || jim.getCrystite() < 50) {
            changed = true;
        } else if (result.equals("Nothing particularly interesting occurred.")) {
            changed = true;
        }

        assertTrue(changed);
    }

}