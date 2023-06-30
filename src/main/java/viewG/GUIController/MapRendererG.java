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
    private GridPane mapPane;
    private GridPane tilePane;
    private GridPane surfacePane;
    private GridPane buidlingGridPane;
    private GridPane treeGridPane;

    private static double ScrollSpeed = 10; // Speed of map movement in pixels

    private double mouseExitedX;
    private double mouseExitedY;

    private Pane backGroundPane;
    private Pane bigPane;
    private Pane centralPane;
    private Pane buildignPane;
    private Pane treePane;
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

    private Pane cellSurfaceContainer;

    private double currentTranslateX = 0;
    private double currentTranslateY = 0;

    private Cell[][] map;

    private int cellRow;
    private int cellCol;


    // move map automatically


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
//        bigPane.getChildren().add(centralPane);
//        centralPane.getChildren().add(bigPane);
        bigPane.setPrefSize(cellSize * numColumns, cellSize * numRows);
        centralPane.setPrefSize(cellSize * (numColumns - 3), cellSize * (numRows - 3));

//        mapPane = new GridPane();
//        mapPane.setPrefSize(cellSize * numColumns, cellSize * numRows);
//        tilePane = new GridPane();

//        tilePane.setPrefSize(cellSize * numColumns, cellSize * numRows);


        Image desertImage = new Image(MapRendererG.class.getResource(Paths.DESERT_BACKGROUND.getPath()).toExternalForm());
        Image grassImage = new Image(MapRendererG.class.getResource("/images/game/map/textures/grass/0.png").toExternalForm());
        Image castleImage = new Image(MapRendererG.class.getResource("/images/game/map/buildings/castleBuildings.png").toExternalForm());
        Image tile = new Image(MapRendererG.class.getResource("/images/game/map/textures/earthAndSand/0.png").toExternalForm());


        HBox bar = new HBox();
        Image barImage = new Image(MapRendererG.class.getResource("/images/game/bar/window.png").toExternalForm());
        ImageView barImageView = new ImageView(barImage);
        barImageView.setFitWidth(1540);
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

        initMap(desertImage, castleImage, transparentImage);
        zoomFeature();

//        backGroundPane.getChildren().addAll(mapPane,tilePane);
        backGroundPane.getChildren().add(bigPane);

        moveMaP();

//        MapController mapController = new MapController(primaryStage, root, backGroundPane);
//        mapController.zoomFeature();
//        mapController.moveMaP();


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
                this.imageView.setFitHeight(targetHeight);
                this.imageView.setFitWidth(targetWidth);
                bigPane.getChildren().add(this.imageView);
                this.imageView.setLayoutX(col * cellSize);
                this.imageView.setLayoutY(row * cellSize);
//                mouseEvent(col, row);
            }
        }

        bigPane.getChildren().add(centralPane);
        centralPane.setLayoutX(2 * cellSize);
        centralPane.setLayoutY(2 * cellSize);




//        for (int row = 0; row < numRows; row++) {
//            for (int col = 0; col < numColumns; col++) {
//                this.imageView = new ImageView();
//                this.imageView.setImage(desertImage);
//                this.imageView.setFitWidth(targetWidth);
//                this.imageView.setFitHeight(targetHeight);
//                cellContainer = new Pane(imageView);
//                cellContainer.setPrefSize(cellSize, cellSize);
////                addToGridPane(col, row);
//                mouseEvent(col, row);
//
//            }
//        }

//        HashMap<TypeofGround, Image> tileImages = new HashMap<>();
//        for (TypeofGround groundType : TypeofGround.values()) {
//            Image tileImage = new Image(MapRendererG.class.getResource(groundType.getFilePath()).toExternalForm());
//            tileImages.put(groundType, tileImage);
//        }

//        buildignPane = new Pane();
//        buildignPane.setPrefSize(cellSize * numRows - 2, cellSize * numColumns - 2);
//        buildignPane.setStyle("-fx-background-color: red");


        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                if (row < 2 || col < 2 || row >= numRows - 1 || col >= numColumns - 1) {
//                    cellImage = desertImage;
//                    this.imageView = new ImageView();
//                    this.imageView.setImage(desertImage);
//                    this.imageView.setFitWidth(targetWidth);
//                    this.imageView.setFitHeight(targetHeight);
//                    bigPane.getChildren().add(this.imageView);
//                    this.imageView.setLayoutX(col * numColumns);
//                    this.imageView.setLayoutY(col * numRows);
//                    cellContainer = new Pane(imageView);
//                    cellContainer.setPrefSize(cellSize, cellSize);
//                    this.imageView.setMouseTransparent(true);
//                    mouseEvent(row, col);
                    //todo change the picture of this part with mountain picture
                } else {
//                    surfacePane = new GridPane();
//                    surfacePane.setPrefSize(cellSize, cellSize);
//                    buildingImageView = new ImageView();
//                    cellContainer = new Pane(surfacePane, buildignPane);
//                    cellContainer.setPrefSize(cellSize, cellSize);
                    for (int smallerRow = 0; smallerRow < 8; smallerRow++) {
                        for (int smallerCol = 0; smallerCol < 8; smallerCol++) {
                            cellRow = (row - 2) * 8 + smallerRow;
                            cellCol = (col - 2) * 8 + smallerCol;
//                            loadTreesOfMap();
//                            loadTilesOfMap();
                            this.imageView = new ImageView();
                            this.imageView.setImage(LoadImages.getTileImages().get(map[cellRow][cellCol].getTypeofGround()));

                            this.imageView.setFitWidth(75);
                            this.imageView.setFitHeight(75);
                            centralPane.getChildren().add(this.imageView);
                            imageView.setLayoutX(cellCol * 35);
                            imageView.setLayoutY(cellRow * 35);
                            if (loadBuildingsOfMap()) {
                                buildingImageView = new ImageView();
//                                buidlingGridPane = new GridPane();

                                // Add row and column constraints
//                                RowConstraints rowConstraints = new RowConstraints();
//                                buildignPane.getRowConstraints().addAll(rowConstraints);
//
//                                ColumnConstraints columnConstraints = new ColumnConstraints();
//                                buildignPane.getColumnConstraints().addAll(columnConstraints

                                this.buildingImageView.setImage(LoadImages.getBuildingImages().get(map[cellRow][cellCol].getBuilding().getBuildingType()));
                                this.buildingImageView.setFitWidth(250);
                                this.buildingImageView.setFitHeight(250);
//                                centralPane.getChildren().add(this.buildingImageView);
                                this.buildingImageView.setLayoutX(cellCol * 35);
                                this.buildingImageView.setLayoutY(cellRow * 35);
//                                buidlingGridPane = new GridPane();
//                                buidlingGridPane.setPrefSize(cellSize, cellSize);
//                                buidlingGridPane.add(buildingImageView, smallerRow, smallerCol);
//                                buildignPane.getChildren().add(buildingImageView);
//                                this.buildingImageView.setLayoutX(cellCol * 35);
//                                this.buildingImageView.setLayoutY(cellRow * 35);
//                                System.out.println("the smallerRow is: " + smallerRow);
//                                System.out.println("the smallerCol is: " + smallerCol);
//                                buildignPane.setStyle("-fx-background-color: red");
//                                buidlingGridPane.setStyle("-fx-background-color: red");
//                                cellContainer.getChildren().add(buildingImageView);
//                                cellContainer.getChildren().add(buildignPane);
//                                surfacePane.add(buildignPane, smallerRow, smallerCol);
                            } else if (loadTreesOfMap()) {
                                treeImageView = new ImageView();
                                this.treeImageView.setImage(LoadImages.getTreeImages().get(map[cellRow][cellCol].getBuilding().getTreeType()));
                                cellContainer.getChildren().add(treeImageView);
                            }

//                            cellSurfaceContainer = new Pane(imageView);
//                            cellSurfaceContainer.setPrefSize((double) cellSize / 8, (double) cellSize / 8);
//                            surfacePane.add(cellSurfaceContainer, smallerRow, smallerCol);
//                            mouseEvent(col, row);
                        }
                    }
                }
//                tilePane.add(cellContainer, row, col);
//                tilePane.setStyle("-fx-background-color: green");
            }
        }
        centralPane.setStyle("-fx-border-color: yellow");
        bigPane.setStyle("-fx-background-color: red");
//        bigPane.setStyle("-fx- -color: red");


    }

    private boolean loadTreesOfMap() {
        if (map[cellRow][cellCol].isHasTreeInCell()) {
            return true;
        }
        return false;
    }

    private boolean loadBuildingsOfMap() {
        if (map[cellRow][cellCol].isHasBuildingInCell()) {
            System.out.println("the cellRow is: " + cellRow);
            System.out.println("the cellCol is: " + cellCol);
            return true;
        }
        return false;
    }

    private void mouseEvent(int col, int row) {
//        int finalCol = col;
//        int finalRow = row;

        this.imageView.setOnMouseEntered(event -> {
            validMoveUp = row > 1;
            validMoveDown = row < numRows - 1;
            validMoveRight = col < numColumns - 1;
            validMoveLeft = col > 1;
//                    ScrollSpeed = isMovingUp && finalRow < 40 ? 4 : 10;
//                    ScrollSpeed = isMovingDown && finalRow > 160 ? 4 : 10;
//                    ScrollSpeed = isMovingLeft && finalCol < 40 ? 4 : 10;
//                    ScrollSpeed = isMovingRight && finalCol > 160 ? 4 : 10;
        });


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
//        centralPane.setOnMouseEntered(this::handleMouseEnter);



        timeline = new Timeline(new KeyFrame(UPDATE_INTERVAL, e -> updateMapPosition()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void handleMouseEnter(MouseEvent event) {
        // Reset the movement flags when the mouse enters the scene
//        isMovingUp = false;
//        isMovingDown = false;
//        isMovingLeft = false;
//        isMovingRight = false;

        validMoveUp = true;
        validMoveDown = true;
        validMoveRight = true;
        validMoveLeft = true;
    }

    private void handleMouseExit(MouseEvent event) {
        // Stop the movement when the mouse exits the scene
//        isMovingUp = false;
//        isMovingDown = false;
//        isMovingLeft = false;
//        isMovingRight = false;
        validMoveUp = false;
        validMoveDown = false;
        validMoveRight = false;
        validMoveLeft = false;
    }


    private void onMouseMoved(MouseEvent event) {
        mouseExitedX = event.getScreenX();
        mouseExitedY = event.getSceneY();

        System.out.println(validMoveRight);


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

//        if (isMovingUp) {
//            dy += ScrollSpeed;
//        }
//        if (isMovingDown) {
//            dy -= ScrollSpeed;
//        }
//        if (isMovingLeft) {
//            dx += ScrollSpeed;
//        }
//        if (isMovingRight) {
//            dx -= ScrollSpeed;
//        }

        backGroundPane.setTranslateX(backGroundPane.getTranslateX() + dx);
        backGroundPane.setTranslateY(backGroundPane.getTranslateY() + dy);
    }


//    private void addToGridPane(int col, int row) {
//        mapPane.add(cellContainer, col, row);
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
