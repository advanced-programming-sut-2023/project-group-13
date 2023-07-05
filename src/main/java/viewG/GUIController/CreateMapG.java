package viewG.GUIController;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateMapG extends Application {

    private Pane backGroundPane;
    private GridPane tilePane;
    private GridPane mapPane;
    private BorderPane root;

    private int cellSize = 300;

    private int numColumns = 23;
    private int numRows = 23;

    @Override
    public void start(Stage stage) throws Exception {
        root = new BorderPane();
        backGroundPane = new Pane();


        mapPane = new GridPane();
        mapPane.setPrefSize(cellSize * numColumns, cellSize * numRows);
        tilePane = new GridPane();

        tilePane.setPrefSize(cellSize * numColumns, cellSize * numRows);
    }
}
