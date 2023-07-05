package viewG.GUIController;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
                IconPath.INITIAL_STAIRS_ICON.getImageView(),
                IconPath.INITIAL_SMALL_WALL_ICON.getImageView(),
                IconPath.INITIAL_BIG_WALL_ICON.getImageView(),
                IconPath.INITIAL_CRENULATED_WALL_ICON.getImageView(),
                IconPath.INITIAL_BARRACKS_ICON.getImageView(),
                IconPath.INITIAL_MERCENARY_ICON.getImageView(),
                IconPath.INITIAL_ARMORY_ICON.getImageView(),
        };

        setAlignment(initialIconsInsideTheBar);

        castlesBuildingPane = new Pane();
        castlesBuildingPane.getChildren().addAll(initialIconsInsideTheBar);
        castlesBuildingPane.setVisible(true);
        MapRendererG.buttomBar.getChildren().add(castlesBuildingPane);
    }


    private void loadBarFoodProcessingBuildings() {
        ImageView[] initialIconsInsideTheBar = {
               IconPath.FOOD_PROCESSING_MILL_ICON.getImageView(),
                IconPath.FOOD_PROCESSING_BREWERY_ICON.getImageView(),
                IconPath.FOOD_PROCESSING_GRANARY_ICON.getImageView(),
                IconPath.FOOD_PROCESSING_INN_ICON.getImageView(),
                IconPath.FOOD_PROCESSING_BAKERY_ICON.getImageView()
        };

        setAlignment(initialIconsInsideTheBar);
        foodProcessingBuildingPane = new Pane();
        foodProcessingBuildingPane.getChildren().addAll(initialIconsInsideTheBar);
        foodProcessingBuildingPane.setVisible(false);
        MapRendererG.buttomBar.getChildren().add(foodProcessingBuildingPane);
    }



    private void loadBarTownBuildings() {
        ImageView[] initialIconsInsideTheBar = {
               IconPath.TOWN_GOOD_THINGS_ICON.getImageView(),
                IconPath.TOWN_HOVEL_ICON.getImageView(),
                IconPath.TOWN_BAD_THINGS_ICON.getImageView(),
                IconPath.TOWN_APOTHECARY_ICON.getImageView(),
                IconPath.TOWN_CATHEDRAL_ICON.getImageView(),
                IconPath.TOWN_CHURCH_ICON.getImageView(),
        };

        setAlignment(initialIconsInsideTheBar);

        townBuildingPane = new Pane();
        townBuildingPane.getChildren().addAll(initialIconsInsideTheBar);
        townBuildingPane.setVisible(false);
        MapRendererG.buttomBar.getChildren().add(townBuildingPane);
    }

    private void loadBarWeaponBuildings() {
        ImageView[] initialIconsInsideTheBar = {
                IconPath.WEAPON_ARMORER_ICON.getImageView(),
                IconPath.WEAPON_BLACKSMITH_ICON.getImageView(),
                IconPath.WEAPON_POLE_TURNER_ICON.getImageView(),
                IconPath.WEAPON_FLETCHER_ICON.getImageView()
        };
        setAlignment(initialIconsInsideTheBar);

        weaponBuildingPane = new Pane();
        weaponBuildingPane.getChildren().addAll(initialIconsInsideTheBar);
        weaponBuildingPane.setVisible(false);
        MapRendererG.buttomBar.getChildren().add(weaponBuildingPane);
    }

    private void loadBarFarmBuildings() {

        ImageView[] initialIconsInsideTheBar = {
                IconPath.FARM_DAIRY_FARM_ICON.getImageView(),
                IconPath.FARM_WHEAT_FARM_ICON.getImageView(),
                IconPath.FARM_APPLE_ORCHARD_ICON.getImageView(),
                IconPath.FARM_HOPS_FARM_ICON.getImageView()
        };

        setAlignment(initialIconsInsideTheBar);

        farmBuildingPane = new Pane();
        farmBuildingPane.getChildren().addAll(initialIconsInsideTheBar);
        farmBuildingPane.setVisible(false);
        MapRendererG.buttomBar.getChildren().add(farmBuildingPane);
    }

    private void loadBarIndustryBuildings() {

        ImageView[] farmBuildingIcons = {
                IconPath.INDUSTRY_BAR_IRON_MINE_ICON.getImageView(),
                IconPath.INDUSTRY_BAR_WOOD_CUTTER_ICON.getImageView(),
                IconPath.INDUSTRY_BAR_QUARRY_ICON.getImageView(),
                IconPath.INDUSTRY_BAR_STOCKPILE_ICON.getImageView(),
                IconPath.INDUSTRY_BAR_MARKET_PLACE_ICON.getImageView(),
                IconPath.INDUSTRY_BAR_OX_TETHER_ICON.getImageView(),
                IconPath.INDUSTRY_BAR_PITCH_RIG_ICON.getImageView()
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
        icon.setOnMousePressed(this::handleMousePressed);
        icon.setOnMouseDragged(this::handleMouseDragged);
    }

    private void handleMousePressed(MouseEvent event) {
        // Store the initial position of the mouse click
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    private void handleMouseDragged(MouseEvent event) {
        // Calculate the distance moved by the mouse
        double deltaX = event.getSceneX() - xOffset;
        double deltaY = event.getSceneY() - yOffset;

        // Update the position of the ImageView
        ImageView imageView = (ImageView) event.getSource();
        imageView.setLayoutX(imageView.getLayoutX() + deltaX);
        imageView.setLayoutY(imageView.getLayoutY() + deltaY);

        // Store the new position of the mouse
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

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

}
