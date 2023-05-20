package model.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    LOGIN("(?=.* -u (?<username>(\"[^\"]*\")|(\\S*)))(?=.* -p (?<password>(\"[^\"]*\")|(\\S*)))" +
            "^user login( -[up] ((\"[^\"]*\")|(\\S*))){2}(?<stayLoggedIn> --stay-logged-in)?$"),
    //create user -u amir -p Aa3#fsaf -c Aa3#fsaf -e email@gmail.com -n feri
    // question pick -q 1 -a hassan -c hassan
    //sample for signup
    FORGOT_PASSWORD("^forgot my password -u (?<username>.*)$"),
    DONT_HAVE_ACCOUNT("^I don't have account$"),
    CHANGE_PASSWORD_FROM_FORGOT_PASSWORD("-p (?<password>.*) -c (?<passwordConfirmation>.*"),
    ;
    String regex;
    LoginMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String command, LoginMenuCommands Regex) {
       return  Pattern.compile(Regex.regex).matcher(command);
    }
}
