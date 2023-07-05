package view.menus;

import controller.ControllerControllers;
import controller.ControllerControllersG;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class MapMenu extends Application {
    private ButtonMaker size200;
    private ButtonMaker size400;
    private ButtonMaker back;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMenuG.class.getResource("/fxml/mapMenu.fxml"));
        Pane pane =  fxmlLoader.load();
        Image background = new Image(LoginMenuG.class.getResource("/png/mainmenu.jpg").toExternalForm());
        size200 = new ButtonMaker("200 x 200", pane, 630, 400);
        size400 = new ButtonMaker("400 x 400 ", pane, 630, 320);
        back = new ButtonMaker("back", pane, 630, 480);

        pane.getChildren().addAll(size200,size400 , back);
        stage.setTitle("MapMenu!");
        BackgroundImage x = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(x));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(pane, screenBounds.getWidth(), screenBounds.getHeight());
        buttonPressed();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(true);
        stage.show();
    }

    public void buttonPressed() {
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainMenuG mainMenuG = new MainMenuG();
                try {
                    mainMenuG.start(ControllerControllersG.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        size400.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        size200.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

    }
    public void backButton() {

    }
}
