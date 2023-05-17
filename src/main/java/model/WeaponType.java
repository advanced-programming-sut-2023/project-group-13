package model;

public enum WeaponType {
    PIKE("Pike"),
    MACE("Mace"),
    BOW("Bow"),
    SPEAR("Spear"),
    SWORD("Sword"),
    METAL_ARMOR("Metal_Armor"),
    LEATHER_ARMOR("Leather_Armor"),
    CROSSBOW("CrossBow");

    private String name;


    WeaponType(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
