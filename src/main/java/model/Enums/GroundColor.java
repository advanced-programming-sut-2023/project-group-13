package model.Enums;

public enum GroundColor {
    YELLOW("\033[43m"),
    GREEN("\033[42m"),
    BRIGHTGREEN("\033[102m"),
    BLUE("\033[44m"),
    GRAY("\033[100m"),
    BRIGHTRED("\033[101m"),
    WHITE("\033[47m"),
    BROWN("\033[48;2;139;69;19m"),
    RESET("\033[0m"),
    GETCOLOR();

    private TypeofGround typeofGround;
    private String color;

    private GroundColor() {

    }
    private GroundColor(String color) {
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
        else if (TypeofGround.WATER.getFullNameType().equals(typeofground))
            return BLUE.getColor();
        else if (TypeofGround.STONEY.getFullNameType().equals(typeofground))
            return WHITE.getColor();
        else if (TypeofGround.SANDY.getFullNameType().equals(typeofground)) {
            return BROWN.getColor();
        }
        else return RESET.getColor();
    }
}
