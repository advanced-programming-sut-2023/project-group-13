package view.menus;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ButtonMaker extends Button {

    private Pane pane;
    private ImageView axImage;


    public ButtonMaker(String text, Pane pane, double x, double y) {
        BackgroundImage backgroundImage =
                new BackgroundImage(new Image(LoginMenuG.class.getResource("/png/button.jpg").toExternalForm()),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background background = new Background(backgroundImage);
        this.setAlignment(Pos.TOP_LEFT);
        this.setPadding(new Insets(5));
        this.setText("  " + text);
        this.setMaxWidth(160);
        this.setMinWidth(160);
        this.setMaxHeight(35);
        this.setMinHeight(35);
        this.setTextFill(Color.YELLOW);
        this.setFont(Font.font("Bodoni MT Italic", FontWeight.BOLD, 16));
        this.setBackground(background);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.pane = pane;
        hoverEvent();
    }

    private void hoverEvent() {
        this.setOnMouseEntered(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                playSoundOfHovering();
            }
        });
    }


//    private void playSoundOfHovering() {
//        Media media = new Media(getClass().getResource("../sources/sounds/swordSound.mp3").toExternalForm());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setVolume(0.2);
//        mediaPlayer.setCycleCount(1);
//        mediaPlayer.play();
//    }

}
