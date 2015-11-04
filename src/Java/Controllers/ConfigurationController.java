package Java.Controllers;

import Java.Objects.MuleGame;
import Java.Objects.Player;
import Java.Objects.Map;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import io.github.jgkamat.JayLayer.JayLayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class ConfigurationController implements Initializable{

    @FXML // fx:id="selectPlayers"
    private ChoiceBox<Integer> selectPlayers; // Value injected by FXMLLoader

    @FXML // fx:id="selectMap"
    private ChoiceBox<String> selectMap; // Value injected by FXMLLoader

    private Stage stage;

    @FXML
    private ChoiceBox<String> selectDifficulty;

    @FXML // fx:id="startGame"
    private Button startGame; // Value injected by FXMLLoader

    private JayLayer sound;

    //@FXML // This method is called by the
    // FXMLLoader when initialization is complete

    /**
     * initializes configuration screen options and displays stage
     * @param url url
     * @param rb resource bundle
     */
    public void initialize(URL url, ResourceBundle rb) {
        selectMap.getItems().addAll("default", "random");
        selectPlayers.getItems().addAll(2, 3, 4);
        selectDifficulty.getItems().addAll("Beginner", "Standard", "Tournament");
        selectDifficulty.getSelectionModel().selectFirst();
        selectMap.getSelectionModel().selectFirst();
        selectPlayers.getSelectionModel().selectFirst();
        stage = new Stage();
    }

    /**
     * method that switches to the add player screen
     * @param event event that triggers this (button click)
     * @throws IOException if add player screen doesn't exist
     */
    public void switchToPlayers(ActionEvent event) throws IOException {

        sound.playSoundEffect(16);
        Player[] players = new Player[selectPlayers.getValue()];
        Map map = new Map(selectMap.getValue());
        MuleGame muleGame = new MuleGame(selectDifficulty.getValue(), map, players, sound);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AddPlayer.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        AddPlayerController addPlayerController = loader.getController();
        addPlayerController.setMuleGame(muleGame);
        addPlayerController.setStage(stage);
        stage.show();
    }

    /**
     * sets the sound for the screen
     * @param sound the sound to be played
     */
    public void setSound(JayLayer sound) {
        this.sound = sound;
    }

}
