package Java.Objects;

import io.github.jgkamat.JayLayer.JayLayer;
import java.util.*;

/**
 * Created by AveryDingler on 9/8/15.
 */
public class MuleGame {

    public Player[] players;
    public String difficulty;
    public Map map;
    private int round = 1;
    private int purchasePrice;
    private int selectPrice = 300;
    private int grantPrice = 0;
    private int playerCount = 0;
    public boolean selectionRound = true;
    public JayLayer sound;
    public int currentPlayer = 0;
    private int timeForTurn = 0;
    public int timeRemaining = 0;
    public Timer t;
    private Store store;

    //Constructor for loading a NEW mule game
    public MuleGame(String difficulty, Map map, Player[] players, JayLayer sound ) {
        this.difficulty = difficulty;
        this.map = map;
        this.players = players;
        this.sound = sound;
        this.store = new Store(difficulty);
    }

    //constructor for loading a mule game
    public MuleGame(Map map, Player[] players, JayLayer sound, Store store) {
        this.map = map;
        this.players = players;
        this.sound = sound;
        this.store = store;
    }

    public int getTimeForTurn() {
        return timeForTurn;
    }

    public void setTimeForTurn(int time) {
        this.timeForTurn = time;
    }

    public Store getStore() {
        return store;
    }

    public Player[] getPlayers() {
        return players;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSelectPrice(int selectPrice) {
        this.selectPrice = selectPrice;
    }

    public void setGrantPrice(int grantPrice) {
        this.grantPrice = grantPrice;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public void setSelectionRound(boolean selectionRound) {
        this.selectionRound = selectionRound;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setSound(JayLayer sound) {
        this.sound = sound;
    }

    public void setPrice() {
        if (playerCount % players.length != 0) {
            Random rng = new Random();
            purchasePrice = 300 + (this.round * rng.nextInt(101));
        }
        playerCount++;
    }

    public int getPurchasePrice() { return purchasePrice; }

    public int getSelectPrice() { return selectPrice; }

    public int getGrantPrice() { return grantPrice; }

    public Map getMap() {
        return map;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getRound() {
        return round;
    }

    public void incRound() {
        round++;
        //Random rng = new Random();
        //purchasePrice = 300 + (this.round * rng.nextInt(101));
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void incCurrentPlayer() {
        currentPlayer++;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private class playerComparator implements Comparator<Player> {

        @Override
        public int compare(Player p1, Player p2) {
            return p1.getScore() - p2.getScore();
        }
    }


    public void arrangePlayers() {
        for (Player p: players) {
            p.refreshScore();
            p.setIsLast(false);
        }
        Arrays.sort(players, new playerComparator());
        players[players.length - 1].setIsLast(true);
    }

}
