package controller;

import com.google.gson.reflect.TypeToken;
import model.Enums.DataEnumFile;
import model.Player;
import model.SaveAndLoadData;
import view.*;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerControllers {
    private final LoginMenu loginMenu;
    private final MainMenu mainMenu;
    private final SignupMenu signupMenu;
    private final MapMenu mapMenu;
    private final ProfileMenu profileMenu;
    private final RunGameMenu RunGameMenu;
    private final MapMenuController mapMenuController;
    public ControllerControllers() {
        loginMenu = new LoginMenu(this);
        mainMenu = new MainMenu(this);
        signupMenu = new SignupMenu(this);
        profileMenu = new ProfileMenu(this);
        mapMenu = new MapMenu(this);
        mapMenuController = new MapMenuController(this);
        mapMenu.setMapMenuController(mapMenuController);
        RunGameMenu = new RunGameMenu(this);
    }
    public void run() throws InterruptedException, IOException {
        Player.players = SaveAndLoadData.LoadData(DataEnumFile.PLAYERS.getFileName(),
                DataEnumFile.PLAYERS.getDataType());
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
                if (loginMenu.run(signupMenu).equals("exit")) {
                    return;
                }
            } catch (InterruptedException e) {
                System.out.println("an error occured!");
            }
        }
        runMainMenu();
    }

    public void runMainMenu() throws IOException, InterruptedException {
        while (true) {
            switch (mainMenu.run()) {
                case "profile menu":
                    profileMenu.run(mainMenu);
                    break;
                case "logout":
                    if (loginMenu.run(signupMenu).equals("exit"))
                        System.exit(0);
                    //todo to make here nicer
                    break;
                case "map menu":
                    mapMenu.run(mainMenu);
                    break;
                case "start game":
                    RunGameMenu.run(mainMenu);
                    break;
            }
        }
    }
}
