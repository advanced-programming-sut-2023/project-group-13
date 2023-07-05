package viewG.GUIController;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ImageListViewExample extends Application {
    private ListView<String> listView;
    private Map<String, Image> imageCache;

    @Override
    public void start(Stage primaryStage) {
        listView = new ListView<>();
        imageCache = new HashMap<>();

        // Set up the cell factory
        listView.setCellFactory(param -> new ImageListCell());

        // Add test data
        for (int i = 1; i <= 10000; i++) {
            listView.getItems().add("Image " + i);
        }

        BorderPane root = new BorderPane();
        root.setCenter(listView);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class ImageListCell extends ListCell<String> {
        private final ImageView imageView;

        public ImageListCell() {
            imageView = new ImageView();
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                return;
            }

            // Check if the image is already in the cache
            if (imageCache.containsKey(item)) {
                imageView.setImage(imageCache.get(item));
                setGraphic(imageView);
            } else {
                // If not in cache, load the image asynchronously
                Task<Image> loadTask = new Task<>() {
                    @Override
                    protected Image call() {
                        // Simulate image loading delay
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Load the image from file or URL
                        String imagePath = "/images/game/map/textures/grass.png";
                        return new Image(imagePath);
                    }
                };

                loadTask.setOnSucceeded(event -> {
                    Image loadedImage = loadTask.getValue();
                    imageCache.put(item, loadedImage);
                    imageView.setImage(loadedImage);
                    setGraphic(imageView);
                });

                new Thread(loadTask).start();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
