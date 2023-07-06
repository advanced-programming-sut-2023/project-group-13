package model.Enums.Images;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.BuildingType;
import model.Enums.TypeofGround;
import model.TreeType;
import viewG.GUIController.MapRendererG;

import java.util.HashMap;


public class LoadImages {
    private static HashMap<TypeofGround, Image> tileImages = new HashMap<>();
    private static HashMap<BuildingType, Image> buildingImages = new HashMap<>();
    private static HashMap<TreeType, Image> treeImages = new HashMap<>();

    private static ImageView[] icons;
    public static void loadAllImages() {
        loadTextures();
        loadBuildings();
        loadTrees();
    }

    public static ImageView[] loadBuildingIcons() {
        ImageView[] icons = {
                IconPath.CASTLE_BUILDING_ICON.getIconImageView(),
                IconPath.FARM_BUILDING_ICON.getIconImageView(),
                IconPath.INDUSTRY_BUILDING_ICON.getIconImageView(),
                IconPath.WEAPON_BUILDING_ICON.getIconImageView(),
                IconPath.TOWN_BUILDING_ICON.getIconImageView()
        };
    return icons;
    }

    private static void loadTextures() {
        for (TypeofGround groundType : TypeofGround.values()) {
            Image tileImage = new Image(MapRendererG.class.getResource(groundType.getFilePath()).toExternalForm());
            tileImages.put(groundType, tileImage);
        }
    }

    private static void loadBuildings() {
        for (BuildingType buildingType : BuildingType.values()) {
            Image buildingImage = new Image(MapRendererG.class.getResource(buildingType.getFilePath()).toExternalForm());
            buildingImages.put(buildingType, buildingImage);
        }
    }


    private static void loadTrees() {
        for (TreeType treeType : TreeType.values()) {
            Image treeImage = new Image(MapRendererG.class.getResource(treeType.getFilePath()).toExternalForm());
            treeImages.put(treeType,treeImage);
        }
    }

    public static HashMap<TypeofGround, Image> getTileImages() {
        return tileImages;
    }

    public static HashMap<BuildingType, Image> getBuildingImages() {
        System.out.println("it successfully loads the image");
        return buildingImages;
    }

    public static HashMap<TreeType, Image> getTreeImages() {
        return treeImages;
    }
}
