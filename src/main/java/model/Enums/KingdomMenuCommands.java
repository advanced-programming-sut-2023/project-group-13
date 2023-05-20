package model.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum KingdomMenuCommands {
    SHOW_POPULARITY_FACTORS("^show popularity factors$"),
    SHOW_POPULARITY("^show popularity"),
    SHOW_FOOD_LIST("^show food list$"),
    FOOD_RATE("^food rate -r (?<rateNumber>-?\\d+)$"),
    FOOD_RATE_SHOW("^food rate show$"),
    TAX_RATE("^tax rate -r (?<rateNumber>-?\\d+)$"),
    TAX_RATE_SHOW("^tax rate show$"),
    FEAR_RATE("^fear rate -r (?<rateNumber>-?\\d+)$"),

    ;
    String regex;

    public String getRegex() {
        return regex;
    }

    KingdomMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String command, KingdomMenuCommands Regex) {
        return  Pattern.compile(Regex.regex).matcher(command);
    }
}
