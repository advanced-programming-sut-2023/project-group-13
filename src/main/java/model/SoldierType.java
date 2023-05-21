package model;

public enum SoldierType {
    KING(1200, 160, 3, 1, 0, false, false, false, "king", false,true ,0),
    ARCHER(200, 100, 1, 10, 12, false, false, false, "archer", true,true ,0),
    CROSSBOW_MAN(250, 120, 3, 5, 20, false, false, false, "crossbow man", true,true ,0 ),
    SPEAR_MAN(150, 180, 3, 2, 8, true, true, false, "spear man", false,true ,0 ),
    PIKE_MAN(500, 200, 2, 1, 20, false, false, false, "pike man", false,true , 0),
    MACE_MAN(350, 300, 3, 1, 20, true, false, false, "mace man", false,true ,0 ),
    SWORDS_MAN(250, 350, 1, 2, 40, false, true, false, "swordsman", false,true , 0),
    KNIGHT(600, 400, 4, 4, 120, false, false, false, "knight", false,false ,0 ),
    TUNNELER(100, 100, 3, 1, 30, false, true, false, "tunneler", false,true , 0),
    LADDER_MAN(80, 0, 3, 1, 6, true, false, false, "ladder man", false,true , 0),
    ENGINEER(80, 0, 3, 0, 30, false, true, false, "engineer", false,true , 0),
    OIL_ENGINEER(80, 100, 2, 2, 30,  false, false, false,  "oil engineer", false ,  false , 1),
    BLACK_MONK(200, 100, 2, 1, 10, false, false, false, "black monk", false,true , 0),

    ARCHER_BOW(200, 100, 3, 11, 60, false, false, true, "archer bow", true, true , 0),
    SLAVE(50, 40, 3, 1, 5, false, true, true, "slave", false, true , 0),
    SLINGER(100, 100, 3, 2, 12, false, false, true, "slinger", true, true , 0),
    ASSASSIN(400, 300, 2, 3, 100, true, false, true, "assassin", false, true , 0),
    HORSE_ARCHER(300, 120, 5, 8, 60, false, false, true, "horse archer", true, false , 0),
    ARABIAN_SWORDSMAN(500, 220, 1, 2, 80, false, false, true, "arabian swordsman", false, true , 0),
    FIRE_THROWER(150, 200, 4, 2, 70, false, false, true, "fire throwers", false, true , 0),
    PORTABLE_SHIELD( 500, 0, 4, 5, 0, true , false , false , "portableShield", false ,  true , 1),
    BATTERING_RAM(1000, 500, 2000, 2, 150,false , false ,false,"battering ram",false,false,4 ),
    TREBUCHET(1000, 1000, 400, 10, 150, false,false,false, "trebuchet" , false,false, 3),
    CATAPULT(1000, 650, 150, 5, 150, false, false,false,"catapult" , false,false, 2),
    FIRE_BALLISTA(1000, 200, 150, 5, 150, false, false,false, "fireballista" , false,false, 2 ),
    SIEGE_TOWER(600, 0, 3300, 3, 150, false,false,false, "siegeTower" , false , false , 4 );

    ;

    private int hp;
    private int SoldierDamage;
    private int speed;
    private int range;
    private int movementRange;
    private int moneyCost;

    private boolean canClimb;

    private boolean canDigDitch;
    private boolean isArab;
    private boolean isArcherType;
    private String name;
    private int engineerNeededAmount;

    public void setCanClimb(boolean canClimb) {
        this.canClimb = canClimb;
    }

    public void setCanDigDitch(boolean canDigDitch) {
        this.canDigDitch = canDigDitch;
    }

    public int getEngineerNeededAmount() {
        return engineerNeededAmount;
    }

    public void setEngineerNeededAmount(int engineerNeededAmount) {
        this.engineerNeededAmount = engineerNeededAmount;
    }

    SoldierType(int hp, int attackPower, int speed, int range, int moneyCost,
                boolean canClimb, boolean canDigDitch, boolean isArab, String name, boolean isArcherType,
                boolean canBeDroppedOnBuilding , int engineerNeededAmount ) {
        this.hp = hp;
        this.SoldierDamage = attackPower;
        this.speed = speed;
        this.range = range;
        this.movementRange = this.speed * 2;
        this.moneyCost = moneyCost;
        this.engineerNeededAmount = engineerNeededAmount;
        this.canClimb = canClimb;
        this.canDigDitch = canDigDitch;
        this.isArab = isArab;
        this.name = name;
        this.isArcherType = isArcherType;
    }

    public int getHp() {
        return hp;
    }

    public int getSoldierDamage() {
        return SoldierDamage;
    }


    public boolean isArcherType() {
        return isArcherType;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRange() {
        return range;
    }

    public int getMoneyCost() {
        return moneyCost;
    }




    public boolean isCanClimb() {
        return canClimb;
    }

    public boolean isCanDigDitch() {
        return canDigDitch;
    }

    public boolean isArab() {
        return isArab;
    }

    public String getName() {
        return name;
    }


    public int getMovementRange() {
        return movementRange;
    }

    public static SoldierType getSoldierTypeByString(String soldierName) {
        SoldierType type = null;
        for (SoldierType temp : SoldierType.values()) {
            if (temp.getName().equals(soldierName)) type = temp;
        }
        return type;
    }
}
