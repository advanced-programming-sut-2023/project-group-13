package view;

import controller.GameMenuController;
import model.Enums.GameMenuCommands;

import java.util.regex.Matcher;

public class GameMenu {
    private GameMenuController gameMenuController = new GameMenuController();
    private KingdomMenu kingdomMenu = new KingdomMenu();
    public String run() {
        String command;
        Matcher matcher;
        while (true) {
            command = ScannerMatcher.getScanner().nextLine();
            if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SELECT_UNIT)).find()) {
                System.out.println(gameMenuController.selectUnit(matcher));

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.ATTACK)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DIG_TUNNEL)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.POUR_OIL)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DISBAND)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.MOVE_UNIT)).find()) {
                System.out.println(gameMenuController.moveUnit(matcher));

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.STOP)).find()) {
                System.out.println(gameMenuController.stop());
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.PATROL_UNIT)).find()) {
                System.out.println(gameMenuController.patrolUnit(matcher));

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.STOP_PATROL)).find()) {
                System.out.println(gameMenuController.stopPatrol());

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SET_STATE)).find()) {

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SHOWMAPCOORDINATED)).find()) {
                System.out.println(gameMenuController.showmap(matcher));
            } else if (command.matches("^exit game$")) {
                return "exit game";
            } else if (command.matches("^next turn$")) {
                System.out.println(GameMenuController.nextTurn());
            } else if (command.matches("^kingdom menu$")) {
                System.out.println("entering kingdom menu");
                kingdomMenu.run();
            } else System.out.println("Invalid command!");
        }
    }
}
