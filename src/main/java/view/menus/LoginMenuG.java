package view.menus;

import controller.ControllerControllers;
import controller.ControllerControllersG;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class LoginMenuG extends Application {
    public static Stage stage;


    @Override
    public  void start(Stage stage) throws Exception {

        LoginMenuG.stage = stage;
        ControllerControllersG.stage = stage;
        Pane pane =  new FXMLLoader(LoginMenuG.class.getResource("/fxml/LoginMenu.fxml")).load();
        Image background = new Image(LoginMenuG.class.getResource("/png/mainmenu.jpg").toExternalForm());

        stage.setTitle("LoginMenu!");
        BackgroundImage x = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(x));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(pane, screenBounds.getWidth(), screenBounds.getHeight());

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }



}
