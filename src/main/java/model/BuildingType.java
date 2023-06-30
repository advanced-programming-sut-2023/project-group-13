package model;

public enum BuildingType {
    TREE(0, 100, 0, 1, null, 0, 0, 0, false, "tree","/images/game/map/trees/desertShrub6.png"),
    SMALL_STONE_GATEHOUSE(0, 1000, 0, 3, null, 0, 0, 0, true, "small stone gatehouse","/images/game/map/buildings/smallStoneGatehouse.png"),
    BIG_STONE_GATEHOUSE(0, 2000, 0, 5, ResourcesType.STONE, 20, 0, 0, true, "big stone gatehouse","/images/game/map/buildings/bigStoneGatehouse.png"),
    DRAWBRIDGE(0, 0, 0, 1, ResourcesType.WOOD, 10, 0, 0, true, "drawbridge","/images/game/map/buildings/drawBridge.png"),
    LOOKOUT_TOWER(0, 1300, 0, 3, ResourcesType.STONE, 10, 0, 0, true, "lookout tower","/images/game/map/buildings/lookoutTower.png"),
    PERIMETER_TOWER(0, 1000, 0, 3, ResourcesType.STONE, 10, 0, 0, true, "perimeter tower","/images/game/map/buildings/perimeterTurret.png"),
    DEFENSE_TURRET(0, 1200, 0, 3, ResourcesType.STONE, 15, 0, 0, true, "defense turret","/images/game/map/buildings/defenseTurret.png"),
    SQUARE_TOWER(0, 1600, 0, 3, ResourcesType.STONE, 35, 0, 0, true, "square tower","/images/game/map/buildings/squareTower.png"),
    ROUND_TOWER(0, 2000, 0, 3, ResourcesType.STONE, 40, 0, 0, true, "round tower","/images/game/map/buildings/roundTower.png"),
    ARMORY(0, 500, 0, 1, ResourcesType.WOOD, 5, 0, 0, false, "armory","/images/game/map/buildings/armoury.png"),
    BARRACKS(0, 500, 0, 3, ResourcesType.STONE, 15, 0, 0, true, "barracks","/images/game/map/buildings/barrack.png"),
    MERCENARY_POST(0, 500, 0, 3, ResourcesType.WOOD, 10, 0, 0, true, "mercenary post","/images/game/map/buildings/mercenaryPost.png"),
    ENGINEERS_GUILD(0, 500, 100, 3, ResourcesType.WOOD, 10, 0, 0, true, "engineers guild","/images/game/map/buildings/engineerGuild.png"),
    KILLING_PIT(0, 0, 0, 1, ResourcesType.WOOD, 6, 0, 0, true, "killing pit","/images/game/map/buildings/killingPit.png"),
    INN(0, 300, 100, 3, ResourcesType.WOOD, 20, 1, 0, false, "inn","/images/game/map/buildings/inn.png"),
    MILL(3, 300, 0, 3, ResourcesType.WOOD, 20, 3, 0, false, "mill","/images/game/map/buildings/mill.png"),
    IRON_MINE(1, 100, 0, 3, ResourcesType.WOOD, 20, 2, 0, false, "iron mine","/images/game/map/buildings/ironMine.png"),
    MARKET(0, 300, 0, 3, ResourcesType.WOOD, 5, 1, 0, false, "market","/images/game/map/buildings/shop.png"),
    OX_TETHER(0, 100, 0, 1, ResourcesType.WOOD, 5, 1, 0, false, "ox tether","/images/game/map/buildings/oxTether.png"),
    PITCH_RIG(10, 100, 0, 3, ResourcesType.WOOD, 20, 1, 0, false, "pitch rig","/images/game/map/buildings/pitchRig.png"),
    QUARRY(36, 300, 0, 3, ResourcesType.WOOD, 20, 3, 0, false, "quarry","/images/game/map/buildings/quarry.png"),
    STOCKPILE(0, 500, 0, 1, null, 0, 0, 0, false, "stockpile","/images/game/map/buildings/stockPile.png"),
    WOODCUTTERS(18, 100, 0, 3, ResourcesType.WOOD, 3, 1, 0, false, "woodcutter","/images/game/map/buildings/woodCutter.png"),
    HOVEL(8, 100, 0, 3, ResourcesType.WOOD, 6, 0, 0, false, "hovel","/images/game/map/buildings/hovel.png"),
    CHURCH(0, 800, 1000, 3, null, 0, 0, 0, false, "church","/images/game/map/buildings/church.png"),
    ARMORER(1, 300, 100, 3, ResourcesType.WOOD, 20, 1, 0, false, "armorer","/images/game/map/buildings/armourer.png"),
    BLACKSMITH(1, 300, 100, 3, ResourcesType.WOOD, 20, 1, 0, false, "blacksmith","/images/game/map/buildings/blackSmith.png"),
    FLETCHER(1, 300, 100, 3, ResourcesType.WOOD, 20, 1, 0, false, "fletcher","/images/game/map/buildings/fletcher.png"),
    POLETURNER(1, 300, 100, 3, ResourcesType.WOOD, 10, 1, 0, false, "poleturner","/images/game/map/buildings/poleTurner.png"),
    PITCH_DITCH(10, 300, 0, 3, ResourcesType.PITCH, 10, 0, 0, true, "pitch ditch",""),
    CAGED_WAR_DOGS(0, 100, 100, 3, ResourcesType.WOOD, 10, 0, 0, false, "caged war dogs","/images/game/map/buildings/cagedWarDogs.png"),
    SIEGE_TENT(0, 100, 0, 1, null, 0, 0, 1, false, "siege tent","/"),
    STABLE(4, 300, 400, 3, ResourcesType.WOOD, 20, 0, 0, false, "stable","/images/game/map/buildings/stable.png"),
    APPLE_ORCHARD(20, 100, 0, 3, ResourcesType.WOOD, 5, 1, 0, false, "apple orchard","/images/game/map/buildings/appleGarden.png"),
    DIARY_FARMER(20, 100, 0, 3, ResourcesType.WOOD, 10, 1, 0, false, "diary farmer","/images/game/map/buildings/dairyProducts.png"),
    HOPS_FARMER(20, 100, 0, 3, ResourcesType.WOOD, 15, 1, 0, false, "hops farmer","/images/game/map/buildings/hopFarm.png"),
    HUNTERS_POST(20, 300, 0, 3, ResourcesType.WOOD, 5, 1, 0, false, "hunter post","/images/game/map/buildings/huntingPost.png"),
    WHEAT_FARMER(20, 300, 0, 3, ResourcesType.WOOD, 15, 1, 0, false, "wheat farmer","/images/game/map/buildings/wheatFarm.png"),
    BAKERY(10, 300, 0, 3, ResourcesType.WOOD, 10, 1, 0, false, "bakery","/images/game/map/buildings/bakery.png"),
    BREWER(10, 300, 0, 3, ResourcesType.WOOD, 10, 1, 0, false, "brewer","/images/game/map/buildings/brewery.png"),
    GRANARY(0, 500, 0, 1, ResourcesType.WOOD, 5, 0, 0, false, "granary","/images/game/map/buildings/granary.png"),
    MAIN_CASTLE(0, 40000, 0, 7, null, 0, 0, 0, true, "main castle","/images/game/map/buildings/castleBuildings.png"),
    STAIR(0, 100, 0, 1, null, 0, 0, 0, false, "stair","/images/game/map/buildings/stairs1.png"),
    WALL(0, 100, 0, 1, null, 0, 0, 0, false, "wall","/images/game/map/buildings/stoneWall.png"),
    BRIDGE(0, 1, 0, 1, null, 0, 0, 0, true, "bridge","/images/game/map/buildings/drawBridge.png"),
    OIL_SMELTER(0,500 , 100 , 1, ResourcesType.IRON   , 10 ,0 , 1 , false ,"oil smelter","/images/game/map/buildings/oilSmelter.png"),

    ;
    private int size;
    private int hp;
    private int goldPrice;
    private int workerNeededAmount;
    private int engineerNeededAmount;
    private boolean penetrable;
    private String filePath;

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
                 int engineerNeededAmount , boolean canYouEnterIt, String name,String filePath) {
        this.hp = hitPoint;
        this.goldPrice = goldPrice;
        this.resourcesPriceType =  resourcesPriceType;
        this.workerNeededAmount = workerNeededAmount;
        this.engineerNeededAmount = engineerNeededAmount;
        this.resourcePriceAmount = resourcePriceAmount;
        this.size = size;
        this.penetrable = canYouEnterIt;
        this.name = name;
        this.filePath = filePath;
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

    public String getFilePath() {
        return filePath;
    }

    //todo set and fix the isPenetrable
    // todo add different walls, stairs and gatehouses

    public static BuildingType getBuildingTypeByName(String name) {
        for (BuildingType buildingType : BuildingType.values()) {
            if (buildingType.getName().equals(name)) return buildingType;
        }
        return null;
    }


}
