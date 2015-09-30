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
    private int price;
    public boolean selectionRound = true;
    public JayLayer sound;
    public int currentPlayer = 0;
    private int timeForTurn = 0;
    public int timeRemaining = 0;
    public Timer t;

    public MuleGame(String difficulty, Map map, Player[] players, JayLayer sound ) {
        this.difficulty = difficulty;
        this.map = map;
        this.players = players;
        this.sound = sound;
    }
    public int getTimeForTurn() {
        return timeForTurn;
    }

    public void setTimeForTurn(int time) {
        this.timeForTurn = time;
    }

    public Player[] getPlayers() {
        return players;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setPrice(int round, Player player) {
        Random rng = new Random();
       // if (round <= 2 && this.round < 2) {
        //    price = 0;
        if (player.landCounter < 2) {
            price = 0;
        } else if (this.round >= 2) {
            price = 300 + (this.round * rng.nextInt(101));
        } else {
            price = 300;
        }
    }

    public int getPrice() { return price; }

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

//    public void startTimer(int turnTime) {
//        timeRemaining = turnTime;
//        System.out.println(timeRemaining);
//        Timer t = new Timer();
//        t.scheduleAtFixedRate(
//                new TimerTask()
//                {
//                    public void run()
//                    {
//                        timeRemaining--;
//                        System.out.println(timeRemaining);
//                        if (timeRemaining == 0) {
//                            System.out.print("TURN ENDED");
//                            //----- we can update a label every second------
//                            //label.update();
//                            //---------------
//
//                            //Here we need to basically hit the pub button
//                            //but dont add any money to the player
//                            t.cancel();
//                        }
//                    }
//                },
//                1000,      // run first occurrence after 1 second
//                1000);  // run every one seconds
//
//    }

}
