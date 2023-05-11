package controller;

import model.Player;
import model.UserFunction;

public class ProfileMenuController {
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
                return "username successfully changed.";
            default:
                return "";
        }
    }

    public static String changePassword(String password, String passwordConfirmation, Player player) {
        switch (UserFunction.CheckPasswordValidation(password)) {
            case 0:
                return "password filed is empty.";
            case 1:
                return "password length is below 6.";
            case 2:
                return "weak password: Invalid password format.";
            case 3:
                if (passwordConfirmation.equals(password)) {
                    player.setPassword(password);
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
                player.setEmail(nickname);
                return "nickname successfully changed.";
            default:
                return "";
        }
    }

    public static String changeSlogan(String nickname, Player player) {
        player.setSlogan(nickname);
        return "slogan successfully changed.";
    }

    public static String removeSlogan(Player player) {
        player.setSlogan("");
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
