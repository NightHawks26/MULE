package Java.Controllers;

import Java.Objects.HighScore;
import Java.Objects.MuleGame;
import Java.SQLiteJDBC;
import io.github.jgkamat.JayLayer.JayLayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class HighScoresController implements Initializable {

    @FXML
    private TableView<HighScore> highScoreTable;

    @FXML
    private Button backButton;

    private JayLayer sound;
    private StartScreenController startScreenController;
    private Stage stage;
    private final ObservableList<HighScore> scores = FXCollections.observableArrayList(
    );

    public void initialize(URL url, ResourceBundle rb) {
        TableColumn nameCol = new TableColumn("Name");
        TableColumn scoreCol = new TableColumn("Score");
        TableColumn placeCol = new TableColumn("Place");
        highScoreTable.getColumns().addAll(placeCol, nameCol, scoreCol);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<HighScore, String>("name")
        );
        scoreCol.setCellValueFactory(
                new PropertyValueFactory<HighScore, Integer>("score")
        );
        placeCol.setCellValueFactory(
                new PropertyValueFactory<HighScore, Integer>("place")
        );
        placeCol.setMinWidth(50);
        nameCol.setMinWidth(250);
        scoreCol.setMinWidth(100);
        highScoreTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        highScoreTable.setItems(scores);
        //sound.startPlaylist(0);
        try {
            SQLiteJDBC connector = new SQLiteJDBC();
            Statement statement = connector.getConn().createStatement();
            String query = "SELECT * FROM high_scores Order by score desc;";
            ResultSet highScores = statement.executeQuery(query);
            int place = 1;
            while (highScores.next()) {
                String name = highScores.getString("name");
                Integer scoreInt = highScores.getInt("score");
                scores.add(new HighScore(name, scoreInt, place));
                place++;
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * Goes to the Start screen
     * @param event event that triggers this switch
     * @throws IOException throws an io exception of configuration screen
     * doesn't exist
     */
    public void backButton(ActionEvent event) throws IOException {
        try {
            sound.stopPlaylist(0);
        } catch (Exception ignored) {
            
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/StartScreen.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        startScreenController = loader.getController();
        startScreenController.setSound(sound);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
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

