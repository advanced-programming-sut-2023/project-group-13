package controller;

import model.*;

import java.util.*;
import java.util.regex.Matcher;

import model.Empire;
import model.Enums.TypeofGround;
import model.Map;

public class GameMenuController {
    public static int highScoreForEmpires = 500;
    public static int rank = 1;
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
    private static int select_x;
    private static List<Cell> path;
    private static int select_y;
    private static int select_x_building;
    private static int select_y_building;
    private static List<Cell> Apath;
    private static ArrayList<Soldier> select;


    public static String nextTurn() {

        roundNumber++;
        System.out.println("the current turn is: " + roundNumber);

        setCurrentEmpire(getEmpires().get(roundNumber % getEmpires().size()));

        System.out.println(currentEmpire.getEmpireName() + "is playing now!");
        endGame();
        calculateTax();
        calculateFood();
        calculateArmouryThings();
        calculateStockpileThings();
        currentEmpire.setTotalGoldAmount(currentEmpire.getTotalGoldAmount() + KingdomMenuController.calculateTax());

        currentEmpire.setPopularity(currentEmpire.getPopularity() + currentEmpire.getInnPopularity() +
                currentEmpire.getTaxPopularity() + currentEmpire.getReligionPopularity()
                + currentEmpire.getFoodPopularity());
        if (currentEmpire.getPopularity() > 100) currentEmpire.setPopularity(100);

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
        for (Building building : currentEmpire.getBuildings()) {
            String name = building.getName();
            if (!name.equalsIgnoreCase("Apple Orchard") && !name.equalsIgnoreCase("Hunting Post") &&
                    !name.equalsIgnoreCase("Bakery") && !name.equalsIgnoreCase("Dairy Farm")) {
                continue;
            }
            double rate = currentEmpire.getEfficiency();
            int productEachRate = (int) (building.getBuildingType().getProduceAmount() * rate);

            for (Granary granary : currentEmpire.getGranaries()) {
                int totalFood = granary.getAppleAmount() + granary.getCheeseAmount() + granary.getBreadAmount() + granary.getMeatAmount();
                if (totalFood < 50 - productEachRate * rate) {
                    switch (name) {
                        case "apple orchard":
                            granary.setAppleAmount(productEachRate);
                            break;

                        case "hunting post":
                            granary.setMeatAmount(productEachRate);
                            break;
                        case "bakery":
                            if (currentEmpire.getStockpiles().get(0).getFlourAmount() >= 1) {
                                currentEmpire.getStockpiles().get(0).setFlourAmount(-1);
                                granary.setBreadAmount(productEachRate);
                                break;
                            }
                        case "dairy farm":
                            granary.setCheeseAmount(productEachRate);
                            break;

                    }
                }
            }
        }
    }


    public static void calculateTax() {
        CurrentEmpire.setTotalGoldAmount(CurrentEmpire.getTotalGoldAmount() + CurrentEmpire.getTaxRate() * CurrentEmpire.getAllPeopleAmount());
        if (CurrentEmpire.getTotalGoldAmount() >= 0) {
            CurrentEmpire.setTaxPopularity(0);
            CurrentEmpire.setTaxPopularity(0);
        }

    }

    public static void calculateArmouryThings() {
        for (Building building : currentEmpire.getBuildings()) {
            if (!building.getBuildingType().equals(BuildingType.FLETCHER) && !building.getBuildingType().equals(BuildingType.BLACKSMITH)
                    && !building.getBuildingType().equals(BuildingType.POLETURNER) && !building.getBuildingType().equals(BuildingType.ARMORER) && !building.getBuildingType().equals(BuildingType.DIARY_FARMER)) {
                continue;

            }
            int rate = (int) (currentEmpire.getEfficiency() * building.getBuildingType().getProduceAmount());
            if (currentEmpire.getArmouries().get(0).getTotalWeaponAmount() + rate < 50) {
                continue;
            }
            switch (building.getBuildingType().getName()) {
                case "fletcher": {
                    if (currentEmpire.getStockpiles().get(0).getWoodAmount() >= rate) {
                        currentEmpire.getStockpiles().get(0).setWoodAmount(currentEmpire.getStockpiles().get(0).getWoodAmount() - rate);
                        currentEmpire.getArmouries().get(0).addBowAmount(rate);
                        currentEmpire.getArmouries().get(0).addCrossBowAmount(rate);
                    }
                }
                case "dairyfarm": {
                    currentEmpire.getArmouries().get(0).addLeatherArmourAmount(rate);
                }
                case "blacksmith": {
                    if (currentEmpire.getStockpiles().get(0).getIronAmount() >= rate) {
                        currentEmpire.getStockpiles().get(0).setIronAmount(currentEmpire.getStockpiles().get(0).getIronAmount() - rate);
                        currentEmpire.getArmouries().get(0).addSwordAmount(rate);
                        currentEmpire.getArmouries().get(0).addMaceAmount(rate);
                    }
                }

                case "poleturner": {
                    if (currentEmpire.getStockpiles().get(0).getWoodAmount() >= rate) {
                        currentEmpire.getStockpiles().get(0).setWoodAmount(currentEmpire.getStockpiles().get(0).getWoodAmount() - rate);
                        currentEmpire.getArmouries().get(0).addPikeAmount(rate);
                        currentEmpire.getArmouries().get(0).addSpearAmount(rate);
                    }
                }

                case "armourer": {
                    if (currentEmpire.getStockpiles().get(0).getIronAmount() >= rate) {
                        currentEmpire.getStockpiles().get(0).setIronAmount(currentEmpire.getStockpiles().get(0).getIronAmount() - rate);
                        currentEmpire.getArmouries().get(0).addMetalArmourAmount(rate);
                    }

                }
            }
        }
    }


    public static void calculateStockpileThings() {
        int oxTethers = 0;
        int quarry = 0;
        for (Building building : currentEmpire.getBuildings()) {
            double rate = currentEmpire.getEfficiency();
            int produce = building.getBuildingType().getProduceAmount();
            int total = (int) (rate * produce);
            switch (building.getBuildingType().getName()) {
                case "Mill": {
                    if (currentEmpire.getStockpiles().get(0).getWoodAmount() < rate) {
                        continue;
                    }

                    currentEmpire.getStockpiles().get(0).setFlourAmount(currentEmpire.getStockpiles().get(0).getFlourAmount() + total);
                    currentEmpire.getStockpiles().get(0).setWheatAmount(currentEmpire.getStockpiles().get(0).getWheatAmount() - total);
                }
                case "IronMine": {
                    if (currentEmpire.getStockpiles().get(0).getFreeSpace() < total) {
                        continue;
                    }
                    currentEmpire.getStockpiles().get(0).setIronAmount(currentEmpire.getStockpiles().get(0).getIronAmount() + total);
                }
                case "PitchRig": {
                    if (currentEmpire.getStockpiles().get(0).getFreeSpace() < total) {
                        continue;
                    }
                    currentEmpire.getStockpiles().get(0).setPitchAmount(currentEmpire.getStockpiles().get(0).getPitchAmount() + total);
                }
                case "Woodcutter": {
                    if (currentEmpire.getStockpiles().get(0).getFreeSpace() < total) {
                        continue;
                    }
                    currentEmpire.getStockpiles().get(0).setWoodAmount(currentEmpire.getStockpiles().get(0).getWoodAmount() + total);
                }
                case "HopsFarm": {
                    if (currentEmpire.getStockpiles().get(0).getFreeSpace() < total) {
                        continue;
                    }
                    currentEmpire.getStockpiles().get(0).setHopsAmount(currentEmpire.getStockpiles().get(0).getHopsAmount() + total);
                }
                case "WheatFarm": {
                    if (currentEmpire.getStockpiles().get(0).getFreeSpace() < total) {
                        continue;
                    }
                    currentEmpire.getStockpiles().get(0).setWheatAmount(currentEmpire.getStockpiles().get(0).getWheatAmount() + total);
                }
                case "Brewery": {
                    if (currentEmpire.getStockpiles().get(0).getHopsAmount() < total) {
                        continue;
                    }
                    if (currentEmpire.getStockpiles().get(0).getFreeSpace() < produce * (rate - 1)) {
                        continue;
                    }
                    currentEmpire.getStockpiles().get(0).setAleAmount(currentEmpire.getStockpiles().get(0).getAleAmount() + total);
                    currentEmpire.getStockpiles().get(0).setHopsAmount(currentEmpire.getStockpiles().get(0).getHopsAmount() - total);
                }
                case "Quarry": {
                    quarry++;
                }
            }
        }
        int productOfAllQuarries = (int) (quarry * currentEmpire.getEfficiency() * BuildingType.QUARRY.getProduceAmount());

        currentEmpire.getStockpiles().get(0).setStoneAmount(currentEmpire.getStockpiles().get(0).getStoneAmount() + productOfAllQuarries);

    }

    //   public static void calculate
    public String dropUnit(Matcher matcher) {
        if (selectedBuilding == null) return "please choose a building first.";
        BuildingType buildingType = selectedBuilding.getBuildingType();
        String unitType = matcher.group("type");
        if (checkAmount(matcher.group("number"))) return "Invalid number of unit";
        int number = Integer.parseInt(matcher.group("number"));
        SoldierType soldierType = SoldierType.getSoldierTypeByString(unitType);
        if (soldierType == null) return "Invalid type: there is no unit with this type.";
        if (soldierType.isArab() && buildingType != BuildingType.MERCENARY_POST)
            return "please select <<mercenary post>> building.";
        if (buildingType != BuildingType.CHURCH && unitType.equals("black monk"))
            return "please select <<church>> building.";
        if (buildingType != BuildingType.ENGINEERS_GUILD &&
                (unitType.equals("tunneler") || unitType.equals("engineer")))
            return "please select <<engineers guild>> building";
        if (buildingType != BuildingType.BARRACKS && !soldierType.isArab() && soldierType != SoldierType.BLACK_MONK && soldierType != SoldierType.ENGINEER && soldierType != SoldierType.TUNNELER)
            return "please select <<barracks>> building.";
        if (soldierType.getMoneyCost() * number > currentEmpire.getTotalGoldAmount())
            return "not enough amount of money.";
        if (number > currentEmpire.getNoneWorkerAmount())
            return "not enough amount of none worker people";

        switch (soldierType.getName()) {
            case "archer":

                if (getWeaponAmount("bow") < number) {
                    return "bows needed";
                }
                removeWeaponFromArmouries("bow", -1 * number);


            case "crossbowman":
                if (getWeaponAmount("crossbow") < number || getWeaponAmount("leather armor") < number) {
                    return "crossbow/leather armor needed";
                }
                removeWeaponFromArmouries("crossbow", -1 * number);
                removeWeaponFromArmouries("leatherArmor", -1 * number);

            case "spearman":
                if (getWeaponAmount("spearman") < number) {
                    return "spear needed";
                }
                removeWeaponFromArmouries("spear", -1 * number);

            case "pikeman":
                if (getWeaponAmount("pike") < number || getWeaponAmount("metal armor") < number) {
                    return "pike/metal armor needed";
                }
                removeWeaponFromArmouries("pike", -1 * number);
                removeWeaponFromArmouries("metalArmor", -1 * number);

            case "maceman":
                if (getWeaponAmount("mace") < number || getWeaponAmount("leather armor") < number) {
                    return "mace/leather armor needed";
                }
                removeWeaponFromArmouries("mace", -1 * number);
                removeWeaponFromArmouries("leatherArmor", -1 * number);

            case "swordsman":
                if (getWeaponAmount("sword") < number || getWeaponAmount("metal armor") < number) {
                    return "sword/metal armor needed";
                }
                removeWeaponFromArmouries("sword", -1 * number);
                removeWeaponFromArmouries("metalArmor", -1 * number);

            case "knight":
                if (getWeaponAmount("sword") < number || getWeaponAmount("metal armor") < number || getHorseAmount() < number) {
                    return "sword/metal armor/horse needed";
                }
                removeWeaponFromArmouries("sword", -1 * number);
                removeWeaponFromArmouries("metalArmor", -1 * number);
                removeHorsesFromStables(-1 * number);
                Soldier soldier = new Soldier(soldierType.getHp(), soldierType.getName(),
                        soldierType, currentEmpire, selectedBuilding.getX() + 1, selectedBuilding.getY());
                currentEmpire.getSoldiers().add(soldier);
                map.getMapCells(select_x_building + 1, select_y_building).getSoldiers().add(soldier);

            default:
                return "success.";
        }
    }

    public String siegeCreep(String name) {
        Boolean engineer = true;
        if (selectedSoldiers.size() == 0) return "please select unit first.";
        for (Soldier soldier : selectedSoldiers) {
            if (soldier.getSoldierType().getEngineerNeededAmount() == 0) {
                engineer = false;
            }
        }
        if (!engineer) return "please only select engineers.";
        SoldierType soldierType = SoldierType.getSoldierTypeByString(name);
        if (soldierType == null) return "Invalid type.";
        if (soldierType.getMoneyCost() > currentEmpire.getTotalGoldAmount()) return "not enough gold amount.";
        if (soldierType.getEngineerNeededAmount() > selectedSoldiers.size()) return "not enough engineer.";
        currentEmpire.setTotalGoldAmount(currentEmpire.getTotalGoldAmount() - soldierType.getMoneyCost());
        Soldier soldier = new Soldier(soldierType.getHp(), soldierType.getName(), soldierType, currentEmpire, select_x, select_y);
        currentEmpire.getSoldiers().add(soldier);
        for (int i = map.getMapCells(select_x, select_y).getSoldiers().size(); i > map.getMapCells(select_x, select_y).getSoldiers().size() - soldierType.getEngineerNeededAmount(); i--) {
            map.getMapCells(select_x, select_y).getSoldiers().remove(i);
        }

        return "successfully done.";
    }


    public String attack(Matcher matcher) {
        if (selectedSoldiers.size() == 0) {
            return "you should select a unit first";
        }
        if (!checkCordination(matcher)) return "Invalid cordination.";
        int x2 = Integer.parseInt(matcher.group("x1"));
        int y2 = Integer.parseInt(matcher.group("y1"));
        int x1 = select_x_building;
        int y1 = select_y_building;
        Boolean enemy = false;
        if (calculateDistance(x1, y1, x2, y2) - 1 > selectedSoldiers.get(0).getSoldierType().getRange())
            return "can't attack , enemy is so far.";
        for (Soldier soldier : map.getMapCells(x2, y2).getSoldiers()) {
            if (!soldier.getOwner().equals(currentEmpire)) enemy = true;
        }
        if (!enemy) return "There is no enemy at this cell.";
        for (Soldier soldier : selectedSoldiers) {
            for (Soldier soldier1 : map.getMapCells(x2, y2).getSoldiers()) {
                if (!soldier.getOwner().equals(soldier1.getOwner())) {
                    soldier1.setHp(-soldier.getSoldierType().getSoldierDamage());
                    if (calculateDistance(x1, y1, x2, y2) <= soldier1.getSoldierType().getRange() + 1)
                        soldier.setHp(-soldier1.getSoldierType().getSoldierDamage());
                }
            }
        }
        return "successfully done.";
    }


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
        paths.put(select, Apath);
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
    }


    public String repair() {
        if (selectedBuilding == null) {
            return "please select a building first.";
        }

        String name = selectedBuilding.getBuildingType().getName();
        BuildingType buildingType1 = BuildingType.getBuildingTypeByName(name);
        if (buildingType1 == null) return "there is no building with this type here.";
        if (!(name.equalsIgnoreCase("SmallStoneGatehouse") || name.equalsIgnoreCase("BigStoneGatehouse")
                || name.equalsIgnoreCase("LookoutTower") || name.equalsIgnoreCase("PerimeterTower")
                || name.equalsIgnoreCase("DefenciveTurret") || name.equalsIgnoreCase("SquareTower")
                || name.equalsIgnoreCase("RoundTower"))) {
            return "This building can not be repaired.";
        }
        if (buildingType1.getHp() <= 0) return "this building is destroyed already.";
        double rate = buildingType1.getResourcePriceAmount() / buildingType1.getHp();
        int hpNeededToBeRepaired = buildingType1.getHp() - selectedBuilding.getHp();
        if (hpNeededToBeRepaired == 0) return "This building has full hp can not be repaired.";

        if (amountOfThingsInStockpile("stone") < (int) (rate * hpNeededToBeRepaired)) {
            return "not enough stone to repair this building";
        }
        int currentStone = currentEmpire.getStockpiles().get(0).getStoneAmount();
        currentEmpire.getStockpiles().get(0).setStoneAmount(currentStone - (int) (rate * hpNeededToBeRepaired));
        selectedBuilding.setHp(selectedBuilding.getBuildingType().getHp());
        return "successfully done.";
    }

    public static int amountOfThingsInStockpile(String name) {
        int amount = 0;
        for (Armoury armoury : currentEmpire.getArmouries()) {
            if (name.equals("wood")) {
                amount += armoury.getMaceAmount();
            } else if (name.equalsIgnoreCase("stone")) {
                amount += armoury.getBowAmount();
            } else if (name.equals("iron")) {
                amount += armoury.getCrossBowAmount();
            } else if (name.equals("wheat")) {
                amount += armoury.getSwordAmount();
            } else if (name.equals("flour")) {
                amount += armoury.getMetalArmourAmount();
            } else if (name.equals("ale")) {
                amount += armoury.getLeatherArmourAmount();
            } else if (name.equals("hops")) {
                amount += armoury.getSpearAmount();
            } else if (name.equals("pitch")) {
                amount += armoury.getPikeAmount();
            }
        }
        return amount;
    }

    public static int addToStockpile(String name) {
        int amount = 0;
        for (Armoury armoury : currentEmpire.getArmouries()) {
            if (name.equals("wood")) {
                amount += armoury.getMaceAmount();
            } else if (name.equalsIgnoreCase("stone")) {
                amount += armoury.getBowAmount();
            } else if (name.equals("iron")) {
                amount += armoury.getCrossBowAmount();
            } else if (name.equals("wheat")) {
                amount += armoury.getSwordAmount();
            } else if (name.equals("flour")) {
                amount += armoury.getMetalArmourAmount();
            } else if (name.equals("ale")) {
                amount += armoury.getLeatherArmourAmount();
            } else if (name.equals("hops")) {
                amount += armoury.getSpearAmount();
            } else if (name.equals("pitch")) {
                amount += armoury.getPikeAmount();
            }
        }
        return amount;
    }


    public String dropBuilding(Matcher matcher) {
        if (checkCordination(matcher)) {

            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));
            select_x_building = x;
            select_y_building = y;
            BuildingType buildingType = BuildingType.getBuildingTypeByName(matcher.group("type"));
            if (buildingType == null) return "there is not such a building type.";
            if (buildingType.getGoldPrice() > currentEmpire.getTotalGoldAmount())
                return "you need more gold to build this building.";
            Cell cell = map.getMapCells(x, y);
            if (cell.isHasTreeInCell()) return "there is tree in this cell";
            TypeofGround typeofGround = cell.getTypeofGround();
            if (cell.getBuilding() != null)
                return "already there is a building at this cell.";
            if (cell.getSoldiers().size() > 0)
                return "there is(are) soldier(s) here.";
            if (buildingType.equals(BuildingType.DIARY_FARMER) || buildingType.equals(BuildingType.APPLE_ORCHARD) ||
                    buildingType.equals(BuildingType.HOPS_FARMER) || buildingType.equals(BuildingType.WHEAT_FARMER)) {
                if (!typeofGround.equals(TypeofGround.GRASS))
                    return "must build on Grass ground.";
            } else if (buildingType.equals(BuildingType.QUARRY)) {
                if (!cell.getTypeofGround().equals(TypeofGround.STONEY)) return "must build on stoney ground.";

            } else if (buildingType.equals(BuildingType.IRON_MINE)) {
                if (!TypeofGround.IRON.equals(cell.getTypeofGround())) return "must build on Iron ground.";
            } else if (buildingType.equals(BuildingType.PITCH_RIG)) {
                if (!cell.getTypeofGround().equals(TypeofGround.OIL) && !cell.getTypeofGround().equals(TypeofGround.PLAIN)) {
                    return "must build on Plain or Oil ground.";
                } else if (!cell.getTypeofGround().equals(TypeofGround.BEACH) && !cell.getTypeofGround().equals(TypeofGround.GRASS) && !cell.getTypeofGround().equals(TypeofGround.EARTH) && !cell.getTypeofGround().equals(TypeofGround.SANDY)) {
                    return "Invalid type of ground.";
                }
            }
            if (currentEmpire.getNoneWorkerAmount() < buildingType.getWorkerNeededAmount())
                return "not enough worker to run this building.";

            switch (buildingType.getResourcesPriceType().getResourceName()) {
                case "wood":
                    int currentWood = currentEmpire.getStockpiles().get(0).getWoodAmount();
                    if (currentWood < buildingType.getResourcePriceAmount()) return "not enough amount of wood.";
                    currentEmpire.getStockpiles().get(0).setWoodAmount(currentWood - buildingType.getResourcePriceAmount());
                case "iron":
                    int currentIron = currentEmpire.getStockpiles().get(0).getIronAmount();
                    if (currentIron < buildingType.getResourcePriceAmount()) return "not enough amount of iron.";
                    currentEmpire.getStockpiles().get(0).setIronAmount(currentIron - buildingType.getResourcePriceAmount());
                case "stone":
                    int currentStone = currentEmpire.getStockpiles().get(0).getStoneAmount();
                    if (currentStone < buildingType.getResourcePriceAmount()) return "not enough amount of stone.";
                    currentEmpire.getStockpiles().get(0).setStoneAmount(currentStone - buildingType.getResourcePriceAmount());
                case "pitch":
                    int currentPitch = currentEmpire.getStockpiles().get(0).getPitchAmount();
                    if (currentPitch < buildingType.getResourcePriceAmount()) return "not enough amount of pitch.";
                    currentEmpire.getStockpiles().get(0).setPitchAmount(currentPitch - buildingType.getResourcePriceAmount());

            }
            currentEmpire.setNoneWorkerAmount(currentEmpire.getNoneWorkerAmount() - buildingType.getWorkerNeededAmount());
            currentEmpire.setWorkerAmount(currentEmpire.getWorkerAmount() + buildingType.getWorkerNeededAmount());
            Building building = new Building(buildingType.getHp(), buildingType.getName(), buildingType, x, y, Empire.getCurrentEmpire());
            currentEmpire.getBuildings().add(building);
            map.getMapCells(x, y).addBuilding(building);

            return "successfully done.";

        } else
            return "Invalid cordination.";
    }

    public String setUnitMode(Matcher matcher) {
        String mode = matcher.group("mode");
        int situation = 0;
        if (!mode.equals("standing") && !mode.equals("defensive") && !mode.equals("offensive")) {
            return "Invalid mode.";
        }
        if (mode.equals("standing")) situation = 1;
        if (mode.equals("defensive")) situation = 2;
        if (mode.equals("offensive")) situation = 3;
        for (Soldier soldier : selectedSoldiers) {
            soldier.setSituation(situation);
        }
        return "successfully done.";
    }

    public static int getHorseAmount() {
        int amount = 0;
        for (Stable stable : currentEmpire.getStables()) {
            amount += stable.getCurrentNumberOfHorses();
        }
        return amount;
    }

    public static void addHorsesToStables(int amount) {

        for (Stable stable : currentEmpire.getStables()) {
            if (stable.getCapacity() >= stable.getCurrentNumberOfHorses() + amount) {
                stable.addCurrentNumberOfHorses(amount);
                break;
            } else {
                int remainSpace = stable.getCapacity() - stable.getCurrentNumberOfHorses();
                stable.addCurrentNumberOfHorses(remainSpace);
                amount -= remainSpace;
                if (amount <= 0) {
                    break;
                }
            }
        }
    }

    public static void removeHorsesFromStables(int amount) {

        for (Stable stable : currentEmpire.getStables()) {
            if (stable.getCurrentNumberOfHorses() >= amount) {
                stable.addCurrentNumberOfHorses(amount);
                break;
            } else {
                stable.addCurrentNumberOfHorses(stable.getCurrentNumberOfHorses());
                amount -= stable.getCurrentNumberOfHorses();
                if (amount <= 0) {
                    break;
                }
            }
        }
    }

    public String disbandUnit() {
        if (selectedSoldiers.size() == 0) {
            return "you must select a unit";
        }
        for (Soldier soldier : selectedSoldiers) {
            map.getMapCells(select_x, select_y).getSoldiers().remove(soldier);
            currentEmpire.getSoldiers().remove(soldier);
        }
        currentEmpire.setNoneWorkerAmount(selectedSoldiers.size());
        selectedSoldiers.clear();
        return "success";
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

    public static int calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y2 - y1);
    }

    public static Empire getEmpireByUserName(String name) {
        for (Empire empire : empires) {
            if (empire.getPlayer().getUsername().equals(name)) return empire;
        }
        return null;
    }

    public static Empire getCurrentEmpire() {
        return currentEmpire;
    }

    public String tunnel(int x, int y) {
        boolean tunneler = true;
        if (selectedSoldiers.size() == 0) return "select a unit first.";
        for (Soldier soldier : selectedSoldiers) {
            if (!soldier.getSoldierType().getName().equalsIgnoreCase("tunneler")) tunneler = false;
        }
        if (!tunneler) return "only select <<tunneler>> units.";
        if (checkNegativity(x, y)) return "Invalid cordination.";
        Building building = map.getMapCells(x, y).getBuilding();
        if (building == null) return "There is no building at this cell.";
        if (building.getEmpire().equals(currentEmpire)) return "you cant attack your own buildings.";
        BuildingType buildingType1 = building.getBuildingType();
        String name = buildingType1.getName();

        if (!name.equalsIgnoreCase("round tower") && !name.equalsIgnoreCase("wall") && !name.equalsIgnoreCase("stair") && !name.equalsIgnoreCase("square tower") && !name.equalsIgnoreCase("defense turret") &&
                !name.equalsIgnoreCase("perimeter tower") && !name.equalsIgnoreCase("lookout tower")
                && !name.equalsIgnoreCase("big stone gatehouse") && !name.equalsIgnoreCase("small stone gatehouse"))
            return "you cant attack this building.";

        //move to x and y for emad
        building.setHp(-SoldierType.TUNNELER.getSoldierDamage());

        return "successfully done.";
    }

    public String digMoat(int x, int y) {
        boolean digger = true;
        if (selectedSoldiers.size() == 0) return "select a unit first.";
        for (Soldier soldier : selectedSoldiers) {
            if (!soldier.getSoldierType().isCanDigDitch()) digger = false;
        }
        if (!digger) return "only select units which can dig.";
        if (!checkNegativity(x, y)) return "Invalid cordination.";

        if (map.getMapCells(x, y).getTypeofGround().equals(TypeofGround.BEACH) ||
                map.getMapCells(x, y).getTypeofGround().equals(TypeofGround.SANDY) || map.getMapCells(x, y).getTypeofGround().equals(TypeofGround.EARTH)
                || map.getMapCells(x, y).getTypeofGround().equals(TypeofGround.DENSEMEADOW) ||
                map.getMapCells(x, y).getTypeofGround().equals(TypeofGround.MEADOW)) {
            //move to x and y for emad
            map.getMapCells(x, y).setTypeofGround(TypeofGround.DITCH);
            return "successfully done.";
        } else return "Invalid type of ground.";
    }

    public String fillMoat(int x, int y) {
        boolean digger = true;
        if (selectedSoldiers.size() == 0) return "select a unit first.";
        for (Soldier soldier : selectedSoldiers) {
            if (!soldier.getSoldierType().isCanDigDitch()) digger = false;
        }
        if (!digger) return "only select units which can dig.";
        if (!checkNegativity(x, y)) return "Invalid cordination.";
        // move for emad
        map.getMapCells(x, y).setTypeofGround(TypeofGround.EARTH);
        return "successfully done.";
    }

    public String pourOil(String cordination) {
        boolean engineer = true;
        if (selectedSoldiers.size() == 0) return "select a unit first.";
        for (Soldier soldier : selectedSoldiers) {
            if (!soldier.getSoldierType().equals(SoldierType.ENGINEER)) engineer = false;
        }
        if (!engineer) return "only select oil engineers.";
        switch (cordination) {
            case "left":
                if (map.getMapCells(select_x, select_y - 1).getSoldiers().size() == 0)
                    return "There is no unit at this cell.";
                for (Soldier soldier : map.getMapCells(select_x, select_y - 1).getSoldiers()) {
                    soldier.setHp(-selectedSoldiers.get(0).getSoldierType().getSoldierDamage());
                }
            case "right":
                if (map.getMapCells(select_x, select_y + 1).getSoldiers().size() == 0)
                    return "There is no unit at this cell.";
                for (Soldier soldier : map.getMapCells(select_x, select_y + 1).getSoldiers()) {
                    soldier.setHp(-selectedSoldiers.get(0).getSoldierType().getSoldierDamage());
                }
            case "top":
                if (map.getMapCells(select_x - 1, select_y).getSoldiers().size() == 0)
                    return "There is no unit at this cell.";
                for (Soldier soldier : map.getMapCells(select_x - 1, select_y).getSoldiers()) {
                    soldier.setHp(-selectedSoldiers.get(0).getSoldierType().getSoldierDamage());
                }
            case "down":
                if (map.getMapCells(select_x + 1, select_y).getSoldiers().size() == 0)
                    return "There is no unit at this cell.";
                for (Soldier soldier : map.getMapCells(select_x, select_y + 1).getSoldiers()) {
                    soldier.setHp(-selectedSoldiers.get(0).getSoldierType().getSoldierDamage());
                }

        }
        return "successfully done.";
    }

    public static void endGame() {
        int[] empireId = new int[empires.size()];
        for (Integer x : empireId) {
            x = 0;
        }
        for (int i = 0; i < empires.size(); i++) {
            for (Soldier soldier : empires.get(i).getSoldiers()) {
                if (soldier.getSoldierType().equals(SoldierType.KING) && soldier.getHp() <= 0) {
                    empireId[i] = 1;
                }
            }
        }
        for (int i = 0; i < empireId.length; i++) {
            if (empireId[i] == 1) {
                rank += 1;
                empires.get(i).getPlayer().setHighScore(rank * highScoreForEmpires);
                for (int j = 0; j < map.getSizeOfTheMap(); j++) {
                    for (int k = 0; k < map.getSizeOfTheMap(); k++) {
                        if (map.getMapCells( j , k).getSoldiers().size() == 0) continue;
                        int length = map.getMapCells( j , k).getSoldiers().size();
                        for (int l = length  -1 ; l >= 0; l++) {
                            if (map.getMapCells( j , k).getSoldiers().get(l).getOwner().equals(empires.get(i)))
                                map.getMapCells( j , k).getSoldiers().remove(l);
                        }

                    }

                }
                for (int j = 0; j < map.getSizeOfTheMap(); j++) {
                    for (int k = 0; k < map.getSizeOfTheMap(); k++) {
                        if (map.getMapCells( j , k).getBuilding()!= null ) {
                            if (map.getMapCells( j , k).getBuilding().getEmpire().equals(empires.get(i))) map.getMapCells( j , k).addBuilding(null);
                    }
                }
            }
            empires.remove(i);
                System.out.println("Dead Empire: " + empires.get(i).getPlayer().getNickname());
        }
    }

}
}



