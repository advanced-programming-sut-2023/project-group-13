package view;

import controller.ControllerControllers;

import java.util.regex.Matcher;

public class StartGameMenu{
    private ControllerControllers controllerControllers;
    private Matcher matcher;
    private MainMenu mainMenu;

    public StartGameMenu(ControllerControllers controllerControllers) {
        this.controllerControllers = controllerControllers;
    }

    public void run(MainMenu mainMenu) {
        int command;
        System.out.println("if you want to start a new game:\t\ttype \"1\"\n" +
                "if you want to load a saved game:\t\ttype \"2\"\n" +
                "if you want to go to the main menu:\t\ttype \"3\"");

        while (true) {
            command = ScannerMatcher.getScanner().nextInt();
            if (command == 1) {
                // todo to complete the controller of this part
            }
            if (command == 2) {
                // todo to complete the controller of this part
            }
            if (command == 3) {
                System.out.println("you entered main menu!");
                mainMenu.run();
            }
        }
    }
}
