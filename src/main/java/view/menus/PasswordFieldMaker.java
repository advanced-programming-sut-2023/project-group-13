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
//    private TextFieldMaker passwordTextField;
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
//        this.createShowPassword();
//        updateFieldValues();
    }



//    private void createShowPassword() {
//        showPassword = new CheckBox("\uD83D\uDC41");
//        showPassword.setStyle("-fx-background-color: rgba(42, 42, 42, 0.7); -fx-text-fill: gray; -fx-font-size: 16; " +
//                "-fx-font-family: 'Bodoni MT Italic'; -fx-font-weight: bold; -fx-padding: 3; -fx-background-radius: 3");
//        showPassword.setTranslateX(this.getTranslateX() + 185);
//        showPassword.setTranslateY(this.getTranslateY());
//        showPassword.selectedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
//                if (newValue) {
//                    setVisible(false);
//                    passwordTextField.setVisible(true);
//                } else {
//                    setVisible(true);
//                    passwordTextField.setVisible(false);
//                }
//            }
//        });
//        //pane.getChildren().add(showPassword);
//    }





    public void handlingError(String errorText) {
        errorLabel.setTextFill(new Color(0.6, 0, 0.1, 1));
        errorLabel.setText(errorText);
    }

    public void handlingCorrect(String correctMessage) {
        errorLabel.setTextFill(new Color(0.2, 0.9, 0.2, 1));
        errorLabel.setText(correctMessage);
    }

//    private void updateFieldValues() {
//        this.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
//                passwordTextField.setText(newValue);
//            }
//        });
//
//        passwordTextField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
//                setText(newValue);
//            }
//        });
//    }

    public void clearErrorOrMessage() {
        errorLabel.setText("");
    }

//    public TextFieldMaker getPasswordTextField() {
//        return passwordTextField;
//    }

//    public void setPasswordTextField(TextFieldMaker passwordTextField) {
//        this.passwordTextField = passwordTextField;
//    }

    public CheckBox getShowPassword() {
        return showPassword;
    }

    public void setShowPassword(CheckBox showPassword) {
        this.showPassword = showPassword;
    }
}
