package viewG.GUIController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MapController {

    private static final Duration UPDATE_INTERVAL = Duration.millis(16); // 60 FPS

    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    public static boolean validMoveUp = false;
    public static boolean validMoveLeft = false;
    public static boolean validMoveRight = false;
    public static boolean validMoveDown = false;

    private Timeline timeline;
    private int cellSize = 300;
    private int numColumns = 23;
    private int numRows = 23;
    double minScale = 0.7;  // Minimum scale (50% zoom)
    double maxScale = 2.0;  // Maximum scale (200% zoom)
    private static double ScrollSpeed = 10; // Speed of map movement in pixels

    private double mouseExitedX;
    private double mouseExitedY;

    private Stage primaryStage;

    private Pane root;
    private Pane backGroundPane;

    public MapController(Stage primaryStage, Pane root, Pane backGroundPane) {
        this.primaryStage = primaryStage;
        this.backGroundPane = backGroundPane;
        this.root = root;
    }
    public void zoomFeature() {
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

    public void moveMaP() {
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
}
