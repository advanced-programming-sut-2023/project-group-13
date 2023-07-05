package model;

public enum FoodType {
    MEAT("/images/resources/meat.png"),
    APPLES("/images/resources/apple.png"),
    CHEESE("/images/resources/cheese.png"),
    BREAD("/images/resources/bread.png"),
    FOODS("/images/resources/foods.png"),
    RAWMATERIAL("/images/resources/rawMaterials.png")
    ;

    String filePath;

    FoodType(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
