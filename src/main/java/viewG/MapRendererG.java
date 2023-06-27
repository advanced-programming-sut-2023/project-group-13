package viewG;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;


public class MapRendererG extends Application {

    private Stage primaryStage;
    private ImageView imageView;
    private GridPane mapPane;
    private GridPane tilePane;
//
//    //for moving the map
    private double initialX;
    private double initialY;
//    private double translateX;
//    private double translateY;
//
//    //* for moving the map

    // for moving with the mouse pointer

    private double translateX;
    private double translateY;
    private double screenWidth;
    private double screenHeight;

    private static double ScrollSpeed = 10; // Speed of map movement in pixels


    private boolean isMouseOnPane = false;

    private boolean isTimelineRunning = false;


    //*for moving with the mouse pointer





    //setting the boundaries
    private double minTranslateX;  // Minimum allowed translation on the X-axis
    private double maxTranslateX;  // Maximum allowed translation on the X-axis
    private double minTranslateY;  // Minimum allowed translation on the Y-axis
    private double maxTranslateY;  // Maximum allowed translation on the Y-axis

// Set the boundaries according to your requirements

    private double mouseExitedX;
    private double mouseExitedY;

    private double mapOffsetX = 0; // Horizontal offset of the map
    private double mapOffsetY = 0; // Vertical offset of the map


    private Pane backGroundPane;
    private Scene scene;
    private Timeline mapMoveTimeline;

    // move map automatically
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    private boolean validMoveUp = false;
    private boolean validMoveLeft = false;
    private boolean validMoveRight = false;

    private boolean validMoveDown = false;
    private Timer moveMapTimer;
    double minScale = 0.5;  // Minimum scale (50% zoom)
    double maxScale = 2.0;  // Maximum scale (200% zoom)


    private static final Duration UPDATE_INTERVAL = Duration.millis(16); // 60 FPS


    private Timeline timeline;
    // move map automatically


    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        screenWidth = screenBounds.getWidth();
        screenHeight = screenBounds.getHeight();
        Pane root = new Pane();


        backGroundPane = new Pane();

        root.getChildren().add(backGroundPane);

        HBox hBox = new HBox();

        mapPane = new GridPane();
        tilePane = new GridPane();

        root.setStyle("-fx-background-color: rgba(165,89,42,0.68)");

        Image desertImage = new Image(MapRendererG.class.getResource("/images/tiles/desert_tile.jpg").toExternalForm());
//        ImageView desertImageView = new ImageView();
        Image grassImage = new Image(MapRendererG.class.getResource("/images/tiles/img.png").toExternalForm());
        ImageView backGroundImageView = new ImageView();
        ImageView backGroundImageView2 = new ImageView();
        backGroundImageView.setImage(desertImage);
        backGroundImageView2.setImage(desertImage);

        //resizing

        double targetWidth = 200;   // Desired width of the resized image
        double targetHeight = 300;  // Desired height of the resized image
        backGroundImageView.setFitWidth(targetWidth);
        backGroundImageView.setFitHeight(targetHeight);

        backGroundImageView2.setFitWidth(targetWidth);
        backGroundImageView2.setFitHeight(targetHeight);


        hBox.getChildren().addAll(backGroundImageView,backGroundImageView2);

        double initialScale = 1.0; // Initial scale factor (1.0 = 100%)
        double pivotX = 0.0;      // Pivot point X coordinate
        double pivotY = 0.0;      // Pivot point Y coordinate

//
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





        for (int row = 0; row < 30; row++) {
            for (int col = 0; col < 30; col++) {
                imageView = new ImageView();
                imageView.setImage(desertImage);
                imageView.setFitWidth(targetWidth);
                imageView.setFitHeight(targetHeight);
                addToGridPane(col, row);

                int finalRow = row;
                int finalCol = col;

            }
        }

        for (int row = 0; row < 200; row++) {
            for (int col = 0; col < 200; col++) {
                imageView = new ImageView();
                imageView.setImage(grassImage);
                tilePane.add(imageView, col, row);

                int finalCol = col;
                int finalRow = row;
                imageView.setOnMouseEntered(event -> {

                    validMoveLeft = finalCol > 30;
                    validMoveRight = finalCol < 170;
                    validMoveUp = finalRow > 30;
                    validMoveDown = finalRow < 170;

                    ScrollSpeed = isMovingUp && finalRow < 32 ? 4 : 10;
                    ScrollSpeed = isMovingDown && finalRow > 168 ? 4 : 10;
                    ScrollSpeed = isMovingLeft && finalCol < 32 ? 4 : 10;
                    ScrollSpeed = isMovingRight && finalCol > 168 ? 4 : 10;
                    System.out.println("the scroll speed image view is: " + ScrollSpeed);
                });
            }
        }
        
        backGroundPane.getChildren().addAll(mapPane,tilePane);
//        backGroundPane.getChildren().addAll(hBox);

        moveMaP();

//        desertImageView.setImage(desertImage);

        scene = new Scene(root,1200,1200);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void moveMaP() {
        backGroundPane.setOnMouseMoved(this::onMouseMoved);
        backGroundPane.setOnMouseEntered(this::handleMouseEnter);
        backGroundPane.setOnMouseExited(this::handleMouseExit);

//        timeline = new Timeline(new KeyFrame(UPDATE_INTERVAL, e -> updateMapPosition()));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();

        moveMapTimer = new Timer();
        moveMapTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateMapPosition();

            }
        },0,16);


//        mapMoveTimeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {
            // Move the map based on the mapOffsetX and mapOffsetY
            // Your code to update the map's position

            // Update the translation properties of the mapPane or its contents
//            backGroundPane.setTranslateX(mapOffsetX);
//            backGroundPane.setTranslateY(mapOffsetY);
//        }));
//        mapMoveTimeline.setCycleCount(Timeline.INDEFINITE); // Run the timeline indefinitely
//        mapMoveTimeline.setCycleCount(Timeline.INDEFINITE);

        

//        backGroundPane.setOnMousePressed(event -> onMousePressed(event));
//        backGroundPane.setOnMouseDragged(event -> onMouseDragged(event));
//        backGroundPane.setOnMouseReleased(event -> onMouseReleased());

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

    private void handleMouseEntered(MouseEvent mouseEvent) {
        isMouseOnPane = true;
    }

    private void handleMouseExited(MouseEvent mouseEvent) {
        isMouseOnPane = false;
    }

    private void onMouseMoved(MouseEvent event) {
        mouseExitedX = event.getScreenX();
        mouseExitedY = event.getSceneY();

//        System.out.println("the mouseExitedX is: " + mouseExitedX);
//        System.out.println("the mouseExitedT is: " + mouseExitedY);
//        System.out.println("the primary stage width is: " + primaryStage.getWidth());


        isMovingLeft = mouseExitedX <= 100;
        isMovingRight = mouseExitedX >= primaryStage.getWidth() - 100;
        isMovingUp = mouseExitedY <= 100;
        isMovingDown = mouseExitedY >= primaryStage.getHeight() - 100;

//        System.out.println("the mapLayout x is: " + mapPane.getLayoutX());

//        System.out.println("the mouse exited y value is:" + mouseExitedY);

//
//        if (mouseExitedX <= 100) {
//            isMovingLeft = true;
//
//
//
//            // Move map to the left
////            mapOffsetX += ScrollSpeed;
////            startMapMoveTimeline();
////            updateMapPosition();
//
////            TranslateTransition transition = new TranslateTransition(Duration.seconds(4), backGroundPane);
////            transition.setToX(backGroundPane.getTranslateX() + ScrollSpeed); // Adjust deltaY according to your requirements
////            transition.play();
////
//
//        } else if (mouseExitedX >= primaryStage.getWidth() - 100) {
//            // Move map to the right
////            System.out.println("it here comes for moving the map to the right");
////            mapOffsetX -= ScrollSpeed;
////            updateMapPosition();
////            startMapMoveTimeline();
//
//
//        }
//
//        else if (mouseExitedY <= 100) {
//
//
//
//            // Move map upward
////            mapOffsetY += ScrollSpeed;
////            startMapMoveTimeline();
////            updateMapPosition();
//
//        } else if (mouseExitedY >= primaryStage.getHeight() - 100) {
//            // Move map downward
////            mapOffsetY -= ScrollSpeed;
////            startMapMoveTimeline();
//
////            updateMapPosition();
//        }
    }

    private void updateMapPosition() {
        double dx = 0;
        double dy = 0;

//        System.out.println("the scroll speed value is: " + ScrollSpeed);
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

        // Update the map position
//        map.setX(map.getX() + dx);
//        map.setY(map.getY() + dy);
//        if (backGroundPane.getTranslateY() != 0)
        backGroundPane.setTranslateX(backGroundPane.getTranslateX() + dx);
        backGroundPane.setTranslateY(backGroundPane.getTranslateY() + dy);

//        System.out.println("the translateX value is: " + backGroundPane.getTranslateX() +  "\n" +
//                "the translateY value is: " + backGroundPane.getTranslateY());
    }


//    private void updateMapPosition() {
//        // Update the translation properties of the mapPane or its contents
//        backGroundPane.setTranslateX(mapOffsetX);
//        backGroundPane.setTranslateY(mapOffsetY);
//    }


    private void startMapMoveTimeline() {
        if (!isTimelineRunning) {
            mapMoveTimeline.play();
            isTimelineRunning = true;
        }
    }

//    private void stopMapMoveTimeline() {
//        if (mapMoveTimeline.isPlaying()) {
//            mapMoveTimeline.stop();
//        }
//    }


// ...


//    private void onMousePressed(MouseEvent event) {
//        initialX = event.getSceneX();
//        initialY = event.getSceneY();
//        translateX = backGroundPane.getTranslateX();
//        translateY = backGroundPane.getTranslateY();
//    }

//    private void onMouseDragged(MouseEvent event) {
//        double offsetX = event.getSceneX() - initialX;
//        double offsetY = event.getSceneY() - initialY;
//
//        double newTranslateX = translateX + offsetX;
//        double newTranslateY = translateY + offsetY;
//
//        // Apply the boundaries for the translation
////        newTranslateX = clamp(newTranslateX, minTranslateX, maxTranslateX);
////        newTranslateY = clamp(newTranslateY, minTranslateY, maxTranslateY);
//
//        backGroundPane.setTranslateX(newTranslateX);
//        backGroundPane.setTranslateY(newTranslateY);
//    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private void onMouseReleased() {
        // Perform any cleanup or additional actions after the mouse is released (if needed)
    }


    private void addToGridPane(int col, int row) {
        mapPane.add(imageView, col, row);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
