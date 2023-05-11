package view;

import controller.ControllerControllers;
import controller.ProfileMenuController;
import model.Enums.ProfileMenuCommands;
import model.Player;

import java.util.regex.Matcher;

public class ProfileMenu {
    private MainMenu mainMenu;
    private ControllerControllers controllerControllers;

    public ProfileMenu(ControllerControllers controllerControllers) {
        this.controllerControllers = controllerControllers;
    }
    public String run(MainMenu mainMenu) {
        System.out.println("you are in profile menu!");
        while (true) {
            Matcher matcher;
            Player player = Player.getCurrentPlayer();
            String command = ScannerMatcher.getScanner().nextLine();
            if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_USERNAME)).find()) {
                System.out.println(ProfileMenuController.changeUsername(matcher.group("username")));
            } else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_NICKNAME)).find()) {
                System.out.println(ProfileMenuController.changeNickname(matcher.group("nickname"), player));
            } else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_PASSWORD)).find()) {
                System.out.println(ProfileMenuController.changePassword(matcher.group("password"), matcher.group("passwordConfirmation"), player));
            } else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_EMAIL)).find()) {
                System.out.println(ProfileMenuController.changeEmail(matcher.group("email"), player));
            } else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_SLOGAN)).find()) {
                System.out.println(ProfileMenuController.changeSlogan(matcher.group("slogan"), player));
            } else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.REMOVE_SLOGAN)).find()) {
                System.out.println(ProfileMenuController.removeSlogan(player));
            } else if (ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.SHOW_HIGH_SCORE).find()) {
                System.out.println(ProfileMenuController.showHighScore(player));
            } else if (ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.SHOW_RANK).find()) {
                System.out.println(ProfileMenuController.showRank(player));
            } else if (ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.SHOW_SLOGAN).find()) {
                System.out.println(ProfileMenuController.showSlogan(player));
            } else if (ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.SHOW_PROFILE).find()) {
                System.out.println(ProfileMenuController.showProfile(player));
            } else if (command.matches("^back$")) {
                mainMenu.run();
            } else {
                System.out.println("profile menu: Invalid command");
            }
        }
    }
}
