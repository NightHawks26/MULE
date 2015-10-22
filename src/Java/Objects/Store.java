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
    private int orePrice;
    private int crystitePrice;
    private int foodPrice;
    private int energyPrice;

    //LOAD GAME CONSTRUCTOR


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

    public int getCrystiteStock() { return crystiteStock; }

    public int getOreStock() { return oreStock; }

    public int getFoodStock() { return foodStock; }

    public int getEnergyStock() { return energyStock; }

    public int getMuleStock() { return muleStock; }

    public void buyOre(Player player) {
        if (oreStock > 0 && player.getMoney() >= orePrice) {
            player.setMoney(player.getMoney() - orePrice);
            player.setOre(player.getOre() + 1);
            oreStock--;
        }
    }

    public void sellOre(Player player) {
        if (player.getOre() > 0) {
            player.setMoney(player.getMoney() + (orePrice / 2));
            player.setOre(player.getOre() - 1);
            oreStock++;
        }
    }

    public void buyCrystite(Player player) {
        if (crystiteStock > 0 && player.getMoney() >= crystitePrice) {
            player.setMoney(player.getMoney() - crystitePrice);
            player.setCrystite(player.getCrystite() + 1);
            crystiteStock--;
        }
    }

    public void sellCrystite(Player player) {
        if (player.getCrystite() > 0) {
            player.setMoney(player.getMoney() + (crystitePrice / 2));
            player.setCrystite(player.getCrystite() - 1);
            crystiteStock++;
        }
    }

    public void buyEnergy(Player player) {
        if (energyStock > 0 && player.getMoney() >= energyPrice) {
            player.setMoney(player.getMoney() - energyPrice);
            player.setEnergy(player.getEnergy() + 1);
            energyStock--;
        }
    }

    public void sellEnergy(Player player) {
        if (player.getEnergy() > 0) {
            player.setMoney(player.getMoney() + (energyPrice / 2));
            player.setEnergy(player.getEnergy() - 1);
            energyStock++;
        }
    }

    public void buyFood(Player player) {
        if (foodStock > 0 && player.getMoney() >= foodPrice) {
            player.setMoney(player.getMoney() - foodPrice);
            player.setFood(player.getFood() + 1);
            foodStock--;
        }
    }

    public void sellFood(Player player) {
        if (player.getFood() > 0) {
            player.setMoney(player.getMoney() + (foodPrice / 2));
            player.setFood(player.getFood() - 1);
            foodStock++;
        }
    }

    public void buyMule(Player player, String type, int price) {
        if (muleStock > 0) {
            if (type.equals("ore")) {
                if (player.getMoney() >= (price)) {
                    player.incNumberOfMules();
                    player.setMoney(player.getMoney() - (price));
                    Mule newMule = new Mule("Ore");
                    player.setMuleInHand(newMule);
                    muleStock--;
                }
            } else if (type.equals("energy")) {
                if (player.getMoney() >= (price)) {
                    player.incNumberOfMules();
                    player.setMoney(player.getMoney() - (price));
                    Mule newMule = new Mule("Energy");
                    player.setMuleInHand(newMule);
                    muleStock--;
                }
            } else if (type.equals("food")) {
                if (player.getMoney() >= (price)) {
                    player.incNumberOfMules();
                    player.setMoney(player.getMoney() - (price));
                    Mule newMule = new Mule("Food");
                    player.setMuleInHand(newMule);
                    muleStock--;
                }
            }
        }
    }
}
