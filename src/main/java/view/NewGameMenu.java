package view;

import model.Enums.NewGameCommands;

import java.util.regex.Matcher;

public class NewGameMenu {
    private Matcher matcher;
    public void run() {
        String command;
        System.out.println("select map:\t\t select map -n <mapName>\n" +
                "select players:\t\t select player -n1 <name> -n2 <name>...\n");
        while (true) {
            command = ScannerMatcher.getScanner().nextLine();
            if ((matcher = ScannerMatcher.getMatcher(command, NewGameCommands.SELECTMAP.getPattern())) != null) {
                // todo complete the controller
            } else if ((matcher = ScannerMatcher.getMatcher(command, NewGameCommands.SELECTPLAYERS.getPattern())) != null) {
                // todo complete the contoller
            } else if (command.matches("^back$")) {
                return;
                // todo to check wether this return is true or wrong
            } else System.out.println("NewGameMenu: invalid command");
        }
    }
}
