package model;
import model.Enums.TypeofGround;

public enum groundColor {
    YELLOW("\033[43m"),
    BRIGHT_YELLOW("\033[103m"),
    GREEN("\033[42m"),
    BRIGHTGREEN("\033[102m"),
    DARKGREEN("\033[48;5;22m"),
    BLUE("\033[104m"),
    LIGHT_BLUE("\033[106m"),
    DARK_BLUE("\033[48;5;21m"),
    DARKER_BLUE("\033[44m"),
    GRAY("\033[100m"),
    BRIGHTRED("\033[101m"),
    WHITE("\033[47m"),
    BROWN("\033[48;2;139;69;19m"),
    DARK("\033[40m"),
    RESET("\033[0m"),
    GETCOLOR();
    private TypeofGround typeofGround;
    private String color;

    private groundColor() {

    }
    private groundColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
    public String getColorByName(String typeofground) {
        if (TypeofGround.EARTH.getFullNameType().equals(typeofground))
            return YELLOW.getColor();
        else if (TypeofGround.GRASS.getFullNameType().equals(typeofground))
            return BRIGHTGREEN.getColor();
        else if (TypeofGround.MEADOW.getFullNameType().equals(typeofground))
            return BRIGHTGREEN.getColor();
        else if (TypeofGround.DENSEMEADOW.getFullNameType().equals(typeofground))
            return GREEN.getColor();
        else if (TypeofGround.IRON.getFullNameType().equals(typeofground))
            return BRIGHTRED.getColor();
        else if (TypeofGround.ROCKY.getFullNameType().equals(typeofground))
            return GRAY.getColor();
        else if (TypeofGround.SEA.getFullNameType().equals(typeofground))
            return BLUE.getColor();
        else if (TypeofGround.STONEY.getFullNameType().equals(typeofground))
            return WHITE.getColor();
        else if (TypeofGround.SANDY.getFullNameType().equals(typeofground)) {
            return BROWN.getColor();
        } else if (TypeofGround.LITTLEPOOL.getFullNameType().equals(typeofground)) {
            return BLUE.getColor();
        } else if (TypeofGround.BIGPOOL.getFullNameType().equals(typeofground)) {
            return DARKER_BLUE.getColor();
        } else if (TypeofGround.BEACH.getFullNameType().equals(typeofground)) {
            return BRIGHT_YELLOW.getColor();
        } else if (TypeofGround.LOWDEPTHWATER.getFullNameType().equals(typeofground)) {
            return LIGHT_BLUE.getColor();
        } else if (TypeofGround.OIL.getFullNameType().equals(typeofground)) {
            return DARK.getColor();
        } else if (TypeofGround.PLAIN.getFullNameType().equals(typeofground)) {
            return DARKGREEN.getColor();
        } else if (TypeofGround.RIVER.getFullNameType().equals(typeofground)) {
            return BLUE.getColor();
        } else {
            return RESET.getColor();
        }
    }
}
