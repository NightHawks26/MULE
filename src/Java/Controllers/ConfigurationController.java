package Java.Controllers;

/**
 * Sample Skeleton for 'Configuration.fxml' Controller Class
 */

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

//    @FXML // ResourceBundle that was given to the FXMLLoader
//    private ResourceBundle resources;
//
//    @FXML // URL location of the FXML file that was given to the FXMLLoader
//    private URL location;

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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL url, ResourceBundle rb) {
        selectMap.getItems().addAll("default", "random");
        selectPlayers.getItems().addAll(2, 3, 4);
        selectDifficulty.getItems().addAll("Beginner", "Standard", "Tournament");
        selectDifficulty.getSelectionModel().selectFirst();
        selectMap.getSelectionModel().selectFirst();
        selectPlayers.getSelectionModel().selectFirst();
        stage = new Stage();
    }

    public void switchToPlayers(ActionEvent event) throws IOException {

        sound.playSoundEffect(0);
        Player[] players = new Player[selectPlayers.getValue()];
        Map map = new Map(selectMap.getValue());
        MuleGame muleGame = new MuleGame(selectDifficulty.getValue(), map, players, sound);

        //((Node)event.getSource()).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AddPlayer.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        //((Node)event.getSource()).getScene().getWindow();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        AddPlayerController addPlayerController = loader.getController();
        addPlayerController.setMuleGame(muleGame);
        addPlayerController.setStage(stage);
        stage.show();
    }

    public void setSound(JayLayer sound) {
        this.sound = sound;
    }

}
