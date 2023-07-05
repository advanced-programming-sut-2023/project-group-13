//package viewG.GUIController;
//ppo0ooo0000000000gggghhhhjjjjk
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//
//public class DragAndDropTest extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        Label source = new Label("Drag me!");
//        source.setPrefSize(100, 50);
//
//        Rectangle target = new Rectangle(200, 200, Color.LIGHTGRAY);
//
//        source.setOnDragDetected(event -> {
//            // Start the drag-and-drop gesture
//            source.startDragAndDrop(event.getTransferMode());
//            event.consume();
//        });
//
//        target.setOnDragOver(event -> {
//            // Accept the drag if it contains a string data
//            if (event.getDragboard().hasString()) {
//                event.acceptTransferModes(event.getTransferMode());
//            }
//            event.consume();
//        });
//
//        target.setOnDragEntered(event -> {
//            // Provide visual feedback to the user that the target is ready to accept the drop
//            if (event.getDragboard().hasString()) {
//                target.setFill(Color.GRAY);
//            }
//            event.consume();
//        });
//
//        target.setOnDragExited(event -> {
//            // Reset the visual feedback
//            target.setFill(Color.LIGHTGRAY);
//            event.consume();
//        });
//
//        target.setOnDragDropped(event -> {
//            // Data dropped
//            var dragboard = event.getDragboard();
//            boolean success = false;
//            if (dragboard.hasString()) {
//                target.setFill(Color.GREEN);
//                success = true;
//            }
//            event.setDropCompleted(success);
//            event.consume();
//        });
//
//        target.setOnDragDone(event -> {
//            // Reset the visual feedback
//            target.setFill(Color.LIGHTGRAY);
//            event.consume();
//        });
//
//        StackPane root = new StackPane();
//        root.getChildren().addAll(target, source);
//        primaryStage.setScene(new Scene(root, 400, 400));
//        primaryStage.setTitle("Drag and Drop Example");
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
