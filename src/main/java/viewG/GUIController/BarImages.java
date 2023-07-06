package viewG.GUIController;

import controller.MapMenuController;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Cell;
import model.Enums.Images.IconPath;

public class BarImages {
    public BarImages() {
        loadBars();
    }
    private Pane castlesBuildingPane;
    private Pane farmBuildingPane;
    private Pane industryBuildingPane;
    private Pane weaponBuildingPane;
    private Pane townBuildingPane;
    private Pane foodProcessingBuildingPane;

    private ImageView selectIconImageView;



    private int layoutXEmptyBar = 200;
    private int layoutYEmptyBar = 200;

    private static final double BRIGHTNESS_DELTA = 0.2;


    private int iconBuildingBarFitWidth = 70;
    private int iconBuildingBarFitHeight = 70;

    private int baseLayoutX = 270;
    private int baseLayoutY = 215;
    private int incrementSpacingIcon = 75;

    private double xOffset;
    private double yOffset;

    private boolean canPlaceBuilding;

    private Rectangle warningRectangle = new Rectangle();

    private MapMenuController mapMenuController = new MapMenuController();

    public void loadBars() {
        loadBarCastleBuildings();
        loadBarFarmBuildings();
        loadBarIndustryBuildings();
        loadBarWeaponBuildings();
        loadBarTownBuildings();
        loadBarFoodProcessingBuildings();
    }

    private void loadBarCastleBuildings() {
        ImageView[] initialIconsInsideTheBar = {
                IconPath.INITIAL_STAIRS_ICON.getIconImageView(),
                IconPath.INITIAL_SMALL_WALL_ICON.getIconImageView(),
                IconPath.INITIAL_BIG_WALL_ICON.getIconImageView(),
                IconPath.INITIAL_CRENULATED_WALL_ICON.getIconImageView(),
                IconPath.INITIAL_BARRACKS_ICON.getIconImageView(),
                IconPath.INITIAL_MERCENARY_ICON.getIconImageView(),
                IconPath.INITIAL_ARMORY_ICON.getIconImageView(),
        };

        setAlignment(initialIconsInsideTheBar);

        castlesBuildingPane = new Pane();
        castlesBuildingPane.getChildren().addAll(initialIconsInsideTheBar);
        castlesBuildingPane.setVisible(true);
        MapRendererG.buttomBar.getChildren().add(castlesBuildingPane);
    }


    private void loadBarFoodProcessingBuildings() {
        ImageView[] initialIconsInsideTheBar = {
               IconPath.FOOD_PROCESSING_MILL_ICON.getIconImageView(),
                IconPath.FOOD_PROCESSING_BREWERY_ICON.getIconImageView(),
                IconPath.FOOD_PROCESSING_GRANARY_ICON.getIconImageView(),
                IconPath.FOOD_PROCESSING_INN_ICON.getIconImageView(),
                IconPath.FOOD_PROCESSING_BAKERY_ICON.getIconImageView()
        };

        setAlignment(initialIconsInsideTheBar);
        foodProcessingBuildingPane = new Pane();
        foodProcessingBuildingPane.getChildren().addAll(initialIconsInsideTheBar);
        foodProcessingBuildingPane.setVisible(false);
        MapRendererG.buttomBar.getChildren().add(foodProcessingBuildingPane);
    }



    private void loadBarTownBuildings() {
        ImageView[] initialIconsInsideTheBar = {
               IconPath.TOWN_GOOD_THINGS_ICON.getIconImageView(),
                IconPath.TOWN_HOVEL_ICON.getIconImageView(),
                IconPath.TOWN_BAD_THINGS_ICON.getIconImageView(),
                IconPath.TOWN_APOTHECARY_ICON.getIconImageView(),
                IconPath.TOWN_CATHEDRAL_ICON.getIconImageView(),
                IconPath.TOWN_CHURCH_ICON.getIconImageView(),
        };

        setAlignment(initialIconsInsideTheBar);

        townBuildingPane = new Pane();
        townBuildingPane.getChildren().addAll(initialIconsInsideTheBar);
        townBuildingPane.setVisible(false);
        MapRendererG.buttomBar.getChildren().add(townBuildingPane);
    }

    private void loadBarWeaponBuildings() {
        ImageView[] initialIconsInsideTheBar = {
                IconPath.WEAPON_ARMORER_ICON.getIconImageView(),
                IconPath.WEAPON_BLACKSMITH_ICON.getIconImageView(),
                IconPath.WEAPON_POLE_TURNER_ICON.getIconImageView(),
                IconPath.WEAPON_FLETCHER_ICON.getIconImageView()
        };
        setAlignment(initialIconsInsideTheBar);

        weaponBuildingPane = new Pane();
        weaponBuildingPane.getChildren().addAll(initialIconsInsideTheBar);
        weaponBuildingPane.setVisible(false);
        MapRendererG.buttomBar.getChildren().add(weaponBuildingPane);
    }

    private void loadBarFarmBuildings() {

        ImageView[] initialIconsInsideTheBar = {
                IconPath.FARM_DAIRY_FARM_ICON.getIconImageView(),
                IconPath.FARM_WHEAT_FARM_ICON.getIconImageView(),
                IconPath.FARM_APPLE_ORCHARD_ICON.getIconImageView(),
                IconPath.FARM_HOPS_FARM_ICON.getIconImageView()
        };

        setAlignment(initialIconsInsideTheBar);

        farmBuildingPane = new Pane();
        farmBuildingPane.getChildren().addAll(initialIconsInsideTheBar);
        farmBuildingPane.setVisible(false);
        MapRendererG.buttomBar.getChildren().add(farmBuildingPane);
    }

    private void loadBarIndustryBuildings() {

        ImageView[] farmBuildingIcons = {
                IconPath.INDUSTRY_BAR_IRON_MINE_ICON.getIconImageView(),
                IconPath.INDUSTRY_BAR_WOOD_CUTTER_ICON.getIconImageView(),
                IconPath.INDUSTRY_BAR_QUARRY_ICON.getIconImageView(),
                IconPath.INDUSTRY_BAR_STOCKPILE_ICON.getIconImageView(),
                IconPath.INDUSTRY_BAR_MARKET_PLACE_ICON.getIconImageView(),
                IconPath.INDUSTRY_BAR_OX_TETHER_ICON.getIconImageView(),
                IconPath.INDUSTRY_BAR_PITCH_RIG_ICON.getIconImageView()
        };

        setAlignment(farmBuildingIcons);

        industryBuildingPane = new Pane();
        industryBuildingPane.getChildren().addAll(farmBuildingIcons);
        industryBuildingPane.setVisible(false);
        MapRendererG.buttomBar.getChildren().add(industryBuildingPane);
    }

    public void castleVisible(int command) {

        System.out.println("it comes here for making bars visible");
        castlesBuildingPane.setVisible(false);
        townBuildingPane.setVisible(false);
        farmBuildingPane.setVisible(false);
        foodProcessingBuildingPane.setVisible(false);
        weaponBuildingPane.setVisible(false);
        industryBuildingPane.setVisible(false);

        switch (command) {
            case 1:
                castlesBuildingPane.setVisible(true);
                break;
            case 2:
                townBuildingPane.setVisible(true);
                break;
            case 3:
                farmBuildingPane.setVisible(true);
                break;
            case 4:
                foodProcessingBuildingPane.setVisible(true);
                break;
            case 5:
                weaponBuildingPane.setVisible(true);
                break;
            case 6:
                industryBuildingPane.setVisible(true);
                break;
        }
    }

    private void setAlignment(ImageView[] initialIconsInsideTheBar) {

        int layoutX = baseLayoutX + incrementSpacingIcon;

        for (ImageView icon : initialIconsInsideTheBar) {
            icon.setLayoutX(layoutX);
            icon.setLayoutY(baseLayoutY);
            selectIconImageView = icon;
            hoverFeature(icon);
            imageBehaviour(icon);
            layoutX += incrementSpacingIcon;
            icon.setFitWidth(iconBuildingBarFitWidth);
            icon.setFitHeight(iconBuildingBarFitHeight);
        }

    }

    private void imageBehaviour(ImageView icon) {
        icon.setOnMouseClicked(event -> handleDropBuilding(event,icon));

//        MapRendererG.centralPane.setOnMouseClicked(event -> {
//            double mouseX = event.getX();
//            double mouseY = event.getY();
//            System.out.println("x = " + mouseX + ", y = " + mouseY);
//        });



//        icon.setOnMousePressed(event -> handleMousePressed(event,icon));
    }

    private void handleDropBuilding(MouseEvent mouseEvent, ImageView icon) {
//        if (mouseEvent.getClickCount() == 2) {

            ImageView buildingImageView = IconPath.getMapIconToBuilding(icon);

            buildingImageView.setVisible(false);

        MapRendererG.centralPane.setOnMouseClicked(event -> {
            MapRendererG.centralPane.getChildren().remove(warningRectangle);
            if (!buildingImageView.isVisible()){
                mouseEvent.consume();
                double xIndex = MapRendererG.currentLayoutXOfCentralPane - 50;
                double yIndex = MapRendererG.currentLayoutYOfCentralPane - 50;
                int xCell = (int) (xIndex / 35);
                int yCell = (int) (yIndex / 35);
                Cell currentCell = MapRendererG.map[xCell][yCell];
                if (!currentCell.isBuildingObstacle() && !currentCell.isObstacle()) {
                    buildingImageView.setLayoutX(MapRendererG.currentLayoutXOfCentralPane - 50);
                    buildingImageView.setLayoutY(MapRendererG.currentLayoutYOfCentralPane - 50);
                    buildingImageView.setVisible(true);
                    MapRendererG.centralPane.getChildren().add(buildingImageView);
                    MapRendererG.mapMenuController.dropBuilding(xCell, yCell, buildingImageView.getId());
                } else {
//                    System.out.println("it comes here to this stupid rectangle");
                    warningRectangle.setHeight(100);
                    warningRectangle.setWidth(100);
                    warningRectangle.setFill(Color.RED);
                    MapRendererG.centralPane.getChildren().add(warningRectangle);
                    warningRectangle.setLayoutX(xIndex);
                    warningRectangle.setLayoutY(yIndex);
                }
            }
        });
//        }
    }


    //** Drag and Drop

//    private void handleMousePressed(MouseEvent event, ImageView icon) {
//
////        System.out.println("the current layoutX of central map is: "+ MapRendererG.currentLayoutXOfCentralPane);
////        System.out.println("the current layoutY of central map is: " + MapRendererG.currentLayoutYOfCentralPane);
//
//
//        ImageView buildingImageView = IconPath.getMapIconToBuilding(icon);
//
//        ImageView imageView = (ImageView) event.getSource();
//
//        xOffset = event.getSceneX();
//        yOffset = event.getSceneY();
//
//
//        buildingImageView.setLayoutY(yOffset);
//        buildingImageView.setLayoutX(xOffset);
//
//        MapRendererG.centralPane.getChildren().add(buildingImageView);
//
//        icon.setOnMouseDragged(event2 -> handleMouseDragged(event2,icon,buildingImageView));
//        // Store the initial position of the mouse click
//
//    }
//
//    private void handleMouseDragged(MouseEvent event, ImageView icon, ImageView buildingImageView) {
//        // Calculate the distance moved by the mouse
////        double deltaX = event.getSceneX() - xOffset;
////        double deltaY = event.getSceneY() - yOffset;
//
//        xOffset = event.getSceneX();
//        yOffset = event.getSceneY();
//
//        buildingImageView.setTranslateX(xOffset);
//        buildingImageView.setTranslateY(yOffset);
////        System.out.println("it comes here to this stupid method");
//        // Update the position of the ImageView
////        ImageView imageView = (ImageView) event.getSource();
////        ImageView buildingImageView = IconPath.getMapIconToBuilding().get(icon);
////        buildingImageView.setLayoutX(buildingImageView.getLayoutX() + deltaX);
////        buildingImageView.setLayoutY(buildingImageView.getLayoutY() + deltaY);
//
////        xOffset = event.getSceneX();
////        yOffset = event.getSceneY();
//
//        // Store the new position of the mouse
//
//    }

    private void hoverFeature(ImageView imageView) {
        ColorAdjust colorAdjust = new ColorAdjust();
        imageView.setEffect(colorAdjust);

//        System.out.println("it comes here to this hover method");

        // Add event handlers for hover effect
        imageView.setOnMouseEntered(event -> {
//            System.out.println("mouse entered this bad method");
            colorAdjust.setBrightness(colorAdjust.getBrightness() + BRIGHTNESS_DELTA);
        });

        imageView.setOnMouseExited(event -> {
//            System.out.println("mouse exited this bad method");
            colorAdjust.setBrightness(colorAdjust.getBrightness() - BRIGHTNESS_DELTA);
        });
    }
    //**//



}
