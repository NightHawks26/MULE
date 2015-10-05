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
    //Image image = new Image("/../../resources/" + terrain + ".jpeg");

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
}
