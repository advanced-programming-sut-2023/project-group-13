package view;

import controller.GameMenuController;
import controller.KingdomMenuController;
import model.Enums.GameMenuCommands;

import java.util.regex.Matcher;

public class GameMenu {
    public String run() {
        String command;
        Matcher matcher;
        while (true) {
            command = ScannerMatcher.getScanner().nextLine();
            if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SELECT_UNIT)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.ATTACK)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DIG_TUNNEL)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.POUR_OIL)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DISBAND)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.MOVE_UNIT)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.STOP)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.STOP_PATROL)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.STOP_PATROL)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SET_STATE)).find()) {

            } else if (command.matches("^exit game$")) {
                return "exit game";
            } else if (command.matches("^next turn$")) {
                System.out.println(GameMenuController.nextTurn());
            } else if (command.matches("^kingdom menu$")) {
                //goto kingdom menu
            }
             else System.out.println("Invalid command!");
        }
    }
}
