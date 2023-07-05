package view.menus;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Enums.DataEnumFile;
import model.Player;
import model.RandomsAndCaptcha;
import model.SaveAndLoadData;


import java.util.ArrayList;

public class SignupMenuG extends Application {
    ComboBox<String> securityQuestion = new ComboBox<>();

    ComboBox<String> slogansCombo = new ComboBox<>();
    CheckBox sloganExist;
    private Button refresh;
    private Label labelUsername;
    private Label labelPassword;
    private Label labelPasswordConfirmation;
    private Label labelEmail;
    private Label labelNickname;
    private Label labelSlogan;
    private TextFieldMaker security;
    private Pane pane;
    private ButtonMaker submit;
    private ButtonMaker back;
    private TextFieldMaker usernameText;
    private TextFieldMaker emailText;

    private TextFieldMaker nicknameText;
    private TextFieldMaker sloganText;
    private TextFieldMaker captchaText;

    private PasswordFieldMaker passwordText;
    private PasswordFieldMaker passwordConfirmationText;
    private Label labelSecurity;
    private Button randomSlogan;
    private Button randomPassword;
    private CheckBox showPassword;

    private boolean validPassword1 = false;
    private boolean validUsername = false;
    private boolean validPasswordConfirmation = false;
    private boolean validEmail = false;
    private boolean validNickname = false;
    private boolean validSlogan = false;
    private ImageView captcha;
    private String captchaNumber;
    private Label labelCaptcha;


    @Override
    public void start(Stage stage) throws Exception {


        makingObjects();

        captchaMaking();

        FXMLLoader fxmlLoader = new FXMLLoader(LoginMenuG.class.getResource("/fxml/signupMenu.fxml"));
        pane = fxmlLoader.load();
        pane.getChildren().addAll(randomPassword, randomSlogan, labelUsername, labelSlogan, labelSecurity, labelPassword,
                labelNickname, labelPasswordConfirmation, labelEmail, sloganText, nicknameText, emailText, usernameText
                , passwordText, passwordConfirmationText, captcha, refresh, captchaText, submit, back, slogansCombo,
                security, securityQuestion, sloganExist, labelCaptcha);

        sloganRandomMaker();
        passwordRandomMaker();

//
        backButton();
        refreshCaptcha();
        sloganActivation();

        readySlogan();
        usernameCheck();
        passwordCheck();
        emailCheck();
        nicknameCheck();
        sloganCheck();
        passwordConfirmationCheck();
        submit();

        Image background = new Image(LoginMenuG.class.getResource("/png/mainmenu.jpg").toExternalForm());
        BackgroundImage x = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(x));
        Scene scene = new Scene(pane);
        stage.setTitle("SignupMenu");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public void submit() {
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (validPassword1 && validUsername && validEmail && validPasswordConfirmation && validNickname) {
                    if (!captchaText.getText().equals(captchaNumber)) {
                        clean();
                        captchaText.handlingError("captcha is wrong");
                    }
                   else if (sloganExist.isSelected() && !validSlogan) {
                        clean();
                        sloganText.handlingError("fill this field");
                    } else if (security.getText().equals("")) {
                       clean();
                        security.handlingError("fill this field");
                    } else if (securityQuestion.getValue().equals("")) {
                       clean();
                        security.handlingError("select question");
                    }else if  (sloganText.getText().equals("")) {

                        Player player = new Player(usernameText.getText(), passwordText.getText(), "", emailText.getText()
                                , nicknameText.getText(), securityQuestion.getValue(), security.getText());
                        Player.players.add(player);
                        SaveAndLoadData.SaveToJson(Player.players, DataEnumFile.PLAYERS.getFileName());
                        clean();
                    } else {

                        Player player = new Player(usernameText.getText(), passwordText.getText(), sloganText.getText(), emailText.getText()
                                , nicknameText.getText(), securityQuestion.getValue(), security.getText());
                        Player.players.add(player);
                        SaveAndLoadData.SaveToJson(Player.players, DataEnumFile.PLAYERS.getFileName());
                        clean();
                    }
                }

            }
        });


    }


    public void captchaMaking() {
        String x = RandomsAndCaptcha.captchaGenerator();
        captcha.setImage(new Image(LoginMenuG.class.getResource("/png/captcha/" + x + ".png").toExternalForm()));
        captchaNumber = x;
    }

    public void makingObjects() {
        captcha = new ImageView();
        captcha.setLayoutY(500);
        captcha.setLayoutX(800);
        labelUsername = new Label("username");
        labelPassword = new Label("password");
        labelPasswordConfirmation = new Label("confirm");
        labelEmail = new Label("email");
        labelNickname = new Label("nickname");
        labelSlogan = new Label("slogan");
        labelSecurity = new Label("security");
        labelCaptcha = new Label("captcha");
        Font font = new Font("Bodoni MT Italic", 16);
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(labelUsername);
        labels.add(labelEmail);
        labels.add(labelNickname);
        labels.add(labelSlogan);
        labels.add(labelPassword);
        labels.add(labelPasswordConfirmation);

        labels.add(labelSecurity);
        labels.add(labelCaptcha);
        for (Label label : labels) {
            label.setFont(font);
            label.setTextFill(Color.YELLOW);
        }
        int y = 200;
        for (Label label : labels) {

            label.setLayoutX(800);
            label.setLayoutY(y);
            y += 30;
        }
        labelCaptcha.setLayoutY(500);
        labelCaptcha.setLayoutX(1200);

        randomPassword = new ButtonMaker("Random Password", pane, 630, 400);
        randomSlogan = new ButtonMaker("Random Slogan", pane, 630, 320);

        usernameText = new TextFieldMaker("usernameText", 400, 200, 150, labelUsername);
        emailText = new TextFieldMaker("emailText", 400, 240, 150, labelEmail);
        nicknameText = new TextFieldMaker("nicknameText", 400, 280, 150, labelNickname);
        sloganText = new TextFieldMaker("sloganText", 400, 320, 150, labelSlogan);
        captchaText = new TextFieldMaker("captchaText", 1050, 520, 150, labelCaptcha);

        passwordText = new PasswordFieldMaker(pane, "password field", "password label", 400, 360, labelPassword);
        passwordConfirmationText = new PasswordFieldMaker(pane, "password field", "password label", 400, 400, labelPasswordConfirmation);
        refresh = new Button();
        refresh.setLayoutY(530);
        refresh.setLayoutX(1000);
        refresh.setMaxWidth(2);
        refresh.setMaxHeight(2);
        refresh.setMinWidth(2);
        refresh.setMinHeight(2);
        Image image = new Image(LoginMenuG.class.getResource("/png/refresh.png").toExternalForm());
        ImageView x = new ImageView(image);
        refresh.setGraphic(x);
        submit = new ButtonMaker("submit", pane, 600, 700);
        back = new ButtonMaker("back", pane, 720, 700);
        back.setMaxWidth(90);
        back.setMinWidth(90);
        submit.setMaxWidth(90);
        submit.setMinWidth(90);
        //submit && and logic

        slogansCombo.getItems().addAll(RandomsAndCaptcha.randomSlogansText);
        slogansCombo.setMaxWidth(150);
        slogansCombo.setMinWidth(150);
        slogansCombo.setLayoutX(500);
        slogansCombo.setLayoutY(500);
        slogansCombo.setDisable(true);
        sloganText.setDisable(true);
        sloganExist = new CheckBox("slogan?");
        sloganExist.setFont(Font.font("Bodoni MT Italic", FontWeight.BOLD, 16));
        sloganExist.setTextFill(Color.YELLOW);
        sloganExist.setLayoutX(550);
        sloganExist.setLayoutY(320);
        securityQuestion.getItems().addAll(questions);
        securityQuestion.setLayoutX(1000);
        securityQuestion.setLayoutY(350);
        security = new TextFieldMaker("security", 1000, 400, 150, labelSecurity);

    }

    String[] questions = {"What is my father’s name?",
            "What was my first pet’s name?",
            "What is my mother’s last name?"};

    public void backButton() {
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LoginMenuG loginMenuG = new LoginMenuG();
                try {
                    loginMenuG.start(LoginMenuG.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void sloganActivation() {
        sloganExist.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(true)) {
                sloganText.setDisable(false);
                slogansCombo.setDisable(false);
            }
            if (newValue.equals(false)) {
                sloganText.setDisable(true);
                slogansCombo.setDisable(true);
            }

        });
    }

    public void refreshCaptcha() {
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                captchaMaking();
            }
        });
    }

    public void sloganRandomMaker() {
        randomSlogan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (sloganExist.isSelected()) sloganText.setText(RandomsAndCaptcha.randomSloganGenerator());
            }
        });
    }

    public void passwordRandomMaker() {
        randomPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                passwordText.setText(RandomsAndCaptcha.randomPasswordGenerator());
            }
        });
    }

    public void readySlogan() {
        slogansCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            sloganText.setText(newValue);
            validSlogan = true;
        });
    }

    public void usernameCheck() {
        usernameText.textProperty().addListener((observable, oldValue, newValue) -> {
            usernameText.clearErrorOrMessage();
            if (newValue.equals("")) {
                usernameText.handlingError("fill this field.");
                validUsername = false;
            } else if (!newValue.matches("([a-z]|[A-Z]|[0-9]|_)*")) {
                usernameText.handlingError("invalid username!");
                validUsername = false;

            } else if (Player.getPlayerByUsername(newValue) != null) {
                usernameText.handlingError("username already exists!");
                validUsername = false;
            } else {
                usernameText.handlingCorrect("valid username");
                validUsername = true;
            }
        });
    }

    public void passwordCheck() {
        passwordText.textProperty().addListener((observable, oldValue, newValue) -> {
            passwordText.clearErrorOrMessage();
            if (newValue.equals("")) {
                passwordText.handlingError("fill this field.");
                validPassword1 = false;

            } else if (newValue.contains(" ")) {
                passwordText.handlingError("you can't use space!");
                validPassword1 = false;
            } else if (newValue.length() < 6) {
                passwordText.handlingError("password length is below 6.");
                validPassword1 = false;
            } else if (!newValue.matches(".*[a-z].*") || !newValue.matches(".*[A-Z].*")
                    || !newValue.matches(".*[0-9].*") || !newValue.matches(".*[\\W].*")) {
                passwordText.handlingError("password pattern is wrong");
                validPassword1 = false;
            } else {
                passwordText.handlingCorrect("strong password");
                validPassword1 = true;
            }
        });
    }

    public void emailCheck() {
        emailText.textProperty().addListener((observable, oldValue, newValue) -> {
            int emailCheck = 0;
            for (Player player : Player.players) {
                if (player.getEmail().equals(newValue)) emailCheck = 1;
            }
            emailText.clearErrorOrMessage();
            if (newValue.equals("")) {
                emailText.handlingError("fill this field.");
                validEmail = false;

            } else if (emailCheck == 1) {
                emailText.handlingError("this email already used.");
                validEmail = false;
            } else if (!newValue.matches("^[\\w_]+@[\\w_]+\\.[\\w_]{2,}$")) {
                emailText.handlingError("Invalid email format.");
                validEmail = false;
            } else {
                emailText.handlingCorrect("valid email");
                validEmail = true;
            }
        });
    }

    public void nicknameCheck() {
        nicknameText.textProperty().addListener((observable, oldValue, newValue) -> {

            nicknameText.clearErrorOrMessage();
            if (newValue.equals("")) {
                nicknameText.handlingError("fill this field.");
                validNickname = false;

            } else {
                nicknameText.handlingCorrect("valid nickname");
                validNickname = true;
            }
        });

    }

    public void sloganCheck() {
        sloganText.textProperty().addListener((observable, oldValue, newValue) -> {
            sloganText.clearErrorOrMessage();
            if ((newValue.equals("") && sloganExist.isSelected())) {
                sloganText.handlingError("fill this field.");
                validSlogan = false;
            } else {
                sloganText.handlingCorrect("valid slogan");
                validSlogan = true;
            }
        });
    }

    public void passwordConfirmationCheck() {
        passwordConfirmationText.textProperty().addListener((observable, oldValue, newValue) -> {
            passwordConfirmationText.clearErrorOrMessage();
            if (newValue.equals("")) {
                passwordConfirmationText.handlingError("fill this field.");
                validPasswordConfirmation = false;

            } else if (!passwordText.getText().equals(newValue)) {
                passwordConfirmationText.handlingError("password didn't match.");
                validPasswordConfirmation = false;

            } else {
                passwordConfirmationText.handlingCorrect("match.");
                validPasswordConfirmation = true;
            }
        });
    }

    public void clean() {
        passwordText.setText("");
        passwordConfirmationText.setText("");
        emailText.setText("");
        usernameText.setText("");
        nicknameText.setText("");
        sloganText.setText("");
        captchaText.setText("");
        refreshCaptcha();
    }

}
