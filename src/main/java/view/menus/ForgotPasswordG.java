package view.menus;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Enums.DataEnumFile;
import model.Player;
import model.RandomsAndCaptcha;
import model.SaveAndLoadData;

public class ForgotPasswordG extends Application {
    @FXML
    public TextField questionT;
    @FXML
    private ImageView captchaImage;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField PasswordConfirmationField;
    String captchaNumber;
    @FXML
    private Button refresh;
    @FXML
    private TextField usernameT;
    @FXML
    private TextField newPasswordT;
    @FXML
    private TextField confirmT;
    @FXML
    private TextField captchaT;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label newPass;
    @FXML
    private Label confirmation;
    @FXML
    private Label question;
    @FXML
    private Label captchaLabel;
    private Player player;


    @Override

    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(LoginMenuG.class.getResource("/fxml/forgotPassword.fxml"));
        Pane pane = fxmlLoader.load();
        Image background = new Image(LoginMenuG.class.getResource("/png/mainmenu.jpg").toExternalForm());


        stage.setTitle("LoginMenu!");
        BackgroundImage x = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(x));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        Scene scene = new Scene(pane, screenBounds.getWidth(), screenBounds.getHeight());

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }

    private void initialize() {
        captchaMaking();
    }

    public void captchaMaking() {
        String x = RandomsAndCaptcha.captchaGenerator();
        captchaImage.setImage(new Image(LoginMenuG.class.getResource("/png/captcha/" + x + ".png").toExternalForm()));
        captchaNumber = x;
        Image image = new Image(LoginMenuG.class.getResource("/png/refresh.png").toExternalForm());
        ImageView p = new ImageView(image);
        refresh.setGraphic(p);

    }


    public void submitClicked1 (MouseEvent mouseEvent) {
        if (usernameT.getText().equals("")) {
            usernameLabel.setText("fill this field");
        } else if (player == null) {
            player = Player.getPlayerByUsername(usernameT.getText());
            usernameLabel.setText("user not found");
        } else if (player.getSecurityQuestionAnswer().equals(questionT.getText())) {
            question.setText("wrong answer");
        } else if (newPasswordField.getText().equals("")) {
            newPass.setText("this filed is empty");
        } else if (newPasswordField.getText().length() < 6) {
            newPass.setText("password is short.");
        } else if (!newPasswordField.getText().matches(".*[a-z].*") || !newPasswordField.getText().matches(".*[A-Z].*")
                || !newPasswordField.getText().matches(".*[0-9].*") || !newPasswordField.getText().matches(".*[\\W].*")) {
            newPass.setText("password pattern is wrong");
        } else if (!newPasswordField.getText().equals(PasswordConfirmationField.getText())) {
            newPass.setText("password didn't match");
        } else if (!captchaT.getText().equals(captchaNumber)) {
            captchaLabel.setText("wrong answer");
        } else {
            player.setPassword(newPasswordField.getText());
            SaveAndLoadData.SaveToJson(Player.players, DataEnumFile.PLAYERS.getFileName());
        }
    }

    public void backToLogin1 (MouseEvent mouseEvent) {
        LoginMenuG loginMenuG = new LoginMenuG();
        try {
            loginMenuG.start(LoginMenuG.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

