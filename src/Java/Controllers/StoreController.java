package Java.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Java.Objects.MuleGame;
import Java.Objects.Pub;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.swing.text.TableView;
import java.awt.*;
import java.awt.Menu;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Adam on 9/29/2015.
 */
public class StoreController implements Initializable {
    @FXML
    public Button food_button;

    @FXML
    private Button energy_button;

    @FXML
    private Button smithore_button;

    @FXML
    private Button crystite_button;

    @FXML
    private Button mule_button;

    @FXML
    private TableView goods_table;

    @FXML
    private Button map_menu_button;

    @FXML
    private Button town_menu_button;
    private Stage stage;
    private MapController mapController;
    private MuleGame muleGame;
    // private int current;


    @FXML
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void start(MapController dGCC, MuleGame mG, Stage s) {
        this.mapController = dGCC;
        this.muleGame = mG;
        this.stage = s;
        food_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //if user has money
                goods_table.append(goods_table);
                //give user x amount of item
                //if user doesn't have money
                //give dialog warning

            }
        });

        map_menu_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader loader = new FXMLLoader();
                System.out.println("Am I working?");
                loader.setLocation(getClass().getResource("/fxml/Map.fxml"));
                try {
                    System.out.println("Am I working?");
                    loader.load();
                    Parent p = loader.getRoot();
                    ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(p));
                    mapController = loader.getController();
                    mapController.setMuleGame(muleGame);
                    mapController.setStage(stage);
                    mapController.start();
                    stage.show();
                    System.out.println("CANNOT GO BACK");
                } catch (Exception e) {
                }

            }
        });

    }
}
