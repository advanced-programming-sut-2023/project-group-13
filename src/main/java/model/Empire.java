package model;

import model.Enums.GroundColor;

import java.util.ArrayList;

public class Empire {
    private int popularity;

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    private static Empire currentEmpire;
    private Player player;
    private int totalGoldAmount;
    private GroundColor groundColor;
    private ArrayList<Granary> granaries;
    private ArrayList<Stockpile> stockpiles;
    private ArrayList<Armoury> armouries;
    private int FearFactorRate;
    private int TotalFood;
    private ArrayList<Food> foods ;
    private int taxRate;
    private int foodRate;
    private int foodDiversity;
    private int fearRate;

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    private String name;
    private ArrayList<PopularityFactor> popularityFactors;
    // private Soldier king;
    private int peopleAmount = 8; //worker and none worker
    private int noneWorkerAmount;
    private int workerAmount;
    private int SoldierAmount;
    private int allPeopleAmount; //worker and none worker and troops
    private int maximumPeopleAmount = 120;
    private ArrayList<Building> buildings;
    private ArrayList<Soldier> soldiers;

    Building kingPit;

    public Empire(Player player, GroundColor groundColor, Building kingPit) {
        this.player = player;
        this.groundColor = groundColor;
        this.kingPit = kingPit;
        this.allPeopleAmount = 10;
        this.noneWorkerAmount = 10;
        this.SoldierAmount = 0;
        this.workerAmount = 0;
        this. totalGoldAmount = 2000;
        this.foodDiversity = 0;
        this.foods = new ArrayList<>();
        this.soldiers = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.popularityFactors = new ArrayList<>();
        this.granaries = new ArrayList<>();
        this.armouries = new ArrayList<>();
        this.stockpiles= new ArrayList<>();

    }

    public static Empire getCurrentEmpire() {
        return currentEmpire;
    }

    public static void setCurrentEmpire(Empire currentEmpire) {
        Empire.currentEmpire = currentEmpire;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getTotalGoldAmount() {
        return totalGoldAmount;
    }

    public void setTotalGoldAmount(int totalGoldAmount) {
        this.totalGoldAmount = totalGoldAmount;
    }

    public GroundColor getGroundColor() {
        return groundColor;
    }

    public void setGroundColor(GroundColor groundColor) {
        this.groundColor = groundColor;
    }

    public ArrayList<Granary> getGranaries() {
        return granaries;
    }

    public void setGranaries(ArrayList<Granary> granaries) {
        this.granaries = granaries;
    }

    public ArrayList<Stockpile> getStockpiles() {
        return stockpiles;
    }

    public void setStockpiles(ArrayList<Stockpile> stockpiles) {
        this.stockpiles = stockpiles;
    }

    public ArrayList<Armoury> getArmouries() {
        return armouries;
    }

    public void setArmouries(ArrayList<Armoury> armouries) {
        this.armouries = armouries;
    }

    public int getFearFactorRate() {
        return FearFactorRate;
    }

    public void setFearFactorRate(int fearFactorRate) {
        FearFactorRate = fearFactorRate;
    }

    public int getTotalFood() {
        return TotalFood;
    }

    public void setTotalFood(int totalFood) {
        TotalFood = totalFood;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public int getFoodDiversity() {
        return foodDiversity;
    }

    public void setFoodDiversity(int foodDiversity) {
        this.foodDiversity = foodDiversity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PopularityFactor> getPopularityFactors() {
        return popularityFactors;
    }

    public void setPopularityFactors(ArrayList<PopularityFactor> popularityFactors) {
        this.popularityFactors = popularityFactors;
    }

    public int getPeopleAmount() {
        return peopleAmount;
    }

    public void setPeopleAmount(int peopleAmount) {
        this.peopleAmount = peopleAmount;
    }

    public int getNoneWorkerAmount() {
        return noneWorkerAmount;
    }

    public void setNoneWorkerAmount(int noneWorkerAmount) {
        this.noneWorkerAmount = noneWorkerAmount;
    }

    public int getWorkerAmount() {
        return workerAmount;
    }

    public void setWorkerAmount(int workerAmount) {
        this.workerAmount = workerAmount;
    }

    public int getSoldierAmount() {
        return SoldierAmount;
    }

    public void setSoldierAmount(int soldierAmount) {
        SoldierAmount = soldierAmount;
    }

    public int getAllPeopleAmount() {
        return allPeopleAmount;
    }

    public void setAllPeopleAmount(int allPeopleAmount) {
        this.allPeopleAmount = allPeopleAmount;
    }

    public int getMaximumPeopleAmount() {
        return maximumPeopleAmount;
    }

    public void setMaximumPeopleAmount(int maximumPeopleAmount) {
        this.maximumPeopleAmount = maximumPeopleAmount;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(ArrayList<Soldier> soldiers) {
        this.soldiers = soldiers;
    }

    public Building getKingPit() {
        return kingPit;
    }

    public void setKingPit(Building kingPit) {
        this.kingPit = kingPit;
    }
}
