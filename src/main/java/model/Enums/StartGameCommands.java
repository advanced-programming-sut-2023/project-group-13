package model.Enums;

public enum StartGameCommands {


    private String regex;

    StartGameCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
