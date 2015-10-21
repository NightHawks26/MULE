package Java.Controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import Java.Objects.MuleGame;
import Java.Objects.Pub;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Adam on 9/29/2015.
 */
public class StoreController implements Initializable {

    @FXML
    private Button sell_food_button;

    @FXML
    private Button sell_smithore_button;

    @FXML
    private Button buy_crystite_button;

    @FXML
    private Button buy_smithore_button;

    @FXML
    private Button town_menu_button;

    @FXML
    private Button sell_energy_button;

    @FXML
    private Button sell_crystite_button;

    @FXML
    private Button buy_ore_mule_button;

    @FXML
    private Button buy_food_mule_button;

    @FXML
    private Button buy_energy_button;

    @FXML
    private Button buy_food_button;

    @FXML
    private Button buy_energy_mule_button;

    @FXML
    private Label store_ore_stock_label;

    @FXML
    private Label player_ore_stock_label;

    @FXML
    private Label store_food_stock_label;

    @FXML
    private Label player_food_stock_label;

    @FXML
    private Label store_energy_stock_label;

    @FXML
    private Label player_energy_stock_label;

    @FXML
    private Label store_crystite_stock_label;

    @FXML
    private Label player_crystite_stock_label;

    @FXML
    private Label store_ore_mule_stock_label;

    @FXML
    private Label store_food_mule_stock_label;

    @FXML
    private Label store_energy_mule_stock_label;

    @FXML
    private Label player_Name;

    @FXML
    private Label player_Money;

    @FXML
    private Button map_menu_button;

    private Stage stage;
    private MapController mapController;
    private MuleGame muleGame;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void start(MapController dGCC, MuleGame mG, Stage s) {
        this.mapController = dGCC;
        this.muleGame = mG;
        this.stage = s;

        buy_ore_mule_button.setText("Cost: 175");
        buy_energy_mule_button.setText("Cost: 150");
        buy_food_mule_button.setText("Cost: 125");
        buy_energy_button.setText("Cost: 25");
        buy_crystite_button.setText("Cost: 100");
        buy_food_button.setText("Cost: 30");
        buy_smithore_button.setText("Cost: 50");
        sell_crystite_button.setText("Price: 50");
        sell_energy_button.setText("Price: 12");
        sell_food_button.setText("Price: 15");
        sell_smithore_button.setText("Price: 25");
        player_Name.setText(muleGame.getCurrentPlayerObject().getName());
        player_Money.setText("$" + muleGame.getCurrentPlayerObject().getMoney());

        store_crystite_stock_label.setText("Stock: " + muleGame.getStore().getCrystiteStock());
        store_energy_stock_label.setText("Stock: " + muleGame.getStore().getEnergyStock());
        store_food_stock_label.setText("Stock: " + muleGame.getStore().getFoodStock());
        store_ore_stock_label.setText("Stock: " + muleGame.getStore().getOreStock());
        store_ore_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
        store_energy_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
        store_food_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
        player_ore_stock_label.setText("Owned: " + muleGame.getCurrentPlayerObject().getOre());
        player_energy_stock_label.setText("Owned: " + muleGame.getCurrentPlayerObject().getEnergy());
        player_crystite_stock_label.setText("Owned: " + muleGame.getCurrentPlayerObject().getCrystite());
        player_food_stock_label.setText("Owned: " + muleGame.getCurrentPlayerObject().getFood());
//        food_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
                //if user has money
                //goods_table.append(goods_table);
                //give user x amount of item
                //if user doesn't have money
                //give dialog warning
        buy_smithore_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyOre(muleGame.getCurrentPlayerObject());
                store_ore_stock_label.setText("Stock: " + muleGame.getStore().getOreStock());
                player_ore_stock_label.setText("Owned: " + muleGame.getCurrentPlayerObject().getOre());
                player_Money.setText("$" + muleGame.getCurrentPlayerObject().getMoney());
            }
        });

        sell_smithore_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().sellOre(muleGame.getCurrentPlayerObject());
                store_ore_stock_label.setText("Stock: " + muleGame.getStore().getOreStock());
                player_ore_stock_label.setText("Owned: " + muleGame.getCurrentPlayerObject().getOre());
                player_Money.setText("$" + muleGame.getCurrentPlayerObject().getMoney());
            }
        });

        buy_crystite_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyCrystite(muleGame.getCurrentPlayerObject());
                store_crystite_stock_label.setText("Stock: " + muleGame.getStore().getCrystiteStock());
                player_crystite_stock_label.setText("Owned: " + muleGame.getCurrentPlayerObject().getCrystite());
                player_Money.setText("$" + muleGame.getCurrentPlayerObject().getMoney());
            }
        });

        sell_crystite_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().sellCrystite(muleGame.getCurrentPlayerObject());
                store_crystite_stock_label.setText("Stock: " + muleGame.getStore().getCrystiteStock());
                player_crystite_stock_label.setText("Owned: " + muleGame.getCurrentPlayerObject().getCrystite());
                player_Money.setText("$" + muleGame.getCurrentPlayerObject().getMoney());
            }
        });

        buy_food_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyFood(muleGame.getCurrentPlayerObject());
                store_food_stock_label.setText("Stock: " + muleGame.getStore().getFoodStock());
                player_food_stock_label.setText("Owned: " + muleGame.getCurrentPlayerObject().getFood());
                player_Money.setText("$" + muleGame.getCurrentPlayerObject().getMoney());
            }
        });

        sell_food_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().sellFood(muleGame.getCurrentPlayerObject());
                store_food_stock_label.setText("Stock: " + muleGame.getStore().getFoodStock());
                player_food_stock_label.setText("Owned: " + muleGame.getCurrentPlayerObject().getFood());
                player_Money.setText("$" + muleGame.getCurrentPlayerObject().getMoney());
            }
        });

        buy_energy_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyEnergy(muleGame.getCurrentPlayerObject());
                store_energy_stock_label.setText("Stock: " + muleGame.getStore().getEnergyStock());
                player_energy_stock_label.setText("Owned: " + muleGame.getCurrentPlayerObject().getEnergy());
                player_Money.setText("$" + muleGame.getCurrentPlayerObject().getMoney());
            }
        });

        sell_energy_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().sellEnergy(muleGame.getCurrentPlayerObject());
                store_energy_stock_label.setText("Stock: " + muleGame.getStore().getEnergyStock());
                player_energy_stock_label.setText("Owned: " + muleGame.getCurrentPlayerObject().getEnergy());
                player_Money.setText("$" + muleGame.getCurrentPlayerObject().getMoney());
            }
        });

        buy_energy_mule_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyMule(muleGame.getCurrentPlayerObject(), "energy", 150);
                store_ore_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                store_energy_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                store_food_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                player_Money.setText("$" + muleGame.getCurrentPlayerObject().getMoney());
                if (muleGame.getCurrentPlayerObject().getMuleInHand() != null) {
                    Image energyMule = new Image("/images/energyCursor.png");
                    stage.getScene().setCursor(new ImageCursor(energyMule));
                    Event.fireEvent(map_menu_button, new MouseEvent(MouseEvent.MOUSE_CLICKED,
                            0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true,
                            true, true, true, true, true, true, true, null));
                }
            }
        });

        buy_ore_mule_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyMule(muleGame.getCurrentPlayerObject(), "ore", 175);
                store_ore_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                store_energy_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                store_food_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                player_Money.setText("$" + muleGame.getCurrentPlayerObject().getMoney());
                if (muleGame.getCurrentPlayerObject().getMuleInHand() != null) {
                    Image oreMule = new Image("/images/oreCursor.png");
                    stage.getScene().setCursor(new ImageCursor(oreMule));
                    Event.fireEvent(map_menu_button, new MouseEvent(MouseEvent.MOUSE_CLICKED,
                            0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true,
                            true, true, true, true, true, true, true, null));
                }
            }
        });

        buy_food_mule_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyMule(muleGame.getCurrentPlayerObject(), "food", 125);
                store_ore_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                store_energy_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                store_food_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                player_Money.setText("$" + muleGame.getCurrentPlayerObject().getMoney());
                if (muleGame.getCurrentPlayerObject().getMuleInHand() != null) {
                    Image foodMule = new Image("/images/foodCursor.gif");
                    stage.getScene().setCursor(new ImageCursor(foodMule));
                    Event.fireEvent(map_menu_button, new MouseEvent(MouseEvent.MOUSE_CLICKED,
                            0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true,
                            true, true, true, true, true, true, true, null));
                }
            }
        });

        town_menu_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/Town.fxml"));
                    loader.load();
                    Parent p = loader.getRoot();
                    //((Node)event.getSource()).getScene().getWindow();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(p));
                    TownController townController = loader.getController();
                    townController.start(mapController, muleGame, stage);
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e + "THERE WAS AN ERROR WITH THE LOADER");
                }
            }
        });

        map_menu_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/Map.fxml"));
                try {
                    loader.load();
                    Parent p = loader.getRoot();
                    ((Node) event.getSource()).getScene().getWindow();
                    Cursor tempCursor = stage.getScene().getCursor();
                    stage.setScene(new Scene(p));
                    stage.getScene().setCursor(tempCursor);
                    mapController = loader.getController();
                    mapController.setMuleGame(muleGame);
                    mapController.setStage(stage);
                    mapController.start(false);
                    stage.show();
                } catch (Exception e) {
                }

            }
        });
    }
}
