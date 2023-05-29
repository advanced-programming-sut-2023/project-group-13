package view;

import controller.ControllerControllers;
import controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Enums.DataEnumFile;
import model.Enums.MainMenuCommands;
import model.Player;
import model.SaveAndLoadData;

import java.net.URL;
import java.util.regex.Matcher;

public class MainMenu extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        URL url = MainMenu.class.getResource("/FXML/MainMenu.fxml");
        GridPane gridPane = FXMLLoader.load(url);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}
