package model;

public class Food {
    private FoodType type;
    private int amount;
    public Food(int amount, FoodType type) {
        this.amount = amount;
        this.type = type;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
