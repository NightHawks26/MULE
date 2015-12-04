package Java.Objects;

import java.util.Collections;
import java.util.HashMap;
//import java.util.Objects;
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

    /**
     * Player contructor, creates a player object and sets it to
     * the default starting values based on race chosen.
     * @param name String name of player
     * @param race String race of player
     * @param difficulty String difficulty of the game the player is in
     * @param color String color of the player
     */

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

    public Player() {
        
    }

    /**
     * Overridden toString() method.
     * @return String name of the player
     */

    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets the name of the player.
     * @return String name of player
     */

    public String getName() {
        return name;
    }

    /**
     * Sets the player object's name.
     * @param name String representing the player's name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the player's race.
     * @return String race of the player
     */

    public String getRace() {
        return race;
    }

    /**
     * Sets the player's race.
     * @param race String race of the player
     */

    public void setRace(String race) {
        this.race = race;
    }

    /**
     * Sets the color of the player.
     * @param color String color of the player
     */

    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Gets the player's color.
     * @return String color of the player
     */

    public String getColor() {
        return color;
    }

    /**
     * Sets the initial land count.
     * @param landCounter int number of lands owned
     */

    public void setLandCounter(int landCounter) {
        this.landCounter = landCounter;
    }

    /**
     * Gets the land counter.
     * @return int number of lands owned
     */

    public int getLandCounter() {
        return landCounter;
    }

    /**
     * Increments the land counter.
     */

    public void incLandCounter() {
        landCounter++;
    }

    /**
     * Sets the player's score.
     * @param score int player's score
     */

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the player's food count.
     * @return int food count
     */

    public int getFood() {
        return food;
    }

    /**
     * Adds food to the player's food count.
     * @param amount int food to be added
     */

    public void addFood(int amount) { this.food += amount; }

    /**
     * Subtracts food from the player's food count. If the player's food count
     * is less than the amount, sets the player's food count to 0.
     * @param amount int amount to subtract from food count
     */

    public void subtractFood(int amount) {
        if (this.food < amount) {
            this.food = 0;
        } else {
            this.food -= amount;
        }
    }

    /**
     * Sets the player's food count.
     * @param food int amount of food
     */

    public void setFood(int food) {
        this.food = food;
    }

    /**
     * Gets the player's energy count.
     * @return int energy amount
     */

    public int getEnergy() {
        return energy;
    }

    /**
     * Adds energy to the player's energy count.
     * @param amount int amount of energy to add
     */

    public void addEnergy(int amount) { this.energy += amount; }

    /**
     * Subtracts energy from the player's energy count. If the player's
     * energy count is less than the amount, sets the player's energy to 0.
     * @param amount int amount to subtract from the energy count
     */

    public void subtractEnergy(int amount) {
        if (this.energy < amount) {
            this.energy = 0;
        } else {
            this.energy -= amount;
        }
    }

    /**
     * Sets the player's energy count.
     * @param energy int amount to set energy count to
     */

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Gets the player's ore count.
     * @return int amount of ore
     */

    public int getOre() {
        return ore;
    }

    /**
     * Adds ore to the player's ore count.
     * @param amount int amount to be added to the ore count
     */

    public void addOre(int amount) { this.ore += amount; }

    /**
     * Subtracts ore from the player's ore count. If the player's ore count
     * is less than the amount, sets the ore count to 0.
     * @param amount int amount to subtract from the ore count
     */

    public void subtractOre(int amount) {
        if (this.ore < amount) {
            this.ore = 0;
        } else {
            this.ore -= amount;
        }
    }

    /**
     * Sets the player's ore count.
     * @param ore int amount to set the ore count to
     */

    public void setOre(int ore) {
        this.ore = ore;
    }

    /**
     * Gets the crystite count.
     * @return int amount of crystite
     */

    public int getCrystite() {
        return crystite;
    }

    /**
     * Adds crystite to the crystite count.
     * @param amount int amount to be added to the crystite count
     */

    public void addCrystite(int amount) { this.crystite += amount; }

    /**
     * Subtracts crystite from the crytite count. If the player's crystite
     * count is less than the amount, sets the crystite count to 0.
     * @param amount int amount to be subtracted from the crystite count
     */

    public void subtractCrystite(int amount) {
        if (this.crystite < amount) {
            this.crystite = 0;
        } else {
            this.crystite -= amount;
        }
    }

    /**
     * Sets the crystite count.
     * @param crystite int amount to set the count to
     */

    public void setCrystite(int crystite) {
        this.crystite = crystite;
    }

    /**
     * Gets the player's money amount.
     * @return int the player's money
     */

    public int getMoney() {
        return money;
    }

    /**
     * Sets the player's money
     * @param money int amount of money
     */

    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Refreshes the player's score based on current player values.
     */

    public void refreshScore() {
        int newScore = 0;
        newScore += landCounter * 500;
        newScore += money;
        newScore += energy;
        newScore += food;
        newScore += ore;
        score = newScore;
    }

    /**
     * Gets the player's score.
     * @return int score of the player
     */

    public int getScore() {
        return score;
    }

    /**
     * Gets the number of MULEs owned by the player.
     * @return int number of MULEs owned
     */

    public int getNumberOfMules() {
        return numberOfMules;
    }

    /**
     * Sets the number of MULEs owned by the player.
     * @param numberOfMules int number of MULEs owned
     */

    public void setNumberOfMules(int numberOfMules) {
        this.numberOfMules = numberOfMules;
    }

    /**
     * Increments the number of MULEs owned by the player.
     */

    public void incNumberOfMules() { numberOfMules++; }

    /**
     * Gets the boolean showing whether or not the player is in last place.
     * @return boolean value true if in last place, false otherwise
     */

    public boolean getIsLast() {
        return isLast;
    }

    /**
     * Sets the value showing whether or not the player is in last place.
     * @param value boolean true if in last place, false otherwise
     */

    public void setIsLast(boolean value) {
        isLast = value;
    }

    /**
     * Adds amount to the player's money.
     * @param amount int amount to be added
     */

    public void addMoney(int amount) {
        this.money += amount;
    }

    /**
     * Subtracts money from the player. If the player's money is less than
     * the amount, sets the player's money to 0.
     * @param amount int amount to be subtracted
     */

    public void subtractMoney(int amount) {
        if (this.money < amount) {
            this.money = 0;
        } else {
            this.money -= amount;
        }
    }

    /**
     * Checks what MULE the player has in hand
     * @return Mule object representing the type of MULE in hand
     */

    public Mule getMuleInHand() {
        return muleInHand;
    }

    /**
     * Sets the MULE in hand
     * @param muleInHand MULE object to be set
     */

    public void setMuleInHand(Mule muleInHand) {
        this.muleInHand = muleInHand;
    }

    /**
     * Calculates the time the player is allotted for their turn based on
     * the amount of food/energy they have
     * @param round int round counter
     * @return int amount of time for the round
     */

    public int calculateTimeForTurn(int round) {
        if (food <= 0) {
            return 5;
        } else {
            if (food >= foodRequirements.get(round) && energy >= numberOfMules) {
                //return 10;
                return 50;
            } else {
                return 30;
            }
        }
    }
}
