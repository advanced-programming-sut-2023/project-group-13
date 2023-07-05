package view.menus;

import controller.ControllerControllers;
import controller.ControllerControllersG;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Enums.DataEnumFile;
import model.Player;
import model.SaveAndLoadData;


import java.io.File;
import java.io.IOException;

public class ProfileMenuG extends Application {
    @FXML
    private Text usernameText;
    @FXML
    private Text emailText;
    @FXML
    private Text passwordText;
    @FXML
    private Text nicknameText;
    @FXML
    private Text sloganText;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView myAvatar;
    @FXML
    private ImageView avatar1;
    @FXML
    private ImageView avatar2;
    @FXML
    private ImageView avatar3;
    @FXML
    private ImageView avatar4;
    @FXML
    private ImageView avatar5;
    @FXML
    private ImageView avatar6;
    public static Stage stage;
    private Player player;

    @Override
    public void start(Stage stage) throws Exception {

        ProfileMenuG.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMenuG.class.getResource("/fxml/profileMenu.fxml"));
        Pane pane = fxmlLoader.load();
        Image background = new Image(LoginMenuG.class.getResource("/png/mainmenu.jpg").toExternalForm());
        BackgroundImage x = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(x));
        Scene scene = new Scene(pane);
        stage.setTitle("ProfileMenu");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public void backToProfile(MouseEvent mouseEvent) throws Exception {
        start(ControllerControllersG.stage);
    }

    //    public void initialize() {
//        player = Player.getCurrentPlayer();
//        usernameText.setText("username : " + player.getUsername());
//        emailText.setText("email : " + player.getEmail());
//        passwordText.setText("password : " + player.getPassword());
//        nicknameText.setText("nickname : " + player.getNickname());
//        if (player.getSlogan().equals("")) {
//            sloganText.setText("slogan: ---");
//        } else sloganText.setText("slogan : " + player.getSlogan());
//    }
    public void enterChangeMenu(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(LoginMenuG.class.getResource("/fxml/changeUsername.fxml"));
        Scene scene = new Scene(anchorPane, 520, 400);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void setNewUsername(MouseEvent mouseEvent) throws Exception {
        String username = this.username.getText();
        if (Player.getPlayerByUsername(username) != null)
            showAlert(Alert.AlertType.ERROR, "Change Error!", "A user with this username already exists!");
        else if (username.isEmpty())
            showAlert(Alert.AlertType.ERROR, "Change Error!", "New username is empty!");
        else {
            Player.getCurrentPlayer().setUsername(username);
            SaveAndLoadData.SaveToJson(Player.players, DataEnumFile.PLAYERS.getFileName());
            showAlert(Alert.AlertType.INFORMATION, "Change Successful!", "Your username successfully changed!");
            start(ControllerControllersG.stage);
        }
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void enterChangePassword(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(LoginMenuG.class.getResource("/fxml/changePassword.fxml"));
        Scene scene = new Scene(anchorPane, 520, 400);

        stage.setScene(scene);
        stage.show();
    }
    public void enterChangeEmail(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(LoginMenuG.class.getResource("/fxml/changePassword.fxml"));
        Scene scene = new Scene(anchorPane, 520, 400);

        stage.setScene(scene);
        stage.show();
    }
    public void enterChangeNickname(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(LoginMenuG.class.getResource("/fxml/changePassword.fxml"));
        Scene scene = new Scene(anchorPane, 520, 400);

        stage.setScene(scene);
        stage.show();
    }
    public void enterChangeSlogan(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(LoginMenuG.class.getResource("/fxml/changePassword.fxml"));
        Scene scene = new Scene(anchorPane, 520, 400);

        stage.setScene(scene);
        stage.show();
    }

    public void setNewPassword(MouseEvent mouseEvent) throws Exception {
        String newPassword = this.password.getText();
        if (newPassword.isEmpty())
            showAlert(Alert.AlertType.ERROR, "Change Error!", "Password is empty!");
        else {
            Player player = Player.getCurrentPlayer();
            player.setPassword(newPassword);
            SaveAndLoadData.SaveToJson(Player.players, DataEnumFile.PLAYERS.getFileName());
            showAlert(Alert.AlertType.INFORMATION, "Change Successful!", "Your password successfully changed!");
            start(stage);
        }

    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        Player.setCurrentPlayer(null);
        new LoginMenuG().start(stage);
    }

    public void deleteAccount(MouseEvent mouseEvent) throws Exception {
        Player player = Player.getCurrentPlayer();
        Player.setCurrentPlayer(null);
        Player.getPlayers().remove(player);
        showAlert(Alert.AlertType.INFORMATION, "Delete Account", "Your account successfully deleted!");
        new LoginMenuG().start(stage);
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenuG().start(stage);
    }

    public void enterChooseAvatar(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(LoginMenuG.class.getResource("/fxml/avatar.fxml"));
        myAvatar = (ImageView) anchorPane.lookup("#myAvatar");
        if (Player.getCurrentPlayer().getAvatarResource() != null)
            myAvatar.setImage(new Image(Player.getCurrentPlayer().getAvatarResource()));
        FileChooser fileChooser = new FileChooser();
        Button chooseFile = new Button("Choose your photo");
        chooseFile.setLayoutX(10);
        chooseFile.setLayoutY(360);
        chooseFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                File selectedFile = fileChooser.showOpenDialog(stage);
                try {
                    setAvatar(selectedFile);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        anchorPane.getChildren().add(chooseFile);
        Scene scene = new Scene(anchorPane, 520, 400);

        stage.setScene(scene);
        stage.show();
    }

    private void setAvatar(File selectedFile) throws Exception {
        String url = selectedFile.getAbsolutePath();
        Player player = Player.getCurrentPlayer();
        player.setAvatarResource(url);
        SaveAndLoadData.SaveToJson(Player.players, DataEnumFile.PLAYERS.getFileName());
        start(stage);
    }

    public void chooseAvatar(MouseEvent mouseEvent) throws Exception {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        Player player = Player.getCurrentPlayer();
        player.setAvatarResource(imageView.getImage().getUrl());
        SaveAndLoadData.SaveToJson(Player.players, DataEnumFile.PLAYERS.getFileName());
        start(stage);
    }
}
