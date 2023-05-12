package controller;

import model.Enums.LoginMenuCommands;
import model.Player;
import model.SaveAndLoadData;
import model.RandomsAndCaptcha;
import view.ScannerMatcher;

import java.util.regex.Matcher;

public class LoginMenuController {




    public static String forgotPassword(Matcher matcher) {
        String username = matcher.group("username");
        Player player = Player.getPlayerByUsername(username);
        if (player == null) return "no user with this username exist.";
        System.out.println(Player.securityQuestions[player.getQuestionNumber()-1]);
        String answer = ScannerMatcher.getScanner().nextLine();
        if (!answer.equals(player.getSecurityQuestionAnswer())) return "answer is wrong";
        //go to main menu
        System.out.println("enter your new password like this with confirmation:-p abc -c abc");
        answer = ScannerMatcher.getScanner().nextLine();
        if (!(matcher = LoginMenuCommands.getMatcher(answer , LoginMenuCommands.CHANGE_PASSWORD_FROM_FORGOT_PASSWORD)).find()) {
            String password = matcher.group("password");
            String passwordConfirmation = matcher.group("passwordConfirmation");
            if (!passwordConfirmation.equals(password)) return "password and password confirmation doesn't match.";

            String[] a = RandomsAndCaptcha.captchaGenerator();
            for (int i = 0; i < a.length; i++) {             // print captcha
                System.out.print(a[i]);
            }
            System.out.println("\nplease enter the captcha numbers.");
            int answerNumber = ScannerMatcher.getScanner().nextInt();
            String temp = ScannerMatcher.getScanner().nextLine();
            if (answerNumber != (Integer.parseInt(RandomsAndCaptcha.getRealNumber()))) {
                RandomsAndCaptcha.setRealNumber("");
                return "answer doesn't match with captcha";
            }
            RandomsAndCaptcha.setRealNumber("");
            player.setPassword(password);
            SaveAndLoadData.SaveToJson(Player.players,"players.json");
            // go to main menu
        }

        return "";
    }
}
