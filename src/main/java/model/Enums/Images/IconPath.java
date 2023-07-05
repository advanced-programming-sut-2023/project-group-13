package model.Enums.Images;

import javafx.scene.image.ImageView;
import viewG.GUIController.MapRendererG;

public enum IconPath {
    CASTLE_BUILDING_ICON("/images/game/Picture1.png","castles"),
    FARM_BUILDING_ICON("/images/game/bar/icons/farmBuildingsIcon.png","farmBuildings"),

    INDUSTRY_BUILDING_ICON("/images/game/bar/icons/industryBuildingsIcon.png","industryBuildings"),

    WEAPON_BUILDING_ICON("/images/game/bar/icons/weaponsBuildingsIcon.png","weaponBuildings"),
    TOWN_BUILDING_ICON("/images/game/bar/icons/townBuildingsIcon.png","townBuildings"),
    FOOD_PROCESSING_BUILDING_Icon("/images/game/bar/icons/foodProcessingBuildingsIcon.png", "foodProcessingBuilding"),
    CLOSE_ICON("/images/game/bar/icons/closeIcon.png","closeIcon"),
    OPNE_ICON("/images/game/bar/icons/openIcon.png","openIcon"),
    DELETE_ICON("/images/game/bar/icons/deleteIcon.png","deleteIcon"),
    LEFT_BUTTON("/images/game/bar/icons/leftButton.png","leftButton"),
    RIGHT_BUTTON("/images/game/bar/icons/rightButton.png","rightButton"),

    BACK_BUTTON("/images/game/bar/icons/backButtonIcon.png","backButton"),

    KEY_ICON("/images/game/bar/icons/keyIcon.png","keyIcon"),

    RIGHT_MILITARY_ICON("/images/game/bar/icons/militaryIcon.png","RightMilitaryIcon"),
    RIGHT_TOWERS_ICON("/images/game/bar/icons/towersIcon.png","RightTowersIcon"),
    RIGHT_GATE_HOUSES_ICON("/images/game/bar/icons/gatehouseIcon.png","rightGateHouseIcon"),
    RIGHT_BOTTOM_ORDER_OF_MERIT_ICON("/images/game/bar/icons/orderOfMeritIcon.png","orderOfMerit"),
    RIGHT_BOTTOM_TALK_TO_ALLIES("/images/game/bar/icons/img_2.png","talkToAllies"),
    INITIAL_ARMORY_ICON("/images/game/bar/icons/armoryIcon.png","Armory"),
    INITIAL_BIG_WALL_ICON("/images/game/bar/icons/bigWallIcon.png", "bigWall"),
    INITIAL_SMALL_WALL_ICON("/images/game/bar/icons/smallWallIcon.png","smallWall"),

    INITIAL_CRENULATED_WALL_ICON("/images/game/bar/icons/crenulatedWallIcon.png","crenulatedWall"),
    INITIAL_STAIRS_ICON("/images/game/bar/icons/stairsIcon.png","stairs"),
    INITIAL_MERCENARY_ICON("/images/game/bar/icons/mercenrayIcon.png","mercenary"),
    INITIAL_BARRACKS_ICON("/images/game/bar/icons/barracksIcon.png","barracks"),

    INDUSTRY_BAR_STOCKPILE_ICON("/images/game/bar/icons/stockPileIcon.png","stockPile"),
    INDUSTRY_BAR_WOOD_CUTTER_ICON("/images/game/bar/icons/stockPileIcon.png","woodCutter"),
    INDUSTRY_BAR_QUARRY_ICON("/images/game/bar/icons/quarryIcon.png","quarry"),
    INDUSTRY_BAR_OX_TETHER_ICON("/images/game/bar/icons/oxTetherIcon.png","oxTether"),
    INDUSTRY_BAR_IRON_MINE_ICON("/images/game/bar/icons/ironMineIcon.png","ironMine"),
    INDUSTRY_BAR_PITCH_RIG_ICON("/images/game/bar/icons/pitchRigIcon.png","pitchRig"),

    INDUSTRY_BAR_MARKET_PLACE_ICON("/images/game/bar/icons/pitchRigIcon.png","marketPlace"),
    FARM_APPLE_ORCHARD_ICON("/images/game/bar/icons/appleFarmIcon.png","appleOrchard"),

    FARM_DAIRY_FARM_ICON("/images/game/bar/icons/dairyIcon.png","dairyFarm"),
    FARM_WHEAT_FARM_ICON("/images/game/bar/icons/wheatFarmIcon.png","wheatFarm"),
    FARM_HOPS_FARM_ICON("/images/game/bar/icons/hopsFarmIcon.png","hopsFarm"),

    TOWN_HOVEL_ICON("/images/game/bar/icons/hovelIcon.png","hovel"),

    TOWN_CHURCH_ICON("/images/game/bar/icons/churchIcon.png","church"),
    TOWN_CATHEDRAL_ICON("/images/game/bar/icons/cathedralIcon.png","cathedralIcon"),
    TOWN_BAD_THINGS_ICON("/images/game/bar/icons/badThings.png","badThings"),
    TOWN_GOOD_THINGS_ICON("/images/game/bar/icons/goodThings.png","goodThings"),
    TOWN_APOTHECARY_ICON("/images/game/bar/icons/apothecary.png","apothecary"),

    WEAPON_FLETCHER_ICON("/images/game/bar/icons/fletcherWorkshopIcon.png","fletcher"),
    WEAPON_POLE_TURNER_ICON("/images/game/bar/icons/poleturnerWorkshopIcon.png","poleTurner"),
    WEAPON_BLACKSMITH_ICON("/images/game/map/buildings/blackSmith.png","blackSmithIcon"),
    WEAPON_TANNER_ICON("/images/game/bar/icons/tannerWorkshopIcon.png","tanner"),
    WEAPON_ARMORER_ICON("/images/game/bar/icons/armorerWorkshopIcon.png","armorer"),
    FOOD_PROCESSING_GRANARY_ICON("/images/game/bar/icons/granaryIcon.png","granary"),
    FOOD_PROCESSING_BAKERY_ICON("/images/game/map/buildings/bakery.png","bakery"),
    FOOD_PROCESSING_BREWERY_ICON("/images/game/map/buildings/brewery.png","brewery"),
    FOOD_PROCESSING_MILL_ICON("/images/game/map/buildings/mill.png","mill"),
    FOOD_PROCESSING_INN_ICON("/images/game/map/buildings/inn.png","inn"),


    EMPTY_BAR("/images/game/bar/menuEmptyBar.png","emptyBar")



    //TODO back button is the same left button


    ;

    private String path;

    private String id;
    public ImageView getImageView() {
        ImageView imageView = new ImageView(MapRendererG.class.getResource(path).toExternalForm());
        imageView.setId(id);
        return imageView;
    }

    IconPath(String path, String id) {
        this.id = id;
        this.path = path;
    }

    public String getId() {
        return id;
    }
}
