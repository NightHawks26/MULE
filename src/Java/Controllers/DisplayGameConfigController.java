package Java.Controllers;


import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import Java.Objects.TileButton;
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
import Java.Objects.Tile;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Popup;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
 * Created by AveryDingler on 9/10/15.
 */

public class DisplayGameConfigController implements Initializable {

    private MuleGame muleGame;
    private int selectingRound = 1;
    private int selectingPlayer = 0;
    private int numSkipped = 0;
    private Stage stage;

//    @FXML //fx: id display
//    private Label display;

    @FXML private GridPane thePane;
    @FXML private Label currentPlayerLabel;
    @FXML private Button skipButton;

    DisplayGameConfigController displayGameConfigController = this;


    public void initialize(URL url, ResourceBundle rb) {
        //display.setText(muleGame.map.getTileValues(0,0));
        System.out.println("BLJDSLKFJLSDKFJL:");
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


                // attempting to make a menu appear when a tile is clicked - would be best to made a pane (or new window)
                // for each tile, and then this method calls to it
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (button.getId().equals("t")) {
                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/Java/Town.fxml"));
                                loader.load();
                                Parent p = loader.getRoot();
                                //((Node)event.getSource()).getScene().getWindow();
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(new Scene(p));
                                TownController townController = loader.getController();
                                townController.start(displayGameConfigController, muleGame, stage);
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
        currentPlayerLabel.setText(muleGame.getPlayers()[selectingPlayer].getName() + " Money Remaining: " + muleGame.getPlayers()[selectingPlayer].getMoney());
    }

//    public void landSelectionStart() {
//        int selectionRound = 0;
//        int Player = 0;
//
//        for (int x = 0; x < 2; x ++) {
//            for (int y = 0; y < (muleGame.getPlayers().length); y++) {
//                Player selectingPlayer = muleGame.getPlayers()[y];
//                currentPlayerLabel.setText(selectingPlayer.getName() + " Money Remaining: " + selectingPlayer.getMoney());
//            }
//        }
//    }

//    public void playerSelectLand(int selectionRound, int PlayerNum) {
//        boolean freeRound = false;
//        if (selectionRound <= 2) {
//            freeRound = true;
//        }
//        Player selectingPlayer = muleGame.getPlayers()[PlayerNum];
//
//    }

    public void setStage(Stage stage) { this.stage =stage;}

    public void skipSelection() {
        if (selectingRound <=2) {
            numSkipped = 0;
        } else {
            numSkipped++;
        }

        if (selectingPlayer == muleGame.getPlayers().length - 1) {
            selectingRound++;
            selectingPlayer = 0;
            if (numSkipped == muleGame.getPlayers().length) {
                System.out.println("ALL SKIPPED END SELECTION PHASE!");
            } else {
                numSkipped = 0;
            }
        } else {
            selectingPlayer++;
        }
        currentPlayerLabel.setText(muleGame.getPlayers()[selectingPlayer].getName()
                + " Money Remaining: " + muleGame.getPlayers()[selectingPlayer].getMoney());
    }

    public void purchaseLand(Player player, TileButton button) {
        button.getTile().setOwner(player);
        int price;
        if (selectingRound <= 2) {
            numSkipped = 0;
            price = 0;
        } else {
            price = muleGame.getPrice(); // this line needs to be changed to actual price of the tile
        }


        //Add a popup window here that says
        //"Do you want to purchase this land for {price}"
        //if yes, run following code
        //if no, return

        String color = player.getColor().substring(2);
        button.setStyle("-fx-background-color: #" + color);
        player.setMoney(player.getMoney() - price);
        //set the tile to be owned by player
        if (selectingPlayer == muleGame.getPlayers().length - 1) {
            selectingRound++;
            selectingPlayer = 0;
            if (numSkipped == muleGame.getPlayers().length) {
                System.out.println("ALL SKIPPED END SELECTION PHASE!");
            } else {
                numSkipped = 0;
            }
        } else {
            selectingPlayer++;
        }

        currentPlayerLabel.setText(muleGame.getPlayers()[selectingPlayer].getName()
                + " Money Remaining: " + muleGame.getPlayers()[selectingPlayer].getMoney());


    }
}


