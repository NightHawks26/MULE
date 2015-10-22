package Java.Controllers;

import Java.Objects.Map;
import Java.Objects.MuleGame;
import Java.Objects.Player;
import Java.XMLParser;
import io.github.jgkamat.JayLayer.JayLayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable {

    private Stage stage;
    private RoundController roundController;
    private ConfigurationController configurationController;
    private JayLayer sound;
    private MuleGame muleGame;
    @FXML
    private Button loadGame;

    @FXML
    private Button newGame;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL url, ResourceBundle rb) {
        //javadoc
        //http://jgkamat.github.io/JayLayer/doc/jay/jaysound/JayLayer.html
        sound = new JayLayer("/audio/", "/audio/");
        int playlistNum = sound.createPlaylist(true);
        // sound.addToPlaylist(playlistNum, "Boyfriend.mp3");
        // sound.addToPlaylist(playlistNum, "No Tellin'.mp3");
        sound.addSoundEffect("round1.mp3");
        sound.addSoundEffect("round2.mp3");
        sound.addSoundEffect("round3.mp3");
        sound.addSoundEffect("store.mp3");
        sound.addSoundEffect("gamble.mp3");
        sound.addSoundEffect("doh.mp3");
        sound.addSoundEffect("nicepick.mp3");
        sound.addSoundEffect("trick.mp3");
        sound.addSoundEffect("encouragement.mp3");
        sound.addToPlaylist(playlistNum, "bensound-littleidea.mp3"); //got all of these from www.bensound.com
        sound.addToPlaylist(playlistNum, "bensound-scifi.mp3"); //got all of these from www.bensound.com
        sound.addToPlaylist(playlistNum, "bensound-sweet.mp3"); //got all of these from www.bensound.com
        sound.addToPlaylist(playlistNum, "bensound-theelevatorbossanova.mp3"); //waiting/background music
        sound.addToPlaylist(playlistNum, "bensound-sadday.mp3"); //could be used when a mule is lost
        sound.addSoundEffect("fart.mp3");
        sound.startPlaylist(0);
        loadGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/src/resources/xml"));
                fileChooser.setTitle("Open Resource File");
                File savedGameXML = fileChooser.showOpenDialog(stage);
                if (savedGameXML != null) {
                        System.out.println(savedGameXML.getAbsolutePath());
                    XMLParser xmlParser = new XMLParser();
                    xmlParser.setSound(sound);
                    String fileLocation = savedGameXML.getAbsolutePath();
                    muleGame = xmlParser.loadGame(fileLocation);
                }

                //switch screen to round controller
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/Round.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent p = loader.getRoot();
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(p));
                roundController = loader.getController();
                roundController.setMuleGame(muleGame);
                roundController.setStage(stage);
                stage.getScene().getWindow().sizeToScene();
                roundController.start();
                stage.show();





            }
        });
    }

    public void switchToConfiguration(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Configuration.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        //((Node)event.getSource()).getScene().getWindow();
        configurationController = loader.getController();
        configurationController.setSound(sound);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();
    }

}
