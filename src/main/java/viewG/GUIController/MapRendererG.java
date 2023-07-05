package viewG.GUIController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Cell;
import model.Enums.Images.IconPath;
import model.Enums.Images.LoadImages;
import model.Enums.Paths;
import model.Map;

import java.util.concurrent.atomic.AtomicReference;


public class MapRendererG extends Application {


    private Stage primaryStage;

    private BorderPane root;
    private static ImageView imageView;
    private static ImageView buildingImageView;
    private static ImageView treeImageView;

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

    private ImageView miniMapImageView;

    private static final double BRIGHTNESS_DELTA = 0.2;

    private Pane buttomBar;

    private int iconBuildingBarFitWidth = 70;
    private int iconBuildingBarFitHeight = 70;

    private int iconSmallerBarFitWidth = 30;
    private int iconSmallerBarFitHeight = 30;



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

        addMiniMap();
        handleButtomBar();

        WritableImage selectImageWhite = new WritableImage(35, 35);
        PixelWriter pixelWriterWhite = selectImageWhite.getPixelWriter();

        // Set all pixels to partially transparent white
        for (int x = 0; x < 35; x++) {
            for (int y = 0; y < 35  ; y++) {
                pixelWriterWhite.setColor(x, y, javafx.scene.paint.Color.rgb(255, 255, 255, 0.5));
            }
        }

        WritableImage selectImageTransparent = new WritableImage(35, 35);
        PixelWriter pixelWriterTransparent = selectImageTransparent.getPixelWriter();

        // Set all pixels to partially transparent white
        for (int x = 0; x < 35; x++) {
            for (int y = 0; y < 35  ; y++) {
                pixelWriterTransparent.setColor(x, y, Color.TRANSPARENT);
            }
        }

        root.setCenter(backGroundPane);
        root.setBottom(buttomBar);

        initMap(desertImage);
        zoomFeature();

        backGroundPane.getChildren().add(bigPane);

        moveMaP();
        chooseTile(selectImageTransparent,selectImageWhite);
        chooseMultipleTiles();
        updateMiniMap();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void addMiniMap() {
        miniMapImageView = new ImageView();
        miniMapImageView.setPreserveRatio(true);
    }

    private void handleButtomBar() {

        buttomBar = new Pane();
        Image barImage = new Image(MapRendererG.class.getResource("/images/game/bar/bar.png").toExternalForm());
        ImageView barImageView = new ImageView(barImage);
        barImageView.setFitWidth(1540);
        barImageView.setLayoutY(100);
        buttomBar.getChildren().addAll(barImageView,miniMapImageView);
        miniMapImageView.setLayoutX(1040);
        miniMapImageView.setLayoutY(165);
//        buttomBar.setMouseTransparent(true);

        addBuidlingTypeIcons();
        addBarBuildingIcons();
        addOtherIconsOfTheRightMainBar();
        addOtherIconsOfTheButtomRightBar();
    }

    private void addOtherIconsOfTheButtomRightBar() {
        int baseLayoutX = 830;
        int baseLayoutY = 295;
        int incrementSpacingIcon = 45;

        ImageView[] iconsOfButtomRightMainBar = {
                IconPath.RIGHT_BOTTOM_TALK_TO_ALLIES.getImageView()
                , IconPath.RIGHT_BOTTOM_ORDER_OF_MERIT_ICON.getImageView()
        };

        int layoutX = baseLayoutX + incrementSpacingIcon;
        for (ImageView icon : iconsOfButtomRightMainBar) {
            imageView = icon;
            icon.setLayoutX(layoutX);
            icon.setLayoutY(baseLayoutY);
            hoverFeature();
            layoutX += incrementSpacingIcon;
        }

        buttomBar.getChildren().addAll(iconsOfButtomRightMainBar);
    }

    private void addOtherIconsOfTheRightMainBar() {
        int baseLayoutX = 830;
        int baseLayoutY = 200;
        int incrementSpacingIcon = 45;

        ImageView[] iconsOfRightMainBar = {
                IconPath.RIGHT_TOWERS_ICON.getImageView(),
                IconPath.RIGHT_MILITARY_ICON.getImageView(),
        };
        int layoutX = baseLayoutX + incrementSpacingIcon;
        for (ImageView icon : iconsOfRightMainBar) {
            icon.setLayoutX(layoutX);
            icon.setLayoutY(baseLayoutY);
            icon.setFitHeight(iconSmallerBarFitHeight);
            icon.setFitWidth(iconSmallerBarFitWidth);
            imageView = icon;
            hoverFeature();
            layoutX += incrementSpacingIcon;
        }

        ImageView gateHouse = IconPath.RIGHT_GATE_HOUSES_ICON.getImageView();
        gateHouse.setLayoutX(baseLayoutX + incrementSpacingIcon);
        gateHouse.setLayoutY(baseLayoutY + incrementSpacingIcon);
        imageView = gateHouse;
        hoverFeature();

        gateHouse.setFitWidth(iconSmallerBarFitHeight);
        gateHouse.setFitHeight(iconSmallerBarFitWidth);
        imageView = gateHouse;

        buttomBar.getChildren().addAll(iconsOfRightMainBar);
        buttomBar.getChildren().add(gateHouse);
    }

    private void addBarBuildingIcons() {
        int baseLayoutX = 270;
        int baseLayoutY = 215;
        int incrementSpacingIcon =  75;

        ImageView[] initialIconsInsideTheBar = {
                IconPath.INITIAL_STAIRS_ICON.getImageView(),
                IconPath.INITIAL_SMALL_WALL_ICON.getImageView(),
                IconPath.INITIAL_BIG_WALL_ICON.getImageView(),
                IconPath.INITIAL_CRENULATED_WALL_ICON.getImageView(),
                IconPath.INITIAL_BARRACKS_ICON.getImageView(),
                IconPath.INITIAL_MERCENARY_ICON.getImageView(),
                IconPath.INITIAL_ARMORY_ICON.getImageView(),
        };

        int layoutX = baseLayoutX + incrementSpacingIcon;
        for (ImageView icon : initialIconsInsideTheBar) {
            icon.setLayoutX(layoutX);
            icon.setLayoutY(baseLayoutY);
            imageView = icon;
            hoverFeature();
            layoutX += incrementSpacingIcon;
            icon.setFitWidth(iconBuildingBarFitWidth);
            icon.setFitHeight(iconBuildingBarFitHeight);
        }

        buttomBar.getChildren().addAll(initialIconsInsideTheBar);

    }

    private void addBuidlingTypeIcons() {
        int baseLayoutX = 300;
        int baseLayoutY = 295;
        int incrementSpacingIcon = 45;

        ImageView[] buildingTypeButtomBaricons = {
                IconPath.CASTLE_BUILDING_ICON.getImageView(),
                IconPath.FARM_BUILDING_ICON.getImageView(),
                IconPath.INDUSTRY_BUILDING_ICON.getImageView(),
                IconPath.WEAPON_BUILDING_ICON.getImageView(),
                IconPath.TOWN_BUILDING_ICON.getImageView(),
                IconPath.FOOD_PROCESSING_BUILDING_Icon.getImageView()
        };

        int layoutX = baseLayoutX + incrementSpacingIcon;
        for (ImageView icon : buildingTypeButtomBaricons) {
            icon.setLayoutX(layoutX);
            icon.setLayoutY(baseLayoutY);
            imageView = icon;
            iconBehaviour();
            hoverFeature();
            layoutX += incrementSpacingIcon;
        }

        buttomBar.getChildren().addAll(buildingTypeButtomBaricons);
        //TODO later on add here to the hash maps of loadImages
    }

    private void iconBehaviour() {
        imageView.setOnMouseClicked(event -> {
            if (imageView.getId().equals("farmBuildings")) {
                barFarmBuildingImageView = IconPath.EMPTY_BAR.getImageView();
            }
        });
    }


    private void hoverFeature() {
        ColorAdjust colorAdjust = new ColorAdjust();
        imageView.setEffect(colorAdjust);

//        System.out.println("it comes here to this hover method");

        // Add event handlers for hover effect
        imageView.setOnMouseEntered(event -> {
//            System.out.println("mouse entered this bad method");
            colorAdjust.setBrightness(colorAdjust.getBrightness() + BRIGHTNESS_DELTA);
        });

        imageView.setOnMouseExited(event -> {
//            System.out.println("mouse exited this bad method");
            colorAdjust.setBrightness(colorAdjust.getBrightness() - BRIGHTNESS_DELTA);
        });
    }

    private void chooseMultipleTiles() {
        Rectangle window = new Rectangle();

        AtomicReference<Double> initialMouseX = new AtomicReference<>((double) 0);
        AtomicReference<Double> initialMouseY = new AtomicReference<>((double) 0);

        centralPane.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                window.setFill(Color.TRANSPARENT); // Make the rectangle transparent
                window.setStroke(Color.BLACK); // Add a border to the rectangle

                initialMouseX.set(event.getX());
                initialMouseY.set(event.getY());

                // Set the initial position and dimensions of the window
                window.setX(initialMouseX.get());
                window.setY(initialMouseY.get());
                window.setWidth(0);
                window.setHeight(0);

                // Make the window visible
                window.setVisible(true);
            }
        });

        centralPane.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY){
            double mouseX = event.getX();
            double mouseY = event.getY();

            // Calculate the new position and dimensions of the window
            double windowX = Math.min(mouseX, initialMouseX.get());
            double windowY = Math.min(mouseY, initialMouseY.get());
            double windowWidth = Math.abs(mouseX - initialMouseX.get());
            double windowHeight = Math.abs(mouseY - initialMouseY.get());

            // Update the position and dimensions of the window
            window.setX(windowX);
            window.setY(windowY);
            window.setWidth(windowWidth);
            window.setHeight(windowHeight);
            }
        });


        centralPane.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                // Perform actions based on the selected window
                // For example, you can retrieve the bounds of the selected window using:
//            double windowX = window.getX();
//            double windowY = window.getY();
//            double windowWidth = window.getWidth();
//            double windowHeight = window.getHeight();

                // Hide the window
//            window.setVisible(false);
                window.setFill(javafx.scene.paint.Color.rgb(255, 255, 255, 0.5));
                window.setStroke(Color.TRANSPARENT);
            }
        });

        centralPane.getChildren().add(window);


    }

    ImageView selectImageView = new ImageView();
    private void chooseTile(WritableImage selectImageTransparent, WritableImage selectImageWhite) {
        centralPane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {

                double x = event.getX();
                double y = event.getY();
//            System.out.println("x = " + x + ", y = " + y);
                double x_coordinate = x / 35;
                double y_coordinate = y / 35;
                selectImageView.setImage(selectImageWhite);
                selectImageView.setLayoutX(x - 15);
                selectImageView.setLayoutY(y - 15);
            } else if (event.getButton() == MouseButton.PRIMARY) {
                selectImageView.setImage(selectImageTransparent);
            }
        });
        centralPane.getChildren().add(selectImageView);
    }

    private void initMap(Image desertImage) {

        double targetWidth = 300;   // Desired width of the resized image
        double targetHeight = 300;  // Desired height of the resized image


        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                MapRendererG.imageView = new ImageView();
                MapRendererG.imageView.setImage(desertImage);
                MapRendererG.imageView.setFitHeight(targetHeight);
                MapRendererG.imageView.setFitWidth(targetWidth);
                bigPane.getChildren().add(MapRendererG.imageView);
                MapRendererG.imageView.setLayoutX(col * cellSize);
                MapRendererG.imageView.setLayoutY(row * cellSize);
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
                            if (map[cellRow][cellCol].getTypeofGround().getFullNameType().equals("Earth")) {
                                continue;
                            }
                            MapRendererG.imageView = new ImageView();
                            MapRendererG.imageView.setImage(LoadImages.getTileImages().get(map[cellRow][cellCol].getTypeofGround()));

                            MapRendererG.imageView.setFitWidth(75);
                            MapRendererG.imageView.setFitHeight(75);
                            centralPane.getChildren().add(MapRendererG.imageView);
                            imageView.setLayoutX(cellCol * 35);
                            imageView.setLayoutY(cellRow * 35);

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
                                MapRendererG.buildingImageView.setImage(LoadImages.getBuildingImages().get(map[cellRow][cellCol].getBuilding().getBuildingType()));
                                MapRendererG.buildingImageView.setFitWidth(250);
                                MapRendererG.buildingImageView.setFitHeight(250);
                                MapRendererG.buildingImageView.setLayoutX(cellCol * 35);
                                MapRendererG.buildingImageView.setLayoutY(cellRow * 35);
                                centralPane.getChildren().add(MapRendererG.buildingImageView);

                            } else if (loadTreesOfMap()) {
                                treeImageView = new ImageView();
                                MapRendererG.treeImageView.setImage(LoadImages.getTreeImages().get(map[cellRow][cellCol].getBuilding().getTreeType()));
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
//        if (isMovingDown) {
//            System.out.println("it is moving down on the buttom bar");
//            buttomBar.setOnMouseEntered(this::handleBottomBarEnter);
//        } else centralPane.setOnMouseExited(this::handleMouseExit);

        timeline = new Timeline(new KeyFrame(UPDATE_INTERVAL, e -> updateMapPosition()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void handleBottomBarEnter(MouseEvent mouseEvent) {
        validMoveDown = true;
        validMoveRight = true;
        validMoveLeft = true;
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

        if (isMovingDown) {
            buttomBar.setOnMouseEntered(this::handleBottomBarEnter);
        } else centralPane.setOnMouseExited(this::handleMouseExit);

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
        
//        updateMiniMap();
    }

    private void updateMiniMap() {
        Image snapshot = backGroundPane.snapshot(null,null);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

//        double miniMapWidth = screenBounds.getWidth() / 4 ;
//        double miniMapHeight = screenBounds.getHeight() / 4 - 65;

        // Resize the snapshot to the desired dimensions

        miniMapImageView.setImage(snapshot);
        miniMapImageView.setFitWidth(165);
        miniMapImageView.setFitHeight(165);
        // todo later on add the present point to the miniMap
        // todo later on update the map when you drop a building
    }

    public static void main(String[] args) {
        launch(args);
    }
}
