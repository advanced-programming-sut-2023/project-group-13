package model.Enums;

import view.NewGameMenu;

public enum NewGameCommands {
    SELECTMAP("^select map -n (?<mapName>\\S+)$"),
    SELECTPLAYERS("(?=.* -n1 (?<player1>\\S+))" +
            "(?=.* -n2 (?<player2>\\S+))(?=.* -n3 (?<player3>\\S+))?" +
            "(?=.* -n4 (?<player4>\\S+))?" +
            "(?=.* -n5 (?<player5>\\S+))?" +
            "(?=.* -n6 (?<player6>\\S+))?" +
            "(?=.* -n7 (?<player7>\\S+))?" +
            "(?=.* -n8 (?<player8>\\S+))?^select players -s (?<size>-?\\d+)( -n[12345678] \\S+){2,8}$");

    private String regex;
    private NewGameCommands (String regex) {
        this.regex = regex;
    }

    public String getPattern() {
        return regex;
    }
}
