package model;

public class Building {
    int hp;
    private String name;
    private BuildingType buildingType;
    private TreeType treeType;
    private int x;
    private int y;
    private Empire empire;


    public Building(int hp, String name, BuildingType buildingType, int x, int y, Empire empire) {
        this.hp = hp;
        this.name = name;
        this.buildingType = buildingType;
        this.x = x;
        this.y = y;
        this.empire = empire;
    }

    public Building(int hp, String name, TreeType treeType, int x, int y) {
        this.hp = hp;
        this.name = name;
        this.treeType = treeType;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    public void setTreeType(TreeType treeType) {
        this.treeType = treeType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }
}
