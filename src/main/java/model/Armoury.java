package model;

public class Armoury extends Building {
   private int MetalArmourAmount;
   private int LeatherArmourAmount;
   private int CrossBowAmount;
   private int MaceAmount;
   private int PikeAmount;
   private int SwordAmount;
   private int BowAmount;
   private int SpearAmount;

    public Armoury(int hp,int x, int y, int metalArmourAmount, int leatherArmourAmount,
                   int crossBowAmount, int maceAmount, int pikeAmount, int swordAmount, int bowAmount, int spearAmount) {
        super(hp, "armoury" , BuildingType.ARMORY , x ,y );
        MetalArmourAmount = metalArmourAmount;
        LeatherArmourAmount = leatherArmourAmount;
        CrossBowAmount = crossBowAmount;
        MaceAmount = maceAmount;
        PikeAmount = pikeAmount;
        SwordAmount = swordAmount;
        BowAmount = bowAmount;
        SpearAmount = spearAmount;
    }

    public int getMetalArmourAmount() {
        return MetalArmourAmount;
    }

    public void setMetalArmourAmount(int metalArmourAmount) {
        MetalArmourAmount = metalArmourAmount;
    }

    public int getLeatherArmourAmount() {
        return LeatherArmourAmount;
    }

    public void setLeatherArmourAmount(int leatherArmourAmount) {
        LeatherArmourAmount = leatherArmourAmount;
    }

    public int getCrossBowAmount() {
        return CrossBowAmount;
    }

    public void setCrossBowAmount(int crossBowAmount) {
        CrossBowAmount = crossBowAmount;
    }

    public int getMaceAmount() {
        return MaceAmount;
    }

    public void setMaceAmount(int maceAmount) {
        MaceAmount = maceAmount;
    }

    public int getPikeAmount() {
        return PikeAmount;
    }

    public void setPikeAmount(int pikeAmount) {
        PikeAmount = pikeAmount;
    }

    public int getSwordAmount() {
        return SwordAmount;
    }

    public void setSwordAmount(int swordAmount) {
        SwordAmount = swordAmount;
    }

    public int getBowAmount() {
        return BowAmount;
    }

    public void setBowAmount(int bowAmount) {
        BowAmount = bowAmount;
    }

    public int getSpearAmount() {
        return SpearAmount;
    }

    public void setSpearAmount(int spearAmount) {
        SpearAmount = spearAmount;
    }
}
