package view;

import com.google.gson.reflect.TypeToken;
import controller.ControllerControllers;
import controller.MapMenuController;
import model.Enums.CommandsMap;
import model.Enums.TypeofGround;
import model.Map;
import model.SaveAndLoadData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class MapMenu {
    private MapMenuController mapMenuController;
    private MainMenu mainMenu;
    private String mapName;
    private static ArrayList<Map> maps = new ArrayList<>();
    private ControllerControllers controllerControllers;
    private Matcher matcher;

    public MapMenu(ControllerControllers controllerControllers) {
        this.controllerControllers = controllerControllers;
    }


//    public MapMenu(MapMenuController mapMenuController) {
//        this.mapMenuController = mapMenuController;
//    }

    public String run(MainMenu mainMenu) throws IOException, InterruptedException {
        this.mainMenu = mainMenu;
        String command;
//        int choose = 2, counter = 0;
        System.out.println("do you want to create map?\n" +
                "type \"create new map -n mapName -s size\" for creating map\n" +
                "type\"load map -n mapName\" to load a prebuilt map\n");
        while (true) {
            command = ScannerMatcher.getScanner().nextLine();
            if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.SHOWMAPCOORDINATED.getPattern())) != null) {
                mapMenuController.showMapCoordinated(Integer.parseInt(matcher.group("x")),Integer.parseInt(matcher.group("y")));
            } else if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.SHOWMAP.getPattern())) != null) {
                mapMenuController.showMap(Integer.parseInt(matcher.group("sizeOfMap")));
            } else if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.MOVEMAP.getPattern())) != null) {
                System.out.println(mapMenuController.moveMap(matcher));
            } else if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.SHOWDETAIL.getPattern())) != null) {
                System.out.println(mapMenuController.showDetails(matcher));
            } else if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.SETTEXTURESINGLEBLOCK.getPattern())) != null) {
                System.out.println(mapMenuController.setTextureOfTheSingleBlock
                        (Integer.parseInt(matcher.group("x")),Integer.parseInt(matcher.group("y"))
                        , matcher.group("type")));
            } else if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.SETTEXTUREAREA.getPattern())) != null) {
                System.out.println(mapMenuController.setTextureOfAnArea(matcher));
            } else if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.CLEARGROUND.getPattern())) != null) {
                System.out.println(mapMenuController.clearGround(matcher));
            } else if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.DROPROCK.getPattern())) != null) {
                System.out.println(mapMenuController.dropRock(matcher));
            } else if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.DROPTREE.getPattern())) != null) {
                System.out.println(mapMenuController.dropTree(matcher));
            } else if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.DROPBUILDING.getPattern())) != null) {
                System.out.println(mapMenuController.dropBuilding(matcher));
            } else if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.DROPUNIT.getPattern())) != null) {
                System.out.println(mapMenuController.dropUnit(matcher));
            } else if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.CREATENEWMAP.getPattern())) != null) {
                System.out.println(mapMenuController.CreateMap(matcher));
            } else if ((matcher = ScannerMatcher.getMatcher(command, CommandsMap.LOADMAP.getPattern())) != null) {
                System.out.println(mapMenuController.loadMap(matcher));
            } else if (command.matches("^back$")) {
                System.out.println("you are in the main menu!");
                controllerControllers.runMainMenu();
            } else if (!command.equals("")) System.out.println("Map menu: invalid command!");
        }
    }

    public MapMenuController getMapMenuController() {
        return mapMenuController;
    }

    public void setMapMenuController(MapMenuController mapMenuController) {
        this.mapMenuController = mapMenuController;
    }
}

