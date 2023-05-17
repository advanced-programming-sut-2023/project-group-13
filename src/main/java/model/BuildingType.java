package model;

public enum BuildingType {
    TREE(100, 0, 1, null, 0, 0, 0, false, "tree"),
    SMALL_STONE_GATEHOUSE(1000,0,3,null,0,0,0,true,"small stone gatehouse"),
    BIG_STONE_GATEHOUSE(2000,0,5,ResourcesType.STONE,20,0,0,true,"big stone gatehouse"),
    KEEP(0,0,7,null,0,0,0,true,"keep"),
    DRAWBRIDGE(0,0,1,ResourcesType.WOOD,10,0,0,true,"drawbridge"),
    LOOKOUT_TOWER(1300,0,3,ResourcesType.STONE,10,0,0,true,"lookout tower"),
    PERIMETER_TOWER(1000,0,3,ResourcesType.STONE,10,0,0,true,"perimeter tower"),
    DEFENSE_TURRET(1200,0,3,ResourcesType.STONE,15,0,0,true,"defense turret"),
    SQUARE_TOWER(1600,0,3,ResourcesType.STONE,35,0,0,true,"square tower"),
    ROUND_TOWER(2000,0,3,ResourcesType.STONE,40,0,0,true,"round tower"),
    ARMORY(500,0,1,ResourcesType.WOOD,5,0,0,false,"armory"),
    BARRACKS(500,0,3,ResourcesType.STONE,15,0,0,true,"barracks"),
    MERCENARY_POST(500,0,3,ResourcesType.WOOD,10,0,0,true,"mercenary post"),
    ENGINEERS_GUILD(500,100,3,ResourcesType.WOOD,10,0,0,true,"engineers guild"),
    KILLING_PIT(0,0,1,ResourcesType.WOOD,6,0,0,true,"killing pit"),
    INN(300,100,3,ResourcesType.WOOD,20,1,0,false,"inn"),
    MILL(300,0,3,ResourcesType.WOOD,20,3,0,false,"mill"),
    IRON_MINE(100,0,3,ResourcesType.WOOD,20,2,0,false,"iron mine"),
    MARKET(300,0,3,ResourcesType.WOOD,5,1,0,false,"market"),
    OX_TETHER(100,0,1,ResourcesType.WOOD,5,1,0,false,"ox tether"),
    PITCH_RIG(100,0,3,ResourcesType.WOOD,20,1,0,false,"pitch rig"),
    QUARRY(300,0,3,ResourcesType.WOOD,20,3,0,false,"quarry"),
    STOCKPILE(500,0,1,null,0,0,0,false,"stockpile"),
    WOODCUTTERS(100,0,3,ResourcesType.WOOD,3,1,0,false,"woodcutter"),
    HOVEL(100,0,3,ResourcesType.WOOD,6,0,0,false,"hovel"),
    CHURCH(800,250,3,null,0,0,0,false,"church"),
    CATHEDRAL(1200,1000,5,null,0,0,0,true,"cathedral"),
    ARMORER(300,100,3,ResourcesType.WOOD,20,1,0,false,"armorer"),
    BLACKSMITH(300,100,3,ResourcesType.WOOD,20,1,0,false,"blacksmith"),
    FLETCHER(300,100,3,ResourcesType.WOOD,20,1,0,false,"fletcher"),
    POLETURNER(300,100,3,ResourcesType.WOOD,10,1,0,false,"poleturner"),
    OIL_SMELTER(300,100,3,ResourcesType.IRON,10,0,1,false,"oil smelter"),
    PITCH_DITCH(300,0,3,ResourcesType.PITCH,10,0,0,true,"pitch ditch"),
    CAGED_WAR_DOGS(100,100,3,ResourcesType.WOOD,10,0,0,false,"caged war dogs"),
    SIEGE_TENT(100,0,1,null,0,0,1,false,"siege tent"),
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
    private int hp;
    private int goldPrice;
    private int workerNeededAmount;
    private int engineerNeededAmount;
    private boolean penetrable;

    private String name;
    ResourcesType resourcesPriceType;
    int resourcePriceAmount;

    public ResourcesType getResourcesPriceType() {
        return resourcesPriceType;
    }

    public void setResourcesPriceType(ResourcesType resourcesPriceType) {
        this.resourcesPriceType = resourcesPriceType;
    }

    public int getResourcePriceAmount() {
        return resourcePriceAmount;
    }

    public void setResourcePriceAmount(int resourcePriceAmount) {
        this.resourcePriceAmount = resourcePriceAmount;
    }

    BuildingType(int hitPoint, int goldPrice, int size,
                 ResourcesType resourcesPriceType, int resourcePriceAmount, int workerNeededAmount,
                 int engineerNeededAmount /*int height*/, boolean canYouEnterIt, String name) {
        this.hp = hitPoint;
        this.goldPrice = goldPrice;
        this.resourcesPriceType =  resourcesPriceType;
        this.workerNeededAmount = workerNeededAmount;
        this.engineerNeededAmount = engineerNeededAmount;
        this.resourcePriceAmount = resourcePriceAmount;
        this.size = size;
        this.penetrable = canYouEnterIt;
        this.name = name;
        //this.height = height;
    }




    public int getWorkerNeededAmount() {
        return workerNeededAmount;
    }

    public int getEngineerNeededAmount() {
        return engineerNeededAmount;
    }

    public int getGoldPrice() {
        return goldPrice;
    }


    public int getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }

    public int getHp() {
        return hp;
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
