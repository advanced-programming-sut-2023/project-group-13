package model;

public enum AllResource {
    WOOD("Wood"),
    IRON("Iron"),
    STONE("Stone"),
    ALE("Ale"),
    PITCH("Pitch"),
    HOPS("HOPS"),
    FLOUR("Flour"),
    WHEAT("Wheat"),
    BREAD("Bread"),
    APPLE("Apple"),
    CHEESE("Cheese"),
    MEAT("Meat"),
    METALARMOUR("MetalArmour"),
    LEATHERARMOUR("LeatherArmour"),
    CROSSBOW("CrossBow"),
    MACE("Mace"),
    PIKE("Pike"),
    SWORD("Sword"),
    BOW("Bow"),
    SPEAR("Spear");
    private String resourceName;

    AllResource(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }
}
