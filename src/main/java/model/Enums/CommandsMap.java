package model.Enums;

public enum CommandsMap {
    SHOWMAPCOORDINATED("^show map -x (?<x>-?\\d+) -y (?<y>-?\\d+)$"),
    SHOWMAP("^show map (?<sizeOfMap>-?\\d+)$"),
//    MOVEMAP("^map ((?<direction1>up|left|right|down) (?<number1>-?\\d+)? )?((?<direction2>up|left|right|down) " +
//            "(?<number2>-?\\d+)? )?((?<direction3>up|left|right|down) (?<number3>-?\\d+)? )?((?<direction4>up|left|right|down)" +
//            " (?<number4>-?\\d+)? )?$"),
//
    MOVEMAP("^map ((?<direction1>up|left|right|down)(?<number1> -?\\d+)?)?((?<direction2> up| left| right| down)(?<number2> -?\\d+)?)?((?<direction3> up| left| right| down)(?<number3> -?\\d+)?)?((?<direction4> up| left| right| down)(?<number4> -?\\d+)?)?$"),
    SHOWDETAIL("^show details -x (?<x>-?\\d+) -y (?<y>-?\\d+)$"),
    SETTEXTURESINGLEBLOCK("^settexture -x (?<x>-?\\d+) -y (?<y>-?\\d+) -t (?<type>\\S+)$"),
    SETTEXTUREAREA("^settexture -x1 (?<x1>-?\\d+) -y1 (?<y1>-?\\d+) -x2 (?<x2>-?\\d+) -y2 (?<y2>-?\\d+) -t " +
            "(?<type>\\S+)$"),
    CLEARGROUND("^clear -x (?<x>-?\\d+) -y (?<y>-?\\d+)$"),
    DROPROCK("^droprock -x (?<x>-?\\d+) -y (?<y>-?\\d+) -d (?<direction>\\S+)$"),
    DROPTREE("^droptree -x (?<x>-?\\d+) -y (?<y>-?\\d+) -t (?<type>\\S+)$"),
    DROPBUILDING("^dropbuilding -x (?<x>-?\\d+) -y (?<y>-?\\d+) -t (?<type>.*)$"),

    DROPUNIT("^dropunit -x (?<x>-?\\d+) -y (?<y>-?\\d+) -t (?<type>\\S+) -c (?<count>-?\\d+)$"),
    CREATENEWMAP("create new map -n (?<mapName>\\S+) -s (?<size>-?\\d+)"),
    LOADMAP("load map -n (?<mapName>\\S+)");

    private String regex;

    private CommandsMap(String regex) {
        this.regex = regex;
    }

    public String getPattern() {
        return regex;
    }
}
