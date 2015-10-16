package Java.Objects;

import javafx.scene.image.Image;

/**
 * Created by Brian on 9/12/2015.
 */
public class Tile {
    int row;
    int column;
    Player owner;
    Terrain terrain;
    Mule mule;

    public Tile(int row, int col, String ter) {
        try {
            if (row < 0 || row > 8) {
                throw new GameException("The row value is " + row + ", which doesn't fit on the map.");
            }
            if (col < 0 || col > 4) {
                throw new GameException("The col value is " + col + ", which doesn't fit on the map.");
            }
            if (!ter.equals("r") || !ter.equals("p") || !ter.equals("t") || !ter.equals("m1") || !ter.equals("m2") || !ter.equals("m3")) {
                throw new GameException("Invalid terrain type: " + ter);
            }
        }
        catch (GameException ex) {
        }
        this.row = row;
        this.column = col;
        owner = null;
        terrain = new Terrain(ter);
    }

    public Tile(int row, int col, String ter, Player owner, Mule mule) {
        try {
            if (row < 0 || row > 8) {
                throw new GameException("The row value is " + row + ", which doesn't fit on the map.");
            }
            if (col < 0 || col > 4) {
                throw new GameException("The col value is " + col + ", which doesn't fit on the map.");
            }
            if (!ter.equals("r") || !ter.equals("p") || !ter.equals("t") || !ter.equals("m1") || !ter.equals("m2") || !ter.equals("m3")) {
                throw new GameException("Invalid terrain type: " + ter);
            }
        }
        catch (GameException ex) {
        }
        this.row = row;
        this.column = col;
        this.owner = owner;
        this.terrain = new Terrain(ter);
        this.mule = mule;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public boolean isOwned() { return owner != null; }

    public Mule getMule() {
        return mule;
    }

    public void setMule(Mule mule) {
        this.mule = mule;
    }

    public void calculateProduction() {
        if (owner != null && owner.getEnergy() < 1) {
            System.out.println("not enough energy for " + owner.getName() + " to produce on " + terrain.getName());
        }
        if (owner != null && mule != null && owner.getEnergy() >= 1) {
            owner.setEnergy(owner.getEnergy() - 1);
            System.out.println("Producing for " + owner.getName() + " on " + terrain.getName());
            if (terrain.getName().equals("m1")) {
                if (mule.getMuleType().equals("Food")) {
                    owner.setFood(owner.getFood() + mule.getMountain1Production());
                } else if (mule.getMuleType().equals("Energy")) {
                    owner.setEnergy(owner.getEnergy() + mule.getMountain1Production());
                } else if (mule.getMuleType().equals("Ore")) {
                    owner.setOre(owner.getOre() + mule.getMountain1Production());
                } else {
                    System.out.println("this should never print");
                }
            } else if (terrain.getName().equals("m2")) {
                if (mule.getMuleType().equals("Food")) {
                    owner.setFood(owner.getFood() + mule.getMountain2Production());
                } else if (mule.getMuleType().equals("Energy")) {
                    owner.setEnergy(owner.getEnergy() + mule.getMountain2Production());
                } else if (mule.getMuleType().equals("Ore")) {
                    owner.setOre(owner.getOre() + mule.getMountain2Production());
                } else {
                    System.out.println("this should never print");
                }
            } else if (terrain.getName().equals("m3")) {
                if (mule.getMuleType().equals("Food")) {
                    owner.setFood(owner.getFood() + mule.getMountain3Production());
                } else if (mule.getMuleType().equals("Energy")) {
                    owner.setEnergy(owner.getEnergy() + mule.getMountain3Production());
                } else if (mule.getMuleType().equals("Ore")) {
                    owner.setOre(owner.getOre() + mule.getMountain3Production());
                } else {
                    System.out.println("this should never print");
                }
            } else if (terrain.getName().equals("r")) {
                if (mule.getMuleType().equals("Food")) {
                    owner.setFood(owner.getFood() + mule.getRiverProduction());
                } else if (mule.getMuleType().equals("Energy")) {
                    owner.setEnergy(owner.getEnergy() + mule.getRiverProduction());
                } else if (mule.getMuleType().equals("Ore")) {
                    owner.setOre(owner.getOre() + mule.getRiverProduction());
                } else {
                    System.out.println("this should never print");
                }
            } else if (terrain.getName().equals("p")) {
                if (mule.getMuleType().equals("Food")) {
                    owner.setFood(owner.getFood() + mule.getPlainProduction());
                } else if (mule.getMuleType().equals("Energy")) {
                    owner.setEnergy(owner.getEnergy() + mule.getPlainProduction());
                } else if (mule.getMuleType().equals("Ore")) {
                    owner.setOre(owner.getOre() + mule.getPlainProduction());
                } else {
                    System.out.println("this should never print");
                }
            } else {
                System.out.println("this should never print");
            }

        }
    }
}
