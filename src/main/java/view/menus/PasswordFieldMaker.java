package view.menus;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PasswordFieldMaker extends PasswordField {
    private Pane pane;
    private String promptText;
    private String labelText;
    public Label errorLabel;
    private CheckBox showPassword;

    public PasswordFieldMaker(Pane pane, String promptText, String labelText, double x, double y , Label errorLabel ) {
        this.promptText = promptText;
        this.labelText = labelText;
        this.errorLabel = errorLabel;
        this.setStyle("-fx-background-color: rgba(42 , 42 , 42 , 0.7); -fx-text-inner-color: white;");
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setMaxWidth(150);
        this.setMinWidth(150);
        this.setMaxHeight(30);
        this.setMinHeight(30);
        this.pane = pane;



        this.setFont(Font.font("Bodoni MT Italic", FontWeight.BOLD, 16));
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


    public CheckBox getShowPassword() {
        return showPassword;
    }

    public void setShowPassword(CheckBox showPassword) {
        this.showPassword = showPassword;
    }
}
