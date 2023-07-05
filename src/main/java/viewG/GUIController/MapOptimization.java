package viewG.GUIController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Cell;
import model.Enums.Paths;
import model.Map;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapOptimization extends Application {

    private BorderPane root;
    private Canvas mapCanvas;
    private Cell[][] strongholdMap;
    private int cellSize = 280;
    private int numColumns = 28;
    private int numRows = 28;

    private static final double ScrollSpeed = 10;

    private ExecutorService executorService;

    @Override
    public void start(Stage primaryStage) throws IOException {
        strongholdMap = Map.loadMap("desert"); // Load the stronghold map here

        root = new BorderPane();
        mapCanvas = new Canvas(cellSize * numColumns, cellSize * numRows);
        root.setCenter(new ScrollPane(mapCanvas));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        // Set up the executor service for multi-threaded rendering
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // Render the initial map
        renderMap();
    }

    private void renderMap() {
        GraphicsContext gc = mapCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());

        int startX = (int) (-mapCanvas.getTranslateX() / cellSize);
        int startY = (int) (-mapCanvas.getTranslateY() / cellSize);
        int endX = Math.min(startX + (int) (mapCanvas.getWidth() / cellSize) + 2, numColumns);
        int endY = Math.min(startY + (int) (mapCanvas.getHeight() / cellSize) + 2, numRows);

        for (int row = startY; row < endY; row++) {
            for (int col = startX; col < endX; col++) {
                int finalRow = row;
                int finalCol = col;
                executorService.execute(() -> renderCell(gc, finalCol, finalRow));
            }
        }
    }

    private void renderCell(GraphicsContext gc, int col, int row) {
        Image cellImage = null;
        if (row < numRows && col < numColumns) {
            // Load the appropriate image based on cell type from the stronghold map
            cellImage = new Image(MapRendererG.class.getResource(strongholdMap[row][col].getTypeofGround().getFilePath()).toExternalForm());
        }

        double x = col * cellSize;
        double y = row * cellSize;

        // Render the cell image on the canvas
        if (cellImage != null) {
            gc.drawImage(cellImage, x, y, cellSize, cellSize);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
