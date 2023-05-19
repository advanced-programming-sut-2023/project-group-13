package model;

import model.Enums.TypeofGround;

import java.util.ArrayList;

public class Cell implements Comparable<Cell> {
    private TypeofGround typeofGround;
    private boolean hasSoldierInCell;
    private boolean hasBuildingInCell;
    private boolean hasTreeInCell;

    private ArrayList<Soldier> soldiers = new ArrayList<Soldier>();
    private Soldier soldier;
    private Building building;
    private int x;
    private int y;
    private int f; // it understands the cost of the path
    private int g; // it understand the distance
    private boolean obstacle;
    private Cell parent;

    private TreeType tree;

    public Cell(int x, int y,boolean obstacle) {
        typeofGround = TypeofGround.EARTH;
        this.obstacle = obstacle;
        this.x = x;
        this.y = y;
        this.f = 0;
        this.g = 0;
        this.parent = null;
    }

    public TypeofGround getTypeofGround() {
        return typeofGround;
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

//    public ArrayList<Building> getBuildings() {
//        return buildings;
//    }

    public void setTypeofGround(TypeofGround typeofGround) {
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
        this.building = new Building(buildingType.getHp(),building,buildingType, x, y, Empire.getCurrentEmpire());
        // todo get building by name and assign it to this.building = building
        return true;
    }
    public void addBuilding(Building building) {
        this.building = building;
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
        this.setTypeofGround(TypeofGround.EARTH);
        this.getSoldiers().clear();
        this.setHasTreeInCell(false);
        this.setHasBuildingInCell(false);
        this.setHasSoldierInCell(false);
        // todo to make empty the arraylist of soldiers
    }
    public boolean putTroop(String type , Empire owner , int x , int y) {
        SoldierType soldierType = SoldierType.getSoldierTypeByString(type);
        if (soldierType == null) return false;
        soldiers.add(new Soldier(soldierType.getHp(),type,soldierType, owner, x, y));
        return true;
    }

    public void removeTroop(Soldier soldier) {
        soldiers.remove(soldier);
        // todo to handle the returning of these method
    }

    public void moveTroop(Soldier soldier) {
        soldiers.add(soldier);
        soldier.setX(getX());
        soldier.setY(getY());
    }
    public Soldier getSoldier() {
        return soldier;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isObstacle() {
        return obstacle;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    @Override
    public int compareTo(Cell other) {
        return Integer.compare(this.f, other.f);
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }
}
