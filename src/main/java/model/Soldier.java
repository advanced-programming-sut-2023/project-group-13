package model;

public class Soldier extends People {

    //todo to add a kingdom of kingdom
    private String name;
    private SoldierType soldierType;

    public Soldier(int hp, String name, SoldierType soldierType) {
        super(hp);
        this.name = name;
        this.soldierType = soldierType;
    }

}
