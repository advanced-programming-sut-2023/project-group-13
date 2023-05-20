package model.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopMenuCommands {

    SHOW_PRICE_LIST("^show price list$"),
    BUY_ITEM("(?=.* -i (?<itemName>(\"[^\"]*\")|(\\S*)))(?=.* -a (?<itemAmount>\\d*))" +
            "^buy( -[ia] ((\"[^\"]*\")|(\\S*))){2}$"),
    SELL_ITEM("(?=.* -i (?<itemName>(\"[^\"]*\")|(\\S*)))(?=.* -a (?<itemAmount>\\d*))" +
            "^sell( -[ia] ((\"[^\"]*\")|(\\S*))){2}$"),
    ;


String regex;

    ShopMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String command, ShopMenuCommands Regex) {
        Matcher matcher;
        matcher =  Pattern.compile(Regex.regex).matcher(command);
        if(matcher != null) return matcher;
        return  null;
    }


}
