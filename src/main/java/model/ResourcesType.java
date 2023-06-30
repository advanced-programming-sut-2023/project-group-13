package model;

public enum ResourcesType {
    WOOD("wood","/images/resources/wood.png"),
    PITCH("pitch","/images/resources/pitch.png"),
    IRON("iron","/images/resources/iron.png"),
    STONE("stone","/images/resources/stone.png"),
    RESOURCES("resources","/images/resources/resources.png")
    ;
    private String resourceName;
    private String filePath;

    ResourcesType(String resourceName, String filePath) {
        this.resourceName = resourceName;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getResourceName() {
        return resourceName;
    }

}
