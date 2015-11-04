package Java.Controllers;

import Java.Objects.Mule;
import Java.Objects.MuleGame;
import Java.Objects.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Label secondTotalScoreLabel;

    @FXML
    private Button secretSurpriseButton;

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
     * houses easter egg code
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

        secretSurpriseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Pane magic = new Pane(new ImageView(new Image("/resources/images/magicword.gif")));
                PasswordField word = new PasswordField();
                VBox vbox = new VBox(magic, word);
                Scene magicScene = new Scene(magic, 620, 400);
                Stage magicStage = new Stage();
                magicStage.setScene(magicScene);
                magicStage.initModality(Modality.APPLICATION_MODAL);
                magicStage.show();
            }
        });
    }
}
