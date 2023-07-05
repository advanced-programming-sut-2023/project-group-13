package view.controller;

import controller.ControllerControllers;
import controller.ControllerControllersG;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Enums.DataEnumFile;
import model.Player;
import model.SaveAndLoadData;
import view.menus.ForgotPasswordG;
import view.menus.MainMenuG;
import view.menus.SignupMenuG;

public class LoginMenuController {

    @FXML
    private TextField u1;
    @FXML
    private PasswordField p1;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelPassword;
    @FXML
    private CheckBox c1;
    private Player player;
    public void forgotPassword(MouseEvent mouseEvent) throws Exception {
        new ForgotPasswordG().start(ControllerControllersG.stage);
    }


    public void dontHaveAccount(MouseEvent mouseEvent) throws Exception {
        new SignupMenuG().start(ControllerControllersG.stage);
    }

    public void submit(MouseEvent mouseEvent) throws Exception {
        player = Player.getPlayerByUsername(u1.getText());
        if (player != null && p1.getText().equals(player.getPassword())) {
            if (c1.isSelected()) {
                player.setLoggedIn(true);
                Player.setCurrentPlayer(player);
                SaveAndLoadData.SaveToJson(Player.players, DataEnumFile.PLAYERS.getFileName());
                new MainMenuG().start(ControllerControllersG.stage);
            } else {
                Player.setCurrentPlayer(player);
                new MainMenuG().start(ControllerControllersG.stage);
            }
        }

        else {
            u1.setText("");
            p1.setText("");
            c1.setSelected(false);
        }
    }
}
