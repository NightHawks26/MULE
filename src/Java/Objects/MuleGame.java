package Java.Objects;

import io.github.jgkamat.JayLayer.JayLayer;
import java.util.*;

/**
 * Created by AveryDingler on 9/8/15.
 */
public class MuleGame {

    public Player[] players;
    private String difficulty;
    public Map map;
    private int round = 1;
    private int purchasePrice;
    private int selectPrice = 300;
    private int grantPrice = 0;
    private int playerCount = 0;
    public boolean selectionRound = true;
    public JayLayer sound;
    private int currentPlayer = 0;
    private int timeForTurn = 0;
    public int timeRemaining = 0;
    public Timer t;
    private Store store;

    //Constructor for loading a NEW mule game

    /**
     * Constructor used when making a MuleGame object for a new game.
     * @param difficulty String difficulty of new game
     * @param map Map of tiles for new game
     * @param players Player[] array of players for the new game
     * @param sound JayLayer sound for the new game
     */

    public MuleGame(String difficulty, Map map, Player[] players, JayLayer sound ) {
        this.difficulty = difficulty;
        this.map = map;
        this.players = players;
        this.sound = sound;
        this.store = new Store(difficulty);
    }

    //constructor for loading a mule game

    /**
     * Constructor used when loading a MuleGame object
     * @param map Map used for loaded game
     * @param players Player[] array used for loaded game
     * @param sound JayLayer used for loaded game
     * @param store Store object for loaded game
     */

    public MuleGame(Map map, Player[] players, JayLayer sound, Store store) {
        this.map = map;
        this.players = players;
        this.sound = sound;
        this.store = store;
    }

    /**
     * Gets the time for turn
     * @return int time for turn
     */

    public int getTimeForTurn() {
        return timeForTurn;
    }

    /**
     * Sets the time for turn
     * @param time int time for turn to be set
     */

    public void setTimeForTurn(int time) {
        this.timeForTurn = time;
    }

    /**
     * Gets the current Store object
     * @return Store object currently being used
     */

    public Store getStore() {
        return store;
    }

    /**
     * Gets the current player array for the game
     * @return Player[] array of players in game
     *
     */

    public Player[] getPlayers() {
        return players;
    }

    /**
     * Gets the difficulty of the game
     * @return String difficulty value
     */

    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the round for the current game
     * @param round int value of the round
     */

    public void setRound(int round) {
        this.round = round;
    }

    /**
     * Sets the purchase price for tiles in the game
     * @param purchasePrice int price for purchasing tiles
     */

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * Sets the price for purchasing tiles in the selection round
     * @param selectPrice int price during selection round
     */

    public void setSelectPrice(int selectPrice) {
        this.selectPrice = selectPrice;
    }

    /**
     * Sets the price of tiles that are granted to players
     * @param grantPrice int price of granted tiles
     */

    public void setGrantPrice(int grantPrice) {
        this.grantPrice = grantPrice;
    }

    /**
     * Sets index of the current player in hte game
     * @param playerCount int index of current player in game
     */

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    /**
     * Boolean representing whether or not the current round is a
     * selection round
     * @param selectionRound boolean true if selection round,
     *                       false otherwise
     */

    public void setSelectionRound(boolean selectionRound) {
        this.selectionRound = selectionRound;
    }

    /**
     * Sets the current Store object
     * @param store Store object to be used by current game
     */

    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Sets the curren JayLayer sound object to be used in game
     * @param sound JayLayer object for sound
     */

    public void setSound(JayLayer sound) {
        this.sound = sound;
    }

    /**
     * Sets the current price for a tile outside of selection and grant
     */

    public void setPrice() {
        if (playerCount % players.length != 0) {
            Random rng = new Random();
            purchasePrice = 300 + (this.round * rng.nextInt(101));
        }
        playerCount++;
    }

    /**
     * Gets boolean representing if it is a selection round
     * @return boolean value true if selection round, false otherwise
     */

    public boolean isSelectionRound() {
        return selectionRound;
    }

    /**
     * Gets the purchase price for a tile
     * @return int price for purchase
     */

    public int getPurchasePrice() { return purchasePrice; }

    /**
     * Gets the selection round price for a tile
     * @return int selection round price
     */

    public int getSelectPrice() { return selectPrice; }

    /**
     * Gets the grant price for a tile
     * @return int grant round price
     */

    public int getGrantPrice() { return grantPrice; }

    /**
     * Gets the current Map object in use
     * @return Map object used by game
     */

    public Map getMap() {
        return map;
    }

    /**
     * Sets the current player array in game
     * @param players Player[] array of players
     */

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Sets the difficulty of the game
     * @param difficulty String value representing the difficulty
     */

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Sets the current Map object used in game
     * @param map Map object to be used
     */

    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Gets the current round of the game
     * @return int round number
     */

    public int getRound() {
        return round;
    }

    /**
     * Increments the round counter
     */

    public void incRound() {
        round++;
    }

    /**
     * Gets the current player's index
     * @return int index of current player in player array
     */

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Increments the current player count
     */

    public void incCurrentPlayer() {
        currentPlayer++;
    }

    /**
     * Gets the actual Player object of the current player
     * @return Player object of the current player
     */

    public Player getCurrentPlayerObject() { return players[currentPlayer]; }

    /**
     * Sets the current player index to specified index
     * @param currentPlayer int current player index
     */

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Comparator for comparing players based on their score
     */

    private class playerComparator implements Comparator<Player> {

        @Override
        public int compare(Player p1, Player p2) {
            return p1.getScore() - p2.getScore();
        }
    }

    /**
     * Rearranges the players in the player array based on the comparator.
     */

    public void arrangePlayers() {
        for (Player p: players) {
            p.refreshScore();
            p.setIsLast(false);
        }
        Arrays.sort(players, new playerComparator());
        players[players.length - 1].setIsLast(true);
    }

}
