package Java.Controllers;

import Java.Objects.Map;
import Java.Objects.MuleGame;
import Java.Objects.Player;
import Java.XMLParser;
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
    private MuleGame muleGame;
    @FXML
    private Button loadGame;

    @FXML
    private Button newGame;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL url, ResourceBundle rb) {

        loadGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                File savedGameXML = fileChooser.showOpenDialog(stage);
                if (savedGameXML != null) {
                        System.out.println(savedGameXML.getAbsolutePath());
                    XMLParser xmlParser = new XMLParser();
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
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();
    }

}
