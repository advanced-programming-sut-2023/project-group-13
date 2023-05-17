package model;

public class Soldier extends People {

    //todo to add a kingdom of kingdom
    public Empire owner;
    private  String name;
    private SoldierType soldierType;
    private int situation;

    public Soldier(int hp, String name, SoldierType soldierType, Empire owner, int x, int y) {
        super(hp);
        this.name = name;
        this.soldierType = soldierType;
        this.situation = 2;
        this.owner = owner;
    }

    public int getSituation() {
        return situation;
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }
}
