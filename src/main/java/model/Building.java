package model;

public class Building {
    int hp;
    private String name;
    private BuildingType buildingType;
    private TreeType treeType;
    int x;
    int y;


    public Building(int hp, String name, BuildingType buildingType,int x , int y) {
        this.hp = hp;
        this.name = name;
        this.buildingType = buildingType;
        this.x = x;
        this.y = y;
    }
    public Building(int hp, String name, TreeType treeType,int x , int y) {
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
}
