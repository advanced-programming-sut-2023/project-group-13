package model.Enums;

import javafx.scene.image.Image;
import model.BuildingType;
import model.TreeType;
import viewG.GUIController.MapRendererG;

import java.util.HashMap;


public class LoadImages {
    private static HashMap<TypeofGround, Image> tileImages = new HashMap<>();
    private static HashMap<BuildingType, Image> buildingImages = new HashMap<>();
    private static HashMap<TreeType, Image> treeImages = new HashMap<>();
    public static void loadAllImages() {
        loadTextures();
        loadBuildings();
        loadTrees();
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
        return buildingImages;
    }

    public static HashMap<TreeType, Image> getTreeImages() {
        return treeImages;
    }
}
