package Java.Objects;

/**
 * Created by AveryDingler on 11/4/15.
 */
public class HighScore {

    private String name;
    private Integer score;
    private int place;

    /**
     * Creates a HighScore object based on a player's name, their score
     * and what "place" they came in in the game
     * @param name String name of player
     * @param score Integer score of player
     * @param place int place player came in
     */

    public HighScore(String name, Integer score, int place) {
        this.name = name;
        this.score = score;
        this.place = place;
    }

    /**
     * Gets the name associated with the HighScore
     * @return String name of player
     */

    public String getName() {
        return name;
    }

    /**
     * Gets the score associate with the HighScore
     * @return Integer score of player
     */

    public Integer getScore() {
        return score;
    }

    /**
     * Gets the place associated with the HighScore.
     * @return int place of palyer
     */

    public int getPlace() {
        return place;
    }
}
