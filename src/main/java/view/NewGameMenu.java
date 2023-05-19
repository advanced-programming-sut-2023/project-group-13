package view;

import controller.NewGameController;
import model.Enums.NewGameCommands;
import top.jfunc.json.impl.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;

public class NewGameMenu {
    private Matcher matcher;
    private GameMenu gameMenu = new GameMenu();
    private NewGameController newGameController = new NewGameController();
    public void run() throws IOException {
        System.out.println("type \"help\" to get more information about this menu");
        String command;
        while (true) {
            command = ScannerMatcher.getScanner().nextLine();
            if ((matcher = ScannerMatcher.getMatcher(command, NewGameCommands.SELECTMAP.getPattern())) != null) {
                System.out.println(newGameController.selectMap(matcher));
            } else if ((matcher = ScannerMatcher.getMatcher(command, NewGameCommands.SELECTPLAYERS.getPattern())) != null) {
                System.out.println(newGameController.Selectplayers(matcher));
            }
            else if (command.matches("^start a new game$")) {
                System.out.println(command = newGameController.gameStartCondition());
                if (command.equals("**game started successfully**"))
                    gameMenu.run();
                // todo in the game menu to put an exit command to come back to here
            } else if (command.matches("^deselect map$")) {
                System.out.println(newGameController.deselectMap());
            } else if (command.matches("^deselect players$")) {
                System.out.println(newGameController.deselectPlayers());
            } else if (command.matches("^help$")) {
                System.out.println("select map:\t\t select map -n <mapName>\n" +
                        "select players:\t\t select players -s <numberOfPlayers> -n1 <userName> -n2 <userName>...\n" +
                        "after selceting map and players:\t\t start a new game");
            } else if (command.matches("^back$"))
                return;
                // todo to check wether this return is true or wrong
            else System.out.println("NewGameMenu: invalid command");
        }
    }
}
