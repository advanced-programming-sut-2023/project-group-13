package viewG.GUIController;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Enums.Images.IconPath;

public class BarImages {
    private Pane barFarmBuildingPane;
    private Pane barIndustryBuildingPane;
    private Pane barWeaponBuildingPane;
    private Pane barTownBuildingPane;
    private Pane barFoodProcessingBuildingPane;

    private ImageView barFarmBuildingImageView;
    private ImageView barIndustryBuildingImageView;
    private ImageView barWeaponBuildingImageView;
    private ImageView barTownBuildingImageView;
    private ImageView barFoodProcessingImageView;

    private int layoutXEmptyBar = 200;
    private int layoutYEmptyBar = 200;

    public void loadBars() {
        loadBarFarmBuildings();
        loadBarIndustryBuildings();
        loadBarWeaponBuildings();
        loadBarTownBuildings();
        loadBarFoodProcessingBuildings();
    }

    private void loadBarFoodProcessingBuildings() {

    }

    private void loadBarTownBuildings() {

    }

    private void loadBarWeaponBuildings() {

    }

    private void loadBarIndustryBuildings() {

    }

    private void loadBarFarmBuildings() {
        barFarmBuildingPane = new Pane();
        barFarmBuildingPane.setLayoutX(layoutXEmptyBar);
        barFarmBuildingPane.setLayoutX(layoutYEmptyBar);

        barFarmBuildingImageView = IconPath.EMPTY_BAR.getImageView();
        loadFarBuildingImagesOnBar();
    }

    private void loadFarBuildingImagesOnBar() {
        int baseLayoutX = 50;
        int baseLayoutT = 50;
        int incrementSpacing = 45;

        ImageView[] farmBuildingIcons = {
          IconPath.

        };
    }
}
