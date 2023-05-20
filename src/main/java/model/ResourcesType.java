package model;

public enum ResourcesType {
    WOOD("wood"),
    PITCH("pitch"),
    IRON("iron"),
    STONE("stone");
    private String resourceName;

    ResourcesType(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }

}
