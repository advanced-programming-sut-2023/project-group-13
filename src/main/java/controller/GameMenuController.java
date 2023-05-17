package controller;

import model.*;

import java.util.ArrayList;
import java.util.regex.Matcher;

import model.Empire;
import model.Map;

import javax.management.MBeanAttributeInfo;

public class GameMenuController {
    public static Map map = NewGameController.getCurrent_map();
    public static ArrayList<Player> playersInGame = new ArrayList<>();
    public static Empire currentEmpire;
    public ArrayList<Soldier> selectedSoldiers = new ArrayList<>();
    public static Building selectedBuilding;
    public static int roundNumber = 0;
    public static Empire CurrentEmpire;
    public static BuildingType buildingType = GameMenuController.selectedBuilding.getBuildingType();

    public GameMenuController() {
        this.selectedSoldiers = new ArrayList<>();
    }

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
    public static String dropUnit(Matcher matcher) {
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

            default:
                return "success.";
        }
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

    public String moveunit(Matcher matcher) {
    return "";
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
        if (amount > getHorseAmount()) return;

        for (Stable stable : currentEmpire.getStables()) {
            if (stable.getCurrentNumberOfHorses() >= amount) {
                stable.addCurrentNumberOfHorses(-amount);
                break;
            } else {
                int remainAmount = amount - stable.getCurrentNumberOfHorses();
                stable.addCurrentNumberOfHorses(-1 * remainAmount);
                amount -= remainAmount;
                if (amount <= 0) {
                    break;
                }
            }
        }
    }

    public static String dropBuilding(Matcher matcher) {
        if (checkCordination(matcher)) {
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));
            BuildingType buildingType1 = BuildingType.getBuildingTypeByName(matcher.group("type"));
            if (buildingType1 == null) return "there is not such a building type.";
            String type = matcher.group("type");
            if (buildingType1.getGoldPrice() > currentEmpire.getTotalGoldAmount())
                return "you need more gold to build this building.";
            Cell cell = map.getMapCells(x , y);
            if (cell.getBuilding() != null)
                return "already there is a building at this cell.";
            if (cell.getSoldiers().size() > 0)
                return "there is(are) soldier(s) here.";

         //   if (cell.getTypeofGround().equals() || cell.isHasTreeInCell()  )
        } else
            return "Invalid cordination.";
        return "";
    }

}
