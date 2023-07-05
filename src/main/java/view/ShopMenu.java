package view;

import controller.SignupMenuController;
import model.Enums.ShopMenuCommands;
import model.Enums.SignupMenuCommands;

import java.util.regex.Matcher;

public class ShopMenu {
    public void run() {
        System.out.println("you are in shop menu");
        Matcher matcher;
        String command;
        String result;
        while (true) {
            command = ScannerMatcher.getScanner().nextLine();

            if (command.matches("^back$")) {
                System.out.println("you are in the login menu!");
                return;
            } else if ((matcher = ShopMenuCommands.getMatcher(command , ShopMenuCommands.SELL_ITEM)).find()) {

            } else if ((matcher = ShopMenuCommands.getMatcher(command , ShopMenuCommands.BUY_ITEM)).find()) {

            } else if ((matcher = ShopMenuCommands.getMatcher(command , ShopMenuCommands.SHOW_PRICE_LIST)).find()) {

            }
              else
                System.out.println("shop menu: invalid command!");

        }

    }
}
