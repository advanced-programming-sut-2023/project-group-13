package model;


import model.Enums.SignupMenuCommands;

public class UserFunction {
    public static int CheckUsernameValidation(String username) {
        if (username.equals("")) return 0;
        if (!username.matches("[a-zA-Z0-9_]+")) return 1;
        if (Player.getPlayerByUsername(username) != null) return 2;
        return 3;
    }

//
//        if (existFlag(command, "n") && nickname.equals("")) return "nickname field is empty.";
//        if (existFlag(command, "e") && email.equals("")) return "email field is empty.";
//        if (existFlag(command, "c") && passwordConfirmation.equals("")) return "passwordConfirmation field is empty.";
//        if (existFlag(command, "p") && password.equals("")) return "password field is empty.";
//        if (existFlag(command, "s") && slogan.equals("")) return "slogan field is empty.";

    public static int CheckNicknameValidation(String nickname) {
        if (nickname.equals("")) return 0;
        return 1;
    }
    public static int CheckPasswordValidation(String password) {
        if (password.equals("")) return 0;
        if (password.length() < 6) return 1;
        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*") || !password.matches(".*\\W.*"))
           return 2;
           // return "weak password: password pattern is wrong";
        return 3;
    }
    public static int CheckEmailValidation(String email) {
        if (email.equals("")) return 0;
        if (!email.matches(SignupMenuCommands.CHECK_EMAIL.getRegex())) return 1;
        return 2;
    }

}
