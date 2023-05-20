package model.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeMenuCommands {
        TRADE_REQUEST("(?=.* -t (?<resourceType>(\"[^\"]*\")|(\\S*)))(?=.* -a (?<resourceAmount>\\d*))"+
                              "(?=.* -p (?<price>\\d*))(?=.* -m (?<message>(\"[^\"]*\")|(\\S*)))^trade( -[tapm] ((\"[^\"]*\")|(\\S*))){4}$"),

        TRADE_LIST("^trade list$"),

        TRADE_ACCEPT("(?=.* -i (?<id>\\d*))(?=.* -m (?<message>(\"[^\"]*\")|(\\S*)))"+
                             "^trade accept( -[im] ((\"[^\"]*\")|(\\S*))){2}$"),

        TRADE_HISTORY("^trade history$"),
                ;
        String regex;



    TradeMenuCommands(String s) {
        this.regex = s;
    }

    public String getRegex() {
            return regex;
        }

        public static Matcher getMatcher(String command, TradeMenuCommands Regex) {
            Matcher matcher;
            matcher = Pattern.compile(Regex.regex).matcher(command);
            if (matcher != null) return matcher;
            return null;
        }



}
