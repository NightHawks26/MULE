package Java.Controllers; /**
 * Sample Skeleton for 'Round.fxml' Controller Class
 */

import Java.Objects.MuleGame;
import Java.Objects.Player;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RoundController implements Initializable {

    @FXML
    private Label player1Name;

    @FXML
    private Label p2Food;

    @FXML
    private Label p2Ore;

    @FXML
    private Label p1Food;

    @FXML
    private Label p3Money;

    @FXML
    private Label roundNumber;

    @FXML
    private ImageView p1Image;

    @FXML
    private ImageView p4Image;

    @FXML
    private Label player4Name;

    @FXML
    private Label p3Ore;

    @FXML
    private Label p2Energy;

    @FXML
    private Label p4Food;

    @FXML
    private Label p1Ore;

    @FXML
    private ImageView p3Image;

    @FXML
    private Label p2Money;

    @FXML
    private Label nextAction;

    @FXML
    private Label player3Name;

    @FXML
    private Label p4Energy;

    @FXML
    private Label p3Food;

    @FXML
    private Label player2Name;

    @FXML
    private Label p3Energy;

    @FXML
    private Label p1Money;

    @FXML
    private Label p1Energy;

    @FXML
    private ImageView p2Image;

    @FXML
    private Label p4Money;

    @FXML
    private Label p4Ore;

    @FXML
    private Button continueButton;

    // private int current = -1;
    private MapController mapController;
    private MuleGame muleGame;
    private Stage stage;
    private int skips;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setSkips(int skips) { this.skips = skips; }

    public void setStage(Stage stage) { this.stage =stage;}

    public void setMuleGame(MuleGame mulegame) {
        this.muleGame = mulegame;
    }

    // public void setCurrent(int current) { this.current = current % muleGame.getPlayers().length; }

    public void start() {

        if (!muleGame.selectionRound) {
            System.out.println("It is " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].toString() + "'s turn!");
            muleGame.setTimeForTurn(muleGame.getPlayers()[muleGame.getCurrentPlayer()].calculateTimeForTurn(muleGame.getRound()));
            nextAction.setText("TURN: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].toString()
                    + "\nTIME FOR TURN: " + muleGame.getTimeForTurn());
        } else {
            System.out.println("Next is a land selection");
            muleGame.arrangePlayers();
            nextAction.setText("Next is a land selection!");
        }
        //Image human = new Image(".." + File.separator + ".." + File.separator + "resources" + File.separator + "images" + File.separator + "human.jpg");
        Image human = new Image("/images/human.jpg");
        Image flapper = new Image("/images/flapper.jpg");
        Image other = new Image("/images/other.gif");
        roundNumber.setText("ROUND: " + Integer.toString(muleGame.getRound()));
        ImageView[] images = {p1Image, p2Image, p3Image, p4Image};
        Player[] players = muleGame.getPlayers();
        Label[] playerNames = {player1Name, player2Name, player3Name, player4Name};
        Label[] playerMoney = {p1Money, p2Money, p3Money, p4Money};
        Label[] playerOre = {p1Ore, p2Ore, p3Ore, p4Ore};
        Label[] playerEnergy = {p1Energy, p2Energy, p3Energy, p4Energy};
        Label[] playerFood = {p1Food, p2Food, p3Food, p4Food};
        for (int x = 0; x < players.length; x++) {
            if (players[x].getRace().contains("Human")) {
                images[x].setImage(human);
            } else if (players[x].getRace().contains("Flapper")) {
                images[x].setImage(flapper);
            } else {
                images[x].setImage(other);
            }
            playerNames[x].setText(players[x].getName());
            playerMoney[x].setText("MONEY: " + players[x].getMoney());
            playerOre[x].setText("ORE: " + players[x].getOre());
            playerEnergy[x].setText("ENERGY: " + players[x].getEnergy());
            playerFood[x].setText("FOOD: " + players[x].getFood());
        }
        updatePlayerScores();
        continueButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/Map.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {

                }
                Parent p = loader.getRoot();
                ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(p));
                mapController = loader.getController();
                mapController.setMuleGame(muleGame);
                mapController.setSkips(skips);
                mapController.setStage(stage);
                mapController.start(true);
                stage.show();
            }
        });
    }

    private void updatePlayerResources() {
        //calculate player
    }
    private void updatePlayerScores() {
        //display money, score, and energy on screen

    }
}
