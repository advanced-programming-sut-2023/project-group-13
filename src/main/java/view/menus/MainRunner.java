package view.menus;

import controller.ControllerControllers;
import controller.ControllerControllersG;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainRunner extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ControllerControllersG controllerControllersG = new ControllerControllersG();
        ControllerControllersG.stage = stage;
        controllerControllersG.run();
    }
}
