package Java.Objects;

import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Map;

/**
 * Created by AveryDingler on 9/8/15.
 */
public class Player {

    private String name;
    private String race;
    private String color;
    private int food;
    private int energy;
    private int ore;
    private int money;
    private int crystite;
    private int landCounter;
    private int score;
    private boolean isLast;
    private int numberOfMules;
    private Mule muleInHand;
    private static final Map<Integer, Integer> foodRequirements;
    static {
        Map<Integer, Integer> aMap = new HashMap<>();
        aMap.put(1, 3);
        aMap.put(2, 3);
        aMap.put(3, 3);
        aMap.put(4, 3);
        aMap.put(5, 4);
        aMap.put(6, 4);
        aMap.put(7, 4);
        aMap.put(8, 4);
        aMap.put(9, 5);
        aMap.put(10, 5);
        aMap.put(11, 5);
        aMap.put(12, 5);
        foodRequirements = Collections.unmodifiableMap(aMap);
    }

    //beginner
    //standard
    //tournament

    public Player(String name, String race, String difficulty, String color) {
        this.color = color;
        this.name = name;
        this.race  = race;
        this.score = 0;
        if (difficulty.contains("Beginner")) {
            food = 8;
            energy = 4;
            ore = 0;
        } else if (difficulty.contains("Standard")) {
            food = 4;
            energy = 2;
            ore = 0;
        } else {
            food = 4;
            energy = 2;
            ore = 0;
        }
        if (race.contains("Flapper")) {
            money = 1600;
        } else if (race.contains("Human")) {
            money = 600;
        } else {
            money = 100;
        }
    }

    public Player() {};

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLandCounter(int landCounter) {
        this.landCounter = landCounter;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }

    public int getCrystite() {
        return crystite;
    }

    public void setCrystite(int crystite) {
        this.crystite = crystite;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public String getColor() {
        return color;
    }

    public void incLandCounter() {
        landCounter++;
    }

    public void refreshScore() {
        int newScore = 0;
        newScore += landCounter * 500;
        newScore += money;
        newScore += energy;
        newScore += food;
        newScore += ore;
        score = newScore;
    }

    public int getScore() {
        return score;
    }

    public int getNumberOfMules() {
        return numberOfMules;
    }

    public void setNumberOfMules(int numberOfMules) {
        this.numberOfMules = numberOfMules;
    }

    public void incNumberOfMules() { numberOfMules++; }

    public boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(boolean value) {
        isLast = value;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public Mule getMuleInHand() {
        return muleInHand;
    }

    public void setMuleInHand(Mule muleInHand) {
        this.muleInHand = muleInHand;
    }


    public int calculateTimeForTurn(int round) {
        if (food <= 0) {
            return 5;
        } else {
            if (food >= foodRequirements.get(round) && energy >= numberOfMules) {
                //return 10;
                return 50;// need for demo tomorrow
            } else {
                return 30;
            }
        }
    }
}
