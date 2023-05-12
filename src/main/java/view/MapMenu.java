package view;

import com.google.gson.reflect.TypeToken;
import controller.ControllerControllers;
import controller.MapMenuController;
import model.Enums.CommandsMap;
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

//    private void choose() throws IOException, InterruptedException {
//        int choose;
//        String command;
//        choose = ScannerMatcher.getScanner().nextInt();
//        ScannerMatcher.getScanner().nextLine();
//        if (choose == 1) {
//            buildMapChoice();
//        }
//        else if (choose == 2) {
//            System.out.println("enter the map name from this list:");
//            loadMapChoice();
//            System.out.println(command = mapMenuController.loadMap(mapName));
//            if (command.equals("there is no preload map!")) {
//                System.out.println("you have to create a map before continuing or exit map menu!");
//                System.out.println("type \"create\" for creating map and type \"back\" for to exit from mapmenu");
//                while(true) {
//                    command = ScannerMatcher.getScanner().nextLine();
//                    if (command.equals("create")) {
//                        buildMapChoice();
//                        break;
//                    } else if (command.matches("^back$")) {
//                        controllerControllers.runMainMenu();
//                    } else System.out.println("invalid command!");
//                }
//            }
//        } else if (choose == 3) {
//            System.out.println("enter main menu!");
//            controllerControllers.runMainMenu();
//        }
//        else{
//            System.out.println("map menu: choose: invalid number");
//            choose();
//        }
//    }

//    private void loadMapChoice() throws IOException, InterruptedException {
//        String command;
//        int counter = 0;
//        int select = 0;
////        ScannerMatcher.getScanner().nextLine();
//        // i added this line in order to consume the remaining new line character and empty the buffer
//        if ((maps = SaveAndLoadData.LoadData("Maps.json", new TypeToken<ArrayList<Map>>(){}.getType())).size() == 0) {
//            System.out.println("you don't have any preload map!");
//            System.out.println("you have to build a map first!");
//            System.out.println("type \"create\" for creating map and type \"back\" for to exit from mapmenu");
//            while (true) {
//                command = ScannerMatcher.getScanner().nextLine();
//                if (command.equals("create")) {
//                    buildMapChoice();
//                    System.out.println("now do you want to load your map?\n" +
//                            "enter 1 if you want to load it and enter 2 if you want to exit");
//                    while(true) {
//                        select = ScannerMatcher.getScanner().nextInt();
//                        if (select == 1) return;
//                        else if (select == 2) controllerControllers.runMainMenu();
//                        else System.out.println("map menu: loadmap: invalid number");
//                    }
//                }
//                else if (command.equals("back")) {
//                    controllerControllers.runMainMenu();
//                }
//                    // todo here may cause some bug
//                else {
//                    System.out.println("map menu: loadmap: invalid command!");
//                }
//            }
//        }
//            for (Map map : maps) {
//                System.out.println(++counter + "." + map.getMapName());
//            }
//            command = ScannerMatcher.getScanner().nextLine();
//        if (!mapMenuController.CheckMapExist(command, maps)) {
//            System.out.println("please enter a valid map name!");
//            loadMapChoice();
//            // todo to add a back or exit option here for the user
//        }
//            mapName = command;
//    }

//    private void buildMapChoice() {
//        System.out.println("what is your favorite size of map?\n" +
//                "if 200 * 200:\t\tenter 1\nif 400 * 400:\t\tenter 2");
//        int sizeOfTheMap;
//        String command;
//        sizeOfTheMap = ScannerMatcher.getScanner().nextInt();
//        ScannerMatcher.getScanner().nextLine();
//        if (sizeOfTheMap != 1 && sizeOfTheMap != 2) {
//            System.out.println("Map menu: buildMap: invalid command");
//            return;
//        }
//        // added this line of the code to empty the buffer
//        System.out.println("choose a name for your map:");
//        command = ScannerMatcher.getScanner().nextLine();
//        System.out.println(command = mapMenuController.CreateMap(sizeOfTheMap,command));
//        if (command.equals("map creation not successful please enter the correct size!")) {
//            buildMapChoice();
//            // todo to add a back or exit command here for the user
//        }
//        mapName = command;
//    }

    public MapMenuController getMapMenuController() {
        return mapMenuController;
    }

    public void setMapMenuController(MapMenuController mapMenuController) {
        this.mapMenuController = mapMenuController;
    }
}

