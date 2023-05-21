package model;

public enum BuildingType {
    TREE(0, 100, 0, 1, null, 0, 0, 0, false, "tree"),
    SMALL_STONE_GATEHOUSE(0, 1000, 0, 3, null, 0, 0, 0, true, "small stone gatehouse"),
    BIG_STONE_GATEHOUSE(0, 2000, 0, 5, ResourcesType.STONE, 20, 0, 0, true, "big stone gatehouse"),
    DRAWBRIDGE(0, 0, 0, 1, ResourcesType.WOOD, 10, 0, 0, true, "drawbridge"),
    LOOKOUT_TOWER(0, 1300, 0, 3, ResourcesType.STONE, 10, 0, 0, true, "lookout tower"),
    PERIMETER_TOWER(0, 1000, 0, 3, ResourcesType.STONE, 10, 0, 0, true, "perimeter tower"),
    DEFENSE_TURRET(0, 1200, 0, 3, ResourcesType.STONE, 15, 0, 0, true, "defense turret"),
    SQUARE_TOWER(0, 1600, 0, 3, ResourcesType.STONE, 35, 0, 0, true, "square tower"),
    ROUND_TOWER(0, 2000, 0, 3, ResourcesType.STONE, 40, 0, 0, true, "round tower"),
    ARMORY(0, 500, 0, 1, ResourcesType.WOOD, 5, 0, 0, false, "armory"),
    BARRACKS(0, 500, 0, 3, ResourcesType.STONE, 15, 0, 0, true, "barracks"),
    MERCENARY_POST(0, 500, 0, 3, ResourcesType.WOOD, 10, 0, 0, true, "mercenary post"),
    ENGINEERS_GUILD(0, 500, 100, 3, ResourcesType.WOOD, 10, 0, 0, true, "engineers guild"),
    KILLING_PIT(0, 0, 0, 1, ResourcesType.WOOD, 6, 0, 0, true, "killing pit"),
    INN(0, 300, 100, 3, ResourcesType.WOOD, 20, 1, 0, false, "inn"),
    MILL(3, 300, 0, 3, ResourcesType.WOOD, 20, 3, 0, false, "mill"),
    IRON_MINE(1, 100, 0, 3, ResourcesType.WOOD, 20, 2, 0, false, "iron mine"),
    MARKET(0, 300, 0, 3, ResourcesType.WOOD, 5, 1, 0, false, "market"),
    OX_TETHER(0, 100, 0, 1, ResourcesType.WOOD, 5, 1, 0, false, "ox tether"),
    PITCH_RIG(10, 100, 0, 3, ResourcesType.WOOD, 20, 1, 0, false, "pitch rig"),
    QUARRY(36, 300, 0, 3, ResourcesType.WOOD, 20, 3, 0, false, "quarry"),
    STOCKPILE(0, 500, 0, 1, null, 0, 0, 0, false, "stockpile"),
    WOODCUTTERS(18, 100, 0, 3, ResourcesType.WOOD, 3, 1, 0, false, "woodcutter"),
    HOVEL(8, 100, 0, 3, ResourcesType.WOOD, 6, 0, 0, false, "hovel"),
    CHURCH(0, 800, 1000, 3, null, 0, 0, 0, false, "church"),
    ARMORER(1, 300, 100, 3, ResourcesType.WOOD, 20, 1, 0, false, "armorer"),
    BLACKSMITH(1, 300, 100, 3, ResourcesType.WOOD, 20, 1, 0, false, "blacksmith"),
    FLETCHER(1, 300, 100, 3, ResourcesType.WOOD, 20, 1, 0, false, "fletcher"),
    POLETURNER(1, 300, 100, 3, ResourcesType.WOOD, 10, 1, 0, false, "poleturner"),
    PITCH_DITCH(10, 300, 0, 3, ResourcesType.PITCH, 10, 0, 0, true, "pitch ditch"),
    CAGED_WAR_DOGS(0, 100, 100, 3, ResourcesType.WOOD, 10, 0, 0, false, "caged war dogs"),
    SIEGE_TENT(0, 100, 0, 1, null, 0, 0, 1, false, "siege tent"),
    STABLE(4, 300, 400, 3, ResourcesType.WOOD, 20, 0, 0, false, "stable"),
    APPLE_ORCHARD(20, 100, 0, 3, ResourcesType.WOOD, 5, 1, 0, false, "apple orchard"),
    DIARY_FARMER(20, 100, 0, 3, ResourcesType.WOOD, 10, 1, 0, false, "diary farmer"),
    HOPS_FARMER(20, 100, 0, 3, ResourcesType.WOOD, 15, 1, 0, false, "hops farmer"),
    HUNTERS_POST(20, 300, 0, 3, ResourcesType.WOOD, 5, 1, 0, false, "hunter post"),
    WHEAT_FARMER(20, 300, 0, 3, ResourcesType.WOOD, 15, 1, 0, false, "wheat farmer"),
    BAKERY(10, 300, 0, 3, ResourcesType.WOOD, 10, 1, 0, false, "bakery"),
    BREWER(10, 300, 0, 3, ResourcesType.WOOD, 10, 1, 0, false, "brewer"),
    GRANARY(0, 500, 0, 1, ResourcesType.WOOD, 5, 0, 0, false, "granary"),
    MAIN_CASTLE(0, 40000, 0, 7, null, 0, 0, 0, true, "main castle"),
    STAIR(0, 100, 0, 1, null, 0, 0, 0, false, "stair"),
    WALL(0, 100, 0, 1, null, 0, 0, 0, false, "wall"),
    BRIDGE(0, 1, 0, 1, null, 0, 0, 0, true, "bridge"),
    OIL_SMELTER(0,500 , 100 , 1, ResourcesType.IRON   , 10 ,0 , 1 , false ,"oil smelter"),
    ;
    private int size;
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
    private int produceAmount;

    public int getProduceAmount() {
        return produceAmount;
    }

    public int getResourcePriceAmount() {
        return resourcePriceAmount;
    }

    public void setResourcePriceAmount(int resourcePriceAmount) {
        this.resourcePriceAmount = resourcePriceAmount;
    }

    BuildingType(int produceAmount ,int hitPoint, int goldPrice, int size,
                 ResourcesType resourcesPriceType, int resourcePriceAmount, int workerNeededAmount,
                 int engineerNeededAmount , boolean canYouEnterIt, String name) {
        this.hp = hitPoint;
        this.goldPrice = goldPrice;
        this.resourcesPriceType =  resourcesPriceType;
        this.workerNeededAmount = workerNeededAmount;
        this.engineerNeededAmount = engineerNeededAmount;
        this.resourcePriceAmount = resourcePriceAmount;
        this.size = size;
        this.penetrable = canYouEnterIt;
        this.name = name;
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
