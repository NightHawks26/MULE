package Java.Controllers;

import Java.Objects.MuleGame;
import Java.Objects.Player;
import Java.Objects.RandomEventGenerator;
import Java.WriteXMLFile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
    private ImageView p1img1;
    @FXML
    private ImageView p2img1;
    @FXML
    private ImageView p3img1;
    @FXML
    private ImageView p4img1;

    @FXML
    private ImageView p1img2;
    @FXML
    private ImageView p2img2;
    @FXML
    private ImageView p3img2;
    @FXML
    private ImageView p4img2;

    @FXML
    private ImageView p1img3;
    @FXML
    private ImageView p2img3;
    @FXML
    private ImageView p3img3;
    @FXML
    private ImageView p4img3;

    @FXML
    private ImageView p1img4;
    @FXML
    private ImageView p2img4;
    @FXML
    private ImageView p3img4;
    @FXML
    private ImageView p4img4;

    @FXML
    private ImageView p1img5;
    @FXML
    private ImageView p2img5;
    @FXML
    private ImageView p3img5;
    @FXML
    private ImageView p4img5;

    @FXML
    private Label p4Money;

    @FXML
    private Label p4Ore;

    @FXML
    private Button continueButton;

    @FXML
    private Label randomEventLabel;

    @FXML
    private MenuItem saveGame;

    private MapController mapController;
    private MuleGame muleGame;
    private Stage stage;
    private int skips;

    @FXML
    /**
     * Empty method needed for jay layer
     * @param url the url needed for initialization
     * @param rb The resource bundle needed for initialization
     */
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Sets the viewable stage to the current area
     * @param stage the viewable area of the game
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the mule game to the current instance
     * @param mulegame current instance of mulegame
     */
    public void setMuleGame(MuleGame mulegame) {
        this.muleGame = mulegame;
    }

    /**
     * Start assigns all images, and values to the appropriate labels and
     * sections of the round controller screen.
     * Once a button is clicked, the game will move to the map screen
     */
    public void start() {
        RandomEventGenerator events = new RandomEventGenerator();
        boolean isLastPlace;
        if (!muleGame.selectionRound) {
            System.out.println("It is "
                    + muleGame.getCurrentPlayerObject().toString()
                    + "'s turn!");
            isLastPlace = muleGame.getCurrentPlayer() == 0;
            String randomEventText = events.getRandomEvent(muleGame);
            System.out.println(randomEventText);
            randomEventLabel.setText(randomEventText);
            muleGame.setTimeForTurn(
                    muleGame.getCurrentPlayerObject()
                            .calculateTimeForTurn(muleGame.getRound()));
            nextAction.setText("TURN: "
                    + muleGame.getCurrentPlayerObject().toString()
                    + "\nTIME FOR TURN: " + muleGame.getTimeForTurn());
        } else {
            randomEventLabel.setText("");
            muleGame.sound.playSoundEffect(muleGame.getRound() - 1);
            System.out.println("Next is a land selection");
            muleGame.arrangePlayers();
            muleGame.map.calculateProduction();
            nextAction.setText("Next is a land selection!");
        }
        Image human = new Image("/images/human.png");
        Image flapper = new Image("/images/flapper.png");
        Image other = new Image("/images/other.gif");
        roundNumber.setText("ROUND: " + Integer.toString(muleGame.getRound()));
        ImageView[] images = {p1Image, p2Image, p3Image, p4Image};
        Player[] players = muleGame.getPlayers();
        Label[] playerNames = {player1Name, player2Name, player3Name,
            player4Name};
        Label[] playerMoney = {p1Money, p2Money, p3Money, p4Money};
        Label[] playerOre = {p1Ore, p2Ore, p3Ore, p4Ore};
        Label[] playerEnergy = {p1Energy, p2Energy, p3Energy, p4Energy};
        Label[] playerFood = {p1Food, p2Food, p3Food, p4Food};
        ImageView[] playerImg1 = {p1img1, p2img1, p3img1, p4img1};
        ImageView[] playerImg2 = {p1img2, p2img2, p3img2, p4img2};
        ImageView[] playerImg3 = {p1img3, p2img3, p3img3, p4img3};
        ImageView[] playerImg4 = {p1img4, p2img4, p3img4, p4img4};
        ImageView[] playerImg5 = {p1img5, p2img5, p3img5, p4img5};

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
        for (int x = players.length; x < 4; x++) {
            playerNames[x].setText("");
            playerMoney[x].setText("");
            playerOre[x].setText("");
            playerEnergy[x].setText("");
            playerFood[x].setText("");
            playerImg1[x].setImage(null);
            playerImg2[x].setImage(null);
            playerImg3[x].setImage(null);
            playerImg4[x].setImage(null);
            playerImg5[x].setImage(null);
        }
        //loads map, displays to screen
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

    /**
     *Sets up save file dialog. once file name is chosen
     *  Saves the game by storing all attributes from
     * the muleGame object. Closes dialog after save completes
     * @param event The event that triggers the save game
     */
    public void saveGame(ActionEvent event) {
        System.out.println("save game");
        WriteXMLFile xmlGenerator = new WriteXMLFile();
        Button closer = new Button("SAVE");
        Label message = new Label("Type the name of you saved game.");
        Pane poppane1 = new Pane(message);
        poppane1.setMinSize(100, 50);
        Pane poppane2 = new Pane(closer);
        poppane2.setMinSize(100, 50);
        TextField fileName = new TextField();
        VBox box = new VBox(message, fileName, closer);
        box.setPadding(new Insets(10, 0, 10, 50));
        Scene popScene = new Scene(box, 300, 100);
        Stage popStage = new Stage();
        popStage.setScene(popScene);
        popStage.initModality(Modality.APPLICATION_MODAL);
        popStage.show();
        closer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String fileNameFinal = fileName.getText().trim().
                        replaceAll(" ", "");
                WriteXMLFile.saveGame(muleGame, fileNameFinal);
                popStage.close();
            }
        });
    }
}
