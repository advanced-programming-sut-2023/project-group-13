package view;

import controller.ControllerControllers;
import controller.MapMenuController;
import model.Enums.CommandsMap;
import model.Map;

import java.util.regex.Matcher;

public class MapMenu {
    private MapMenuController mapMenuController;
    private MainMenu mainMenu;
    private ControllerControllers controllerControllers;
    private Matcher matcher;

    public MapMenu(ControllerControllers controllerControllers) {
        this.controllerControllers = controllerControllers;
    }


//    public MapMenu(MapMenuController mapMenuController) {
//        this.mapMenuController = mapMenuController;
//    }

    public String run(MainMenu mainMenu) {
        String command;
        int choose = 2;
        System.out.println("do you want to create map?\n" +
                "type \"1\" for creating map and \"2\" to load a prebuilt map");
        choose = ScannerMatcher.getScanner().nextInt();
        if (choose == 1) {
            buildMapChoice();
        }
        else {
            System.out.println("enter the map name from this list:");
            for (int i = 0; i < Map.getMaps().size(); i++) {
                System.out.println(Map.getMaps().get());
                // todo to complete here
            }
            System.out.println(command = mapMenuController.loadMap());
            if (command.equals("there is no preload map!")) {
                System.out.println("you have to create a map before continuing or exit map menu!");
                System.out.println("type \"create\" for creating map and type \"exit\" for to exit from mapmenu");
                while(true) {
                    command = ScannerMatcher.getScanner().nextLine();
                    if (command.equals("create")) {
                        buildMapChoice();
                        break;
                    } else if (command.matches("^back$")) {
                        mainMenu.run();
                    } else System.out.println("invalid command!");
                }
            }
        }
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
                        ,matcher.group("type")));
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
            } else if (command.matches("^back$")) {
                System.out.println("you are in the main menu!");
                mainMenu.run();
            }
            else if(!command.equals("")) System.out.println("Map menu: invalid command!");
        }
    }

    private void buildMapChoice() {
        int sizeOfTheMap;
        System.out.println("what is your favorite size of map?\n" +
                "if 200 * 200:\t\tenter 1\nif 400 * 400:\t\tenter 2");
        sizeOfTheMap = ScannerMatcher.getScanner().nextInt();
        System.out.println(mapMenuController.setSize(sizeOfTheMap));
    }

    public MapMenuController getMapMenuController() {
        return mapMenuController;
    }

    public void setMapMenuController(MapMenuController mapMenuController) {
        this.mapMenuController = mapMenuController;
    }
}

