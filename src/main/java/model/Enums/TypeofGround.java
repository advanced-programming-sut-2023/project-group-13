package model.Enums;

public enum TypeofGround {
    EARTH("Earth","E","/images/game/map/textures/earth.png"),
    //todo put the photo of Earth for another type of ground
    SANDY("Sandy","Sa","/images/game/map/textures/earthAndSand.png"),
    ROCKY("Rocky","R","/images/game/map/textures/rockTexture/0.png"),
    STONEY("Stoney","St","/images/game/map/textures/boulder/0.png"),
    IRON("Iron","I","/images/game/map/textures/ironTexture/0.png"),
    GRASS("Grass","G","/images/game/map/textures/grass/1.png"),
    MEADOW("Meadow","M","/images/game/map/textures/thickGrass/0.png"),
    DENSEMEADOW("DenseMeadow","DS","/images/game/map/textures/oasisGrass/1.png"),
    SEA("Sea","S","/images/game/map/textures/sea.png"),
    LITTLEPOOL("Littlepool","LP","/images/game/map/textures/smallPond.png"),
    BIGPOOL("Bigpool","BP","/images/game/map/textures/largePond.png"),
    RIVER("River","RI","/images/game/map/textures/river.png"),
    BEACH("Beach","Be","/images/game/map/textures/beach.png"),
    LOWDEPTHWATER("Lowdepthwater","LDW","/images/game/map/textures/lowDepthWater.png"),
    OIL("Oil","O","/images/game/map/textures/oil.png"),
    PLAIN("Plain","P","/images/game/map/textures/marsh/0.png"),
    DITCH("Ditch" , "D","/images/game/map/textures/moat.jpg");

    private String fullNameType;
    private String abbrevaiteNameType;
    private String filePath;
    private TypeofGround(String fullNameType,String abbreviateNameType, String filePath) {
        this.abbrevaiteNameType = abbreviateNameType;
        this.fullNameType = fullNameType;
        this.filePath = filePath;
    }

    public String getFullNameType() {
        return fullNameType;
    }

    public String getAbbrevaiteNameType() {
        return abbrevaiteNameType;
    }
    public static TypeofGround getTypeOfGroundByName(String name) {
        for (TypeofGround typeofGround : TypeofGround.values()) {
            if (typeofGround.getFullNameType().equals(name)) return typeofGround;
        }
        return null;
    }

    public String getFilePath() {
        return filePath;
    }
}
