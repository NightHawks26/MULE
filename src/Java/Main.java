package Java;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    private Stage currentStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        currentStage = primaryStage;
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/Configuration.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StartScreen.fxml"));
        currentStage.setTitle("NightHawks M.U.L.E");
        currentStage.setScene(new Scene(root, 600, 400));
        currentStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
