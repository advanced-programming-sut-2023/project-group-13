package view.controller;

import javafx.scene.input.MouseEvent;
import view.menus.LoginMenuG;

public class SignupMenuController {
    public void loginMenu(MouseEvent mouseEvent) throws Exception {
        LoginMenuG loginMenuG = new LoginMenuG();
        loginMenuG.start(LoginMenuG.stage);
    }
}

