package Java.Objects;

/**
 * Created by AveryDingler on 11/4/15.
 */
public class HighScore {

    private String name;
    private Integer score;
    private int place;

    public HighScore(String name, Integer score, int place) {
        this.name = name;
        this.score = score;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public int getPlace() {
        return place;
    }
}
