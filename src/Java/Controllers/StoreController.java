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
import javafx.scene.control.Label;
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

        store_crystite_stock_label.setText("Stock: " + muleGame.getStore().getCrystiteStock());
        store_energy_stock_label.setText("Stock: " + muleGame.getStore().getEnergyStock());
        store_food_stock_label.setText("Stock: " + muleGame.getStore().getFoodStock());
        store_ore_stock_label.setText("Stock: " + muleGame.getStore().getOreStock());
        store_ore_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
        store_energy_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
        store_food_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
        player_ore_stock_label.setText("Owned: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].getOre());
        player_energy_stock_label.setText("Owned: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].getEnergy());
        player_crystite_stock_label.setText("Owned: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].getCrystite());
        player_food_stock_label.setText("Owned: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].getFood());
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
                muleGame.getStore().buyOre(muleGame.getPlayers()[muleGame.getCurrentPlayer()]);
                store_ore_stock_label.setText("Stock: " + muleGame.getStore().getOreStock());
                player_ore_stock_label.setText("Owned: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].getOre());
            }
        });

        sell_smithore_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().sellOre(muleGame.getPlayers()[muleGame.getCurrentPlayer()]);
                store_ore_stock_label.setText("Stock: " + muleGame.getStore().getOreStock());
                player_ore_stock_label.setText("Owned: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].getOre());
            }
        });

        buy_crystite_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyCrystite(muleGame.getPlayers()[muleGame.getCurrentPlayer()]);
                store_crystite_stock_label.setText("Stock: " + muleGame.getStore().getCrystiteStock());
                player_crystite_stock_label.setText("Owned: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].getCrystite());
            }
        });

        sell_crystite_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().sellCrystite(muleGame.getPlayers()[muleGame.getCurrentPlayer()]);
                store_crystite_stock_label.setText("Stock: " + muleGame.getStore().getCrystiteStock());
                player_crystite_stock_label.setText("Owned: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].getCrystite());
            }
        });

        buy_food_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyFood(muleGame.getPlayers()[muleGame.getCurrentPlayer()]);
                store_food_stock_label.setText("Stock: " + muleGame.getStore().getFoodStock());
                player_food_stock_label.setText("Owned: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].getFood());
            }
        });

        sell_food_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().sellFood(muleGame.getPlayers()[muleGame.getCurrentPlayer()]);
                store_food_stock_label.setText("Stock: " + muleGame.getStore().getFoodStock());
                player_food_stock_label.setText("Owned: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].getFood());
            }
        });

        buy_energy_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyEnergy(muleGame.getPlayers()[muleGame.getCurrentPlayer()]);
                store_energy_stock_label.setText("Stock: " + muleGame.getStore().getEnergyStock());
                player_energy_stock_label.setText("Owned: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].getEnergy());
            }
        });

        sell_energy_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().sellEnergy(muleGame.getPlayers()[muleGame.getCurrentPlayer()]);
                store_energy_stock_label.setText("Stock: " + muleGame.getStore().getEnergyStock());
                player_energy_stock_label.setText("Owned: " + muleGame.getPlayers()[muleGame.getCurrentPlayer()].getEnergy());
            }
        });

        buy_energy_mule_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyMule(muleGame.getPlayers()[muleGame.getCurrentPlayer()], "energy");
                store_ore_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                store_energy_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                store_food_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
            }
        });

        buy_ore_mule_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyMule(muleGame.getPlayers()[muleGame.getCurrentPlayer()], "ore");
                store_ore_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                store_energy_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                store_food_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
            }
        });

        buy_food_mule_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                muleGame.getStore().buyMule(muleGame.getPlayers()[muleGame.getCurrentPlayer()], "food");
                store_ore_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                store_energy_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
                store_food_mule_stock_label.setText("Stock: " + muleGame.getStore().getMuleStock());
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
                    stage.setScene(new Scene(p));
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
