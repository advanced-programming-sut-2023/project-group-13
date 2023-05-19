package controller;

import model.*;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import model.Empire;
import model.Enums.TypeofGround;
import model.Map;

public class GameMenuController {
    public static Map map;
    public static ArrayList<Player> playersInGame = new ArrayList<>();
    public static Empire currentempire;
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


    public String nextTurn() {
        calculateTax();
        calculateFood();
        calculateArmouryThings();
        calculateStockpileThings();
        currentempire.setTotalGoldAmount(currentempire.getTotalGoldAmount() + KingdomMenuController.calculateTax());

        currentempire.setPopularity(currentempire.getPopularity() + currentempire.getInnPopularity() +
                currentempire.getTaxPopularity() + currentempire.getReligionPopularity()
                + currentempire.getFoodPopularity());
        if (currentempire.getPopularity() > 100) currentempire.setPopularity(100);
        setCurrentEmpire(getEmpires().get(roundNumber % getEmpires().size()));
        Empire.setCurrentEmpire(currentempire);
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
                        moveElements(soldier.getPatrol_x_end(), soldier.getPatrol_y_end(), selectedSoldiersPatrol);
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
        selectedBuilding = null;
        roundNumber++;
        Empire.setCurrentEmpire(currentempire);

        return "moved to the next turn: " + currentempire.getPlayer().getNickname() + "round: " + roundNumber;
    }

    public static void calculateFood() {
        for (Building building : currentempire.getBuildings()) {
            String name = building.getName();
            if (!name.equalsIgnoreCase("Apple Orchard") && !name.equalsIgnoreCase("Hunting Post") &&
                    !name.equalsIgnoreCase("Bakery") && !name.equalsIgnoreCase("Dairy Farm")) {
                continue;
            }
            double rate = currentempire.getEfficiency();
            int productEachRate = (int) (building.getBuildingType().getProduceAmount() * rate);

            for (Granary granary : currentempire.getGranaries()) {
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
                            if (currentempire.getStockpiles().get(0).getFlourAmount() >= 1) {
                                currentempire.getStockpiles().get(0).setFlourAmount(-1);
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
        currentempire.setTotalGoldAmount(currentempire.getTotalGoldAmount() + currentempire.getTaxRate() * currentempire.getAllPeopleAmount());
        if (currentempire.getTotalGoldAmount() >= 0) {
            currentempire.setTaxPopularity(0);
            currentempire.setTaxPopularity(0);
        }

    }

    public static void calculateArmouryThings() {
        for (Building building : currentempire.getBuildings()) {
            if (!building.getBuildingType().equals(BuildingType.FLETCHER) && !building.getBuildingType().equals(BuildingType.BLACKSMITH)
                    && !building.getBuildingType().equals(BuildingType.POLETURNER) && !building.getBuildingType().equals(BuildingType.ARMORER) && !building.getBuildingType().equals(BuildingType.DIARY_FARMER)) {
                continue;
            }
            int rate = (int) (currentempire.getEfficiency() * building.getBuildingType().getProduceAmount());
            if (currentempire.getArmouries().get(0).getTotalWeaponAmount() + rate < 50) {
                continue;
            }
            switch (building.getBuildingType().getName()) {
                case "fletcher": {
                    if (currentempire.getStockpiles().get(0).getWoodAmount() >= rate) {
                        currentempire.getStockpiles().get(0).setWoodAmount(currentempire.getStockpiles().get(0).getWoodAmount() - rate);
                        currentempire.getArmouries().get(0).addBowAmount(rate);
                        currentempire.getArmouries().get(0).addCrossBowAmount(rate);
                    }
                }
                case "dairyfarm": {
                    currentempire.getArmouries().get(0).addLeatherArmourAmount(rate);
                }
                case "blacksmith": {
                    if (currentempire.getStockpiles().get(0).getIronAmount() >= rate) {
                        currentempire.getStockpiles().get(0).setIronAmount(currentempire.getStockpiles().get(0).getIronAmount() - rate);
                        currentempire.getArmouries().get(0).addSwordAmount(rate);
                        currentempire.getArmouries().get(0).addMaceAmount(rate);
                    }
                }

                case "poleturner": {
                    if (currentempire.getStockpiles().get(0).getWoodAmount() >= rate) {
                        currentempire.getStockpiles().get(0).setWoodAmount(currentempire.getStockpiles().get(0).getWoodAmount() - rate);
                        currentempire.getArmouries().get(0).addPikeAmount(rate);
                        currentempire.getArmouries().get(0).addSpearAmount(rate);
                    }
                }

                case "armourer": {
                    if (currentempire.getStockpiles().get(0).getIronAmount() >= rate) {
                        currentempire.getStockpiles().get(0).setIronAmount(currentempire.getStockpiles().get(0).getIronAmount() - rate);
                        currentempire.getArmouries().get(0).addMetalArmourAmount(rate);
                    }

                }
            }
        }
    }


    public static void calculateStockpileThings() {
        int oxTethers = 0;
        int quarry = 0;
        for (Building building : currentempire.getBuildings()) {
            double rate = currentempire.getEfficiency();
            int produce = building.getBuildingType().getProduceAmount();
            int total = (int) (rate * produce);
            switch (building.getBuildingType().getName()) {
                case "Mill": {
                    if (currentempire.getStockpiles().get(0).getWoodAmount() < rate) {
                        continue;
                    }

                    currentempire.getStockpiles().get(0).setFlourAmount(currentempire.getStockpiles().get(0).getFlourAmount() + total);
                    currentempire.getStockpiles().get(0).setWheatAmount(currentempire.getStockpiles().get(0).getWheatAmount() - total);
                }
                case "IronMine": {
                    if (currentempire.getStockpiles().get(0).getFreeSpace() < total) {
                        continue;
                    }
                    currentempire.getStockpiles().get(0).setIronAmount(currentempire.getStockpiles().get(0).getIronAmount() + total);
                }
                case "PitchRig" : {
                    if (currentempire.getStockpiles().get(0).getFreeSpace() < total) {
                        continue;
                    }
                    currentempire.getStockpiles().get(0).setPitchAmount(currentempire.getStockpiles().get(0).getPitchAmount() + total);
                }
                case "Woodcutter" :{
                    if (currentempire.getStockpiles().get(0).getFreeSpace() < total) {
                        continue;
                    }
                    currentempire.getStockpiles().get(0).setWoodAmount(currentempire.getStockpiles().get(0).getWoodAmount() + total);
                }
                case "HopsFarm" : {
                    if (currentempire.getStockpiles().get(0).getFreeSpace() < total) {
                        continue;
                    }
                    currentempire.getStockpiles().get(0).setHopsAmount(currentempire.getStockpiles().get(0).getHopsAmount()+ total);
                }
                case "WheatFarm" : {
                    if (currentempire.getStockpiles().get(0).getFreeSpace() < total) {
                        continue;
                    }
                    currentempire.getStockpiles().get(0).setWheatAmount(currentempire.getStockpiles().get(0).getWheatAmount()+ total);
                }
                case "Brewery" : {
                    if (currentempire.getStockpiles().get(0).getHopsAmount() < total) {
                        continue;
                    }
                    if (currentempire.getStockpiles().get(0).getFreeSpace() < produce * (rate - 1)) {
                        continue;
                    }
                    currentempire.getStockpiles().get(0).setAleAmount(currentempire.getStockpiles().get(0).getAleAmount()+ total);
                    currentempire.getStockpiles().get(0).setHopsAmount( currentempire.getStockpiles().get(0).getHopsAmount() -total);
                }
                case "Quarry" : {
                    quarry++;
                }
            }
        }
        int productOfAllQuarries = (int) (quarry * currentempire.getEfficiency() * BuildingType.QUARRY.getProduceAmount());

        currentempire.getStockpiles().get(0).setStoneAmount(currentempire.getStockpiles().get(0).getStoneAmount() + productOfAllQuarries);

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
        if (soldierType.getMoneyCost() * number > currentempire.getTotalGoldAmount())
            return "not enough amount of money.";
        if (number > currentempire.getNoneWorkerAmount())
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
                        soldierType, currentempire, selectedBuilding.getX() + 1, selectedBuilding.getY());
                currentempire.getSoldiers().add(soldier);
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
        if (soldierType.getMoneyCost() > currentempire.getTotalGoldAmount()) return "not enough gold amount.";
        if (soldierType.getEngineerNeededAmount() > selectedSoldiers.size()) return "not enough engineer.";
        currentempire.setTotalGoldAmount(currentempire.getTotalGoldAmount() - soldierType.getMoneyCost());
        Soldier soldier = new Soldier(soldierType.getHp(), soldierType.getName(), soldierType, currentempire, select_x, select_y);
        currentempire.getSoldiers().add(soldier);
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
            if (!soldier.getOwner().equals(currentempire)) enemy = true;
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
        if (x < 0 || y < 0 || x > NewGameController.getCurrent_map().getSizeOfTheMap() - 1 ||
                y > NewGameController.getCurrent_map().getSizeOfTheMap() - 1) {
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
        for (Armoury armoury : currentempire.getArmouries()) {
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

        for (Armoury armoury : currentempire.getArmouries()) {
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
        for (Armoury armoury : currentempire.getArmouries()) {
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
//
//        List<Cell> path = AstarShortestPath.findShortestPath(map, map.getMapCells(getSelect_x(), getSelect_y()), map.getMapCells(x, y));
        result = moveElements(x, y, selectedSoldiers);
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

    public static int moveElements(int x, int y, ArrayList<Soldier> selectedSoldiers) {

        for (Soldier soldier : selectedSoldiers) {
            soldier.setStop(false);
            // after the soldiers are stopped you
            // have to make it false to move them again
        }
        path = AstarShortestPath.findShortestPath(map, map.getMapCells(getSelect_x(), getSelect_y()), map.getMapCells(x, y));
        Apath = new ArrayList<>(path);
        select = new ArrayList<>(selectedSoldiers);
        if (path.size() > selectedSoldiers.get(0).getSoldierType().getMovementRange()) {

            System.out.println("Shortest path:");

            for (Cell cell : Apath) {
                System.out.println("(" + cell.getX() + ", " + cell.getY() + ")");
            }

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
        moveElements(x1, y1, selectedSoldiersPatrol);
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
                if (selectedSoldiers.isEmpty()) return "There was no unit with this type at this cell.";
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
        int currentStone = currentempire.getStockpiles().get(0).getStoneAmount();
        currentempire.getStockpiles().get(0).setStoneAmount(currentStone - (int) (rate * hpNeededToBeRepaired));
        selectedBuilding.setHp(selectedBuilding.getBuildingType().getHp());
        return "successfully done.";
    }

    public static int amountOfThingsInStockpile(String name) {
        int amount = 0;
        for (Armoury armoury : currentempire.getArmouries()) {
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
        for (Armoury armoury : currentempire.getArmouries()) {
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
            if (buildingType.getGoldPrice() > currentempire.getTotalGoldAmount())
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
            if (currentempire.getNoneWorkerAmount() < buildingType.getWorkerNeededAmount())
                return "not enough worker to run this building.";

            switch (buildingType.getResourcesPriceType().getResourceName()) {
                case "wood":
                    int currentWood = currentempire.getStockpiles().get(0).getWoodAmount();
                    if (currentWood < buildingType.getResourcePriceAmount()) return "not enough amount of wood.";
                    currentempire.getStockpiles().get(0).setWoodAmount(currentWood - buildingType.getResourcePriceAmount());
                case "iron":
                    int currentIron = currentempire.getStockpiles().get(0).getIronAmount();
                    if (currentIron < buildingType.getResourcePriceAmount()) return "not enough amount of iron.";
                    currentempire.getStockpiles().get(0).setIronAmount(currentIron - buildingType.getResourcePriceAmount());
                case "stone":
                    int currentStone = currentempire.getStockpiles().get(0).getStoneAmount();
                    if (currentStone < buildingType.getResourcePriceAmount()) return "not enough amount of stone.";
                    currentempire.getStockpiles().get(0).setStoneAmount(currentStone - buildingType.getResourcePriceAmount());
                case "pitch":
                    int currentPitch = currentempire.getStockpiles().get(0).getPitchAmount();
                    if (currentPitch < buildingType.getResourcePriceAmount()) return "not enough amount of pitch.";
                    currentempire.getStockpiles().get(0).setPitchAmount(currentPitch - buildingType.getResourcePriceAmount());

            }
            currentempire.setNoneWorkerAmount(currentempire.getNoneWorkerAmount() - buildingType.getWorkerNeededAmount());
            currentempire.setWorkerAmount(currentempire.getWorkerAmount() + buildingType.getWorkerNeededAmount());
            Building building = new Building(buildingType.getHp(), buildingType.getName(), buildingType, x, y, Empire.getCurrentEmpire());
            currentempire.getBuildings().add(building);
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
        for (Stable stable : currentempire.getStables()) {
            amount += stable.getCurrentNumberOfHorses();
        }
        return amount;
    }

    public static void addHorsesToStables(int amount) {

        for (Stable stable : currentempire.getStables()) {
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

        for (Stable stable : currentempire.getStables()) {
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
            currentempire.getSoldiers().remove(soldier);
        }
        currentempire.setNoneWorkerAmount(selectedSoldiers.size());
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
        GameMenuController.currentempire = currentEmpire;
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

    public String digTunnel() {
        return "";
    }

    public String pourOil() {
        return "";
    }
    public static Empire getEmpireByUserName(String name) {
        for (Empire empire : empires) {
            if (empire.getPlayer().getUsername().equals(name)) return empire;
        }
        return null;
    }

}

