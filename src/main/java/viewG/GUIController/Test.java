package viewG.GUIController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Test extends Application {
    private static final int PANE_WIDTH = 7000;
    private static final int PANE_HEIGHT = 7000;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);

        pane.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            System.out.println("x = " + mouseX + ", y = " + mouseY);
        });

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
