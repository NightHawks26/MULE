package Java.Objects;

/**
 * Created by Brian on 9/13/2015.
 */
public class Terrain {

    private final String name;
    private int food;
    private int energy;
    private int ore;
    private int crystite;

    /**
     * Terrain constructor based on terrain type
     * @param name String type of terrain
     */

    public Terrain(String name) {
        this.name = name;
        int upper = 3;
        int lower = 0;
        if (name.equals("r")) {
            food = 4;
            energy = 2;
            ore = 0;
            crystite = 0;
        } else if (name.equals("p")) {
            food = 4;
            energy = 1;
            ore = 1;
            crystite = (int) (Math.random() * (upper - lower)) + lower;
        } else if (name.equals("m1")) {
            food = 1;
            energy = 1;
            ore = 2;
            crystite = (int) (Math.random() * (upper - lower)) + lower;
        } else if (name.equals("m2")) {
            food = 1;
            energy = 1;
            ore = 3;
            crystite = (int) (Math.random() * (upper - lower)) + lower;
        } else if (name.equals("m3")) {
            food = 1;
            energy = 1;
            ore = 4;
            crystite = (int) (Math.random() * (upper - lower)) + lower;
        }
    }

    /**
     * Gets the terrain type defined as the name
     * @return String terrain type
     */

    public String getName() {
        return name;
    }

    /**
     * Gets the food value of the terrain
     * @return int food
     */

    public int getFood() {
        return food;
    }

    /**
     * Gets the energy value of the terrain
     * @return int energy
     */

    public int getEnergy() {
        return energy;
    }

    /**
     * Gets the ore value of the terrain
     * @return int ore
     */

    public int getOre() {
        return ore;
    }

    /**
     * Gets the crystite value of the terrain
     * @return int crystite
     */

    public int getCrystite() {
        return crystite;
    }
}
