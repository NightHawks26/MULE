package Java.Controllers;
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
    private MapController mapController;
    private MuleGame muleGame;
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/images/bar.jpg");
        ImageView imgView = new ImageView(img);
        StackPane sp = new StackPane();
        sp.getChildren().add(imgView);
        Scene scene = new Scene(sp);
        stage.setScene(scene);
        stage.show();
    }

    public void start(MapController dGCC, MuleGame mG, Stage s) {


        this.mapController = dGCC;
        this.muleGame = mG;
        this.stage = s;

        gamble_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {


            }
        });
    }
}
