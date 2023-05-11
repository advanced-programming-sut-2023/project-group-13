package view;

import controller.ControllerControllers;
import controller.LoginMenuController;
import model.Enums.LoginMenuCommands;
import model.Player;
import model.PlayerSaveAndLoadData;

import java.io.IOException;
import java.util.regex.Matcher;

public class LoginMenu {
    private ControllerControllers controllerControllers;

    public LoginMenu(ControllerControllers controllerControllers) {
        this.controllerControllers = controllerControllers;
    }

    private static Long y = 0L;
    final private static int delayTime = 5;

    public static int getDelayTime() {
        return delayTime;
    }

    public static int getNumberOfAttempt() {
        return numberOfAttempt;
    }

    public static void setNumberOfAttempt(int numberOfAttempt) {
        LoginMenu.numberOfAttempt = numberOfAttempt;
    }

    private static int numberOfAttempt = 0;

    public String run(SignupMenu signupMenu) throws InterruptedException, IOException {

        System.out.println("you are in login menu!");
        System.out.println("if you have account login\nif you don't have account type:\t\t" +
                "I don't have account");
        Matcher matcher;

        String command;

        while (true) {


            command = ScannerMatcher.getScanner().nextLine();

            if (command.matches("^exit$")) {
                return "exit";
            } else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.LOGIN)).find()) {
                String username = matcher.group("username");
                String password = matcher.group("password");
                Player player = Player.getPlayerByUsername(username);
                if (player == null) {
                    System.out.println("no user with this username exist.");
                } else if (!player.getPassword().equals(password)) {
                    y = System.currentTimeMillis();
                    int x = LoginMenu.getNumberOfAttempt();
                    x++;
                    LoginMenu.setNumberOfAttempt(x);
                    System.out.println("Username and password didn't match!\n you must wait " + LoginMenu.getDelayTime()
                            * LoginMenu.getNumberOfAttempt() +
                            " seconds for next try, please dont enter any command.");
                    Thread.sleep((LoginMenu.getDelayTime() * LoginMenu.getNumberOfAttempt() * 1000L));

                } else {
                    //go to main menu
                    if (matcher.group("stayLoggedIn") != null) {
                        player.setLoggedIn(true);
                        for (Player temp : Player.players) {
                            if (temp != player) temp.setLoggedIn(false);
                        }
                        PlayerSaveAndLoadData.SaveToJson(Player.players);
                    }
                    Player.setCurrentPlayer(player);
                    LoginMenu.setNumberOfAttempt(0);

                    System.out.println("login player successful!");
                    return "logged in";
//                    return "main menu";
                }
            } else if ((matcher =  LoginMenuCommands.getMatcher(command, LoginMenuCommands.DONT_HAVE_ACCOUNT)).find()) {
                //go to signup menu
                System.out.println("going to sign up menu");
                signupMenu.run();
            }
            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.FORGOT_PASSWORD)).find()) {
                System.out.println(LoginMenuController.forgotPassword(matcher));
            }
            else
                System.out.println("login menu: invalid command!");
        }
    }

    private boolean commandValidation(long l) {
        return l / 1000L + (long) LoginMenu.getDelayTime() * LoginMenu.getNumberOfAttempt() <= System.currentTimeMillis();
    }
}