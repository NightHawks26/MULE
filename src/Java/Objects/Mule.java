package Java.Objects;

/**
 * Created by AveryDingler on 10/4/15.
 */
public class Mule {

    private int riverProduction;
    private int plainProduction;
    private int mountain1Production;
    private int mountain2Production;
    private int mountain3Production;
    private final String muleType;

    /**
     * Creates a MULE object based on what type is passed in
     * @param muleType String type of MULE to be created
     */

    public Mule(String muleType) {
        this.muleType = muleType;
        if (muleType.equals("Food")) {
            this.riverProduction = 4;
            this.plainProduction = 2;
            this.mountain1Production = 1;
            this.mountain2Production = 1;
            this.mountain3Production = 1;
        } else if (muleType.equals("Energy")) {
            this.riverProduction = 2;
            this.plainProduction = 3;
            this.mountain1Production = 1;
            this.mountain2Production = 1;
            this.mountain3Production = 1;
        } else if (muleType.equals("Ore")) {
            this.riverProduction = 0;
            this.plainProduction = 1;
            this.mountain1Production = 2;
            this.mountain2Production = 3;
            this.mountain3Production = 4;
        } else {
            System.out.println("We have not implemented Crystite");
        }
    }

    /**
     * Gets the production value for river terrain
     * @return int production value
     */

    public int getRiverProduction() {
        return riverProduction;
    }

    /**
     * Gets the production value for plain terrain
     * @return int production value
     */

    public int getPlainProduction() {
        return plainProduction;
    }

    /**
     * Gets the production value for mountain1 terrain
     * @return int production value
     */

    public int getMountain1Production() {
        return mountain1Production;
    }

    /**
     * Gets the production value for mountain2 terrain
     * @return int production value
     */

    public int getMountain2Production() {
        return mountain2Production;
    }

    /**
     * Gets the production value for mountain3 terrain
     * @return int production value
     */

    public int getMountain3Production() {
        return mountain3Production;
    }

    /**
     * Gets the MULE object's type
     * @return String type
     */

    public String getMuleType() {
        return muleType;
    }
}
