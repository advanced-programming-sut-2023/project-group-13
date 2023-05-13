package model;

public enum BuildingType {
    TREE(100, 0, 1, null, 0, 0, 0, false, "tree"),
    SMALL_STONE_GATEHOUSE(1000,0,3,null,0,0,0,true,"small stone gatehouse"),
    BIG_STONE_GATEHOUSE(2000,0,5,ResourcesType.STONE,20,0,0,true,"big stone gatehouse"),
    KEEP(0,0,7,null,0,0,0,true,"keep"), //maghar
    DRAWBRIDGE(0,0,1,ResourcesType.WOOD,10,0,0,true,"drawbridge"), //pol moteharek
    LOOKOUT_TOWER(1300,0,3,ResourcesType.STONE,10,0,0,true,"lookout tower"), // borje didbani
    PERIMETER_TOWER(1000,0,3,ResourcesType.STONE,10,0,0,true,"perimeter tower"), // borj mohiti
    DEFENSE_TURRET(1200,0,3,ResourcesType.STONE,15,0,0,true,"defense turret"), //borjak defaei
    SQUARE_TOWER(1600,0,3,ResourcesType.STONE,35,0,0,true,"square tower"), // borj moarabei
    ROUND_TOWER(2000,0,3,ResourcesType.STONE,40,0,0,true,"round tower"), // borj dayerei
    ARMORY(500,0,1,ResourcesType.WOOD,5,0,0,false,"armory"), //aslahekhane
    BARRACKS(500,0,3,ResourcesType.STONE,15,0,0,true,"barracks"), // sarbaz khane
    MERCENARY_POST(500,0,3,ResourcesType.WOOD,10,0,0,true,"mercenary post"), //sarbaz khane mozdooran
    ENGINEERS_GUILD(500,100,3,ResourcesType.WOOD,10,0,0,true,"engineers guild"), // senf mohandesan
    KILLING_PIT(0,0,1,ResourcesType.WOOD,6,0,0,true,"killing pit"), //godal koshtar
    INN(300,100,3,ResourcesType.WOOD,20,1,0,false,"inn"), //mosafer khane
    MILL(300,0,3,ResourcesType.WOOD,20,3,0,false,"mill"), //asyab
    IRON_MINE(100,0,3,ResourcesType.WOOD,20,2,0,false,"iron mine"),
    MARKET(300,0,3,ResourcesType.WOOD,5,1,0,false,"market"),
    OX_TETHER(100,0,1,ResourcesType.WOOD,5,1,0,false,"ox tether"), //afsar gav
    PITCH_RIG(100,0,3,ResourcesType.WOOD,20,1,0,false,"pitch rig"), //dakal ghir
    QUARRY(300,0,3,ResourcesType.WOOD,20,3,0,false,"quarry"), //maadan sang
    STOCKPILE(500,0,1,null,0,0,0,false,"stockpile"), //anbar
    WOODCUTTERS(100,0,3,ResourcesType.WOOD,3,1,0,false,"woodcutter"),
    HOVEL(100,0,3,ResourcesType.WOOD,6,0,0,false,"hovel"), //khane
    CHURCH(800,250,3,null,0,0,0,false,"church"), //kelisa
    CATHEDRAL(1200,1000,5,null,0,0,0,true,"cathedral"), //kelisaye jame
    ARMORER(300,100,3,ResourcesType.WOOD,20,1,0,false,"armorer"), //zereh sazi
    BLACKSMITH(300,100,3,ResourcesType.WOOD,20,1,0,false,"blacksmith"), //sakhteman ahangari
    FLETCHER(300,100,3,ResourcesType.WOOD,20,1,0,false,"fletcher"), //kaman sazi
    POLETURNER(300,100,3,ResourcesType.WOOD,10,1,0,false,"poleturner"), //neyze sazi
    OIL_SMELTER(300,100,3,ResourcesType.IRON,10,0,1,false,"oil smelter"), //karkhane zob
    PITCH_DITCH(300,0,3,ResourcesType.PITCH,10,0,0,true,"pitch ditch"), //khandagh ghir
    CAGED_WAR_DOGS(100,100,3,ResourcesType.WOOD,10,0,0,false,"caged war dogs"),
    SIEGE_TENT(100,0,1,null,0,0,1,false,"siege tent"), //chador mohasere
    STABLE(300,400,3,ResourcesType.WOOD,20,0,0,false,"stable"),
    APPLE_ORCHARD(100,0,3,ResourcesType.WOOD,5,1,0,false,"apple orchard"), //bagh sib
    DIARY_FARMER(100,0,3,ResourcesType.WOOD,10,1,0,false,"diary farmer"), //labani
    HOPS_FARMER(100,0,3,ResourcesType.WOOD,15,1,0,false,"hops farmer"), //mazrae jo
    HUNTERS_POST(300,0,3,ResourcesType.WOOD,5,1,0,false,"hunter post"),
    WHEAT_FARMER(300,0,3,ResourcesType.WOOD,15,1,0,false,"wheat farmer"),
    BAKERY(300,0,3,ResourcesType.WOOD,10,1,0,false,"bakery"),
    BREWER(300,0,3,ResourcesType.WOOD,10,1,0,false,"brewer"), //abjo sazi
    GRANARY(500,0,1,ResourcesType.WOOD,5,0,0,false,"granary"), //anbar ghaza
    MAIN_CASTLE(40000, 0, 7, null, 0, 0, 0, true, "main castle"),
    // STAIR and WALL are temporary and need to be fixed !!! todo
    STAIR(100, 0, 1, null, 0, 0, 0, false, "stair"),
    WALL(100, 0, 1, null, 0, 0, 0, false, "wall"),
    BRIDGE(1, 0, 1, null, 0, 0, 0, true, "bridge");

    private int size;
    private int height;
    private int hitPoint;
    private int goldPrice;
    private Resources resourcesPrice;
    private int workerNeededAmount;
    private int engineerNeededAmount;
    private boolean penetrable;

    private String name;
    private Class<?> BuildingClass;
    BuildingType(int hitPoint, int goldPrice, int size,
                 ResourcesType resourcesPriceType, int resourcePriceAmount, int workerPrice,
                 int engineerPrice/*,int height*/, boolean canYouEnterIt, String name) {
        this.hitPoint = hitPoint;
        this.goldPrice = goldPrice;
//        this.resourcesPrice =  new Resources(resourcePriceAmount, ResourcesType.]\\);
        this.workerNeededAmount = workerPrice;
        this.engineerNeededAmount = engineerPrice;
        this.size = size;
        this.penetrable = canYouEnterIt;
        this.name = name;
        /*this.height = height;*/
    }

    public static BuildingType getBuildingTypeByString(String type){
        BuildingType buildingType;
        for(BuildingType temp : BuildingType.values()) if (temp.name.equals(type)) return temp;
        return null;
    }

//    public static boolean checkGround(BuildingType buildingType, TileStructure tileStructure){
//        if(buildingType == QUARRY)
//            return tileStructure == TileStructure.ROCK;
//        else if(buildingType == PITCH_RIG)
//            return tileStructure == TileStructure.PLAIN;
//        else if(buildingType == IRON_MINE)
//            return tileStructure == TileStructure.IRON;
//        else if(buildingType == OIL_SMELTER)
//            return tileStructure == TileStructure.OIL;
//        else if(tileStructure.isBlue())
//            return false;
//        else if(buildingType == APPLE_ORCHARD || buildingType == HOPS_FARMER || buildingType == WHEAT_FARMER)
//            return tileStructure == TileStructure.GRASS || tileStructure == TileStructure.DENSE_MEADOW;
//        else if(tileStructure == TileStructure.EARTH || tileStructure == TileStructure.GRASS ||
//                tileStructure == TileStructure.MEADOW)
//            return true;
//        else if(buildingType == MILL)
//            return false;
//        else if(buildingType == TREE)
//            return tileStructure == TileStructure.DENSE_MEADOW;
//        else if(buildingType.getBuildingClass() == Towers.class)
//            return tileStructure != TileStructure.ROCK && tileStructure != TileStructure.STONE;
//        else return tileStructure == TileStructure.PEBBLE ||
//                    tileStructure == TileStructure.DENSE_MEADOW;
//    }

    public int getWorkerNeededAmount() {
        return workerNeededAmount;
    }

    public int getEngineerNeededAmount() {
        return engineerNeededAmount;
    }

    public int getGoldPrice() {
        return goldPrice;
    }

    public Resources getResourcesPrice() {
        return resourcesPrice;
    }

    public int getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public boolean isPenetrable() {
        return penetrable;
    }

    public String getName() {
        return name;
    }

    public static BuildingType getBuildingTypeByName(String name) {
        for (BuildingType buildingType : BuildingType.values()) {
            if (buildingType.getName().equals(name)) return buildingType;
        }
        return null;
    }

}
