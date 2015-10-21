package Java.Objects;

import java.util.Random;

/**
 * Created by anthonybonitatibus on 10/20/15.
 */
public class RandomEventGenerator {

    private Random rng;

    public RandomEventGenerator() {
        this.rng = new Random();
    }

    public String getRandomEvent(Player player, boolean isLastPlace) {
        int event = rng.nextInt(8);
        if (event == 0) {
            if (!isLastPlace) {
                if (player.getMoney() >= 50) {
                    player.setMoney(player.getMoney() - 50);
                    return "Dust storm damaged your buildings! -50 money";
                } else {
                    int someMoney = player.getMoney();
                    player.setMoney(0);
                    return "Dust storm damaged your buildings! -" + someMoney + " money";
                }
            } else {
                return "Dust storm narrowly missed your buildings";
            }
        } else if (event == 1) {
            if (!isLastPlace) {
                if (player.getEnergy() > 0) {
                    player.setEnergy(player.getEnergy() - 1);
                    return "Geothermal venting fried a power line! -1 energy";
                } else {
                    return "Geothermal venting fried a power line! But you're already out of energy";
                }
            } else {
                return "Geothermal venting almost fried your power lines, but the insulation held!";
            }
        } else if (event == 2) {
            player.setFood(player.getFood() + 2);
            return "You stumble upon some wild space-corn! +2 food";
        } else if (event == 3) {
            player.setOre(player.getOre() + 1);
            return "Happened upon an ore vein after an earthquake! +1 ore";
        } else if (event == 4) {
            if (!isLastPlace) {
                if (player.getFood() > 0) {
                    player.setFood(player.getFood() - 1);
                    return "Space locusts ate part of your crops! -1 food";
                } else {
                    return "Space locusts came to eat your crops but you didn't have any!";
                }
            } else {
                return "Space locusts flew right over your crops! Must have been that scarecrow you have set up";
            }
        } else if (event == 5) {
            player.setEnergy(player.getEnergy() + 1);
            return "Lightning struck your power station! +1 energy";
        } else if (event == 6) {
            if (!isLastPlace) {
                if (player.getOre() > 0) {
                    player.setOre(player.getOre() - 1);
                    return "Cave in! -1 ore";
                } else {
                    return "Cave in! But your mines are barren anyways...";
                }
            } else {
                return "Your mine nearly caved in, but your supports held!";
            }
        } else if (event == 7) {
            player.setMoney(player.getMoney() + 25);
            return "Found some loose change in the couch! +25 money";
        } else {
            return "This shouldn't be able to happen";
        }
    }
}
