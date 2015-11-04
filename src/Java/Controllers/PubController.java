package Java.Controllers;
import javafx.fxml.FXML;
import Java.Objects.MuleGame;
import Java.Objects.Pub;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.swing.text.TableView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Adam on 9/29/2015.
 */
public class PubController implements Initializable{

    @FXML
    private Button gamble_button;

    private Stage stage;
    private MuleGame muleGame;
    //@FXML

    /**
     * initializes pub, gambles using a combination of
     * time remaining in a players turn and the current round
     * after gambling, a player's turn ends
     * @param url the url
     * @param rb the resource bundle
     */
    public void initialize(URL url, ResourceBundle rb) {
        gamble_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.t.cancel();
                Pub p = new Pub();
                System.out.println(muleGame.timeRemaining);
                int bonus = p.gamble(muleGame.timeRemaining,
                        muleGame.getRound());
                muleGame.sound.playSoundEffect(14);
                muleGame.getCurrentPlayerObject().addMoney(bonus);
                System.out.println("Gambled for: " + bonus);
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource(
                            "/fxml/Round.fxml"));
                    loader.load();
                    Parent par = loader.getRoot();
                    stage = (Stage) ((Node)
                            event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(par));
                    RoundController roundController = loader.getController();
                    if (muleGame.getCurrentPlayer()
                            == (muleGame.getPlayers().length - 1)) {
                        muleGame.setCurrentPlayer(0);
                        muleGame.selectionRound = true;
                        muleGame.incRound();
                        System.out.println(muleGame.getRound());
                        if (muleGame.getRound() > 12) {
                            try {
                                FXMLLoader endLoader = new FXMLLoader();
                                endLoader.setLocation(getClass().getResource(
                                        "/fxml/FinalScores.fxml"));
                                endLoader.load();
                                System.out.println(endLoader.getRoot()
                                        == null);
                                Parent endP = endLoader.getRoot();
                                stage.setScene(new Scene(endP));
                                FinalScoresController finals
                                        = endLoader.getController();
                                finals.setMuleGame(muleGame);
                                finals.setStage(stage);
                                finals.start();
                                stage.show();
                            } catch (Exception e) {
                                System.out.println(e
                                        + "Oh no, the final"
                                        + "scores don't want to work!");
                            }
                        }
                    } else {
                        muleGame.incCurrentPlayer();
                    }
                    roundController.setMuleGame(muleGame);
                    roundController.setStage(stage);
                    roundController.start();
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e + "THERE WAS AN ERROR "
                            + "WITH THE LOADER in the pub");
                }

            }
        });
    }

    /**
     * This starts the pub controller
     * @param dGCC the map controller instance
     * @param mG the mule game instance
     * @param s the stage being displayed
     */
    public void start(MapController dGCC, MuleGame mG, Stage s) {
        this.muleGame = mG;
        this.stage = s;
    }
}
