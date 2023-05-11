package model.Enums;

public enum MainMenuCommands {
    WHICHMENU("^\\s*enter\\s+(?<Menu>.+)\\s*$");
//    LOGINMENU("\\s*login\\s+menu\\s*\""),
//    SIGNUPMENU("\\s*signup\\s+menu\\s*"),
//    PROFILEMENU("\\s*profile\\s+menu\\s*"),
//    MAPMENU("\\s*map\\s+menu\\s*");

    private String regex;
    private MainMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getPattern() {
        return regex;
    }

}
