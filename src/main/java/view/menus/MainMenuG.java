package view.menus;

import controller.ControllerControllers;
import controller.ControllerControllersG;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainMenuG extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMenuG.class.getResource("/fxml/mainMenu.fxml"));
        Pane pane = fxmlLoader.load();
        Image background = new Image(LoginMenuG.class.getResource("/png/mainmenu.jpg").toExternalForm());
        BackgroundImage x = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(x));
        Scene scene = new Scene(pane);
        stage.setTitle("MainMenu");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

    }
    public void logout(MouseEvent mouseEvent) throws Exception {
        new LoginMenuG().start(ControllerControllersG.stage);
    }

    public void mapMenu(MouseEvent mouseEvent) throws Exception {
        new MapMenu().start(ControllerControllersG.stage);
    }

    public void newGame(MouseEvent mouseEvent) {
    }

    public void profileMenu(MouseEvent mouseEvent) throws Exception {
        new ProfileMenuG().start(ControllerControllersG.stage);
    }
}
