package controller;

import model.*;
import model.Enums.DataEnumFile;
import model.Enums.TypeofGround;
import view.MapMenu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class MapMenuController {
    private ControllerControllers controllerControllers;
    public MapMenuController(ControllerControllers controllerControllers) {
        this.controllerControllers = controllerControllers;
    }

    private MapMenu mapMenu;
    private Cell cell;
    private Cell[][] loaded_map;
    private ArrayList<Map> maps = new ArrayList<>();
    private boolean isMapExist;

    private int size_of_map;

    private int x_initial;
    private int y_initial;


    private Map map;

    public void showMap(int sizeOfMap) {
        if (sizeOfMap != 200 && sizeOfMap != 400) {
            System.out.println("invalid size of the map");
        }
        int scaleofmap = (sizeOfMap == 200 ? 81: 161);
        for (int i = 0; i < scaleofmap; i++) {
            System.out.println("");
            for (int j = 0; j < 121; j++) {
                if (i % 4 == 0) {
                    System.out.print("-");
                }
                if (i % 4 != 0) {
//                    System.out.print("|     ");
                    if (j != 20)
                        System.out.print("|" + "\033[43m" + "#####" + "\033[0m");
                    if (j == 20) {
                        System.out.print("|");
                        break;
                    }
                }
            }
        }
    }

    public void showMapCoordinated(int x, int y) {
        x_initial = x;
        y_initial = y;
        int scaleOfBiggerMap = 0;
        if (getSize_of_map() == 400) scaleOfBiggerMap = 200;
        if (y > 192 + scaleOfBiggerMap && x > 197 + scaleOfBiggerMap) {
            x = 197 + scaleOfBiggerMap;
            y = 192 + scaleOfBiggerMap;
        }
        if (x > 197 + scaleOfBiggerMap && y <= 192 + scaleOfBiggerMap) {
            x = 197 + scaleOfBiggerMap;
        }
        if (y >= 192 + scaleOfBiggerMap && x <= 197 + scaleOfBiggerMap) {
            y = 192 + scaleOfBiggerMap;
        }
        if (y < 7 && x < 2){
            y = 7;
            x = 2;
        }
        if (y <= 7 && x >= 2) {
            y = 7;
        }
        if (y >= 7 && x < 2) {
            x = 2;
        }
        for (int i = 0; i < 21; i++) {
            System.out.println("");
            for (int j = 0; j < 120; j++) {
                if (i % 4 == 0) {
                    System.out.print("-");
                }
                if (i % 4 != 0) {
//                    System.out.print("|     ");
                    if (j != 15) {
                        if (i > 0 && i <= 3)
                            specifyTypeOfGround(x - 2, y - 7 + j, i, j);
                        else if (i > 4 && i <= 7)
                            specifyTypeOfGround(x - 1, y - 7 + j, i, j);
                        else if (i > 8 && i <= 11)
                            specifyTypeOfGround(x, y - 7 + j, i, j);
                        else if (i > 12 && i <= 15)
                            specifyTypeOfGround(x + 1, y - 7 + j, i, j);
                        else if (i > 16 && i <= 19)
                            specifyTypeOfGround(x + 2, y - 7 + j, i, j);
                    }
//                        System.out.print("|" + "\033[43m" + "#######" + "\033[0m");
                    if (j == 15) {
                        System.out.print("|");
                        break;
                    }
                }
            }
        }
    }

    private void specifyTypeOfGround(int x, int y, int i, int j) {
        cell = map.getMapCells(x,y);
        if (cell.getTypeofGround().equals(TypeofGround.EARTH.getFullNameType()))
            printMap(TypeofGround.EARTH.getFullNameType(), i, x, y, j);
        else if (cell.getTypeofGround().equals(TypeofGround.GRASS.getFullNameType()))
            printMap(TypeofGround.GRASS.getFullNameType(), i, x, y, j);
        else if (cell.getTypeofGround().equals(TypeofGround.DENSEMEADOW.getFullNameType()))
            printMap(TypeofGround.DENSEMEADOW.getFullNameType(), i, x, y, j);
        else if (cell.getTypeofGround().equals(TypeofGround.MEADOW.getFullNameType()))
            printMap(TypeofGround.MEADOW.getFullNameType(), i, x, y, j);
        else if (cell.getTypeofGround().equals(TypeofGround.ROCKY.getFullNameType()))
            printMap(TypeofGround.ROCKY.getFullNameType(), i, x, y, j);
        else if (cell.getTypeofGround().equals(TypeofGround.STONEY.getFullNameType()))
            printMap(TypeofGround.STONEY.getFullNameType(), i, x, y, j);
        else if (cell.getTypeofGround().equals(TypeofGround.SANDY.getFullNameType()))
            printMap(TypeofGround.SANDY.getFullNameType(), i, x, y, j);
        else if (cell.getTypeofGround().equals(TypeofGround.IRON.getFullNameType()))
            printMap(TypeofGround.IRON.getFullNameType(), i, x, y, j);
        else if (cell.getTypeofGround().equals(TypeofGround.SEA.getFullNameType()))
            printMap(TypeofGround.SEA.getFullNameType(), i, x, y, j);

    }
    //todo to complete this part of the code


    private void printMap(String fullNameType, int i, int x, int y, int j) {
//        if (x == x_initial) {
//            x = x_initial;
//        }
        int scaleofBiggermap = 0;
        if (getSize_of_map() == 400) scaleofBiggermap = 200;
        String hash = "######" , hashForSoldier = "####";
        String star = "******";
        String colorstar = "\033[1;31m" + "******", starForSoldier = "\033[1;31m" + "****";
        String selectFormating = hash;
//        String selectFormatingForSoldier = hashForSoldier;
        // todo to handle number of the soldiers in a cell
        if (y_initial < 15 && x < 1) {
            if (j == y_initial && x == x_initial) {
                selectFormating = colorstar;
//                selectFormatingForSoldier = starForSoldier;
            }
        }
        if (y_initial > 185 + scaleofBiggermap && x > 198 + scaleofBiggermap) {
            if (j == (y_initial - 185 - scaleofBiggermap) && x == x_initial) {
//                    selectFormatingForSoldier = starForSoldier;
                selectFormating = colorstar;
            }

        }
        else if (x == x_initial && y == y_initial)
            selectFormating = colorstar;
        if(cell.isHasSoldierInCell() && (i % 4 == 2)) {
            int countSoldiers = cell.getSoldiers().size();

            String numberString = Integer.toString(countSoldiers);
            if (numberString.length() < selectFormating.length()) {
                colorstar = "\033[1;32m" + numberString + star.substring(numberString.length());
                selectFormating = colorstar;
            }
            System.out.print("|" + groundColor.GETCOLOR.getColorByName(fullNameType)
                    + "S" + selectFormating + groundColor.RESET.getColor());
            return;
        }
        if (cell.isHasBuildingInCell() && (i % 4 == 1)) {
            System.out.print("|" + groundColor.GETCOLOR.getColorByName(fullNameType)
                    + "B" + selectFormating + groundColor.RESET.getColor());
            return;
        }
        if (cell.isHasTreeInCell() && (i % 4 == 3)) {
            System.out.print("|" + groundColor.GETCOLOR.getColorByName(fullNameType)
                    + "T" + selectFormating + groundColor.RESET.getColor());
            return;
        }
        else System.out.print("|" + groundColor.GETCOLOR.getColorByName(fullNameType)
                + "#" + selectFormating + groundColor.RESET.getColor());

    }

    public String moveMap(Matcher matcher) {
        //todo complete this part after new regex handling
        String direction;
        int number = 0;
        for (int i = 1; i <= 4; i++) {
            direction = matcher.group("direction" + i) != null ? matcher.group("direction" + i).replaceAll(" ","") : "null";
            number = matcher.group("number" + i) != null ? Integer.parseInt(matcher.group("number" + i).replaceAll(" ","")) : 1;
            moveMapForAllDirection(direction, number);
        }
        if(!checkNegativity(x_initial,y_initial))
            return "negative index!";
        showMapCoordinated(x_initial,y_initial);
        return "\nmap moved successfully";
    }

    private void moveMapForAllDirection(String direction, int number) {
        if (direction.equals("up"))
            x_initial += number;
        else if (direction.equals("down"))
            x_initial -= number;
        else if (direction.equals("right"))
            y_initial += number;
        else if (direction.equals("left"))
            y_initial -= number;
    }

    public String showDetails(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!checkNegativity(x, y))
            return "negative index!";
        cell = map.getMapCells(x,y);
        System.out.println("Type of ground is :" + cell.getTypeofGround());
        if (cell.isHasBuildingInCell()) {
            System.out.println("Type of the building is : " + cell.getBuilding().getName());
        }
        if (cell.isHasSoldierInCell()) {
            //todo to complete this part after soldier completion
        }
        if (cell.isHasTreeInCell()) {
            System.out.println("Type of the tree is: " + cell.getTree().getName());
        }
        return "showing detail successful!";

    }

    public String setTextureOfTheSingleBlock(int x, int y, String type) {
        if (!checkNegativity(x, y)) {
            return "negative index!";
        }
        map.getMapCells(x,y).setTypeofGround(type);
        map.SavetoJason();
        return "change texture of ground successful";
        // todo to complete this method
    }

    public String setTextureOfAnArea(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        if (!checkNegativity(x1, x2, y1, y2)) {
            return "negative index!";
        }
        String type = matcher.group("type");
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                map.getMapCells(i,j).setTypeofGround(type);
            }
        }
        map.SavetoJason();
        // todo to complete this method
        return "setTexture of an area successful!";
    }

    public String clearGround(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if(!checkNegativity(x,y))
            return "negative index!";
        map.getMapCells(x,y).remove();
        map.SavetoJason();
        //todo to deal with the soldires in this function
        return "clear tile successful";
    }
    public String dropRock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String direction = matcher.group("direction");
        if (!checkNegativity(x,y))
            return "negative index!";
        if (!direction.equals("n") && !direction.equals("s") && !direction.equals("w")
                && !direction.equals("e") && !direction.equals("r"))
            return "direction not valid!";
        if (direction.equals("r"))
            direction = "s";
        map.getMapCells(x,y).setTypeofGround(TypeofGround.ROCKY.getFullNameType());
        map.SavetoJason();
        return "dropped rock successful";
        // todo to upgrade the random direction to a better thing
        // todo to later on upgrade the direction of the rock
    }

    private boolean checkTypeOfGround(String dropType, int x, int y, String drop) {
        cell = map.getMapCells(x, y);
        String typeOfGround = map.getMapCells(x,y).getTypeofGround();
        if (dropType.equals("Soldier")) {
            if (typeOfGround.equals(TypeofGround.SEA.getFullNameType()) ||
                    typeOfGround.equals(TypeofGround.BIGPOOL.getFullNameType())) {
                return false;
                // todo to print in the calling method to sout the relevant message
            }
            if (cell.getBuilding() != null){
                if (cell.getBuilding().getName().equals(BuildingType.SQUARE_TOWER.getName()) ||
                        cell.getBuilding().getName().equals(BuildingType.ROUND_TOWER.getName()) ||
                        cell.getBuilding().getName().equals(BuildingType.LOOKOUT_TOWER.getName())
                        || cell.getBuilding().getName().equals(BuildingType.PERIMETER_TOWER.getName())) {
                    if (drop.equals(SoldierType.HORSE_ARCHER.getName())) {
                        return false;
                        // todo to print the relevant message in here
                    }
                    if (drop.equals(SoldierType.KNIGHT.getName())) {
                        return false;
                        // todo to print the relevant message in here
                    }
                }
                if (cell.isHasTreeInCell()) {
                    return false;
                    // todo to print the relevant message in here
                }
            }
        } else if (dropType.equals("Building") || dropType.equals("Tree")) {
            if (typeOfGround.equals(TypeofGround.BIGPOOL.getFullNameType()) ||
                    typeOfGround.equals(TypeofGround.SEA.getFullNameType())
                    || typeOfGround.equals(TypeofGround.IRON.getFullNameType()) ||
                    typeOfGround.equals(TypeofGround.RIVER.getFullNameType()) ||
                    typeOfGround.equals(TypeofGround.OIL.getFullNameType()) ||
                    typeOfGround.equals(TypeofGround.LOWDEPTHWATER.getFullNameType())
                    || typeOfGround.equals(TypeofGround.LITTLEPOOL.getFullNameType())
                    || typeOfGround.equals(TypeofGround.STONEY.getFullNameType())
                    || typeOfGround.equals(TypeofGround.ROCKY.getFullNameType())) {
                return false;
                // todo to print relevant message in the calling method
            }
            if (cell.isHasSoldierInCell() || cell.isHasTreeInCell() || cell.isHasBuildingInCell()) {
                return false;
                // todo to print relevant message in the calling method
            }
        }
        return true;
    }

    public String dropTree(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        if (!checkNegativity(x, y)) {
            return "negative index!";
        }
        if (!checkTypeOfGround("Tree", x, y, type)) {
            return "you can't drop here!";
        }
//            if (cell.getTypeofGround().equals("water")) {
//                return "you can't drop tree in water";
//            }
        if (!cell.setTree(type, x, y)) {
            return "there is no tree with this typename";
        }
        cell.setHasTreeInCell(true);
        map.SavetoJason();
        return "tree dropped successfully";
        //todo to complete this method
    }

    public String dropBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        cell = map.getMapCells(x,y);
        String type = matcher.group("type");
        if (!checkNegativity(x, y)) {
            return "negative index!";
        }
        if (!checkTypeOfGround("Building", x, y, type)) {
            return "you can't drop building in here";
        }
        if (cell.getTypeofGround().equals("Water")) {
            return "you can't drop building in here";
            // todo to put check type of ground in an enum which needs more effort
        }
//            if (cell.getTypeofGround().equals("Water")) {
//                return "you can't drop building in here";
//                // todo to put check type of ground in an enum which needs more effort
//            }
        if (!cell.setBuilding(type,x,y)) {
            return "there is no building with this type name!";
        }
        cell.setHasBuildingInCell(true);
        map.SavetoJason();
        return "building dropped successfully";
        // todo to complete this method
    }

    // todo later on change the int types to the Integer
    public String dropUnit(Matcher matcher) {
        if (!checkPosition(matcher)) {
            return "invalid position";
        }
        int count = Integer.parseInt((matcher.group("count")));
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        checkNegativity(x,y,count);
        if (!checkTypeOfGround("Soldier", x, y, type)) {
            return "you can't drop soldier here!";
        }
        cell = map.getMapCells(x,y);
//            if (cell.getTypeofGround().equals("Water")) {
//                return "you can't drop units in water area";
//            }
        for (int i = 0 ; i < count; i++)
            cell.putTroop(type, Empire.getCurrentEmpire(),x , y);
        cell.setHasSoldierInCell(true);
        map.SavetoJason();
        return "dropping unit was successful";
    }

    private boolean checkNegativity(int... param) {
        for (int a : param) {
            if (a < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPosition(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (x < 0 || x > 400 || y < 0 || y > 400) {
            return false;
        }
        return true;
    }
    public String CreateMap(Matcher matcher) throws IOException {
        int size = Integer.parseInt(matcher.group("size"));
        String mapName = matcher.group("mapName");
        File file = new File(DataEnumFile.MAPS.getFileName());
        if (file.exists()) {
            maps = SaveAndLoadData.LoadData(DataEnumFile.MAPS.getFileName(), DataEnumFile.MAPS.getDataType());
            for (Map map1 : maps) {
                if (map1.getMapName().equals(mapName))
                    return "a map with this name already exists!";
            }
        }
        if (!checkNegativity(size)) return "invalid size";
        if (size != 200 && size != 400) return "map size can only be 200 or 400";
        map = new Map(size,mapName);
        setSize_of_map(size);
        map.createMap();
        return "map created successfully with size" + size + "!";
    }

    public int getSize_of_map() {
        return size_of_map;
    }

    public void setSize_of_map(int size_of_map) {
        this.size_of_map = size_of_map;
    }

    public String loadMap(Matcher matcher) throws IOException {
        String mapName = matcher.group("mapName");
        // todo to upgrade the size of the map while loading the map;
        if ((loaded_map = Map.loadMap(mapName)) == null) {
            return "there is no such map!";
        }
        map = new Map(loaded_map.length, mapName);
        map.setMap(loaded_map);
        return "map loaded successfully!";
    }

    public boolean CheckMapExist(String command, ArrayList<Map> maps) {
        for (Map map1 : maps) {
            if (map1.getMapName().equals(command))
                setMapExist(true);
        }
        return isMapExist();
    }

    public void setMapExist(boolean mapExist) {
        isMapExist = mapExist;
    }

    public boolean isMapExist() {
        return isMapExist;
    }
}
