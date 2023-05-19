package model.Enums;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupMenuCommands {
    CREATE_USER("(?=.* -u (?<username>(\"[^\"]*\")|(\\S*)))(?=.* -e (?<email>(\"[^\"]*\")|(\\S*)))" +
            "(?=.* -n (?<nickname>(\"[^\"]*\")|(\\S*)))(?=.* -s (?<slogan>(\"[^\"]*\")|(\\S*)))?" +
            "(?=.* -p (?<password>(\"[^\"]*\")|(\\S*)))(?=.* -c (?<passwordConfirmation>(\"[^\"]*\")|(\\S*)))?" +
            "^create user( -[unpces] ((\"[^\"]*\")|(\\S*))){4,6}$"),
    //create user -u amir -p Aa3#fsaf -c Aa3#fsaf -e email@gmail.com -n feri
    //sample for signup
    SECURITY_QUESTION("Pick your security question:\n" +
            "1. What is my father’s name?\n" +
            "2. What was my first pet’s name?\n" +
            "3. What is my mother’s last name?"),
    QUESTION_PICK("^question pick -q (?<questionNumber>-?\\d) -a (?<answer>.*) -c (?<answerConfirm>.*)$"),

    CHECK_EMAIL("^[\\w_]+@[\\w_]+\\.[\\w_]{2,}$");
    ;

    String regex;
    SignupMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public static Matcher getMatcher(String command, SignupMenuCommands Regex) {
        Matcher matcher;
        matcher =  Pattern.compile(Regex.regex).matcher(command);
        if(matcher != null) return matcher;
        return  null;
    }

}
