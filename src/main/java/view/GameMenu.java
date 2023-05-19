package view;

import controller.GameMenuController;
import model.Enums.GameMenuCommands;

import java.util.regex.Matcher;

public class GameMenu {
    private GameMenuController gameMenuController = new GameMenuController();
    public String run() {
        String command;
        Matcher matcher;
        while (true) {
            command = ScannerMatcher.getScanner().nextLine();
            if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SELECT_UNIT)).find()) {
                System.out.println(gameMenuController.selectUnit(matcher));

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.ATTACK)).find()) {
                System.out.println(gameMenuController.attack(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DIG_TUNNEL)).find()) {
                System.out.println(gameMenuController.digTunnel());
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.POUR_OIL)).find()) {
                System.out.println(gameMenuController.pourOil());
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DISBAND)).find()) {
                System.out.println(gameMenuController.disbandUnit());
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.MOVE_UNIT)).find()) {
                System.out.println(gameMenuController.moveUnit(matcher));

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.STOP)).find()) {
                System.out.println(gameMenuController.stop());
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.PATROL_UNIT)).find()) {
                System.out.println(gameMenuController.patrolUnit(matcher));

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.STOP_PATROL)).find()) {
                System.out.println(gameMenuController.stopPatrol());

            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SET_STATE)).find()) {
                System.out.println(gameMenuController.setUnitMode(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SHOWMAPCOORDINATED)).find()) {
                System.out.println(gameMenuController.showmap(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DIG_TUNNEL)).find()) {
                System.out.println(gameMenuController.showmap(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.FILL_MOAT)).find()) {
                System.out.println(gameMenuController.showmap(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.OPEN_TRADE_MENU)).find()) {
                System.out.println(gameMenuController.showmap(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.OPEN_SHOP_MENU)).find()) {
                System.out.println(gameMenuController.showmap(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.BUILD)).find()) {
                System.out.println(gameMenuController.siegeCreep(matcher.group("equipmentType")));
            }
            else if (command.matches("^exit game$")) {
                return "exit game";
            } else if (command.matches("^next turn$")) {
                System.out.println(gameMenuController.nextTurn());
            } else if (command.matches("^kingdom menu$")) {
                //goto kingdom menu
            } else System.out.println("Invalid command!");
        }
    }
}
