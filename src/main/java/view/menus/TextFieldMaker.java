package view.menus;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TextFieldMaker extends TextField {

    private Label errorLabel;

    public TextFieldMaker(String promptText  , double x , double y, int width ,  Label errorLabel)  {
        this.setStyle("-fx-background-color: rgba(42 , 42 , 42 , 0.7); -fx-text-inner-color: white;");
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setMaxWidth(width);
        this.setMinWidth(width);
        this.setMaxHeight(30);
        this.setMinHeight(30);

        this.errorLabel = errorLabel;
        this.setFont(Font.font("Bodoni MT Italic" , FontWeight.BOLD , 16));
        this.setPromptText(promptText);
    }



    public void handlingError(String errorText) {
        errorLabel.setTextFill(new Color(0.6, 0, 0.1, 1));
        errorLabel.setText(errorText);
    }

    public void handlingCorrect(String correctMessage) {
        errorLabel.setTextFill(new Color(0.2, 0.9, 0.2, 1));
        errorLabel.setText(correctMessage);
    }

    public void clearErrorOrMessage() {
        errorLabel.setText("");
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

}
