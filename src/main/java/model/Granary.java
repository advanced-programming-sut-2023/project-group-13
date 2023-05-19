package model;

public class Granary extends Building {
    private int CheeseAmount;
    private int AppleAmount;
    private int MeatAmount;
    private int BreadAmount;
    public static int Capacity = 50;

    public Granary(int hp, String name , int x , int y, int cheeseAmount, int appleAmount, int meatAmount, int breadAmount ) {
        super(hp, name, BuildingType.GRANARY,x ,y, Empire.getCurrentEmpire());
        CheeseAmount = cheeseAmount;
        AppleAmount = appleAmount;
        MeatAmount = meatAmount;
        BreadAmount = breadAmount;
    }

    public int getCheeseAmount() {
        return CheeseAmount;
    }

    public void setCheeseAmount(int cheeseAmount) {
        CheeseAmount += cheeseAmount;
    }

    public int getAppleAmount() {
        return AppleAmount;
    }

    public void setAppleAmount(int appleAmount) {
        AppleAmount += appleAmount;
    }

    public int getMeatAmount() {
        return MeatAmount;
    }

    public void setMeatAmount(int meatAmount) {
        MeatAmount += meatAmount;
    }

    public int getBreadAmount() {
        return BreadAmount;
    }

    public void setBreadAmount(int breadAmount) {
        BreadAmount += breadAmount;
    }
}
