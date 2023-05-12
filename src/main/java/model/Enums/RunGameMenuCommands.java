package model.Enums;

public enum RunGameMenuCommands {
    STARTNEWGAME("^start a new game -n (?<mapName>\\S+)$"),
    LOADSAVEDGAME("^load a saved game -n (?<gameName>\\S+)$");

    private String regex;

    RunGameMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
