package model;

import java.util.ArrayList;

public class Shop {
    private static final ArrayList<Shop> commodities = new ArrayList<>();
    private final String itemName;
    private int itemAmount;
    private final int price;
    private int storeInventory;
    private final String category;
    public Shop(String category, String itemName, int itemAmount, int price) {
        this.itemName = itemName;
        this.itemAmount = itemAmount;
        this.price = price;
        this.category = category;
        this.storeInventory = 10000;
    }

    public static ArrayList<Shop> getCommodities() {
        return commodities;
    }

    public static void addCommodities(Shop addCommodities) {
        commodities.add(addCommodities);
    }

    public String getItemName() {
        return itemName;
    }
    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount += itemAmount;
    }

    public int getPrice() {
        return price;
    }

    public int getStoreInventory() {
        return storeInventory;
    }

    public void setStoreInventory(int storeInventory) {
        this.storeInventory += storeInventory;
    }

    public String getCategory() {
        return category;
    }

}
