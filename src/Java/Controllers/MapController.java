package Java.Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import Java.Objects.TileButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Java.Objects.Player;
import Java.Objects.MuleGame;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
    private int currentPrice;

    private Image image;
    private String colorName;

//    @FXML //fx: id display
//    private Label display;

    @FXML private GridPane thePane;
    @FXML private Label currentPlayerLabel;
    @FXML private Button skipButton;
    @FXML private Button timerEnds;
    @FXML private HBox bottomBar;

    MapController mapController = this;


    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setMuleGame(MuleGame mulegame) {
        this.muleGame = mulegame;
    }

//    public void displayTile() {
//        display.setText(muleGame.map.getTileValues(0,0));
//    }

    public void start(boolean startOfTurn) {
        muleGame.setPrice();
        if (!muleGame.selectionRound) {
            //skipButton.setVisible(false);
            String playerColor = muleGame.getCurrentPlayerObject().getColor();
            bottomBar.setStyle("-fx-background-color: " + playerColor);

        } else {
            String playerColor = muleGame.getPlayers()[selectingPlayer].getColor();
            bottomBar.setStyle("-fx-background-color: " + playerColor);
        }
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 9; k++) {
                TileButton button = new TileButton(muleGame.getMap().getTile(i,k));
                button.setPrefWidth(Double.MAX_VALUE);
                button.setPrefHeight(Double.MAX_VALUE);
                button.setId(muleGame.getMap().getTile(i, k).getTerrain().getName());
                button.setCol(i);
                button.setRow(k);
                button.getStylesheets().addAll(this.getClass().getResource("../../resources/style/style.css").toExternalForm());
                if (button.getTile().isOwned()) {
                    String color = button.getTile().getOwner().getColor();
                    button.setStyle("-fx-background-color: " + color);
                    if (color == "#FF6600") {
                        colorName = "orange";
                    }
                    else if (color == "#FF66FF") {
                        colorName = "purple";
                    }
                    else if (color == "#FFFFFF") {
                        colorName = "white";
                    }
                    else if (color == "#FF5050") {
                        colorName = "red";
                    }
                    else if (color == "#33CCCC") {
                        colorName = "blue";
                    }
                    else if (color == "#FFFF99") {
                        colorName = "yellow";
                    } else {
                        colorName = "black";
                    }
                    image = new Image("images/flags/animated_" + colorName + "_flag.gif");
                    button.setGraphic(new ImageView(image));

                } else {
                    image = null;
                }
                if (button.getTile().getMule() != null) {
                    button.setText("M U              L E");
                }



                // attempting to make a menu appear when a tile is clicked - would be best to made a pane (or new window)
                // for each tile, and then this method calls to it
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (muleGame.getCurrentPlayerObject().getMuleInHand() != null
                                && !button.getTile().isOwned()) {
                            System.out.println("you lost that mule, dummie. tile not owned");
                            stage.getScene().setCursor(Cursor.DEFAULT);
                            muleGame.getCurrentPlayerObject().setMuleInHand(null);
                        } else if (muleGame.getCurrentPlayerObject().getMuleInHand() != null
                                && button.getTile().getMule() != null) {
                            System.out.println("You lost that mule, dummie. already has a mule");
                            stage.getScene().setCursor(Cursor.DEFAULT);
                            muleGame.getCurrentPlayerObject().setMuleInHand(null);
                        } else if (muleGame.getCurrentPlayerObject().getMuleInHand() != null
                                && !(button.getId().equals("t"))) {
                            if (button.getTile().getOwner().equals(muleGame.getCurrentPlayerObject())) {
                                System.out.println("CORRECT OWNER");
                                button.setText("M U              L E");
                                button.getTile().setMule(muleGame.getCurrentPlayerObject().getMuleInHand());
                            } else {
                                System.out.println("You lost that mule, dummie");
                            }
                            stage.getScene().setCursor(Cursor.DEFAULT);
                            muleGame.getCurrentPlayerObject().setMuleInHand(null);
                        } else {
                            if (button.getId().equals("t")) {
                                if (!muleGame.selectionRound) {
                                    try {
                                        FXMLLoader loader = new FXMLLoader();
                                        loader.setLocation(getClass().getResource("/fxml/Town.fxml"));
                                        loader.load();
                                        Parent p = loader.getRoot();
                                        //((Node)event.getSource()).getScene().getWindow();
                                        Cursor tempCursor = stage.getScene().getCursor();
                                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        stage.setScene(new Scene(p));
                                        stage.getScene().setCursor(tempCursor);
                                        TownController townController = loader.getController();
                                        townController.start(mapController, muleGame, stage);
                                        stage.show();
                                    } catch (Exception e) {
                                        System.out.println(e + "THERE WAS AN ERROR WITH THE LOADER");
                                    }
                                }
                            } else if (!button.getTile().isOwned() && muleGame.isSelectionRound()) {
//                        StackPane pane = new StackPane();
//                        Rectangle r = new Rectangle();
//                        r.setX(100);
//                        r.setY(100);
//                        r.setWidth(100);
//                        r.setHeight(100);
//                        pane.getChildren().add(r);
//                        thePane.getChildren().add(pane);
                                Button accept = new Button();
//                            if (muleGame.selectionRound) {
//                                muleGame.setPrice(muleGame.getRound(), muleGame.getPlayers()[selectingPlayer]);
//                            } else {
//                                muleGame.setPrice(muleGame.getRound(), muleGame.getCurrentPlayerObject());
//                            }
                                accept.setText("Accept");
                                Button decline = new Button();
                                decline.setText("Return");
                                Pane popPane1 = new Pane();
                                Pane popPane2 = new Pane();
                                popPane1.setMinSize(100, 50);
                                popPane2.setMinSize(100, 50);
                                decline.setMinWidth(accept.getWidth());
                                popPane1.getChildren().addAll(accept);
                                popPane2.getChildren().addAll(decline);
                                VBox vbox = new VBox(popPane1, popPane2);
                                vbox.setPadding(new Insets(10, 0, 10, 50));
                                vbox.setStyle("-fx-background-color: #54CC94;");
                                //vbox.getChildren().setAll(vbox);
//                            Popup popup = new Popup();
//                            popup.setX(200);
//                            popup.setY(200);
//                            popup.setAnchorX(event.getScreenX());
//                            popup.setAnchorY(event.getScreenY());
//                            popup.getContent().addAll(vbox);
                                Scene popScene = new Scene(vbox, 200, 100);
                                //popup.show(stage);
                                Stage popStage = new Stage();
                                popStage.setScene(popScene);
                                popStage.initModality(Modality.APPLICATION_MODAL);
                                if (muleGame.selectionRound && selectingRound <= 2 && muleGame.getRound() == 1) {
                                    currentPrice = muleGame.getGrantPrice();
                                } else if (muleGame.selectionRound) {
                                    currentPrice = muleGame.getSelectPrice();
                                } else {
                                    currentPrice = muleGame.getPurchasePrice();
                                }
                                popStage.setTitle("Purchase for: " + currentPrice);
                                popStage.setX(event.getScreenX());
                                popStage.setY(event.getScreenY());
                                popStage.show();
                                accept.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if (muleGame.selectionRound) {
                                            //muleGame.setPrice(muleGame.getRound(), muleGame.getPlayers()[selectingPlayer]);
                                            if (muleGame.getPlayers()[selectingPlayer].getMoney() >= currentPrice) {
                                                purchaseLand(muleGame.getPlayers()[selectingPlayer], button, muleGame.selectionRound);
                                                //popup.hide();
                                                popStage.close();
                                            } else {
                                                TextField failText = new TextField();
                                                failText.setText("Not enough Money!");
                                                vbox.getChildren().setAll(accept, decline, failText);
                                            }
                                        } else {
                                            //muleGame.setPrice(muleGame.getRound(), muleGame.getCurrentPlayerObject());
                                            if (muleGame.getCurrentPlayerObject().getMoney() >= currentPrice) {
                                                purchaseLand(muleGame.getCurrentPlayerObject(), button, muleGame.selectionRound);
                                            } else {
                                                TextField failText = new TextField();
                                                failText.setText("Not enough Money!");
                                                vbox.getChildren().setAll(accept, decline, failText);
                                            }
                                        }

                                    }
                                });

                                decline.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        //popup.hide();
                                        popStage.close();
                                    }
                                });
                            }
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
            currentPlayerLabel.setText("TURN: " + muleGame.getCurrentPlayerObject().getName()
                    + " Money Remaining: " + muleGame.getCurrentPlayerObject().getMoney());
            if (startOfTurn) {
                startTimer(muleGame.getTimeForTurn());
            }
        }

    }

    public void setStage(Stage stage) { this.stage =stage;}

    public void startTimer(int turnTime) {
        muleGame.timeRemaining = turnTime;
        int startTime = turnTime;
        System.out.println(muleGame.timeRemaining);
        muleGame.t = new Timer();
        muleGame.t.schedule(new TimerTask() {
            @Override
            public void run() {
                muleGame.t.cancel();
                Platform.runLater(new Runnable() {
                    public void run() {
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/fxml/Round.fxml"));
                            loader.load();
                            Parent p = loader.getRoot();
                            //stage = new Stage();
                            Cursor tempCursor = stage.getScene().getCursor();
                            stage.setScene(new Scene(p));
                            stage.getScene().setCursor(tempCursor);
                            RoundController roundController = loader.getController();
                            if (muleGame.getCurrentPlayer() == (muleGame.getPlayers().length - 1)) {
                                muleGame.setCurrentPlayer(0);
                                muleGame.selectionRound = true;
                                muleGame.incRound();
                                if (muleGame.getRound() > 12) {
                                    FXMLLoader endLoader = new FXMLLoader();
                                    endLoader.setLocation(getClass().getResource("/fxml/FinalScores.fxml"));
                                    endLoader.load();
                                    Parent endP = endLoader.getRoot();
                                    Cursor tmp = stage.getScene().getCursor();
                                    stage.setScene(new Scene(endP));
                                    stage.getScene().setCursor(tmp);
                                    FinalScoresController finals = endLoader.getController();
                                    finals.setMuleGame(muleGame);
                                    finals.setStage(stage);
                                    finals.start();
                                    stage.show();
                                }
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
            }
        }, startTime * 1000);
        muleGame.t.scheduleAtFixedRate(
                new TimerTask()
                {
                    public void run()
                    {
                        //if (muleGame.timeRemaining > 0) {
                        muleGame.timeRemaining--;
                        System.out.println(muleGame.timeRemaining);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                skipButton.setText(Integer.toString(muleGame.timeRemaining));
                            }
                        });
                    }
                },
                1000,      // run first occurrence after 1 second
                1000);  // run every one seconds

    }

    public void skipSelection(ActionEvent event) {
        if (!muleGame.selectionRound) {
            return;
        }
        muleGame.sound.playSoundEffect(muleGame.getRound() - 1);
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
                    Cursor tempCursor = stage.getScene().getCursor();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(p));
                    stage.getScene().setCursor(tempCursor);
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
        String playerColor = muleGame.getPlayers()[selectingPlayer].getColor();
        bottomBar.setStyle("-fx-background-color: " + playerColor);
    }

    public void setSkips(int skips) {
        this.numSkipped = skips;
    }

    public void purchaseLand(Player player, TileButton button, boolean selectionRound) {
        if (selectionRound) {
            button.getTile().setOwner(player);
            player.incLandCounter();
            if (selectingRound <= 2) {
                numSkipped = 0;
            }

            String color = player.getColor();
            button.setStyle("-fx-background-color: " + color);
            player.setMoney(player.getMoney() - currentPrice);
            //set the tile to be owned by player
            //if (muleGame.selectionRound) {
            if (selectingPlayer == muleGame.getPlayers().length - 1) {
                selectingRound++;
                selectingPlayer = 0;
                numSkipped = 0;
            } else {
                selectingPlayer++;
            }
            //}

            currentPlayerLabel.setText("LS: " + muleGame.getPlayers()[selectingPlayer].getName()
                    + " Money Remaining: " + muleGame.getPlayers()[selectingPlayer].getMoney());
            String playerColor = muleGame.getPlayers()[selectingPlayer].getColor();
            if (color == "#FF6600") {
                colorName = "orange";
            }
            else if (color == "#FF66FF") {
                colorName = "purple";
            }
            else if (color == "#FFFFFF") {
                colorName = "white";
            }
            else if (color == "#FF5050") {
                colorName = "red";
            }
            else if (color == "#33CCCC") {
                colorName = "blue";
            }
            else if (color == "#FFFF99") {
                colorName = "yellow";
            } else {
                colorName = "black";
            }
            image = new Image("images/flags/animated_" + colorName + "_flag.gif");
            button.setGraphic(new ImageView(image));
            bottomBar.setStyle("-fx-background-color: " + playerColor);
        } else {
            button.getTile().setOwner(player);
            player.incLandCounter();

            String color = player.getColor();
            button.setStyle("-fx-background-color: " + color);
            player.setMoney(player.getMoney() - currentPrice);
            currentPlayerLabel.setText("TURN: " + muleGame.getCurrentPlayerObject().getName()
                    + " Money Remaining: " + muleGame.getCurrentPlayerObject().getMoney());

        }


    }
}


