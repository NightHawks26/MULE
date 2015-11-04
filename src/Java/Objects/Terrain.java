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

    public String getName() {
        return name;
    }
    public int getFood() {
        return food;
    }
    public int getEnergy() {
        return energy;
    }
    public int getOre() {
        return ore;
    }
    public int getCrystite() {
        return crystite;
    }
}
