package model;

public enum TreeType {
    DESERTTREE("DesertTree",500),
    CHERRYTREE("CherryTree",500),
    OLIVETREE("OliveTree",500),
    COCONUTTREE("COCONUTTREE",500),
    PALMTREE("PalmTree",500);
    private String name;
    private int hp;

    TreeType(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public static TreeType getTreeTypeByName(String treeName) {
        for (TreeType treeType : TreeType.values()) {
            if (treeType.getName().equals(treeName)) return treeType;
        }
        return null;
    }
}
