package model.Enums;

public enum Paths {
    DESERT_BACKGROUND("/desert_tile.jpg"),
    ;
    private String path;

    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
