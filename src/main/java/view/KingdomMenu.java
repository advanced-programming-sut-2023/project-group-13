package view;

import controller.KingdomMenuController;
import model.Enums.KingdomMenuCommands;

import java.util.regex.Matcher;

public class KingdomMenu {
    public String run() {
        String command;
        Matcher matcher;
        while (true) {
            command = ScannerMatcher.getScanner().nextLine();
            if ((matcher = KingdomMenuCommands.getMatcher(command, KingdomMenuCommands.FEAR_RATE)).find()) {
                KingdomMenuController.setFearRate(matcher);

            }else if ((matcher = KingdomMenuCommands.getMatcher(command, KingdomMenuCommands.FOOD_RATE)).find()) {
                KingdomMenuController.setFoodRate(matcher);

            }else if ((matcher = KingdomMenuCommands.getMatcher(command, KingdomMenuCommands.FOOD_RATE_SHOW)).find()) {
                System.out.println(KingdomMenuController.showFoodRate());
            }else if ((matcher = KingdomMenuCommands.getMatcher(command, KingdomMenuCommands.TAX_RATE)).find()) {
                KingdomMenuController.setTaxRate(matcher);
            }else if ((matcher = KingdomMenuCommands.getMatcher(command, KingdomMenuCommands.TAX_RATE_SHOW)).find()) {
                System.out.println(KingdomMenuController.showTaxRate());
            }else if ((matcher = KingdomMenuCommands.getMatcher(command, KingdomMenuCommands.SHOW_POPULARITY)).find()) {
                System.out.println(KingdomMenuController.showPopularity());

            }else if ((matcher = KingdomMenuCommands.getMatcher(command, KingdomMenuCommands.SHOW_POPULARITY_FACTORS)).find()) {
                System.out.println(KingdomMenuController.showPopularityFactors());
            }else if ((matcher = KingdomMenuCommands.getMatcher(command, KingdomMenuCommands.SHOW_FOOD_LIST)).find()) {
                System.out.println(KingdomMenuController.showFoodList());
            }else if (command.matches("^back$")) {
                return "back";
            }
            else System.out.println("Invalid command!");
        }
    }
}
