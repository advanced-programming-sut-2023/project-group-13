package controller;

import model.Enums.SignupMenuCommands;
import model.Player;
import model.PlayerSaveAndLoadData;
import model.RandomsAndCaptcha;
import view.ScannerMatcher;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupMenuController {

    public static String signup(Matcher matcher, String command) {
        Scanner scanner = ScannerMatcher.getScanner();
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirmation = matcher.group("passwordConfirmation");
        String slogan = "";
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");
        int emailCheck = 0;

        if (matcher.group("slogan") != null) {
            slogan = matcher.group("slogan");
        }

        if (existFlag(command, "u") && username.equals("")) return "username field is empty.";
        if (existFlag(command, "n") && nickname.equals("")) return "nickname field is empty.";
        if (existFlag(command, "e") && email.equals("")) return "email field is empty.";

        if (existFlag(command, "c") && passwordConfirmation.equals("")) return "passwordConfirmation field is empty.";
        if (existFlag(command, "p") && password.equals("")) return "password field is empty.";
        if (existFlag(command, "s") && slogan.equals("")) return "slogan field is empty.";
        if (slogan.equals("random")) slogan = RandomsAndCaptcha.randomSloganGenerator();
        // handel "
        if (username.contains("\"")) username = username.replaceAll("\"", "");
        if (password.contains("\"")) password = password.replaceAll("\"", "");
        if (nickname.contains("\""))  nickname = nickname.replaceAll("\"", "");
        if (email.contains("\"")) email = email.replaceAll("\"", "");
        if (passwordConfirmation != null)
            if (passwordConfirmation.contains("\""))
                passwordConfirmation = passwordConfirmation.replaceAll("\"", "");
        if (slogan.contains("\"")) slogan = slogan.replaceAll("\"", "");

        if (!username.matches("[a-zA-Z0-9_]+"))
            return "Invalid username format.";
        if (Player.getPlayerByUsername(username) != null)
            return "player with this username exist, please try something different.";
        if (password.equals("random")) {
            String x = RandomsAndCaptcha.passwordGenerator();
            System.out.println("your random password is: " + x + "\nplease re-enter your password here:");
            String y = scanner.nextLine();
            if (!y.equals(x)) return "random password and your entered password didn't match.";
            password = x;
            passwordConfirmation =y;
        }
        if (password.length() < 6)
            return "weak password: password length is below 6.";
        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*") || !password.matches(".*\\W.*"))
            return "weak password: password pattern is wrong";
        if (!password.equals(passwordConfirmation)) return "password and confirmation are not the same.";
            for (Player player : Player.players) {
                if (player.getEmail().equals(email)) emailCheck = 1;
            }
        if (emailCheck == 1) return "this email already used.";
        if (SignupMenuCommands.getMatcher(email, SignupMenuCommands.CHECK_EMAIL) == null)
            return "Invalid email format.";

        System.out.println(SignupMenuCommands.SECURITY_QUESTION.getRegex());

        String questionPickAnswer = scanner.nextLine();
        if ((matcher = SignupMenuCommands.getMatcher(questionPickAnswer, SignupMenuCommands.QUESTION_PICK)).find()) {
            if (Integer.parseInt(matcher.group("questionNumber")) > 3 ||
                    Integer.parseInt(matcher.group("questionNumber")) < 1)
                return "please choose between 1 and 3.";


            if (matcher.group("answer").equals("") ||
                    matcher.group("answerConfirm").equals("") ||
                    matcher.group("questionNumber").equals("")) return "please fill all the fields";
            int questionNumber = Integer.parseInt(matcher.group("questionNumber")) - 1;
            String answer = matcher.group("answer");
            String answerConfirm = matcher.group("answerConfirm");

            if (!answerConfirm.equals(answer)) return "answer doesn't match with confirmation";

            String[] a = RandomsAndCaptcha.captchaGenerator();
            for (int i = 0; i < a.length; i++) {             // print captcha
                System.out.print(a[i]);
            }
            System.out.println("\nplease enter the captcha numbers.");
            int answerNumber = scanner.nextInt();
            String temp = scanner.nextLine();
            if (answerNumber != (Integer.parseInt(RandomsAndCaptcha.getRealNumber()))) {
                RandomsAndCaptcha.setRealNumber("");
                return "answer doesn't match with captcha";
            }
            RandomsAndCaptcha.setRealNumber("");
            Player player = new Player(username, password, slogan, email, nickname, questionNumber, answer);
            Player.players.add(player);
            PlayerSaveAndLoadData.SaveToJson(Player.players);
            return "user was successfully signed up";
        } else return "Invalid command!";


    }

    private static boolean existFlag(String command, String flag) {
        return Pattern.compile(" -" + flag + " ").matcher(command).find();
    }
}
