package model;

public enum StockpileType {
    FLOUR("/images/resources/flour.png"),
    HOPS("/images/resources/hops.png"),
    ALE("/images/resources/ale.png"),
    WHEAT("/images/resources/wheat.png")
    ;

    private String filePath;

    StockpileType(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
