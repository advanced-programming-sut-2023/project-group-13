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
    WATER("Water","WT");
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
