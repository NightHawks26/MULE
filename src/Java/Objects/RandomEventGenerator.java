package Java.Objects;

import java.util.*;

/**
 * Created by anthonybonitatibus on 10/20/15.
 */
public class RandomEventGenerator {

    private MuleGame muleGame;
    private Player player;
    private boolean isLastPlace;
    private int round;
    private Player[] allPlayers;
    private final Random rng;
    private static final java.util.Map<Integer, Integer> randomMultiplier;
    static {
        java.util.Map<Integer, Integer> aMap = new HashMap<>();
        aMap.put(1, 25);
        aMap.put(2, 25);
        aMap.put(3, 25);
        aMap.put(4, 50);
        aMap.put(5, 50);
        aMap.put(6, 50);
        aMap.put(7, 50);
        aMap.put(8, 75);
        aMap.put(9, 75);
        aMap.put(10, 75);
        aMap.put(11, 75);
        aMap.put(12, 100);
        randomMultiplier = Collections.unmodifiableMap(aMap);
    }


    public RandomEventGenerator() {
        this.rng = new Random();
    }

    public String getRandomEvent(MuleGame muleGame) {
        this.player = muleGame.getCurrentPlayerObject();
        this.round = muleGame.getRound();
        this.isLastPlace = player.getIsLast();
        this.allPlayers = muleGame.getPlayers();
        int event = rng.nextInt(100);
        if (event < 27) {
            event = rng.nextInt(17);
            if (event == 0) {
                if (!isLastPlace) {
                    int val = randomMultiplier.get(round) * 4;
                    player.subtractMoney(val);
                    return "Dust storm damaged your buildings!"
                            +" Repairs cost " + val + " money!";
                } else {
                    return "A dust storm narrowly missed your buildings!";
                }
            } else if (event == 1) {
                if (!isLastPlace) {
                    player.subtractEnergy(1);
                    return "Geothermal venting fried a power line! -1 energy";
                } else {
                    return "Geothermal venting almost fried your " +
                            "power lines, but the insulation held!";
                }
            } else if (event == 2) {
                player.addFood(2);
                return "You stumble upon some wild space-corn! +2 food";
            } else if (event == 3) {
                player.addOre(1);
                return "Happened upon an ore vein after an earthquake! +1 ore";
            } else if (event == 4) {
                if (!isLastPlace) {
                    player.subtractFood(1);
                    return "Space locusts ate part of your crops! -1 food";
                } else {
                    return "Space locusts flew right over your crops! " +
                            "Must have been that scarecrow you have set up";
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
                int val = randomMultiplier.get(round) * 2;
                player.addMoney(val);
                return "Found some loose change in the couch! +" + val + " money";
            } else if (event == 8) {
                player.addFood(3);
                player.addEnergy(2);
                return "YOU JUST RECEIVED A PACKAGE FROM THE GT " +
                        "ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.";
            } else if (event == 9) {
                player.addOre(2);
                return "A WANDERING TECH STUDENT REPAID YOUR " +
                        "HOSPITALITY BY LEAVING TWO BARS OF ORE.";
            } else if (event == 10) {
                int val = 8 * randomMultiplier.get(round);
                player.addMoney(val);
                return "THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL " +
                        "COMPUTER FOR $" + val;
            } else if (event == 11) {
                int val = 2 * randomMultiplier.get(round);
                player.setMoney(player.getMoney() + val);
                return "YOU FOUND A DEAD MOOSE RAT AND SOLD" +
                        " THE HIDE FOR $" + val;
            } else if (event == 12) {
                if (!isLastPlace) {
                    int val = 4 * randomMultiplier.get(round);
                    player.subtractMoney(val);
                    return "FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $" + val;
                } else {
                    return "Those flying cat-bugs just missed you!";
                }
            } else if (event == 13) {
                if (!isLastPlace) {
                    player.subtractFood(player.getFood() / 2);
                    return "MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.";
                } else {
                    return "Your advanced security system kept the u(sic)ga students at bay.";
                }
            } else if (event == 14) {
                if (!isLastPlace) {
                    int val = 6 * randomMultiplier.get(round);
                    player.subtractMoney(val);
                    return "YOUR SPACE GYPSY IN-LAWS MADE"
                            + " A MESS OF THE TOWN. IT COST YOU $" + val
                            + " TO CLEAN IT UP.";
                } else {
                    return "Wow, your space relatives are remarkably"
                        + " clean and made no mess at all!";
                }
            } else if (event == 15) {
                for (Player p : allPlayers) {
                    p.setMoney(p.getMoney() + 3 * randomMultiplier.get(round));
                }
                return "Economic stimulus package! Everyone gained "
                        + (3 * randomMultiplier.get(round)) +
                        " money!";
            } else if (event == 16) {
                int decVal = randomMultiplier.get(round) * 4;
                for (int i = 1; i < allPlayers.length; i++) {
                    allPlayers[i].subtractMoney(decVal);
                }
                return "Everyone except " + allPlayers[0].getName()
                        + " lost " + decVal
                        + " money when the housing market crashed!";
            } else {
                return "This shouldn't be able to happen";
            }
        } else {
            return "Nothing particularly interesting occurred.";
        }
    }
}
