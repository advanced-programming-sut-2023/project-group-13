package controller;

import javafx.stage.Stage;
import model.Enums.DataEnumFile;
import model.Player;
import model.SaveAndLoadData;
import view.menus.LoginMenuG;
import view.menus.MainMenuG;

import java.io.File;
import java.util.ArrayList;

public class ControllerControllersG {
    public static Stage stage = new Stage();
    public ControllerControllersG() {
    }


    public void run() throws Exception {
        File file = new File(DataEnumFile.PLAYERS.getFileName());
        if (file.exists()) {
            Player.players = SaveAndLoadData.LoadData(DataEnumFile.PLAYERS.getFileName(),
                    DataEnumFile.PLAYERS.getDataType());
        }
        if (Player.players == null) {
            Player.players = new ArrayList<>();
        }
        // add this part for null pointer exception
//        if (Player.players != null) {
        for (Player temp : Player.players) {
            if (temp.getLoggedIn()) Player.setCurrentPlayer(temp);
        }
//        }

        String command;
        if (Player.getCurrentPlayer() == null) {
            try {
                 LoginMenuG loginMenuG = new LoginMenuG();
               loginMenuG.start(stage);

            } catch (InterruptedException e) {
                System.out.println("an error occured!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else runMainMenu();
    }

    public void runMainMenu() throws Exception {
      MainMenuG mainMenuG = new MainMenuG();
      mainMenuG.start(stage);
    }
}
