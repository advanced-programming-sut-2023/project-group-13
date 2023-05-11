package model;

public enum TypeofGround {
    EARTH("Earth","E"),
    SANDY("Sandy","Sa"),
    ROCKY("Rocky","R"),
    STONEY("Stoney","St"),
    IRON("Iron","I"),
    GRASS("Grass","G"),
    MEADOW("Meadow","M"),
    DENSEMEADOW("DenseMeadow","DS"),
    SEA("Sea","S"),
    LITTLEPOOL("Littlepool","LP"),
    BIGPOOL("Bigpool","BP"),
    RIVER("River","RI"),
    BEACH("Beach","Be"),
    LOWDEPTHWATER("Lowdepthwater","LDW"),
    OIL("OIL","O"),
    PLAIN("PLAIN","P");


    private String fullNameType;
    private String abbrevaiteNameType;
    private TypeofGround(String fullNameType,String abbreviateNameType) {
        this.abbrevaiteNameType = abbreviateNameType;
        this.fullNameType = fullNameType;
    }

    public String getFullNameType() {
        return fullNameType;
    }

    public String getAbbrevaiteNameType() {
        return abbrevaiteNameType;
    }
}
