package model;

public enum TreeType {
    DESERTTREE("DesertTree",500,"/images/game/map/trees/desertShrub1.png"),
    CHERRYTREE("CherryTree",500,"/images/game/map/trees/cherryPalm.png"),
    OLIVETREE("OliveTree",500,"/images/game/map/trees/oliveTree.png"),
    COCONUTTREE("COCONUTTREE",500,"/images/game/map/trees/coconutPalm.png"),
    PALMTREE("PalmTree",500,"/images/game/map/trees/datePalm.png");
    private String filePath;
    private String name;
    private int hp;

    TreeType(String name, int hp, String filePath) {
        this.name = name;
        this.hp = hp;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public String getFilePath() {
        return filePath;
    }

    public static TreeType getTreeTypeByName(String treeName) {
        for (TreeType treeType : TreeType.values()) {
            if (treeType.getName().equals(treeName)) return treeType;
        }
        return null;
    }
}
