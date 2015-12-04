package Java.Objects;

/**
 * Created by AveryDingler on 9/8/15.
 */
public class Store {
    private int oreStock;
    private int crystiteStock;
    private int foodStock;
    private int energyStock;
    private int muleStock;
    private final int orePrice;
    private final int crystitePrice;
    private final int foodPrice;
    private final int energyPrice;

    //LOAD GAME CONSTRUCTOR

    /**
     * Constructor for the store based on various values. Used when
     * loading an old game and stock amounts are different than default
     * @param oreStock int amount of ore in stock
     * @param crystiteStock int amount of crystite in stock
     * @param foodStock int amount of food in stock
     * @param energyStock int amount of energy in stok
     * @param muleStock int number of MULEs in stock
     * @param orePrice int standard price of ore
     * @param crystitePrice int standard price of crystite
     * @param foodPrice in standard price of food
     * @param energyPrice in standard price of energy
     */

    public Store(int oreStock, int crystiteStock, int foodStock, int energyStock,
                 int muleStock, int orePrice, int crystitePrice, int foodPrice, int energyPrice) {
        this.oreStock = oreStock;
        this.crystiteStock = crystiteStock;
        this.foodStock = foodStock;
        this.energyStock = energyStock;
        this.muleStock = muleStock;
        this.orePrice = orePrice;
        this.crystitePrice = crystitePrice;
        this.foodPrice = foodPrice;
        this.energyPrice = energyPrice;
    }

    //NEW GAME CONSTRUCTOR

    /**
     * Initializes a store object with the default values depending on the
     * difficulty of the game.
     * @param difficulty string representing the difficulty level
     */

    public Store(String difficulty) {
        if (difficulty.equals("Beginner")) {
            this.oreStock = 0;
        } else {
            this.oreStock = 8;
        }
        this.crystiteStock = 0;
        if (difficulty.equals("Beginner")) {
            this.foodStock = 16;
        } else {
            this.foodStock = 8;
        }
        if (difficulty.equals("Beginner")) {
            this.energyStock = 16;
        } else {
            this.energyStock = 8;
        }
        if (difficulty.equals("Beginner")) {
            this.muleStock = 25;
        } else {
            this.muleStock = 14;
        }
        this.orePrice = 50;
        this.crystitePrice = 100;
        this.foodPrice = 30;
        this.energyPrice = 25;
    }

    /**
     * Gets the store's current crystite stock.
     * @return int amount of crystite in stock
     */

    public int getCrystiteStock() { return crystiteStock; }

    /**
     * Gets the store's current ore stock.
     * @return int amount of ore in stock
     */

    public int getOreStock() { return oreStock; }

    /**
     * Gets the store's current food stock.
     * @return int amount of food in stock
     */

    public int getFoodStock() { return foodStock; }

    /**
     * Gets the store's current energy stock.
     * @return int amount of energy in stock
     */

    public int getEnergyStock() { return energyStock; }

    /**
     * Gets the store's current MULE stock.
     * @return int amount of MULEs in stock
     */

    public int getMuleStock() { return muleStock; }

    /**
     * Gets the current price of ore.
     * @return int price of ore
     */

    public int getOrePrice() {
        return orePrice;
    }

    /**
     * Gets the current price of crystite.
     * @return int price of crystite
     */

    public int getCrystitePrice() {
        return crystitePrice;
    }

    /**
     * Gets the current price of food.
     * @return int price of food
     */

    public int getFoodPrice() {
        return foodPrice;
    }

    /**
     * Gets the current price of energy.
     * @return int price of energy
     */

    public int getEnergyPrice() {
        return energyPrice;
    }

    /**
     * Purchases one ore for the player, so long as the player has
     * enough money to purchase it
     * @param player Player purchasing the ore
     */

    public void buyOre(Player player) {
        if (oreStock > 0 && player.getMoney() >= orePrice) {
            player.subtractMoney(orePrice);
            player.addOre(1);
            oreStock--;
        }
    }

    /**
     * Sells one ore from the player so long as the player
     * has at least one ore.
     * @param player Player selling ore
     */

    public void sellOre(Player player) {
        if (player.getOre() > 0) {
            player.addMoney(orePrice / 2);
            player.subtractOre(1);
            oreStock++;
        }
    }

    /**
     * Purchases one crystite for the player, so long as the player has
     * enough money to purchase it.
     * @param player Player purchasing the crystite
     */

    public void buyCrystite(Player player) {
        if (crystiteStock > 0 && player.getMoney() >= crystitePrice) {
            player.subtractMoney(crystitePrice);
            player.addCrystite(1);
            crystiteStock--;
        }
    }

    /**
     * Sells one crystite from the player so long as the player
     * has at least one crystite.
     * @param player Player selling crystite
     */

    public void sellCrystite(Player player) {
        if (player.getCrystite() > 0) {
            player.addMoney(crystitePrice / 2);
            player.subtractCrystite(1);
            crystiteStock++;
        }
    }

    /**
     * Purchases one energy for the player, so long as the player has
     * enough money to purchase it
     * @param player Player purchasing the energy
     */

    public void buyEnergy(Player player) {
        if (energyStock > 0 && player.getMoney() >= energyPrice) {
            player.subtractMoney(energyPrice);
            player.addEnergy(1);
            energyStock--;
        }
    }

    /**
     * Sells one energy from the player so long as the player
     * has at least one energy.
     * @param player Player selling energy
     */

    public void sellEnergy(Player player) {
        if (player.getEnergy() > 0) {
            player.addMoney(energyPrice / 2);
            player.subtractEnergy(1);
            energyStock++;
        }
    }

    /**
     * Purchases one food for the player, so long as the player has
     * enough money to purchase it
     * @param player Player purchasing the food
     */

    public void buyFood(Player player) {
        if (foodStock > 0 && player.getMoney() >= foodPrice) {
            player.subtractMoney(foodPrice);
            player.addFood(1);
            foodStock--;
        }
    }

    /**
     * Sells one food from the player so long as the player
     * has at least one food.
     * @param player Player selling food
     */

    public void sellFood(Player player) {
        if (player.getFood() > 0) {
            player.addMoney(foodPrice / 2);
            player.subtractFood(1);
            foodStock++;
        }
    }

    /**
     * Purchases a MULE for the player so long as the player has enough money
     * based on the type of MULE
     * @param player Player purchasing the MULE
     * @param type String type of MULE being purchased
     * @param price int price of MULE based on type
     */

    public void buyMule(Player player, String type, int price) {
        if (muleStock > 0) {
            if (type.equals("ore")) {
                if (player.getMoney() >= price) {
                    player.incNumberOfMules();
                    player.subtractMoney(price);
                    Mule newMule = new Mule("Ore");
                    player.setMuleInHand(newMule);
                    muleStock--;
                }
            } else if (type.equals("energy")) {
                if (player.getMoney() >= price) {
                    player.incNumberOfMules();
                    player.subtractMoney(price);
                    Mule newMule = new Mule("Energy");
                    player.setMuleInHand(newMule);
                    muleStock--;
                }
            } else if (type.equals("food")) {
                if (player.getMoney() >= price) {
                    player.incNumberOfMules();
                    player.subtractMoney(price);
                    Mule newMule = new Mule("Food");
                    player.setMuleInHand(newMule);
                    muleStock--;
                }
            }
        }
    }
}
