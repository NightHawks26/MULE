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
    private int mulePrice;

    public Store() {
        this.oreStock = 0;
        this.crystiteStock = 0;
        this.foodStock = 16;
        this.energyStock = 16;
        this.muleStock = 25;
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

    public void buyMule(Player player, String type) {
        if (muleStock > 0) {
            switch (type) {
                case "ore":
                    if (player.getMoney() >= (mulePrice + 75)) {
                        player.incNumberOfMules();
                        player.setMoney(player.getMoney() - (mulePrice + 75));
                        muleStock--;
                    }
                    break;
                case "energy":
                    if (player.getMoney() >= (mulePrice + 50)) {
                        player.incNumberOfMules();
                        player.setMoney(player.getMoney() - (mulePrice + 50));
                        muleStock--;
                    }
                    break;
                case "food":
                    if (player.getMoney() >= (mulePrice + 25)) {
                        player.incNumberOfMules();
                        player.setMoney(player.getMoney() - (mulePrice + 25));
                        muleStock--;
                    }
            }
        }
    }
}
