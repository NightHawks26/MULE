package Java.Objects;

import javafx.scene.control.Button;

/**
 * Created by anthonybonitatibus on 9/17/15.
 */
public class TileButton extends Button {

    private int row;
    private int col;
    private Player owner;
    private final Tile tile;

    public TileButton(Tile tile) {
        super();
        this.tile = tile;
    }

    public int getRow() { return row; }

    public void setRow(int row) { this.row = row; }

    public int getCol() { return col; }

    public void setCol(int col) { this.col = col; }

    public Tile getTile() { return tile; }
}
