package model.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {


    SELECT_UNIT("(?=.* -x (?<x>-?\\d+))(?=.* -y (?<y>-?\\d+))(?=.* -t (?<unitType>(\"[^\"]*\")|(\\S+)))" +
            "^select unit( -[xyt] ((\"[^\"]*\")|(\\S+))){2,3}$"),
    MOVE_UNIT("(?=.* -x (?<x>-?\\d+))(?=.* -y (?<y>-?\\d+))^move unit to( -[xy] -?\\d+){2}$"),
    PATROL_UNIT("(?=.* -x (?<x>-?\\d+))(?=.* -y (?<y>-?\\d+))^patrol unit( -[xy] \\d+){2}$"),
    SET_STATE("^set -x (?<x>-?\\d+) -y (?<y>-?\\d+) -s (?<s>\\S+)$"),
    ATTACK("^attack -e (?<xEnemy>-?\\d+) (?<yEnemy>-?\\d+)$"),
    POUR_OIL("^pour oil -d (?<direction>\\S+)$"),
    OPEN_TRADE_MENU("^open trade menu$"),
    OPEN_SHOP_MENU("^open shop menu$"),
    OPEN_SHOW_MAP_MENU("^open show map menu$"),
    STOP_PATROL("^stop patrol$"),

    ATTACK_ARCHER("(?=.* -x (?<x>-?\\d+))(?=.* -y (?<y>-?\\d+))^attack( -[xy] \\d+){2}$"),
    DIG_TUNNEL("(?=.* -x (?<x>-?\\d+))(?=.* -y (?<y>-?\\d+))^dig tunnel( -[xy] \\d+){2}$"),
    FILL_MOAT("fill moat -d (?<direction>\\S*)}$"),
    STOP("^stop$"),
    BUILD("^build -q (?<equipmentType>(\"[^\"]*\")|(\\S+))$"),
    DISBAND("^disband unit$"),

    ;
    final String regex;

    public String getRegex() {
        return regex;
    }

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, GameMenuCommands Regex) {
        return Pattern.compile(Regex.regex).matcher(command);
    }
}
