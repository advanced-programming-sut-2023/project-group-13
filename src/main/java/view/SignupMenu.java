package view;

import controller.ControllerControllers;
import controller.SignupMenuController;
import model.Enums.SignupMenuCommands;

import java.util.regex.Matcher;

public class SignupMenu {
    private ControllerControllers controllerControllers;

    public SignupMenu(ControllerControllers controllerControllers) {
        this.controllerControllers = controllerControllers;
    }

    public void run() {
        System.out.println("you are in sign up menu");
        Matcher matcher;
        String command;
        String result;
        while (true) {
            command = ScannerMatcher.getScanner().nextLine();

            if (command.matches("^back$")) {
                System.out.println("you are in the login menu!");
                return;
                //todo to understand whether we can exit from here or not
            } else if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find()) {
                result = SignupMenuController.signup(matcher, command);
                System.out.println(result);
                if (result.equals("user was successfully signed up")) {
                    System.out.println("you are in the login menu!");
                    return;
                }
            }
//            else if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find()) {
//                System.out.println(SignupMenuController.signup(matcher));
//            }
            else
                System.out.println("Sign up menu: invalid command!");

        }

    }
}
