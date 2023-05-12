package view;

import controller.ControllerControllers;
import model.Enums.RunGameMenuCommands;

import java.io.IOException;
import java.util.regex.Matcher;

public class RunGameMenu {

        private ControllerControllers controllerControllers;
        private Matcher matcher;
        private MainMenu mainMenu;

        public RunGameMenu(ControllerControllers controllerControllers) {
            this.controllerControllers = controllerControllers;
        }

        public void run(MainMenu mainMenu) throws IOException, InterruptedException {
            String command;
            System.out.println("if you want to start a new game:\t\ttype \"start a new game -n mapname\"\n" +
                    "if you want to load a saved game:\t\ttype \"load a saved game -n name\"\n" +
                    "if you want to go to the main menu:\t\ttype \"back\"" +
                    "if you want to see the available maps:\t\t \"show all maps\"" +
                    "if you want to see the availabe players:\t\t \"show all players\"");

            while (true) {
                command = ScannerMatcher.getScanner().nextLine();
                if ((matcher = ScannerMatcher.getMatcher(command, RunGameMenuCommands.STARTNEWGAME.getRegex())) != null) {
                    // todo complete
                }
                else if ((matcher = ScannerMatcher.getMatcher(command, RunGameMenuCommands.LOADSAVEDGAME.getRegex())) != null) {
                    // todo complete
                } else if (command.matches("\\s*show\\s+all\\s+maps\\s*")) {
                    // todo complete
                } else if (command.matches("\\s*show\\s+all\\s+players\\s*")) {
                    // todo complete
                }
            }
        }
}
