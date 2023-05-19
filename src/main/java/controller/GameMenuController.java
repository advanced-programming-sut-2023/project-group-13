package controller;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import model.Empire;
import model.Map;

public class GameMenuController {
    public static Map map;
    public static ArrayList<Player> playersInGame = new ArrayList<>();
    public static Empire currentEmpire;
    private static MapMenuController mapMenuController = new MapMenuController();
    public static HashMap<ArrayList<Soldier>, List<Cell>> paths = new HashMap<ArrayList<Soldier>, List<Cell>>();
    public static HashMap<ArrayList<Soldier>, List<Cell>> pathsPatrol = new HashMap<ArrayList<Soldier>, List<Cell>>();

    public static ArrayList<Soldier> selectedSoldiers = new ArrayList<>();
    public static ArrayList<Soldier> selectedSoldiersPatrol;

    public static ArrayList<Empire> empires = new ArrayList<>();
    public static Building selectedBuilding;
    public static int roundNumber = 0;
    private static Soldier soldierToBeRemoved;
    public static Empire CurrentEmpire;
    public static BuildingType buildingType;
    private static int select_x;
    private static List<Cell> path;
    private static int select_y;
    public static ArrayList<Soldier> selected_soldiers = new ArrayList<>();
    private static List<Cell> Apath;
    private static ArrayList<Soldier> select;


    public static String nextTurn() {
        roundNumber++;
        setCurrentEmpire(getEmpires().get(roundNumber % getEmpires().size()));

        getPaths().forEach((key, value) -> {
            Iterator<Soldier> iterator = key.iterator();

            while (iterator.hasNext()) {

                Soldier soldier = iterator.next();

                if (soldier.isStop()) {
                    soldier.reSetTotalMove(0);
                    iterator.remove();
                    continue;
                }
                soldier.setTotalMove(soldier.getSoldierType().getMovementRange());
                map.getMapCells(soldier.getX(), soldier.getY()).removeTroop(soldier);



                if (soldier.getTotalMove() - 1 < value.size()) {
                    Cell cell = value.get(soldier.getTotalMove() - 1);
                    cell.moveTroop(soldier);
                } else {
                    Cell cell = value.get(value.size() - 1);
                    cell.moveTroop(soldier);
                }
                if (soldier.getX() == value.get(value.size() - 1).getX()
                        && soldier.getY() == value.get(value.size() - 1).getY()) {
                    if (soldier.isPatrol() && soldier.getX() == soldier.getPatrol_x_start()
                    && soldier.getY() == soldier.getPatrol_y_start()
                    && !soldier.isStopPatrol()) {
                        setSelect_x(soldier.getPatrol_x_start());
                        setSelect_y(soldier.getPatrol_y_start());
                        moveElemets(soldier.getPatrol_x_end(), soldier.getPatrol_y_end(), selectedSoldiersPatrol);
                        int temp_x = soldier.getPatrol_x_start();
                        int temp_y = soldier.getPatrol_y_start();
                        soldier.setPatrol_x_start(soldier.getPatrol_x_end());
                        soldier.setPatrol_y_start(soldier.getPatrol_y_end());
                        soldier.setPatrol_x_end(temp_x);
                        soldier.setPatrol_y_end(temp_y);
                        pathsPatrol.put(select, Apath);
                    }
                    soldier.reSetTotalMove(0);
                    iterator.remove();
                }
            }

        });
        paths.putAll(pathsPatrol);
        selectedSoldiers.clear();
        return "moved to the next turn";
    }

    public static void calculateFood() {

    }

    public static void calculateTax() {

    }

    public static void calculateArmouryThings() {

    }

    public static void calculateStockpileThings() {

    }

    //   public static void calculate
//    public static String dropUnit(Matcher matcher) {
//        String unitType = matcher.group("type");
//        if (checkAmount(matcher.group("number"))) return "Invalid number of unit";
//        int number = Integer.parseInt(matcher.group("number"));
//        SoldierType soldierType = SoldierType.getSoldierTypeByString(unitType);
//        if (soldierType == null) return "Invalid type: there is no unit with this type.";
//        if (soldierType.isArab() && buildingType != BuildingType.MERCENARY_POST)
//            return "please select <<mercenary post>> building.";
//        if (buildingType != BuildingType.CHURCH && unitType.equals("black monk"))
//            return "please select <<church>> building.";
//        if (buildingType != BuildingType.ENGINEERS_GUILD &&
//                (unitType.equals("tunneler") || unitType.equals("engineer")))
//            return "please select <<engineers guild>> building";
//        if (buildingType != BuildingType.BARRACKS && !soldierType.isArab() && soldierType != SoldierType.BLACK_MONK && soldierType != SoldierType.ENGINEER && soldierType != SoldierType.TUNNELER)
//            return "please select <<barracks>> building.";
//        if (soldierType.getMoneyCost() * number > currentEmpire.getTotalGoldAmount())
//            return "not enough amount of money.";
//        if (number > currentEmpire.getNoneWorkerAmount())
//            return "not enough amount of none worker people";
//
//        switch (soldierType.getName()) {
//            case "archer":
//
//                if (getWeaponAmount("bow") < number) {
//                    return "bows needed";
//                }
//                removeWeaponFromArmouries("bow", -1 * number);
//
//
//            case "crossbowman":
//                if (getWeaponAmount("crossbow") < number || getWeaponAmount("leather armor") < number) {
//                    return "crossbow/leather armor needed";
//                }
//                removeWeaponFromArmouries("crossbow", -1 * number);
//                removeWeaponFromArmouries("leatherArmor", -1 * number);
//
//            case "spearman":
//                if (getWeaponAmount("spearman") < number) {
//                    return "spear needed";
//                }
//                removeWeaponFromArmouries("spear", -1 * number);
//
//            case "pikeman":
//                if (getWeaponAmount("pike") < number || getWeaponAmount("metal armor") < number) {
//                    return "pike/metal armor needed";
//                }
//                removeWeaponFromArmouries("pike", -1 * number);
//                removeWeaponFromArmouries("metalArmor", -1 * number);
//
//            case "maceman":
//                if (getWeaponAmount("mace") < number || getWeaponAmount("leather armor") < number) {
//                    return "mace/leather armor needed";
//                }
//                removeWeaponFromArmouries("mace", -1 * number);
//                removeWeaponFromArmouries("leatherArmor", -1 * number);
//
//            case "swordsman":
//                if (getWeaponAmount("sword") < number || getWeaponAmount("metal armor") < number) {
//                    return "sword/metal armor needed";
//                }
//                removeWeaponFromArmouries("sword", -1 * number);
//                removeWeaponFromArmouries("metalArmor", -1 * number);
//
//            case "knight":
//                if (getWeaponAmount("sword") < number || getWeaponAmount("metal armor") < number || getHorseAmount() < number) {
//                    return "sword/metal armor/horse needed";
//                }
//                removeWeaponFromArmouries("sword", -1 * number);
//                removeWeaponFromArmouries("metalArmor", -1 * number);
//                removeHorsesFromStables(-1 * number);
//
//            default:
//                return "success.";
//        }
//    }


    public static Boolean checkCordination(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        // emad size map ro mikham
        if (x < 0 || y < 0 || x > NewGameController.getCurrent_map().getSizeOfTheMap() - 1 || y > NewGameController.getCurrent_map().getSizeOfTheMap() - 1) {
            return false;
        }
        return true;
    }

    public static Boolean checkAmount(String n) {
        int number = Integer.parseInt(n);
        return number >= 0;
    }

    public static int getWeaponAmount(String name) {
        int amount = 0;
        for (Armoury armoury : currentEmpire.getArmouries()) {
            if (name.equals("mace")) {
                amount += armoury.getMaceAmount();
            } else if (name.equals("bow")) {
                amount += armoury.getBowAmount();
            } else if (name.equals("crossbow")) {
                amount += armoury.getCrossBowAmount();
            } else if (name.equals("sword")) {
                amount += armoury.getSwordAmount();
            } else if (name.equals("metal armor")) {
                amount += armoury.getMetalArmourAmount();
            } else if (name.equals("leather armor")) {
                amount += armoury.getLeatherArmourAmount();
            } else if (name.equals("spear")) {
                amount += armoury.getSpearAmount();
            } else if (name.equals("pike")) {
                amount += armoury.getPikeAmount();
            }
        }
        return amount;
    }

    public static void addWeaponToArmouries(String name, int amount) {

        for (Armoury armoury : currentEmpire.getArmouries()) {
            if (armoury.getCapacity() >= armoury.getTotalWeaponAmount() + amount) {
                if (name.equals("mace")) {
                    armoury.addMaceAmount(amount);
                } else if (name.equals("bow")) {
                    armoury.addBowAmount(amount);
                } else if (name.equals("crossbow")) {
                    armoury.addCrossBowAmount(amount);
                } else if (name.equals("sword")) {
                    armoury.addSwordAmount(amount);
                } else if (name.equals("metal armor")) {
                    armoury.addMetalArmourAmount(amount);
                } else if (name.equals("leather armor")) {
                    armoury.addLeatherArmourAmount(amount);
                } else if (name.equals("spear")) {
                    armoury.addSpearAmount(amount);
                } else if (name.equals("pike")) {
                    armoury.addPikeAmount(amount);
                }
                break;
            } else {
                int remainSpace = armoury.getCapacity() - armoury.getTotalWeaponAmount();
                if (name.equals("mace")) {
                    armoury.addMaceAmount(remainSpace);
                } else if (name.equals("bow")) {
                    armoury.addBowAmount(remainSpace);
                } else if (name.equals("crossbow")) {
                    armoury.addCrossBowAmount(remainSpace);
                } else if (name.equals("sword")) {
                    armoury.addSwordAmount(remainSpace);
                } else if (name.equals("metal armor")) {
                    armoury.addMetalArmourAmount(remainSpace);
                } else if (name.equals("leather armor")) {
                    armoury.addLeatherArmourAmount(remainSpace);
                } else if (name.equals("spear")) {
                    armoury.addSpearAmount(remainSpace);
                } else if (name.equals("pike")) {
                    armoury.addPikeAmount(remainSpace);
                }
                amount -= remainSpace;
                if (amount <= 0) {
                    break;
                }
            }
        }
    }

    public static void removeWeaponFromArmouries(String name, int amount) {
        if (getWeaponAmount(name) < amount) return;
        for (Armoury armoury : currentEmpire.getArmouries()) {
            if (armoury.getTotalWeaponAmount() >= amount) {
                if (name.equals("mace")) {
                    armoury.addMaceAmount(-amount);
                } else if (name.equals("bow")) {
                    armoury.addBowAmount(-amount);
                } else if (name.equals("crossbow")) {
                    armoury.addCrossBowAmount(-amount);
                } else if (name.equals("sword")) {
                    armoury.addSwordAmount(-amount);
                } else if (name.equals("metal armor")) {
                    armoury.addMetalArmourAmount(-amount);
                } else if (name.equals("leather armor")) {
                    armoury.addLeatherArmourAmount(-amount);
                } else if (name.equals("spear")) {
                    armoury.addSpearAmount(-amount);
                } else if (name.equals("pike")) {
                    armoury.addPikeAmount(-amount);
                }
                break;
            } else {
                int remainSpace = amount - armoury.getTotalWeaponAmount();
                if (name.equals("mace")) {
                    armoury.addMaceAmount(-remainSpace);
                } else if (name.equals("bow")) {
                    armoury.addBowAmount(-remainSpace);
                } else if (name.equals("crossbow")) {
                    armoury.addCrossBowAmount(-remainSpace);
                } else if (name.equals("sword")) {
                    armoury.addSwordAmount(-remainSpace);
                } else if (name.equals("metal armor")) {
                    armoury.addMetalArmourAmount(-remainSpace);
                } else if (name.equals("leather armor")) {
                    armoury.addLeatherArmourAmount(-remainSpace);
                } else if (name.equals("spear")) {
                    armoury.addSpearAmount(-remainSpace);
                } else if (name.equals("pike")) {
                    armoury.addPikeAmount(-remainSpace);
                }
                amount -= remainSpace;
                if (amount <= 0) {
                    break;
                }
            }
        }
    }

    public static void setSelect_x(int select_x) {
        GameMenuController.select_x = select_x;
    }

    public static void setSelect_y(int select_y) {
        GameMenuController.select_y = select_y;
    }

    public static int getSelect_y() {
        return select_y;
    }

    public String moveUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (selectedSoldiers.isEmpty()) {
            return "you haven't selected any troops";
        }
        if (!checkNegativity(x, y)) {
            return "negative index";
        }
        int result = 0;
//
//        for (Soldier soldier : selectedSoldiers) {
//            soldier.setStop(false);
//            // after the soldiers are stopped you
//            // have to make it false to move them again
//        }

//        List<Cell> path = AstarShortestPath.findShortestPath(map, map.getMapCells(getSelect_x(), getSelect_y()), map.getMapCells(x, y));
       result = moveElemets(x, y, selectedSoldiers);
        if (getPath().isEmpty()) {
            return "No path found.";
        } else {
            System.out.println("Shortest path:");

            for (Cell cell : getPath()) {
                System.out.println("(" + cell.getX() + ", " + cell.getY() + ")");
            }

        }
        if (result == 1) {

//        if (path.size() > selectedSoldiers.get(0).getSoldierType().getMovementRange()) {
//            List<Cell> Apath = new ArrayList<>(path);
//            ArrayList<Soldier> select = new ArrayList<>(selectedSoldiers);
//            paths.put(select,Apath);
//            selectedSoldiers.clear();
            return "unit will move in several turns";
        }
//        }

//        for (Soldier soldier : selectedSoldiers) {
//            map.getMapCells(getSelect_x(), getSelect_y()).removeTroop(soldier);
//        }
//
//        for (Soldier soldier : selectedSoldiers) {
//            path.get(path.size() - 1).moveTroop(soldier);
//        }
//        selectedSoldiers.clear();
        return "moved unit successfully";
    }

    public static int moveElemets(int x, int y, ArrayList<Soldier> selectedSoldiers) {

        for (Soldier soldier : selectedSoldiers) {
            soldier.setStop(false);
            // after the soldiers are stopped you
            // have to make it false to move them again
        }
        path = AstarShortestPath.findShortestPath(map, map.getMapCells(getSelect_x(), getSelect_y()), map.getMapCells(x, y));
        Apath = new ArrayList<>(path);
        select = new ArrayList<>(selectedSoldiers);
        if (path.size() > selectedSoldiers.get(0).getSoldierType().getMovementRange()) {
//
//            System.out.println("Shortest path:");
//
//            for (Cell cell : Apath) {
//                System.out.println("(" + cell.getX() + ", " + cell.getY() + ")");
//            }

            if (!selectedSoldiers.get(0).isPatrol()) {
                selectedSoldiers.clear();
                paths.put(select, Apath);
            }

            return 1;
        }

        for (Soldier soldier : selectedSoldiers) {
            map.getMapCells(getSelect_x(), getSelect_y()).removeTroop(soldier);
        }

        for (Soldier soldier : selectedSoldiers) {
            path.get(path.size() - 1).moveTroop(soldier);
        }
        if (!selectedSoldiers.get(0).isPatrol())
            selectedSoldiers.clear();
        return 2;
    }

    public String patrolUnit(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        if (!checkNegativity(x1, y1, x2, y2)) {
            return "negative index!";
        }

        if (selectedSoldiers.isEmpty()) {
            return "you haven't selected any troops";
        }
        for (Soldier soldier : selectedSoldiers) {
            soldier.setPatrol(true);
            soldier.setPatrol_x_start(x1);
            soldier.setPatrol_x_end(x2);
            soldier.setPatrol_y_start(y1);
            soldier.setPatrol_y_end(y2);
        }
        selectedSoldiersPatrol = new ArrayList<>(selectedSoldiers);
        moveElemets(x1, y1, selectedSoldiersPatrol);
        paths.put(select,Apath);
        return "patrol of units started";
    }


    public String selectUnit(Matcher matcher) {

        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("unitType");

        if (!checkNegativity(x, y)) {
            return "negative index!";
        }
        if (NewGameController.getCurrent_map().getMapCells(x, y).getSoldiers().isEmpty()) {
            return "there is no unit in this cell";
        }
        if (type != null) {
            if (SoldierType.getSoldierTypeByString(type) != null) {
                for (Soldier soldier : map.getMapCells(x, y).getSoldiers()) {
                    if (soldier.getSoldierType().getName().equals(type)) {
                        selectedSoldiers.add(soldier);
                        setSelect_x(x);
                        setSelect_y(y);
                    }
                }
                if (selectedSoldiers.isEmpty()) return "there was no unit with this type at this cell.";
            } else return "Invalid unit type.";
        } else {
            selectedSoldiers.addAll(map.getMapCells(x, y).getSoldiers());
            setSelect_x(x);
            setSelect_y(y);
        }
        return "units selected successfully!";
    }

    private boolean checkNegativity(int... param) {
        for (int a : param) {
            if (a < 0) {
                return false;
            }
        }
        return true;
    }

    public static int getSelect_x() {
        return select_x;

//    public static void setBuildingType(BuildingType buildingType) {
//        GameMenuController.buildingType = buildingType;
//    }


//    public static String dropBuilding(Matcher matcher) {
//        if (checkCordination(matcher)) {
//            int x = Integer.parseInt(matcher.group("x"));
//            int y = Integer.parseInt(matcher.group("y"));
//            BuildingType buildingType1 = BuildingType.getBuildingTypeByName(matcher.group("type"));
//            if (buildingType1 == null) return "there is not such a building type.";
//            String type = matcher.group("type");
//            if (buildingType1.getGoldPrice() > currentEmpire.getTotalGoldAmount())
//                return "you need more gold to build this building.";
//            Cell cell = map.getMapCells(x , y);
//            if (cell.getBuilding() != null)
//                return "already there is a building at this cell.";
//            if (cell.getSoldiers().size() > 0)
//                return "there is(are) soldier(s) here.";
//
//         //   if (cell.getTypeofGround().equals() || cell.isHasTreeInCell()  )
//        } else
//            return "Invalid cordination.";
//        return "";
//    }

//    public static int getHorseAmount() {
//        int amount = 0;
//        for (Stable stable : currentEmpire.getStables()) {
//            amount += stable.getCurrentNumberOfHorses();
//        }
//        return amount;
//    }
//
//    public static void addHorsesToStables(int amount) {
//
//        for (Stable stable : currentEmpire.getStables()) {
//            if (stable.getCapacity() >= stable.getCurrentNumberOfHorses() + amount) {
//                stable.addCurrentNumberOfHorses(amount);
//                break;
//            } else {
//                int remainSpace = stable.getCapacity() - stable.getCurrentNumberOfHorses();
//                stable.addCurrentNumberOfHorses(remainSpace);
//                amount -= remainSpace;
//                if (amount <= 0) {
//                    break;
//                }
//            }
//        }
//    }

    }

    public static void setPlayersInGame(ArrayList<Player> playersInGame) {
        GameMenuController.playersInGame = playersInGame;
    }

    public static void setEmpires(ArrayList<Empire> empires) {
        mapMenuController.setMapFromGameMenu(map);
        GameMenuController.empires = empires;
        for (Empire empire : empires) {
            int x = empire.getKingPit().getX();
            int y = empire.getKingPit().getY();
            mapMenuController.dropBuilding(x, y, BuildingType.MAIN_CASTLE.getName());
        }
    }

    public static ArrayList<Empire> getEmpires() {
        return empires;
    }

    public static void setCurrentEmpire(Empire currentEmpire) {
        GameMenuController.currentEmpire = currentEmpire;
    }

    public static HashMap<ArrayList<Soldier>, List<Cell>> getPaths() {
        return paths;
    }

    public static void setMap(Map map) {
        GameMenuController.map = map;
    }

    public String showmap(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!checkNegativity(x, y)) {
            return "negative index";
        }
        mapMenuController.showMapCoordinated(x, y);
        return "\n" +
                "map showed successfully";
    }

    public static Soldier getSoldierToBeRemoved() {
        return soldierToBeRemoved;
    }

    public static void setSoldierToBeRemoved(Soldier soldierToBeRemoved) {
        GameMenuController.soldierToBeRemoved = soldierToBeRemoved;
    }

    public String stop() {
        // you have to first select some soldiers and stop them afterwards
        if (selectedSoldiers.isEmpty()) {
            return "please choose the soldiers you want to stop them";
        }
        for (Soldier soldier : selectedSoldiers) {
            soldier.setStop(true);
        }
        return "the troops stopped successfully";
    }

    public List<Cell> getPath() {
        return path;
    }

    public String stopPatrol() {
        if (selectedSoldiers.isEmpty()) {
            return "please choose the soldiers you want to stop them";
        }
        for (Soldier soldier : selectedSoldiersPatrol) {
            soldier.setStopPatrol(true);
        }
        return "patrol stopped successfully";
    }
}

