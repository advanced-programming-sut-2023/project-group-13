package model;

public enum WeaponType {
    PIKE("Spike",60),
    MACE("Mace",60),
    BOW("Bow",40),
    SPEAR("Spear",20),
    SWORD("Sword",60),
    METAL_ARMOR("Metal_Armor",60),
    LEATHER_ARMOR("Leather_Armor",40),
    CROSSBOW("CrossBow",40);

    private String name;
    private int price;

    WeaponType(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
