package controller;

import model.Enums.DataEnumFile;
import model.Player;
import model.SaveAndLoadData;
import model.RandomsAndCaptcha;
import model.UserFunction;
import view.ScannerMatcher;

import java.util.Scanner;

public class ProfileMenuController {
    public static Scanner scanner = ScannerMatcher.getScanner();
    public static String showSlogan(Player player) {
        if (player.getSlogan() == null) return "slogan is empty.";
        return player.getSlogan();
    }

    static Player player = Player.getCurrentPlayer();

    public static String changeUsername(String username) {
        switch (UserFunction.CheckUsernameValidation(username)) {
            case 0:
                return "username filed is empty.";
            case 1:
                return "Invalid username format.";
            case 2:
                return "player with this username already exist.";
            case 3:
                player.setUsername(username);
                SaveAndLoadData.SaveToJson(Player.players,DataEnumFile.PLAYERS.getFileName());
                return "username successfully changed.";
            default:
                return "";
        }
    }

    public static String changePassword(String oldPassword , String newPassword , Player player) {
        if (!player.getPassword().equals(oldPassword)) return "password is wrong.";
        switch (UserFunction.CheckPasswordValidation(newPassword)) {
            case 0:
                return "new password filed is empty.";
            case 1:
                return "new password length is below 6.";
            case 2:
                return "weak password: Invalid new password format.";
            case 3:
                System.out.println("please re-enter your new password:");
                String confirmation = ScannerMatcher.getScanner().nextLine();
                if (confirmation.equals(newPassword)) {
//                    String[] a = RandomsAndCaptcha.captchaGenerator();
//                    for (int i = 0; i < a.length; i++) {             // print captcha
//                        System.out.print(a[i]);
//                    }
                    System.out.println("\nplease enter the captcha numbers.");
                    int answerNumber = ScannerMatcher.getScanner().nextInt();
                    String temp = ScannerMatcher.getScanner().nextLine();
                    if (answerNumber != (Integer.parseInt(RandomsAndCaptcha.getRealNumber()))) {
                        RandomsAndCaptcha.setRealNumber("");
                        return "answer doesn't match with captcha";
                    }
                    RandomsAndCaptcha.setRealNumber("");
                    player.setPassword(newPassword);
                    SaveAndLoadData.SaveToJson(Player.players, DataEnumFile.PLAYERS.getFileName());
                    return "password successfully changed.";
                }
                return "password and the confirmation doesn't match.";
            default:
                return "";
        }
    }

    public static String changeEmail(String email, Player player) {
        switch (UserFunction.CheckEmailValidation(email)) {
            case 0:
                return "email filed is empty.";
            case 1:
                return "Invalid email format.";
            case 2:
                player.setEmail(email);
                SaveAndLoadData.SaveToJson(Player.players,DataEnumFile.PLAYERS.getFileName());
                return "email successfully changed.";
            default:
                return "";
        }
    }

    public static String changeNickname(String nickname, Player player) {
        switch (UserFunction.CheckNicknameValidation(nickname)) {
            case 0:
                return "nickname filed is empty.";
            case 1:
                player.setNickname(nickname);
                SaveAndLoadData.SaveToJson(Player.players,DataEnumFile.PLAYERS.getFileName());
                return "nickname successfully changed.";
            default:
                return "";
        }
    }

    public static String changeSlogan(String nickname, Player player) {
        System.out.println(player.getSlogan());
        player.setSlogan(nickname);
        SaveAndLoadData.SaveToJson(Player.players,DataEnumFile.PLAYERS.getFileName());
        System.out.println(player.getSlogan());
        return "slogan successfully changed.";
    }

    public static String removeSlogan(Player player) {
        player.setSlogan("");
        SaveAndLoadData.SaveToJson(Player.players,DataEnumFile.PLAYERS.getFileName());
        return "slogan successfully removed.";
    }

    public static String showProfile(Player player) {

        return "username: " + player.getUsername() + "\npassword: " + player.getPassword() + "\nnickname: " +
                player.getNickname() + "\nslogan: " + player.getSlogan() + "\nemail: " + player.getEmail() +
                "\nsecurity question answer: " + player.getSecurityQuestionAnswer() + "\nplayer rank: " +
                player.getPlayerRank() + "\nhighScore: " + player.getHighScore();
    }

    public static String showHighScore(Player player) {
        return "high score: " + player.getHighScore();
    }

    public static String showRank(Player player) {
        return "rank: " + player.getPlayerRank();
    }
}

