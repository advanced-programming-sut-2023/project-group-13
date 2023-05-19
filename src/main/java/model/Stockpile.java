package model;

public class Stockpile extends Building {
    private int woodAmount;
    private int ironAmount;
    private int stoneAmount;
    private int aleAmount;
    private int flourAmount;
    private int pitchAmount;
    private int hopsAmount;
    private int wheatAmount;
    private int freeSpace;

    public int getFreeSpace() {
        return wheatAmount+hopsAmount+pitchAmount+flourAmount+aleAmount+stoneAmount+ironAmount+woodAmount;
    }

    public void setFreeSpace(int freeSpace) {
        this.freeSpace = freeSpace;
    }

    public Stockpile(int x, int y, int woodAmount, int ironAmount, int stoneAmount,
                     int aleAmount, int flourAmount, int pitchAmount, int hopsAmount, int wheatAmount) {
        super(500, "stockpile", BuildingType.STOCKPILE, x, y, Empire.getCurrentEmpire());
        this.woodAmount = woodAmount;
        this.ironAmount = ironAmount;
        this.stoneAmount = stoneAmount;
        this.aleAmount = aleAmount;
        this.flourAmount = flourAmount;
        this.pitchAmount = pitchAmount;
        this.hopsAmount = hopsAmount;
        this.wheatAmount = wheatAmount;
    }



    public int getWoodAmount() {
        return woodAmount;
    }

    public void setWoodAmount(int woodAmount) {
        this.woodAmount = woodAmount;
    }

    public int getIronAmount() {
        return ironAmount;
    }

    public void setIronAmount(int ironAmount) {
        this.ironAmount = ironAmount;
    }

    public int getStoneAmount() {
        return stoneAmount;
    }

    public void setStoneAmount(int stoneAmount) {
        this.stoneAmount = stoneAmount;
    }

    public int getAleAmount() {
        return aleAmount;
    }

    public void setAleAmount(int aleAmount) {
        this.aleAmount = aleAmount;
    }

    public int getFlourAmount() {
        return flourAmount;
    }

    public void setFlourAmount(int flourAmount) {
        this.flourAmount = flourAmount;
    }

    public int getPitchAmount() {
        return pitchAmount;
    }

    public void setPitchAmount(int pitchAmount) {
        this.pitchAmount = pitchAmount;
    }

    public int getHopsAmount() {
        return hopsAmount;
    }

    public void setHopsAmount(int hopsAmount) {
        this.hopsAmount = hopsAmount;
    }

    public int getWheatAmount() {
        return wheatAmount;
    }

    public void setWheatAmount(int wheatAmount) {
        this.wheatAmount = wheatAmount;
    }
}
