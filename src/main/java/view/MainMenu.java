package view;

import controller.ControllerControllers;
import controller.MainMenuController;
import model.Enums.DataEnumFile;
import model.Enums.MainMenuCommands;
import model.Player;
import model.SaveAndLoadData;

import java.util.regex.Matcher;

public class MainMenu {
    private ControllerControllers controllerControllers;
    private MainMenuController mainMenuController = new MainMenuController() ;
    private Matcher matcher;

    public MainMenu(ControllerControllers controllerControllers) {
        this.controllerControllers = controllerControllers;
    }

    public String run() {
        System.out.println("you are in main menu");
        System.out.println("type \"help\" to get more information about this menu");
        String command;
        while(true) {
           command = ScannerMatcher.getScanner().nextLine();
            if ((matcher = ScannerMatcher.getMatcher(command, MainMenuCommands.WHICHMENU.getPattern())) != null) {
//
                if (matcher.group("Menu").matches("\\s*profile\\s+menu\\s*")) {
                    System.out.println("entered profile menu!");
                    return "profile menu";
                } else if (matcher.group("Menu").matches("\\s*map\\s+menu\\s*")) {
                    System.out.println("entered map menu!");
                    return "map menu";
                }
                else System.out.println("enter menu failed : invalid menu name!");
                // todo complete MainMenu
            } else if (command.matches("^\\s*logout\\s*$")) {
                // todo do the work to logout the current user\
                Player.getCurrentPlayer().setLoggedIn(false);
                SaveAndLoadData.SaveToJson(Player.getPlayers(), DataEnumFile.PLAYERS.getFileName());
                return "logout";
            } else if (command.matches("^\\s*run\\s+a\\s+new\\s+game\\s*$")) {
                System.out.println("prepare to start a new game!");
                return "start game";

            } else if (command.matches("^\\s*show\\s+all\\s+menus\\s*")) {
                mainMenuController.showAllMenus();
            } else if (command.matches("^help$")) {
                System.out.println("type \"show all menus\" to see all the menus\n" +
                        "type logout to go back to the login menu\n" +
                        "in some menus by typing \"help\" you can get some guides");
            } else {
                System.out.println("Main menu: invalid command!");
            }
        }

    }

}
