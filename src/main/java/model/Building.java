package model;

public class Building {
    int hp;
    private String name;
    private BuildingType buildingType;
    private TreeType treeType;


    public Building(int hp, String name, BuildingType buildingType) {
        this.hp = hp;
        this.name = name;
        this.buildingType = buildingType;
    }
    public Building(int hp, String name, TreeType treeType) {
        this.hp = hp;
        this.name = name;
        this.treeType = treeType;
    }

    public String getName() {
        return name;
    }
}
