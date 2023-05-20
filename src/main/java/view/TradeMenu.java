package view;

import controller.GameMenuController;
import controller.ReceiveCommodityController;
import controller.SignupMenuController;
import model.Enums.SignupMenuCommands;
import model.Enums.TradeMenuCommands;

import java.util.regex.Matcher;

public class TradeMenu {
    private ReceiveCommodityController receiveCommodityController = new ReceiveCommodityController();
    public void run() {
        System.out.println("you are in trade menu");
        Matcher matcher;
        String command;
        String result;
//        while (true) {
//            command = ScannerMatcher.getScanner().nextLine();
//
//            if (command.matches("^back$")) {
//                System.out.println("you are in the game menu!");
//                return;
//            } else if ((matcher = TradeMenuCommands.getMatcher(command , TradeMenuCommands.TRADE_HISTORY)).find()) {
//                receiveCommodityController.tradeHistory(GameMenuController.currentempire);
//            }else if ((matcher = TradeMenuCommands.getMatcher(command , TradeMenuCommands.TRADE_ACCEPT)).find()) {
//                receiveCommodityController.tradeAccept(,GameMenuController.currentempire,matcher.group("message"));
//            }else if ((matcher = TradeMenuCommands.getMatcher(command , TradeMenuCommands.TRADE_REQUEST)).find()) {
//                receiveCommodityController.sendRequest();
//
//            }else if ((matcher = TradeMenuCommands.getMatcher(command , TradeMenuCommands.TRADE_LIST)).find()) {
//
//            }
//
//            else
//                System.out.println("trade menu: invalid command!");
//
//        }
//
    }
}
