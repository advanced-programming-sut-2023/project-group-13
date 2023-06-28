package viewG.GUIController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;


public class MapRendererG extends Application {

    private Stage primaryStage;

    private BorderPane root;
    private ImageView imageView;
    private GridPane mapPane;
    private GridPane tilePane;

    private static double ScrollSpeed = 10; // Speed of map movement in pixels

    private double mouseExitedX;
    private double mouseExitedY;

    private Pane backGroundPane;
    private Scene scene;

    // move map automatically
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    private boolean validMoveUp = false;
    private boolean validMoveLeft = false;
    private boolean validMoveRight = false;

    private boolean validMoveDown = false;
    double minScale = 0.7;  // Minimum scale (50% zoom)
    double maxScale = 2.0;  // Maximum scale (200% zoom)


    private static final Duration UPDATE_INTERVAL = Duration.millis(16); // 60 FPS


    private Timeline timeline;
    private int cellSize = 300;
    private int numColumns = 23;
    private int numRows = 23;
    //todo later on assign this variables to size of the map which can be 200 * 200 or 400 * 400

    private Pane cellContainer;

    private double currentTranslateX = 0;
    private double currentTranslateY = 0;

    // move map automatically


    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;

        root = new BorderPane();



        backGroundPane = new Pane();


        mapPane = new GridPane();
        mapPane.setPrefSize(cellSize * numColumns, cellSize * numRows);
        tilePane = new GridPane();

        tilePane.setPrefSize(cellSize * numColumns, cellSize * numRows);



        Image desertImage = new Image(MapRendererG.class.getResource("/desert_tile.jpg").toExternalForm());
//        Image grassImage = new Image(MapRendererG.class.getResource("/img.png").toExternalForm());
        Image grassImage = new Image(MapRendererG.class.getResource("/images/game/map/textures/grass/0.png").toExternalForm());
        Image castleImage = new Image(MapRendererG.class.getResource("/images/game/map/buildings/castleBuildings.png").toExternalForm());

        HBox bar = new HBox();
        Image barImage = new Image(MapRendererG.class.getResource("/images/game/bar/bar.png").toExternalForm());
        ImageView barImageView = new ImageView(barImage);
        bar.getChildren().add(barImageView);
        bar.setMouseTransparent(true);

        root.setCenter(backGroundPane);
        root.setBottom(bar);




        WritableImage transparentImage = new WritableImage(30, 30);
        PixelWriter pixelWriter = transparentImage.getPixelWriter();

        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++) {
                pixelWriter.setColor(x, y, javafx.scene.paint.Color.TRANSPARENT);
            }
        }




        initMap(desertImage,castleImage,transparentImage);
        zoomFeature();

        backGroundPane.getChildren().addAll(mapPane,tilePane);

        moveMaP();


        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void initMap(Image desertImage, Image grassImage, WritableImage transparentImage) {

        double targetWidth = 300;   // Desired width of the resized image
        double targetHeight = 300;  // Desired height of the resized image

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                this.imageView = new ImageView();
                this.imageView.setImage(desertImage);
                this.imageView.setFitWidth(targetWidth);
                this.imageView.setFitHeight(targetHeight);
                cellContainer = new Pane(imageView);
                cellContainer.setPrefSize(cellSize,cellSize);
                addToGridPane(col, row);
            }
        }

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
//                if (row % 10 == 0 && col % 10 == 0) {
                    this.imageView = new ImageView();
                if (row < 2 || col < 2 || row > 21 || col > 21) {
                    this.imageView.setImage(desertImage);
                    this.imageView.setFitWidth(targetWidth);
                    this.imageView.setFitHeight(targetHeight);

                    //todo change the picture of this part with mountain picture
                }
                else this.imageView.setImage(grassImage);

                cellContainer = new Pane(imageView);
                cellContainer.setPrefSize(cellSize,cellSize);
                tilePane.add(cellContainer, col, row);

                int finalCol = col;
                int finalRow = row;
//                this.imageView.setMouseTransparent(true);
                this.imageView.setOnMouseEntered(event -> {

                    validMoveLeft = finalCol >= 1;
                    validMoveRight = finalCol < 22;
                    validMoveUp = finalRow >= 1;
                    validMoveDown = finalRow < 22;

//                    ScrollSpeed = isMovingUp && finalRow < 40 ? 4 : 10;
//                    ScrollSpeed = isMovingDown && finalRow > 160 ? 4 : 10;
//                    ScrollSpeed = isMovingLeft && finalCol < 40 ? 4 : 10;
//                    ScrollSpeed = isMovingRight && finalCol > 160 ? 4 : 10;
                });
            }
        }

    }

    private void zoomFeature() {
        double initialScale = 1.0; // Initial scale factor (1.0 = 100%)
        double pivotX = 0.0;      // Pivot point X coordinate
        double pivotY = 0.0;      // Pivot point Y coordinate

        backGroundPane.setScaleX(initialScale);
        backGroundPane.setScaleY(initialScale);
        backGroundPane.setTranslateX(pivotX);
        backGroundPane.setTranslateY(pivotY);

        Scale scaleTransform = new Scale(initialScale, initialScale, pivotX, pivotY);

        backGroundPane.getTransforms().add(scaleTransform);

        double zoomFactor = 1.1; // Zoom factor (1.1 = 10% zoom in/out per step)

        backGroundPane.setOnScroll(event -> {
            double delta = event.getDeltaY();
            double scale = (delta > 0) ? zoomFactor : (1.0 / zoomFactor);

            double newScaleX = scaleTransform.getX() * scale;
            double newScaleY = scaleTransform.getY() * scale;

            // Check if the new scale is within the limits
            if (newScaleX >= minScale && newScaleX <= maxScale && newScaleY >= minScale && newScaleY <= maxScale) {
                scaleTransform.setX(newScaleX);
                scaleTransform.setY(newScaleY);
            }
        });


    }

    private void moveMaP() {
        root.setOnMouseMoved(this::onMouseMoved);
        root.setOnMouseEntered(this::handleMouseEnter);
        root.setOnMouseExited(this::handleMouseExit);


        timeline = new Timeline(new KeyFrame(UPDATE_INTERVAL, e -> updateMapPosition()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void handleMouseEnter(MouseEvent event) {
        // Reset the movement flags when the mouse enters the scene
        isMovingUp = false;
        isMovingDown = false;
        isMovingLeft = false;
        isMovingRight = false;
    }

    private void handleMouseExit(MouseEvent event) {
        // Stop the movement when the mouse exits the scene
        isMovingUp = false;
        isMovingDown = false;
        isMovingLeft = false;
        isMovingRight = false;
        validMoveUp = false;
        validMoveDown = false;
        validMoveRight = false;
        validMoveLeft = false;
    }


    private void onMouseMoved(MouseEvent event) {
        mouseExitedX = event.getScreenX();
        mouseExitedY = event.getSceneY();

        isMovingLeft = mouseExitedX <= 100;
        isMovingRight = mouseExitedX >= primaryStage.getWidth() - 100;
        isMovingUp = mouseExitedY <= 100;
        isMovingDown = mouseExitedY >= primaryStage.getHeight() - 100;

    }

    private void updateMapPosition() {
        double dx = 0;
        double dy = 0;

        if (isMovingUp && validMoveUp) {
            dy += ScrollSpeed;
        }
        if (isMovingDown && validMoveDown) {
            dy -= ScrollSpeed;
        }
        if (isMovingLeft && validMoveLeft) {
            dx += ScrollSpeed;
        }
        if (isMovingRight && validMoveRight) {
            dx -= ScrollSpeed;
        }

            backGroundPane.setTranslateX(backGroundPane.getTranslateX() + dx);
            backGroundPane.setTranslateY(backGroundPane.getTranslateY() + dy);
    }


    private void addToGridPane(int col, int row) {
        mapPane.add(cellContainer, col, row);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
