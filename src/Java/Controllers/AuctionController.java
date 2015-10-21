package Java.Controllers;

import Java.Objects.MuleGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by anthonybonitatibus on 9/23/15.
 */
public class AuctionController implements Initializable {

    @FXML
    private Button submit;
    @FXML
    private Label currentPlayer;
    @FXML
    private Label currentMoney;
    @FXML
    private PasswordField auctionAmount;

    private MuleGame muleGame;
    private int counter = 0;
    private int[] auctionValues;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setMuleGame(MuleGame muleGame) {
        this.muleGame = muleGame;
        currentPlayer.setText(muleGame.getPlayers()[0].getName());
        currentMoney.setText(muleGame.getPlayers()[0].getName() + "'s money: " + muleGame.getPlayers()[0].getMoney());
        auctionValues = new int[muleGame.getPlayers().length];
    }

    public void submitAuction(ActionEvent e) throws IOException {
        if (counter < muleGame.getPlayers().length) {
            auctionValues[counter] = Integer.parseInt(auctionAmount.getText());
        }
    }



}
