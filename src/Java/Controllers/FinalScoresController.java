package Java.Controllers;

import Java.Objects.Mule;
import Java.Objects.MuleGame;
import Java.Objects.Player;
import Java.SQLiteJDBC;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.sqlite.SQLiteJDBCLoader;

import java.io.IOException;
import java.sql.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by anthonybonitatibus on 10/22/15.
 */
public class FinalScoresController implements Initializable {

    @FXML
    private Label firstTotalScoreLabel;

    @FXML
    private Label thirdNameLabel;

    @FXML
    private Label thirdTotalScoreLabel;

    @FXML
    private Button quitButton;

    @FXML
    private Label fourthIndScoreLabel;

    @FXML
    private Label fourthTotalScoreLabel;

    @FXML
    private Label firstIndScoreLabel;

    @FXML
    private Label thirdIndScoreLabel;

    @FXML
    private Label secondIndScoreLabel;

    @FXML
    private Label secondNameLabel;

    @FXML
    private Button playAgainButton;

    @FXML
    private Button saveScores;

    @FXML
    private Label secondTotalScoreLabel;


    @FXML
    private Label fourthNameLabel;

    @FXML
    private Label firstNameLabel;

    private MuleGame muleGame;
    private Stage stage;
    private Player first;
    private Player second;
    private Player third;
    private Player fourth;


    /**
     * empty method
     * @param url url
     * @param rb resouce bundle
     */
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setMuleGame(MuleGame muleGame) {
        this.muleGame = muleGame;
    }

    /**
     * sets stage to be displayed
     * @param stage stage to be dispalyed
     */
    public void setStage(Stage stage) {
        Stage stage1 = stage;
    }

    /**
     * Displays user end game scores
     */
    public void start() {
        for (Player p: muleGame.getPlayers()) {
            p.refreshScore();
        }
        muleGame.arrangePlayers();
        first = muleGame.getPlayers()[0];
        firstNameLabel.setText(first.getName());
        firstIndScoreLabel.setText(first.getScore() + "");
        firstTotalScoreLabel.setText("SUPER");
        second = muleGame.getPlayers()[1];
        secondNameLabel.setText(second.getName());
        secondIndScoreLabel.setText(second.getScore() + "");
        secondTotalScoreLabel.setText("LESS SUPER");
        if (muleGame.getPlayers().length > 2) {
            third = muleGame.getPlayers()[2];
            thirdNameLabel.setText(third.getName());
            thirdIndScoreLabel.setText(third.getScore() + "");
            thirdTotalScoreLabel.setText("Not even duper");
        } else {
            thirdNameLabel.setText("");
            thirdIndScoreLabel.setText("");
            thirdTotalScoreLabel.setText("");
        }
        if (muleGame.getPlayers().length > 3) {
            fourth = muleGame.getPlayers()[3];
            fourthNameLabel.setText(third.getName());
            fourthIndScoreLabel.setText(third.getScore() + "");
            fourthTotalScoreLabel.setText("Not even duper");
        } else {
            fourthNameLabel.setText("");
            fourthIndScoreLabel.setText("");
            fourthTotalScoreLabel.setText("");
        }
    }

    /**
     * Handles when the playAgainButton is clicked, switching
     * back to the start screen for the game.
     * @param event ActionEvent when the button is clicked
     * @throws IOException potential exception
     */

    public void playAgainButton(ActionEvent event) throws IOException {
        try {
            muleGame.sound.stopPlaylist(0);
        } catch (Exception ignored) {

        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/StartScreen.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();

    }

    /**
     * Quits out of the game
     */

    public void quitGame() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Saves the scores to the high score database
     */

    public void saveScores() {
        SQLiteJDBC connector = new SQLiteJDBC();
        System.out.println("scores saved");
        Statement statement;
        for (Player player : muleGame.getPlayers()) {
            try {
                statement = connector.getConn().createStatement();
                String sql = "INSERT INTO high_scores (name, score) VALUES ('"
                        + player.getName() +  "', " + player.getScore() + ");";
                statement.executeUpdate(sql);
                statement.close();

            } catch (Exception e) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
        }
        try {
            connector.getConn().close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        saveScores.setDisable(true);
        saveScores.setVisible(false);
    }
}
