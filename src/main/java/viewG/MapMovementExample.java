package viewG;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MapMovementExample extends Application {
    private static final double MAP_WIDTH = 800;
    private static final double MAP_HEIGHT = 600;
    private static final double SCENE_WIDTH = 1000;
    private static final double SCENE_HEIGHT = 800;
    private static final double MOVEMENT_SPEED = 2.0;
    private static final Duration UPDATE_INTERVAL = Duration.millis(16); // 60 FPS

    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    private Rectangle map;
    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) {
        map = new Rectangle(0, 0, MAP_WIDTH, MAP_HEIGHT);
        map.setFill(Color.GREEN);

        Group root = new Group(map);
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        scene.setOnMouseMoved(this::handleMouseMovement);
        scene.setOnMouseEntered(this::handleMouseEnter);
        scene.setOnMouseExited(this::handleMouseExit);

        primaryStage.setTitle("Map Movement Example");
        primaryStage.setScene(scene);
        primaryStage.show();

        timeline = new Timeline(new KeyFrame(UPDATE_INTERVAL, e -> updateMapPosition()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void handleMouseMovement(MouseEvent event) {
        Point2D mouseLocation = new Point2D(event.getSceneX(), event.getSceneY());

        // Check if the mouse is near the edges or corners
        isMovingUp = mouseLocation.getY() <= 10;
        isMovingDown = mouseLocation.getY() >= SCENE_HEIGHT - 10;
        isMovingLeft = mouseLocation.getX() <= 10;
        isMovingRight = mouseLocation.getX() >= SCENE_WIDTH - 10;
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
    }

    private void updateMapPosition() {
        double dx = 0;
        double dy = 0;

        if (isMovingUp) {
            dy -= MOVEMENT_SPEED;
        }
        if (isMovingDown) {
            dy += MOVEMENT_SPEED;
        }
        if (isMovingLeft) {
            dx -= MOVEMENT_SPEED;
        }
        if (isMovingRight) {
            dx += MOVEMENT_SPEED;
        }

        // Update the map position
        map.setX(map.getX() + dx);
        map.setY(map.getY() + dy);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
