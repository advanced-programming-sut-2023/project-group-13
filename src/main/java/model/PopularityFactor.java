package model;

public class PopularityFactor {
   private PopularityFactorType popularityFactorType;
   private int amount;

    public PopularityFactor(PopularityFactorType popularityFactorType, int amount) {
        this.popularityFactorType = popularityFactorType;
        this.amount = amount;
    }

    public PopularityFactorType getPopularityFactorType() {
        return popularityFactorType;
    }

    public void setPopularityFactorType(PopularityFactorType popularityFactorType) {
        this.popularityFactorType = popularityFactorType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
