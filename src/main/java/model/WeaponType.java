package model;

public enum WeaponType {
    PIKE("Pike","/images/resources/pike.png"),
    MACE("Mace","/images/resources/pike.png"),
    BOW("Bow","/images/resources/pike.png"),
    SPEAR("Spear","/images/resources/spear.png"),
    SWORD("Sword","/images/resources/sword.png"),
    METAL_ARMOR("Metal_Armor","/images/resources/metalArmour.png"),
    LEATHER_ARMOR("Leather_Armor","/images/resources/leatherArmour.png"),
    CROSSBOW("CrossBow","/images/resources/crossBow.png"),
    WEAPONS("Weapons","/images/resources/weapons.png")
    ;

    private String name;
    private String filePath;



    WeaponType(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
