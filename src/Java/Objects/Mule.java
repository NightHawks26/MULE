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

    public int getRiverProduction() {
        return riverProduction;
    }

    public int getPlainProduction() {
        return plainProduction;
    }

    public int getMountain1Production() {
        return mountain1Production;
    }

    public int getMountain2Production() {
        return mountain2Production;
    }

    public int getMountain3Production() {
        return mountain3Production;
    }

    public String getMuleType() {
        return muleType;
    }
}
