package view;

import controller.ControllerControllers;
import controller.RunGameController;
import model.Enums.RunGameMenuCommands;

import java.io.IOException;
import java.util.regex.Matcher;

public class RunGameMenu {

        private ControllerControllers controllerControllers;
        private Matcher matcher;
        private MainMenu mainMenu;
        private NewGameMenu newGameMenu = new NewGameMenu();
//        private LoadGameMenu loadGameMenu;
        private RunGameController runGameController = new RunGameController();

        public RunGameMenu(ControllerControllers controllerControllers) {
            this.controllerControllers = controllerControllers;
        }

        public void run(MainMenu mainMenu) throws IOException, InterruptedException {
            String command;
            System.out.println("type \"help\" to get more information about this menu");
            while (true) {
                command = ScannerMatcher.getScanner().nextLine();
                if ((matcher = ScannerMatcher.getMatcher(command, RunGameMenuCommands.STARTNEWGAME.getRegex())) != null) {
                    System.out.println("entered new game menu");
                    newGameMenu.run();
                }
//                else if ((matcher = ScannerMatcher.getMatcher(command, RunGameMenuCommands.LOADSAVEDGAME.getRegex())) != null) {
//                    loadGameMenu.run();
//                }
                else if (command.matches("^\\s*show\\s+all\\s+maps\\s*$")) {
                    System.out.println(runGameController.ShowAllMaps());

                } else if (command.matches("^\\s*show\\s+all\\s+players\\s*$")) {
                    runGameController.showAllMembers();
                } else if (command.matches("^help$")) {
                    System.out.println("if you want to start a new game:\t\ttype \"start a new game\"\n" +
//                    "if you want to load a saved game:\t\ttype \"load a saved game -n name\"\n" +
                            "if you want to go to the main menu:\t\ttype \"back\"\n" +
                            "if you want to see the available maps:\t\t \"show all maps\"\n" +
                            "if you want to see the availabe players:\t\t \"show all players\"");
                } else if (command.matches("^back$")) {
                    controllerControllers.runMainMenu();
                } else {
                    System.out.println("invalid command!");
                }
            }
        }
}
