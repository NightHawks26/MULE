package Java.Controllers;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Java.Objects.Player;
import Java.Objects.MuleGame;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by AveryDingler on 9/9/15.
 */
public class AddPlayerController implements Initializable{
    @FXML
    private ToggleGroup raceGroup;
    @FXML
    private ChoiceBox<String> colorPicker;
    @FXML
    private Button finishPlayer;
    @FXML
    private Label playerNumber;
    @FXML
    private Text currentMap;
    @FXML
    private Text currentDifficulty;
    @FXML
    private TextField newName;

    private MuleGame muleGame;

    private Stage stage;

   // private MapController mapController;
    private RoundController roundController;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setMuleGame(MuleGame muleGame) {
        //Helpful Video https://www.youtube.com/watch?v=Vh7XDjWlm_w
        currentDifficulty.setText(muleGame.getDifficulty());
        currentMap.setText(muleGame.getMap().getName());
        playerNumber.setText("PLAYER 1");
        this.muleGame = muleGame;
        colorPicker.getItems().addAll("Orange", "Purple", "Blue", "Yellow", "Red", "White");
        //delete the following after done testing
        newName.setText("Player 1");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void addPlayer(ActionEvent event) {//throws IOException {
        try {
            muleGame.sound.playSoundEffect(0);
            int x = nextNull(muleGame.getPlayers());
            if (x == 10) {
                return;
            } else {
                String color = null;
                if (colorPicker.getValue() == null) {
                    throw new IOException("color");
                }
                if (colorPicker.getValue().equals("Orange")) {
                    color = "#FF6600";
                } else if (colorPicker.getValue().equals("Purple")) {
                    color = "#FF66FF";
                } else if (colorPicker.getValue().equals("White")) {
                    color = "#FFFFFF";
                } else if (colorPicker.getValue().equals("Red")) {
                    color = "#FF5050";
                } else if (colorPicker.getValue().equals("Blue")) {
                    color = "#33CCCC";
                } else if (colorPicker.getValue().equals("Yellow")) {
                    color = "#FFFF99";
                } else {
                    System.out.println("DIDN'T PICK A COLOR??");
                }
                if (newName.getText().equals("")) {
                    throw new IOException("noname");
                } else if (raceGroup.getSelectedToggle() == null) {
                    throw new IOException("norace");
                }
                for (Player p: muleGame.players) {
                    if (p != null) {
                        if (p.getName().equals(newName.getText())) {
                            throw new IOException("samename");
                        }
                        if (p.getColor().equals(colorPicker.getValue())) {
                            throw new IOException("samecolor");
                        }
                    }
                }
                muleGame.players[x] = new Player(newName.getText(), raceGroup.getSelectedToggle().toString(), muleGame.getDifficulty(), color);
                System.out.println(Arrays.toString(muleGame.getPlayers()));

                colorPicker.getItems().removeAll(colorPicker.getValue());
                colorPicker.setValue(null);
                if (muleGame.players[(muleGame.getPlayers().length) - 1] != null) {

                    muleGame.arrangePlayers();
                    muleGame.getPlayers()[muleGame.getPlayers().length - 1].setIsLast(true);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/Round.fxml"));
                    loader.load();
                    Parent p = loader.getRoot();
                    ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(p));
                    roundController = loader.getController();
                    roundController.setMuleGame(muleGame);
                    roundController.setStage(stage);
                    startGame();
                    stage.show();
                } else {
                    newName.clear();
                    playerNumber.setText("PLAYER " + (x + 2));
                    //delete following after testing
                    newName.setText("Player " + (x + 2));
                }
            }
        } catch (IOException e) {
            String errorMsg;
            if (e.getMessage().equals("norace")) {
                errorMsg = "You didn't pick a race!";
            } else if (e.getMessage().equals("color")) {
                errorMsg = "You didn't pick a color!";
            } else if (e.getMessage().equals("noname")) {
                errorMsg = "You didn't pick a name!";
            } else if (e.getMessage().equals("samename")) {
                errorMsg = "Someone else already has that name!";
            } else if (e.getMessage().equals("samecolor")) {
                errorMsg = "Please pick a different color!";
            } else {
                errorMsg = "Wow you really goofed up";
            }
            Button closer = new Button("Try again");
            Label errMsg = new Label(errorMsg);
            Pane poppane1 = new Pane(errMsg);
            poppane1.setMinSize(100, 50);
            Pane poppane2 = new Pane(closer);
            poppane2.setMinSize(100, 50);
            VBox box = new VBox(errMsg, closer);
            box.setPadding(new Insets(10, 0, 10, 50));
            Scene popScene = new Scene(box, 300, 100);
            Stage popStage = new Stage();
            popStage.setScene(popScene);
            popStage.initModality(Modality.APPLICATION_MODAL);
            popStage.show();
            closer.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    popStage.close();
                }
            });


            //System.out.println("Error with Loader");
        }
    }

    public void startGame() {
        roundController.start();
    }

    private int nextNull(Player[] players) {
        for (int x = 0; x < players.length; x++) {
            if (players[x] == null) {
                return x;
            }
        }
        return 10;
    }

}


