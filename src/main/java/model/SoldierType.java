package model;

public enum SoldierType {
    KING(800, 160, 0.2, 3, 1, 0, 0, 0, 0, null, null, false, false, false, "king", false,true),
    ARCHER(200, 100, 0.15, 1, 10, 4, 12, 0.3, 0, WeaponType.BOW, null, false, false, false, "archer", true,true),
    CROSSBOW_MAN(250, 120, 0.2, 3, 5, 0, 20, 0.1, 0, WeaponType.CROSSBOW, WeaponType.LEATHER_ARMOR, false, false, false, "crossbow man", true,true),
    SPEAR_MAN(150, 180, 0.1, 3, 2, 0, 8, 0.1, 2, WeaponType.SPEAR, null, true, true, false, "spear man", false,true),
    PIKE_MAN(500, 200, 0.3, 2, 1, 0, 20, 0, 0, WeaponType.PIKE, WeaponType.METAL_ARMOR, false, false, false, "pike man", false,true),
    MACE_MAN(350, 300, 0.2, 3, 1, 0, 20, 0, 0, WeaponType.MACE, WeaponType.LEATHER_ARMOR, true, false, false, "mace man", false,true),
    SWORDS_MAN(250, 350, 0.27, 1, 2, 0, 40, 0, 3, WeaponType.SWORD, WeaponType.METAL_ARMOR, false, true, false, "swordsman", false,true),
    KNIGHT(600, 400, 0.35, 4, 4, 0, 120, 0, 0, WeaponType.SWORD, WeaponType.METAL_ARMOR, false, false, false, "knight", false,false),
    TUNNELER(100, 100, 0.1, 3, 1, 0, 30, 0, 2, null, null, false, true, false, "tunneler", false,true),
    LADDER_MAN(80, 0, 0.05, 3, 1, 0, 6, 0, 2, null, null, true, false, false, "ladder man", false,true),
    ENGINEER(80, 0, 0.05, 3, 0, 0, 30, 0, 3, null, null, false, true, false, "engineer", false,true),
//    OIL_ENGINEER(80, 100, 0.05, 2, 1, 0, 0, 0, 0, WeaponType.OIL, null, false, false, false, "oil engineer", false),
    //todo to resolve Oil_EnGineer
    BLACK_MONK(200, 100, 0.15, 2, 1, 0, 10, 0.2, 0, null, null, false, false, false, "black monk", false,true),

    ARCHER_BOW(200, 100, 0.12, 3, 11, 5, 60, 0.3, 0, WeaponType.BOW, null, false, false, true, "archer bow", true, true),
    SLAVE(50, 40, 0, 3, 1, 0, 5, 0, 4, null, null, false, true, true, "slave", false, true),
    SLINGER(100, 100, 0.1, 3, 2, 0, 12, 0.2, 0, null, null, false, false, true, "slinger", true, true),
    ASSASSIN(400, 300, 0.3, 2, 3, 0, 100, 0, 0, null, null, true, false, true, "assassin", false, true),
    HORSE_ARCHER(300, 120, 0.2, 5, 8, 3, 60, 0.5, 0, WeaponType.BOW, null, false, false, true, "horse archer", true, false),
    ARABIAN_SWORDSMAN(500, 220, 0.2, 1, 2, 0, 80, 0, 0, WeaponType.SWORD, WeaponType.LEATHER_ARMOR, false, false, true, "arabian swordsman", false, true),
    FIRE_THROWER(150, 200, 0.1, 4, 2, 0, 70, 0.1, 0, null, null, false, false, true, "fire throwers", false, true),

  ;

    private int hp;
    private int SoldierDamage;
    private double defensePower;
    private int speed;
    private int range;
    private int movementRange;
    private int secondRange;
    private int moneyCost;
    private double precision;
    private int delay;
    private WeaponType weapon;
    private WeaponType weapon2;
    private boolean canClimb;
    /*boolean canThrowLadders;*/
    private boolean canDigDitch;
    private boolean isArab;
    private boolean isArcherType;
    private String name;




    SoldierType(int hp, int attackPower, double defensePower, int speed, int range, int secondRange,int moneyCost,
                double precision, int delay, WeaponType weapon, WeaponType weapon2, boolean canClimb,/* boolean canThrowLadders,*/ boolean canDigDitch, boolean isArab, String name, boolean isArcherType,
                boolean canBeDroppedOnBuilding) {
        this.hp = hp;
        this.SoldierDamage = attackPower;
        this.defensePower = defensePower;
        this.speed = speed;
        this.range = range;
        this.movementRange = this.speed * 2;
        this.secondRange = secondRange;
        this.moneyCost = moneyCost;
        this.precision = precision;
        this.delay = delay;
        this.weapon = weapon;
        this.weapon2 = weapon2;
        this.canClimb = canClimb;
        /*this.canThrowLadders = canThrowLadders;*/
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

    public double getDefensePower() {
        return defensePower;
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

    public int getSecondRange() {
        return secondRange;
    }

    public int getMoneyCost() {
        return moneyCost;
    }

    public double getPrecision() {
        return precision;
    }

    public int getDelay() {
        return delay;
    }

    public WeaponType getWeapon() {
        return weapon;
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

    public WeaponType getWeapon2() {
        return weapon2;
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
