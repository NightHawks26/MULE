package Java.Controllers;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Java.Objects.Player;
import Java.Objects.MuleGame;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by AveryDingler on 9/9/15.
 */
public class AddPlayerController implements Initializable{
    @FXML
    public ToggleGroup raceGroup;
    @FXML
    public ChoiceBox<String> colorPicker;
    @FXML
    public Button finishPlayer;
    @FXML
    public Label playerNumber;
    @FXML
    public Text currentMap;
    @FXML
    public Text currentDifficulty;
    @FXML
    private TextField newName;

    private MuleGame muleGame;

    private Stage stage;

   // private MapController mapController;
    private RoundController roundController;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setMuleGame(MuleGame muleGame) {
        //Helpful Video https://www.youtube.com/watch?v=Vh7XDjWlm_w
        currentDifficulty.setText(muleGame.getDifficulty());
        currentMap.setText(muleGame.getMap().getName());
        playerNumber.setText("PLAYER 1");
        this.muleGame = muleGame;
        colorPicker.getItems().addAll("Orange", "Purple", "Blue", "Yellow", "Red", "White");
        //delete the following after done testing
        newName.setText("Player 1");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void addPlayer(ActionEvent event) {//throws IOException {
        try {
            muleGame.sound.playSoundEffect(0);
            int x = nextNull(muleGame.getPlayers());
            if (x == 10) {
                return;
            } else {
                String color = null;
                if (colorPicker.getValue().equals("Orange")) {
                    color = "#FF6600";
                } else if (colorPicker.getValue().equals("Purple")) {
                    color = "#FF66FF";
                } else if (colorPicker.getValue().equals("White")) {
                    color = "#FFFFFF";
                } else if (colorPicker.getValue().equals("Red")) {
                    color = "#FF5050";
                } else if (colorPicker.getValue().equals("Blue")) {
                    color = "#33CCCC";
                } else if (colorPicker.getValue().equals("Yellow")) {
                    color = "#FFFF99";
                } else {
                    System.out.println("DIDN'T PICK A COLOR??");
                }
                muleGame.players[x] = new Player(newName.getText(), raceGroup.getSelectedToggle().toString(), muleGame.getDifficulty(), color);
                System.out.println(Arrays.toString(muleGame.getPlayers()));

                colorPicker.getItems().removeAll(colorPicker.getValue());

                if (muleGame.players[(muleGame.getPlayers().length) - 1] != null) {

                    muleGame.arrangePlayers();
                    muleGame.getPlayers()[muleGame.getPlayers().length - 1].setIsLast(true);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/Round.fxml"));
                    loader.load();
                    Parent p = loader.getRoot();
                    ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(p));
                    roundController = loader.getController();
                    roundController.setMuleGame(muleGame);
                    roundController.setStage(stage);
                    startGame();
                    stage.show();
                } else {
                    newName.clear();
                    playerNumber.setText("PLAYER " + (x + 2));
                    //delete following after testing
                    newName.setText("Player " + (x + 2));
                }
            }
        } catch (IOException e) {
            System.out.println("Error with Loader");
        }
    }

    public void startGame() {
        roundController.start();
    }

    private int nextNull(Player[] players) {
        for (int x = 0; x < players.length; x++) {
            if (players[x] == null) {
                return x;
            }
        }
        return 10;
    }

}


