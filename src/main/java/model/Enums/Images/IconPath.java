package model.Enums.Images;

import javafx.scene.image.ImageView;
import model.BuildingType;
import model.Enums.TypeofGround;
import viewG.GUIController.MapRendererG;

import java.util.HashMap;

public enum IconPath {
    CASTLE_BUILDING_ICON("/images/game/Picture1.png","castles","/images/faces/face0.png"),
    FARM_BUILDING_ICON("/images/game/bar/icons/farmBuildingsIcon.png","farmBuildings","/images/faces/face0.png"),

    INDUSTRY_BUILDING_ICON("/images/game/bar/icons/industryBuildingsIcon.png","industryBuildings","/images/faces/face0.png"),

    WEAPON_BUILDING_ICON("/images/game/bar/icons/weaponsBuildingsIcon.png","weaponBuildings","/images/faces/face0.png"),
    TOWN_BUILDING_ICON("/images/game/bar/icons/townBuildingsIcon.png","townBuildings","/images/faces/face0.png"),
    FOOD_PROCESSING_BUILDING_Icon("/images/game/bar/icons/foodProcessingBuildingsIcon.png", "foodProcessingBuilding","/images/faces/face0.png"),
    CLOSE_ICON("/images/game/bar/icons/closeIcon.png","closeIcon","/images/faces/face0.png"),
    OPNE_ICON("/images/game/bar/icons/openIcon.png","openIcon","/images/faces/face0.png"),
    DELETE_ICON("/images/game/bar/icons/deleteIcon.png","deleteIcon","images/faces/face0.png"),
    LEFT_BUTTON("/images/game/bar/icons/leftButton.png","leftButton","images/faces/face0.png"),
    RIGHT_BUTTON("/images/game/bar/icons/rightButton.png","rightButton","/images/faces/face0.png"),

    BACK_BUTTON("/images/game/bar/icons/backButtonIcon.png","backButton","/images/faces/face0.png"),

    KEY_ICON("/images/game/bar/icons/keyIcon.png","keyIcon","/images/faces/face0.png"),

    RIGHT_MILITARY_ICON("/images/game/bar/icons/militaryIcon.png","RightMilitaryIcon","/images/faces/face0.png"),
    RIGHT_TOWERS_ICON("/images/game/bar/icons/towersIcon.png","RightTowersIcon","/images/faces/face0.png"),
    RIGHT_GATE_HOUSES_ICON("/images/game/bar/icons/gatehouseIcon.png","rightGateHouseIcon","/images/faces/face0.png"),
    RIGHT_BOTTOM_ORDER_OF_MERIT_ICON("/images/game/bar/icons/orderOfMeritIcon.png","orderOfMerit","/images/faces/face0.png"),
    RIGHT_BOTTOM_TALK_TO_ALLIES("/images/game/bar/icons/img_2.png","talkToAllies","/images/faces/face0.png"),
    INITIAL_ARMORY_ICON("/images/game/bar/icons/armoryIcon.png", BuildingType.ARMORY.getName(), "/images/game/map/buildings/armoury.png"),
    INITIAL_BIG_WALL_ICON("/images/game/bar/icons/bigWallIcon.png", "wall","/images/game/map/buildings/crenulatedWall.png"),
    INITIAL_SMALL_WALL_ICON("/images/game/bar/icons/smallWallIcon.png","wall","/images/game/map/buildings/lowWall.png"),

    INITIAL_CRENULATED_WALL_ICON("/images/game/bar/icons/crenulatedWallIcon.png","wall","/images/game/map/buildings/crenulatedWall.png"),
    INITIAL_STAIRS_ICON("/images/game/bar/icons/stairsIcon.png", BuildingType.STAIR.getName(),"/images/game/map/buildings/stairs2.png"),
    INITIAL_MERCENARY_ICON("/images/game/bar/icons/mercenrayIcon.png",BuildingType.MERCENARY_POST.getName(), "/images/game/map/buildings/mercenaryPost.png"),
    INITIAL_BARRACKS_ICON("/images/game/bar/icons/barracksIcon.png",BuildingType.BARRACKS.getName(), "/images/game/map/buildings/barrack.png"),

    INDUSTRY_BAR_STOCKPILE_ICON("/images/game/bar/icons/stockPileIcon.png",BuildingType.STOCKPILE.getName(), "/images/game/map/buildings/stockPile.png"),
    INDUSTRY_BAR_WOOD_CUTTER_ICON("/images/game/bar/icons/woodCutterIcon.png",BuildingType.WOODCUTTERS.getName(), "/images/game/map/buildings/woodCutter.png"),
    INDUSTRY_BAR_QUARRY_ICON("/images/game/bar/icons/quarryIcon.png",BuildingType.QUARRY.getName(), "/images/game/map/buildings/quarry.png"),
    INDUSTRY_BAR_OX_TETHER_ICON("/images/game/bar/icons/oxTetherIcon.png",BuildingType.OX_TETHER.getName(), "/images/game/map/buildings/oxTether.png"),
    INDUSTRY_BAR_IRON_MINE_ICON("/images/game/bar/icons/ironMineIcon.png",BuildingType.IRON_MINE.getName(), "/images/game/map/buildings/ironMine.png"),
    INDUSTRY_BAR_PITCH_RIG_ICON("/images/game/bar/icons/pitchRigIcon.png",BuildingType.PITCH_RIG.getName(), "/images/game/map/buildings/pitchRig.png"),

    INDUSTRY_BAR_MARKET_PLACE_ICON("/images/game/map/buildings/shop.png",BuildingType.MARKET.getName(), "/images/game/map/buildings/shop.png"),
    FARM_APPLE_ORCHARD_ICON("/images/game/bar/icons/appleFarmIcon.png",BuildingType.APPLE_ORCHARD.getName(), "/images/game/map/buildings/appleGarden.png"),

    FARM_DAIRY_FARM_ICON("/images/game/bar/icons/dairyIcon.png",BuildingType.DIARY_FARMER.getName(), "/images/game/map/buildings/dairyProducts.png"),
    FARM_WHEAT_FARM_ICON("/images/game/bar/icons/wheatFarmIcon.png",BuildingType.WHEAT_FARMER.getName(), "/images/game/map/buildings/wheatFarm.png"),
    FARM_HOPS_FARM_ICON("/images/game/bar/icons/hopsFarmIcon.png",BuildingType.HOPS_FARMER.getName(), "/images/game/map/buildings/sc_misc_hopsfarm.gif"),

    TOWN_HOVEL_ICON("/images/game/bar/icons/hovelIcon.png",BuildingType.HOVEL.getName(), "/images/game/map/buildings/hovel.png"),

    TOWN_CHURCH_ICON("/images/game/bar/icons/churchIcon.png",BuildingType.CHURCH.getName(), "/images/game/map/buildings/church.png"),
    TOWN_CATHEDRAL_ICON("/images/game/bar/icons/cathedralIcon.png","cathedralIcon","/images/game/map/buildings/cathedral.png"),
    TOWN_BAD_THINGS_ICON("/images/game/bar/icons/badThings.png","badThings","/images/game/map/buildings/gameinfo_buildings_good-bad_gallows.gif"),
    TOWN_GOOD_THINGS_ICON("/images/game/bar/icons/goodThings.png","goodThings","/images/game/map/buildings/gameinfo_buildings_good-bad_garden.gif"),
    TOWN_APOTHECARY_ICON("/images/game/bar/icons/apothecary.png","apothecary","/images/game/bar/icons/apothecary.png"),

    WEAPON_FLETCHER_ICON("/images/game/bar/icons/fletcherWorkshopIcon.png",BuildingType.FLETCHER.getName(), "/images/game/map/buildings/fletcher.png"),
    WEAPON_POLE_TURNER_ICON("/images/game/bar/icons/poleturnerWorkshopIcon.png",BuildingType.POLETURNER.getName(), "/images/game/map/buildings/poleTurner.png"),
    WEAPON_BLACKSMITH_ICON("/images/game/map/buildings/blackSmith.png",BuildingType.BLACKSMITH.getName(), "/images/game/map/buildings/blackSmith.png"),
    WEAPON_TANNER_ICON("/images/game/bar/icons/tannerWorkshopIcon.png","tanner","/images/game/bar/icons/tannerWorkshopIcon.png"),
    WEAPON_ARMORER_ICON("/images/game/bar/icons/armorerWorkshopIcon.png",BuildingType.ARMORER.getName(), "/images/game/map/buildings/armourer.png"),
    FOOD_PROCESSING_GRANARY_ICON("/images/game/bar/icons/granaryIcon.png",BuildingType.GRANARY.getName(), "/images/game/map/buildings/granary.png"),
    FOOD_PROCESSING_BAKERY_ICON("/images/game/map/buildings/bakery.png",BuildingType.BAKERY.getName(), "/images/game/map/buildings/bakery.png"),
    FOOD_PROCESSING_BREWERY_ICON("/images/game/map/buildings/brewery.png",BuildingType.BREWER.getName(), "/images/game/map/buildings/brewery.png"),
    FOOD_PROCESSING_MILL_ICON("/images/game/map/buildings/mill.png",BuildingType.MILL.getName(), "/images/game/map/buildings/mill.png"),
    FOOD_PROCESSING_INN_ICON("/images/game/map/buildings/inn.png",BuildingType.INN.getName(), "/images/game/map/buildings/inn.png"),


    EMPTY_BAR("/images/game/bar/menuEmptyBar.png","emptyBar","/images/game/bar/menuEmptyBar.png")




    //TODO back button is the same left button


    ;

//    private static HashMap<ImageView,ImageView> mapIconToBuilding = new HashMap<>();

    private String iconPath;
    private String buildingPath;

    private String id;
    public ImageView getIconImageView() {
        ImageView imageView = new ImageView(MapRendererG.class.getResource(iconPath).toExternalForm());
        imageView.setId(id);
//        mapIconToBuilding.put(imageView,getBuildingImageView());
        return imageView;
    }

    public ImageView getBuildingImageView() {
        ImageView imageView = new ImageView(MapRendererG.class.getResource(buildingPath).toExternalForm());
        imageView.setId(id);
//        imageView.setLayoutY(-20000);
//        imageView.setLayoutY(-20000);
        return imageView;
    }

    IconPath(String iconPath, String id, String buildingPath) {
        this.id = id;
        this.iconPath = iconPath;
        this.buildingPath = buildingPath;
    }


    public String getId() {
        return id;
    }

    public static ImageView getMapIconToBuilding(ImageView icon) {
//        return mapIconToBuilding.get(icon);
        for (IconPath iconPath1 : IconPath.values()) {
            if (iconPath1.getId().equals(icon.getId())) {
                ImageView imageView = new ImageView(MapRendererG.class.getResource(iconPath1.buildingPath).toExternalForm());
                imageView.setId(icon.getId());
                return imageView;
            }
        }
        return null;
    }
}
