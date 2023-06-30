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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Cell;
import model.Enums.LoadImages;
import model.Enums.Paths;
import model.Map;


public class MapRendererG extends Application {


    private Stage primaryStage;

    private BorderPane root;
    private ImageView imageView;
    private ImageView buildingImageView;
    private ImageView treeImageView;

    private static double ScrollSpeed = 10; // Speed of map movement in pixels

    private double mouseExitedX;
    private double mouseExitedY;

    private Pane backGroundPane;
    private Pane bigPane;
    private Pane centralPane;
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
    private int cellSize = 280;
    private int numColumns = 28;
    private int numRows = 28;
    //todo later on assign this variables to size of the map which can be 200 * 200 or 400 * 400

    private Pane cellContainer;

    private Cell[][] map;

    private int cellRow;
    private int cellCol;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;

        map = Map.loadMap("desert");

        LoadImages.loadAllImages();

        // todo later on load the map which user has determined
        root = new BorderPane();

        backGroundPane = new Pane();

        centralPane = new Pane();
        bigPane = new Pane();
        bigPane.setPrefSize(cellSize * numColumns, cellSize * numRows);
        centralPane.setPrefSize(cellSize * (numColumns - 3), cellSize * (numRows - 3));

        Image desertImage = new Image(MapRendererG.class.getResource(Paths.DESERT_BACKGROUND.getPath()).toExternalForm());
        Image castleImage = new Image(MapRendererG.class.getResource("/images/game/map/buildings/castleBuildings.png").toExternalForm());


        HBox bar = new HBox();
        Image barImage = new Image(MapRendererG.class.getResource("/images/game/bar/window.png").toExternalForm());
        ImageView barImageView = new ImageView(barImage);
        barImageView.setFitWidth(1540);
        bar.getChildren().add(barImageView);
        bar.setMouseTransparent(true);


        WritableImage selectImage = new WritableImage(35, 35);
        PixelWriter pixelWriter = selectImage.getPixelWriter();

        // Set all pixels to partially transparent white
        for (int x = 0; x < 35; x++) {
            for (int y = 0; y < 35  ; y++) {
                pixelWriter.setColor(x, y, javafx.scene.paint.Color.rgb(255, 255, 255, 0.5));
            }
        }




        root.setCenter(backGroundPane);
        root.setBottom(bar);

        initMap(desertImage,selectImage);
        zoomFeature();

        backGroundPane.getChildren().add(bigPane);

        moveMaP();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void initMap(Image desertImage, WritableImage selectImage) {

        double targetWidth = 300;   // Desired width of the resized image
        double targetHeight = 300;  // Desired height of the resized image


        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                this.imageView = new ImageView();
                this.imageView.setImage(desertImage);
                this.imageView.setFitHeight(targetHeight);
                this.imageView.setFitWidth(targetWidth);
                bigPane.getChildren().add(this.imageView);
                this.imageView.setLayoutX(col * cellSize);
                this.imageView.setLayoutY(row * cellSize);
            }
        }

        bigPane.getChildren().add(centralPane);
        centralPane.setLayoutX(2 * cellSize);
        centralPane.setLayoutY(2 * cellSize);


        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                if (row < 2 || col < 2 || row >= numRows - 1 || col >= numColumns - 1) {
                    //todo change the picture of this part with mountain picture
                } else {
                    for (int smallerRow = 0; smallerRow < 8; smallerRow++) {
                        for (int smallerCol = 0; smallerCol < 8; smallerCol++) {
                            cellRow = (row - 2) * 8 + smallerRow;
                            cellCol = (col - 2) * 8 + smallerCol;
                            this.imageView = new ImageView();
                            this.imageView.setImage(LoadImages.getTileImages().get(map[cellRow][cellCol].getTypeofGround()));

                            this.imageView.setFitWidth(75);
                            this.imageView.setFitHeight(75);
                            centralPane.getChildren().add(this.imageView);
                            imageView.setLayoutX(cellCol * 35);
                            imageView.setLayoutY(cellRow * 35);

                            this.imageView.setOnMouseClicked(event -> {
                                ImageView chooseImageView = new ImageView();
                                if (event.getButton() == MouseButton.PRIMARY) {
                                    System.out.println("the cell col is: " + cellCol);
                                    System.out.println("the cell row is: " + cellRow);
//                                    System.out.println("the mouse is clicked with right click");
                                    chooseImageView.setImage(selectImage);
                                    chooseImageView.setLayoutX(cellCol * 35);
                                    chooseImageView.setLayoutY(cellRow * 35);
//                                    System.out.println("the x coordinate is: " + cellCol * 35);
//                                    System.out.println("the y coordinate is: " + cellRow * 35);
                                    centralPane.getChildren().add(chooseImageView);
                                } else if (event.getButton() == MouseButton.SECONDARY) {
                                    centralPane.getChildren().remove(chooseImageView);
                                }
                            });
//                            if (loadBuildingsOfMap()) {
//                                buildingImageView = new ImageView();
//                                this.buildingImageView.setImage(LoadImages.getBuildingImages().get(map[cellRow][cellCol].getBuilding().getBuildingType()));
//                                this.buildingImageView.setFitWidth(250);
//                                this.buildingImageView.setFitHeight(250);
//                                this.buildingImageView.setLayoutX(cellCol * 35);
//                                this.buildingImageView.setLayoutY(cellRow * 35);
//                                centralPane.getChildren().add(this.buildingImageView);
//
//                            } else if (loadTreesOfMap()) {
//                                treeImageView = new ImageView();
//                                this.treeImageView.setImage(LoadImages.getTreeImages().get(map[cellRow][cellCol].getBuilding().getTreeType()));
//                                cellContainer.getChildren().add(treeImageView);
//                            }

                        }
                    }
                }
            }
        }

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                if (row < 2 || col < 2 || row >= numRows - 1 || col >= numColumns - 1) {
                    //todo change the picture of this part with mountain picture
                } else {
                    for (int smallerRow = 0; smallerRow < 8; smallerRow++) {
                        for (int smallerCol = 0; smallerCol < 8; smallerCol++) {
                            cellRow = (row - 2) * 8 + smallerRow;
                            cellCol = (col - 2) * 8 + smallerCol;
                            if (loadBuildingsOfMap()) {
                                buildingImageView = new ImageView();
                                this.buildingImageView.setImage(LoadImages.getBuildingImages().get(map[cellRow][cellCol].getBuilding().getBuildingType()));
                                this.buildingImageView.setFitWidth(250);
                                this.buildingImageView.setFitHeight(250);
                                this.buildingImageView.setLayoutX(cellCol * 35);
                                this.buildingImageView.setLayoutY(cellRow * 35);
                                centralPane.getChildren().add(this.buildingImageView);

                            } else if (loadTreesOfMap()) {
                                treeImageView = new ImageView();
                                this.treeImageView.setImage(LoadImages.getTreeImages().get(map[cellRow][cellCol].getBuilding().getTreeType()));
                            }

                        }
                    }
                }
            }
        }
    }

    private boolean loadTreesOfMap() {
        return map[cellRow][cellCol].isHasTreeInCell();
    }

    private boolean loadBuildingsOfMap() {
        return map[cellRow][cellCol].isHasBuildingInCell();
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
        centralPane.setOnMouseEntered(this::handleMouseEnter);
        centralPane.setOnMouseExited(this::handleMouseExit);

        timeline = new Timeline(new KeyFrame(UPDATE_INTERVAL, e -> updateMapPosition()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void handleMouseEnter(MouseEvent event) {
        validMoveUp = true;
        validMoveDown = true;
        validMoveRight = true;
        validMoveLeft = true;
    }

    private void handleMouseExit(MouseEvent event) {
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

    public static void main(String[] args) {
        launch(args);
    }
}
