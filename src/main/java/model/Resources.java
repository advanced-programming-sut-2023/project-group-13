package model;

public class Resources {
    private ResourcesType resourcesType;
    private int resourcePriceAmount;

    public Resources(ResourcesType resourcesType, int resourcePriceAmount) {
        this.resourcesType = resourcesType;
        this.resourcePriceAmount = resourcePriceAmount;
    }

    public ResourcesType getResourcesType() {
        return resourcesType;
    }

    public int getResourcePriceAmount() {
        return resourcePriceAmount;
    }

}
