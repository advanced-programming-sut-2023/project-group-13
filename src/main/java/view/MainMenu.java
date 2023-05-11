package view;

import controller.ControllerControllers;
import model.Enums.MainMenuCommands;

import java.util.regex.Matcher;

public class MainMenu {
    private ControllerControllers controllerControllers;
    private Matcher matcher;

    public MainMenu(ControllerControllers controllerControllers) {
        this.controllerControllers = controllerControllers;
    }

    public String run() {
        System.out.println("you are in main menu");
        String command;
        while(true) {
           command = ScannerMatcher.getScanner().nextLine();
            if ((matcher = ScannerMatcher.getMatcher(command, MainMenuCommands.WHICHMENU.getPattern())) != null) {
//                if (matcher.group("Menu").matches("\\s*login\\s+menu\\s*")) {
//                    System.out.println("entered login menu!");
//                    return "login menu";
//                    // todo can we go from here to login menu
//                    // i think no we can't
//                }
//                if (matcher.group("Menu").matches("\\s*sign up\\s+menu\\s*")) {
//                    System.out.println("entered sign up menu!");
//                    return "sign up menu";
//                }
                if (matcher.group("Menu").matches("\\s*profile\\s+menu\\s*")) {
                    System.out.println("entered profile menu!");
                    return "profile menu";
                } else if (matcher.group("Menu").matches("\\s*map\\s+menu\\s*")) {
                    System.out.println("entered map menu!");
                    return "map menu";
                }
                else System.out.println("enter menu failed : invalid menu name!");
                // todo complete MainMenu
            } else if (command.matches("^\\s*logout\\s*$")) {
                // todo do the work to logout the current user
                return "logout";
            }
            else System.out.println("Main menu: invalid command!");
        }

    }

}
