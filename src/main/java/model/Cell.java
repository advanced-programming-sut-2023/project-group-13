package model;

import model.Enums.TypeofGround;

import java.util.ArrayList;

public class Cell {
    private String typeofGround;
    private boolean hasSoldierInCell;
    private boolean hasBuildingInCell;
    private boolean hasTreeInCell;

    private ArrayList<Soldier> soldiers = new ArrayList<Soldier>();
    private Soldier soldier;
    private Building building;
    private boolean obstacle;

    private TreeType tree;

    public Cell(boolean obstacle) {
        typeofGround = TypeofGround.EARTH.getFullNameType();
        this.obstacle = obstacle;
    }

    public String getTypeofGround() {
        return typeofGround;
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

//    public ArrayList<Building> getBuildings() {
//        return buildings;
//    }

    public void setTypeofGround(String typeofGround) {
        this.typeofGround = typeofGround;
    }

//    public ArrayList<TreeType> getTrees() {
//        return trees;
//    }

    public void setHasSoldierInCell(boolean hasSoldierInCell) {
        this.hasSoldierInCell = hasSoldierInCell;
    }

    public boolean isHasSoldierInCell() {
        return hasSoldierInCell;
    }

    public boolean setBuilding(String building , int x, int y) {
        BuildingType buildingType = BuildingType.getBuildingTypeByName(building);
        if (buildingType == null ) return false;
        this.building = new Building(buildingType.getHitPoint(),building,buildingType, x, y);
        // todo get building by name and assign it to this.building = building
        return true;
    }

    public Building getBuilding() {
        return building;
    }

    public void setHasBuildingInCell(boolean hasBuildingInCell) {
        this.hasBuildingInCell = hasBuildingInCell;
    }

    public boolean isHasBuildingInCell() {
        return hasBuildingInCell;
    }

    public boolean setTree(String type ,int x , int y) {
        TreeType treeType = TreeType.getTreeTypeByName(type);
        if (treeType == null) return false;
        this.building = new Building(treeType.getHp(),type,treeType, x , y);
        // todo to complete this part
        return true;
    }

    public TreeType getTree() {
        return tree;
    }

    public void setHasTreeInCell(boolean hasTreeInCell) {
        this.hasTreeInCell = hasTreeInCell;
    }

    public boolean isHasTreeInCell() {
        return hasTreeInCell;
    }

    public void remove() {
        this.building = null;
        this.tree = null;
        this.setTypeofGround(TypeofGround.EARTH.getFullNameType());
        this.getSoldiers().clear();
        this.setHasTreeInCell(false);
        this.setHasBuildingInCell(false);
        this.setHasSoldierInCell(false);
        // todo to make empty the arraylist of soldiers
    }
    public boolean putTroop(String type , Empire owner, int x, int y) {
        SoldierType soldierType = SoldierType.getSoldierTypeByString(type);
        if (soldierType == null) return false;
        soldiers.add(new Soldier(soldierType.getHp(),type,soldierType, owner, x, y));
        return true;
    }

    public Soldier getSoldier() {
        return soldier;
    }
}
