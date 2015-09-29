package Java.Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import Java.Objects.TileButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Java.Objects.Player;
import Java.Objects.MuleGame;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Popup;

/**
 * Created by AveryDingler on 9/10/15.
 */

public class MapController implements Initializable {

    private MuleGame muleGame;
    private int selectingRound = 1;
    private int selectingPlayer = 0;
    private int numSkipped = 0;
    private Stage stage;
    private boolean isSelectRound;
    private int current;

//    @FXML //fx: id display
//    private Label display;

    @FXML private GridPane thePane;
    @FXML private Label currentPlayerLabel;
    @FXML private Button skipButton;
    @FXML private Button timerEnds;

    MapController mapController = this;


    public void initialize(URL url, ResourceBundle rb) {
        //display.setText(muleGame.map.getTileValues(0,0));
    }

    public void setMuleGame(MuleGame mulegame) {
        this.muleGame = mulegame;
    }

//    public void displayTile() {
//        display.setText(muleGame.map.getTileValues(0,0));
//    }

    public void start() {
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 9; k++) {
                TileButton button = new TileButton(muleGame.getMap().getTile(i,k));
                button.setPrefWidth(Double.MAX_VALUE);
                button.setPrefHeight(Double.MAX_VALUE);
                button.setId(muleGame.getMap().getTile(i, k).getTerrain().getName());
                button.setCol(i);
                button.setRow(k);
                button.getStylesheets().addAll(this.getClass().getResource("/resources/style/style.css").toExternalForm());
                if (button.getTile().isOwned()) {
                    String color = button.getTile().getOwner().getColor().substring(2);
                    button.setStyle("-fx-background-color: #" + color);
                }



                // attempting to make a menu appear when a tile is clicked - would be best to made a pane (or new window)
                // for each tile, and then this method calls to it
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (button.getId().equals("t")) {
                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/fxml/Town.fxml"));
                                loader.load();
                                Parent p = loader.getRoot();
                                //((Node)event.getSource()).getScene().getWindow();
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(new Scene(p));
                                TownController townController = loader.getController();
                                townController.start(mapController, muleGame, stage);
                                stage.show();
                            } catch (Exception e) {
                                System.out.println(e + "THERE WAS AN ERROR WITH THE LOADER");
                            }
                        } else if (!button.getTile().isOwned()) {
//                        StackPane pane = new StackPane();
//                        Rectangle r = new Rectangle();
//                        r.setX(100);
//                        r.setY(100);
//                        r.setWidth(100);
//                        r.setHeight(100);
//                        pane.getChildren().add(r);
//                        thePane.getChildren().add(pane);
                            muleGame.setPrice(selectingRound);
                            Button accept = new Button();
                            accept.setText("Purchase for: " + muleGame.getPrice());
                            Button decline = new Button();
                            decline.setText("Return");
                            Pane popPane = new Pane();
                            popPane.setMinHeight(200);
                            popPane.setMinWidth(200);
                            VBox vbox = new VBox(accept, decline);
                            popPane.getChildren().setAll(vbox);
                            Popup popup = new Popup();
                            popup.setX(200);
                            popup.setY(200);
                            popup.setAnchorX(event.getScreenX());
                            popup.setAnchorY(event.getScreenY());
                            popup.getContent().addAll(popPane);
                            popup.show(stage);
                            accept.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    if (muleGame.getPlayers()[selectingPlayer].getMoney() >= muleGame.getPrice()) {
                                        purchaseLand(muleGame.getPlayers()[selectingPlayer], button);
                                        popup.hide();
                                    } else {
                                        TextField failText = new TextField();
                                        failText.setText("Not enough Money!");
                                        vbox.getChildren().setAll(accept, decline, failText);
                                    }
                                }
                            });
                            timerEnds.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        FXMLLoader loader = new FXMLLoader();
                                        loader.setLocation(getClass().getResource("/fxml/Round.fxml"));
                                        loader.load();
                                        Parent par = loader.getRoot();
                                        //((Node)event.getSource()).getScene().getWindow();
                                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        stage.setScene(new Scene(par));
                                        RoundController roundController = loader.getController();
                                        if (muleGame.getCurrentPlayer() == (muleGame.getPlayers().length - 1)) {
                                            muleGame.setCurrentPlayer(0);
                                            muleGame.selectionRound = true;
                                            muleGame.incRound();
                                        } else {
                                            muleGame.incCurrentPlayer();
                                        }
                                        roundController.setMuleGame(muleGame);
                                        //  roundController.setCurrent(current++);
                                        roundController.setStage(stage);
                                        roundController.start();
                                        stage.show();
                                    } catch (Exception e) {
                                        System.out.println(e + "THERE WAS AN ERROR WITH THE LOADER");
                                    }

                                }
                            });
                            decline.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    popup.hide();
                                }
                            });
                        }

                    }
                });

                thePane.add(button, i, k);
            }
        }
        if (muleGame.selectionRound) {
            currentPlayerLabel.setText("LS: " + muleGame.getPlayers()[selectingPlayer].getName()
                    + " Money Remaining: " + muleGame.getPlayers()[selectingPlayer].getMoney());
        } else {
            currentPlayerLabel.setText("TURN: " + muleGame.getPlayers()[selectingPlayer].getName()
                    + " Money Remaining: " + muleGame.getPlayers()[selectingPlayer].getMoney());
            System.out.println("Start TIMER");
            //muleGame.startTimer(muleGame.getTimeForTurn());
            startTimer(muleGame.getTimeForTurn());
        }

    }

    public void setStage(Stage stage) { this.stage =stage;}

    public void startTimer(int turnTime) {
        muleGame.timeRemaining = turnTime;
        System.out.println(muleGame.timeRemaining);
        muleGame.t = new Timer();
        muleGame.t.scheduleAtFixedRate(
                new TimerTask()
                {
                    public void run()
                    {
                        muleGame.timeRemaining--;
                        System.out.println(muleGame.timeRemaining);
                        if (muleGame.timeRemaining == 0) {
                            System.out.print("TURN ENDED");
//                            ActionEvent event = new ActionEvent();
//                            timerEnds.fireEvent(event);
                            //timerEnds.fireEvent(ActionEvent event);
                            //----- we can update a label every second------
                            //label.update();
                            //---------------

                            //Here we need to basically hit the pub button
                            //but dont add any money to the player
                            muleGame.t.cancel();
                        }
                    }
                },
                1000,      // run first occurrence after 1 second
                1000);  // run every one seconds

    }

    public void skipSelection(ActionEvent event) {
        muleGame.sound.playSoundEffect(0);
        if (selectingRound <=2 && muleGame.getRound() == 1) {
            numSkipped = 0;
        } else {
            numSkipped++;
        }

        if (selectingPlayer == muleGame.getPlayers().length - 1) {
            selectingRound++;
            selectingPlayer = 0;
            if (numSkipped == muleGame.getPlayers().length) {
                System.out.println("ALL SKIPPED END SELECTION PHASE!");
                muleGame.selectionRound = false;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/Round.fxml"));
                    loader.load();
                    Parent p = loader.getRoot();
                    //((Node)event.getSource()).getScene().getWindow();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(p));
                    RoundController roundController = loader.getController();
                    roundController.setMuleGame(muleGame);
                   // roundController.setCurrent(current);
                    roundController.setStage(stage);
                    roundController.start();
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e + "THERE WAS AN ERROR WITH THE LOADER");
                }
            } else {
                numSkipped = 0;
            }
        } else {
            selectingPlayer++;
        }
        currentPlayerLabel.setText("LS: " + muleGame.getPlayers()[selectingPlayer].getName()
                + " Money Remaining: " + muleGame.getPlayers()[selectingPlayer].getMoney());
    }

    public void setSkips(int skips) {
        this.numSkipped = skips;
    }

    public void purchaseLand(Player player, TileButton button) {
        button.getTile().setOwner(player);
        player.incLandCounter();
        int price;
        if (selectingRound <= 2) {
            numSkipped = 0;
            price = muleGame.getPrice();
        } else {
            price = muleGame.getPrice();
        }

        String color = player.getColor().substring(2);
        button.setStyle("-fx-background-color: #" + color);
        player.setMoney(player.getMoney() - price);
        //set the tile to be owned by player
        if (selectingPlayer == muleGame.getPlayers().length - 1) {
            selectingRound++;
            selectingPlayer = 0;
            numSkipped = 0;
        } else {
            selectingPlayer++;
        }

        currentPlayerLabel.setText("LS: " + muleGame.getPlayers()[selectingPlayer].getName()
                + " Money Remaining: " + muleGame.getPlayers()[selectingPlayer].getMoney());


    }
}


