package Java.Controllers;

import Java.Objects.MuleGame;
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
    private HighScoresController highScoresController;
    private Boolean firstTimeOpeningGame;
    private JayLayer sound;
    private MuleGame muleGame;
    private boolean musicGoing;
    @FXML
    private Button loadGame;

    @FXML
    private Button newGame;

    @FXML
    private Button highScoreButton;


    //@FXML // This method is called by the FXMLLoader
    // when initialization is complete

    /**
     * this method imports/sets up all of the in game audio
     * also allows a user to start a new game or load a game from a saved file
     * @param url url for initialization
     * @param rb resouce bundle for initialization
     */
    public void initialize(URL url, ResourceBundle rb) {
        //javadoc
        //http://jgkamat.github.io/JayLayer/doc/jay/jaysound/JayLayer.html
        sound = new JayLayer("/audio/", "/audio/");
        int playlistNum = sound.createPlaylist(true);
        sound.addSoundEffect("round1.mp3"); //0
        sound.addSoundEffect("round2.mp3"); //1
        sound.addSoundEffect("round3.mp3"); //2
        sound.addSoundEffect("round4.mp3"); //3
        sound.addSoundEffect("round5.mp3"); //4
        sound.addSoundEffect("round6.mp3"); //5
        sound.addSoundEffect("round7.mp3"); //6
        sound.addSoundEffect("round8.mp3"); //7
        sound.addSoundEffect("round9.mp3"); //8
        sound.addSoundEffect("round10.mp3"); //9
        sound.addSoundEffect("round11.mp3"); //10
        sound.addSoundEffect("round12.mp3"); //11
        sound.addSoundEffect("gameOver.mp3"); //12
        sound.addSoundEffect("store.mp3"); //13
        sound.addSoundEffect("gamble.mp3"); //14
        sound.addSoundEffect("doh.mp3"); //15
        sound.addSoundEffect("nicepick.mp3"); //16
        sound.addSoundEffect("trick.mp3"); //17
        sound.addSoundEffect("encouragement.mp3"); //18
        sound.addSoundEffect("fart.mp3"); //19
        //start playlist numbering
        sound.addToPlaylist(playlistNum, "bensound-littleidea.mp3"); //0
        sound.addToPlaylist(playlistNum, "bensound-scifi.mp3"); //1
        sound.addToPlaylist(playlistNum, "bensound-sweet.mp3"); // 2
        //  got all of these from www.bensound.com
        sound.addToPlaylist(playlistNum,
                "bensound-theelevatorbossanova.mp3"); //3
        //    waiting/background music
        sound.addToPlaylist(playlistNum, "bensound-sadday.mp3"); //4
        //    could be used when a mule is lost

        sound.startPlaylist(0);


        loadGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File(
                        System.getProperty("user.dir")
                                + "/src/resources/xml"));
                fileChooser.setTitle("Open Resource File");
                FileChooser.ExtensionFilter filter
                        = new FileChooser.ExtensionFilter(
                        "MULE Games", "*.xml");
                fileChooser.getExtensionFilters().add(filter);
                File savedGameXML
                        = fileChooser.showOpenDialog(stage);
                if (savedGameXML != null) {
                    System.out.println(
                            savedGameXML.getAbsolutePath());
                    XMLParser xmlParser = new XMLParser();
                    xmlParser.setSound(sound);
                    String fileLocation
                            = savedGameXML.getAbsolutePath();
                    muleGame = xmlParser.loadGame(fileLocation);
                    //switch screen to round controller
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource(
                            "/fxml/Round.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent p = loader.getRoot();
                    stage = (Stage) ((Node) event.
                            getSource()).getScene().getWindow();
                    stage.setScene(new Scene(p));
                    roundController = loader.getController();
                    roundController.setMuleGame(muleGame);
                    roundController.setStage(stage);
                    stage.getScene().getWindow().sizeToScene();
                    roundController.start();
                    stage.show();
                }
            }
        });
    }

    /**
     * Goes to the configuration screen
     * @param event event taht triggers this switch
     * @throws IOException throws an io exception of configuration screen
     * doesn't exist
     */
    public void switchToConfiguration(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Configuration.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        ConfigurationController configurationController = loader.getController();
        configurationController.setSound(sound);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();

    }

    /**
     * Goes to the highScore screen
     * @param event event that triggers this switch
     * @throws IOException throws an io exception of configuration screen
     * doesn't exist
     */
    public void switchToHighScores(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/HighScores.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        highScoresController = loader.getController();
        highScoresController.setSound(sound);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();

    }

    /**
     * Sets the current sound
     * @param sound JayLayer sound
     */

    public void setSound(JayLayer sound) {
        this.sound = sound;
    }

}
