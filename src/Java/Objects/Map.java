package Java.Objects;

/**
 * Created by AveryDingler on 9/8/15.
 */
public class Map {
    private Tile[][] tiles = new Tile[5][9];
    private String level;

    private final String[] terrains;

    /**
     *
     * Map Object Constructor
     *
     * Used to hold the tiles that make up the entire map
     * There are 45 tiles in total, 5 columns and 9 rows
     *
     * @param level the level configuration of the map
     **/

    public Map(String level) {
        this.level = level;
        terrains = new String[]{"r", "p", "m1", "m2", "m3"};

        //if default random
        if (level.equals("default")) {
            String[] defaultLevel = {
                    "p", "p", "m1", "p", "r", "p", "m3", "p", "p",
                    "p", "m1", "p", "p", "r", "p", "p", "p", "m3",
                    "m3", "p", "p", "p", "t", "p", "p", "p", "m1",
                    "p", "m2", "p", "p", "r", "p", "m2", "p", "p",
                    "p", "p", "m2", "p", "r", "p", "p", "p", "m2"};
            int j = 0;
            for (int i = 0; i < tiles.length; i++) {
                for (int k = 0; k < tiles[0].length; k++, j++) {
                    tiles[i][k] = new Tile(i, k, defaultLevel[j]);
                }
            }
        }

        // if random level
        if (level.equals("random")) {
            for (int i = 0; i < tiles.length; i++) {
                for (int k = 0; k < tiles[0].length; k++) {
                    String ter = terrains[(int) (Math.random() * (terrains.length - 1))];
                    tiles[i][k] = new Tile(i, k, ter);
                }
            }
            tiles[2][4] = new Tile(2, 4, "t");
        }
    }

    /**
     * Creates a Map based on a preset array of tiles
     * @param tiles Tile objects to be used
     */

    public Map(Tile[][] tiles) {
        terrains = new String[]{"r", "p", "m1", "m2", "m3"};
        this.tiles = tiles;
    }

    /**
     * Gets the level of the Map
     * @return String level
     */

    public String getName() {
        return level;
    }

    /**
     * Gets a specific Tile from the Map
     * @param i int row of Tile
     * @param k int column of Tile
     * @return Tile object being looked for
     */

    public Tile getTile(int i, int k) {
        return tiles[i][k];
    }

    /**
     * Prints out all of the tiles and their attributes to the console.
     */

    public void printTiles() {
        for (int i = 0; i < tiles.length; i++) {
            for (int k = 0; k < tiles[0].length; k++) {
                System.out.println("//");
                System.out.println("ROW: " + tiles[i][k].getRow());
                System.out.println("COL: " + tiles[i][k].getColumn());
                System.out.println("TER: " + tiles[i][k].getTerrain().getName());
            }
        }
    }

    /**
     * Tells each Tile to calculate its own production
     */

    public void calculateProduction() {
        for (int i = 0; i < tiles.length; i++) {
            for (int k = 0; k < tiles[0].length; k++) {
                tiles[i][k].calculateProduction();
            }
        }
    }

}
