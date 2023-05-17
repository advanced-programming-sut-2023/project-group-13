package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import model.Empire;
import model.Map;

public class GameMenuController {
    public static Map map = NewGameController.getCurrent_map();
    public static ArrayList<Player> playersInGame = new ArrayList<>();
    public static Empire currentEmpire;
    public ArrayList<Soldier> selectedSoldiers = new ArrayList<>();
    public static Building selectedBuilding;
    public static int roundNumber = 0;
    public static Empire CurrentEmpire;
    public static BuildingType buildingType;
    private static int select_x;
    private static int select_y;
    public static ArrayList<Soldier> selected_soldiers = new ArrayList<>();


    public static String nextTurn() {
        roundNumber++;
        return "";
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
//        if (buildingType != BuildingType.BARRACKS && !soldierType.isArab())
//            return "please select <<barracks>> building.";
//        if (soldierType.getMoneyCost() * number > currentEmpire.getTotalGoldAmount())
//            return "not enough amount of money.";
//        if (number > currentEmpire.getNoneWorkerAmount())
//            return "not enough amount of none worker people";
//
//        switch (soldierType.getName()) {
//            case "archer" -> {
//
//                if (getWeaponAmount("bow") < number) {
//                    return "bows needed";
//                }
//                currentEmpire.getArmoury().addArmoury("bow", -1 * number);
//            }
//            case "crossbowman" -> {
//                if (currentEmpire.getArmoury().getLeatherArmor() < number || currentEmpire.getArmoury().getCrossbow() < number) {
//                    return "weapons needed";
//                }
//                currentEmpire.getArmoury().addArmoury("crossbow", -1 * number);
//                currentEmpire.getArmoury().addArmoury("leatherArmor", -1 * number);
//            }
//            case "spearman" -> {
//                if (currentEmpire.getArmoury().getSpear() < number) {
//                    return "weapons needed";
//                }
//                currentEmpire.getArmoury().addArmoury("spear", -1 * number);
//            }
//            case "pikeman" -> {
//                if (currentEmpire.getArmoury().getPike() < number || currentEmpire.getArmoury().getMetalArmor() < number) {
//                    return "weapons needed";
//                }
//                currentEmpire.getArmoury().addArmoury("pike", -1 * number);
//                currentEmpire.getArmoury().addArmoury("metalArmor", -1 * number);
//            }
//            case "maceman" -> {
//                if (currentEmpire.getArmoury().getMace() < number || currentEmpire.getArmoury().getLeatherArmor() < number) {
//                    return "weapons needed";
//                }
//                currentEmpire.getArmoury().addArmoury("mace", -1 * number);
//                currentEmpire.getArmoury().addArmoury("leatherArmor", -1 * number);
//            }
//            case "swordsman" -> {
//                if (currentEmpire.getArmoury().getSword() < number || currentEmpire.getArmoury().getMetalArmor() < number) {
//                    return "weapons needed";
//                }
//                currentEmpire.getArmoury().addArmoury("sword", -1 * number);
//                currentEmpire.getArmoury().addArmoury("metalArmor", -1 * number);
//            }
//            case "knight" -> {
//                if (currentEmpire.getArmoury().getSword() < number || currentEmpire.getArmoury().getMetalArmor() < number || currentEmpire.getArmoury().getHorse() < number) {
//                    return "weapons needed";
//                }
//                currentEmpire.getArmoury().addArmoury("sword", -1 * number);
//                currentEmpire.getArmoury().addArmoury("metalArmor", -1 * number);
//                currentEmpire.getArmoury().addHorse(-1 * number);
//            }
//        }
//
//
//        ().getName() > )
//
//
//        if (checkCordination(matcher)) {
//            if ((buildingType != BuildingType.BARRACKS) || (buildingType != BuildingType.ENGINEERS_GUILD) || (buildingType != BuildingType.MERCENARY_POST)) {
//            }
//        }
//        return "Invalid cordination!";
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
    public static void addWeaponToArmouries(String name , int amount) {
        for (Armoury armoury : currentEmpire.getArmouries()) {
            if (armoury.getCapacity() > armoury.getTotalWeaponAmount() + amount) {
                if (name.equals("mace")) {
                    armoury.setMaceAmount(amount);
                } else if (name.equals("bow")) {
                     armoury.setBowAmount(amount);
                } else if (name.equals("crossbow")) {
                     armoury.setCrossBowAmount(amount);
                } else if (name.equals("sword")) {
                      armoury.setSwordAmount(amount);
                } else if (name.equals("metal armor")) {
                      armoury.setMetalArmourAmount(amount);
                } else if (name.equals("leather armor")) {
                      armoury.setLeatherArmourAmount(amount);
                } else if (name.equals("spear")) {
                      armoury.setSpearAmount(amount);
                } else if (name.equals("pike")) {
                      armoury.setPikeAmount(amount);
                }
            }
        }
    }

    public String moveUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!checkNegativity(x, y)) {
            return "negative index";
        }
        List<Cell> path = AstarShortestPath.findShortestPath(NewGameController.getCurrent_map(), NewGameController.getCurrent_map().getMapCells(getSelect_x(), getSelect_y()), NewGameController.getCurrent_map().getMapCells(x, y));
        if (path.isEmpty()) {
            return "No path found.";
        } else {
            System.out.println("Shortest path:");
            for (Cell cell : path) {
                System.out.println("(" + cell.getX() + ", " + cell.getY() + ")");
            }
        }
        return "moved unit successfully";
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
            if (SoldierType.getSoldierTypeByString(type)!= null) {
                for (Soldier soldier : NewGameController.getCurrent_map().getMapCells(x, y).getSoldiers()) {
                    if (soldier.getSoldierType().getName().equals(type)) {
                        selectedSoldiers.add(soldier);
                    }
                }
                if (selectedSoldiers.isEmpty()) return "there was no unit with this type at this cell.";
            }
            else return "Invalid unit type.";
        }
        else {
            selectedSoldiers.addAll(NewGameController.getCurrent_map().getMapCells(x, y).getSoldiers());
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
    }

    public static void setSelect_x(int select_x) {
        GameMenuController.select_x = select_x;
    }

    public static int getSelect_y() {
        return select_y;
    }

    public static void setSelect_y(int select_y) {
        GameMenuController.select_y = select_y;
    }

    public static void setBuildingType(BuildingType buildingType) {
        GameMenuController.buildingType = buildingType;
    }
}
