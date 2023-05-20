package controller;

import model.Empire;
import model.Food;
import model.FoodType;

import java.util.regex.Matcher;

public class KingdomMenuController {
    public static int showFearRate() {
        return GameMenuController.getCurrentEmpire().getFearRate();
    }

    public static void setFearRate(Matcher matcher) {
        int fearRate = Integer.parseInt(matcher.group("rateNumber"));
        GameMenuController.getCurrentEmpire().setFearRate(fearRate);
    }

    public static void setTaxRate(Matcher matcher) {
        int taxRate = Integer.parseInt(matcher.group("rateNumber"));
        GameMenuController.getCurrentEmpire().setTaxRate(taxRate);
    }

    public static void setFoodRate(Matcher matcher) {
        int foodRate = Integer.parseInt(matcher.group("rateNumber"));
        GameMenuController.getCurrentEmpire().setFearRate(foodRate);
    }

    public static int showTaxRate() {
        return GameMenuController.getCurrentEmpire().getTaxRate();
    }

    public static int showFoodRate() {
        return GameMenuController.getCurrentEmpire().getFoodRate();
    }


    public static String showPopularityFactors() {

        return "tax: " + GameMenuController.getCurrentEmpire().getTaxPopularity()
                + "\nreligion: " + GameMenuController.getCurrentEmpire().getReligionPopularity()
                + "\nfood: " + GameMenuController.getCurrentEmpire().getFoodPopularity()
                + "\ninn: " + GameMenuController.getCurrentEmpire().getInnPopularity();
    }

    public static String showFoodList() {
        String result = "";
        int cheese = 0;
        int apple = 0;
        int meat = 0;
        int bread = 0;
        for (Food food : GameMenuController.getCurrentEmpire().getFoods()) {
            if (food.getType().equals(FoodType.CHEESE)) cheese++;
            if (food.getType().equals(FoodType.APPLES)) apple++;
            if (food.getType().equals(FoodType.MEAT)) meat++;
            if (food.getType().equals(FoodType.BREAD)) bread++;

        }
        return "cheese: " + cheese +
                "\napple: " + apple +
                "\nmeat: " + meat +
                "\nbread: " + bread;
    }

    public static int showPopularity() {
        return GameMenuController.getCurrentEmpire().getPopularity();
    }

    public static int calculateTax() {
        int taxRate = GameMenuController.getCurrentEmpire().getTaxRate();
        int peopleAmount = GameMenuController.getCurrentEmpire().getAllPeopleAmount();
        switch (taxRate) {
            case -3:
                GameMenuController.getCurrentEmpire().setTaxPopularity(7);
                return (int) (-1 * peopleAmount);
            case -2:
                GameMenuController.getCurrentEmpire().setFoodPopularity(5);
                return (int) (-0.8 * peopleAmount);
            case -1:
                GameMenuController.getCurrentEmpire().setFoodPopularity(3);
                return (int) (-0.6 * peopleAmount);
            case 0:
                GameMenuController.getCurrentEmpire().setFoodPopularity(1);
                return 0;
            case 1:
                GameMenuController.getCurrentEmpire().setFoodPopularity(-2);
                return (int) (0.6 * peopleAmount);
            case 2:
                GameMenuController.getCurrentEmpire().setFoodPopularity(-4);
                return (int) (0.8 * peopleAmount);
            case 3:
                GameMenuController.getCurrentEmpire().setFoodPopularity(-6);
                return (int) (1.0 * peopleAmount);
            case 4:
                GameMenuController.getCurrentEmpire().setFoodPopularity(-8);
                return (int) (1.2 * peopleAmount);
            case 5:
                GameMenuController.getCurrentEmpire().setFoodPopularity(-12);
                return (int) (1.4 * peopleAmount);
            case 6:
                GameMenuController.getCurrentEmpire().setFoodPopularity(-16);
                return (int) (1.6 * peopleAmount);
            case 7:
                GameMenuController.getCurrentEmpire().setFoodPopularity(-20);
                return (int) (1.8 * peopleAmount);
            case 8:
                GameMenuController.getCurrentEmpire().setFoodPopularity(-24);
                return (int) (2.0 * peopleAmount);
            default:
                return 0;
        }
    }

    public static int calculateFood() {
        int foodRate = GameMenuController.getCurrentEmpire().getFoodRate();
        int peopleAmount = GameMenuController.getCurrentEmpire().getAllPeopleAmount();
        switch (foodRate) {

            case -2:
                GameMenuController.getCurrentEmpire().setFoodPopularity(-8);
                return 0;
            case -1:
                GameMenuController.getCurrentEmpire().setFoodPopularity(-4);
                return (int) (0.5 * peopleAmount);
            case 0:
                return  peopleAmount;
            case 1:
                GameMenuController.getCurrentEmpire().setFoodPopularity(4);
                return (int) (1.5 * peopleAmount);
            case 2:
                GameMenuController.getCurrentEmpire().setFoodPopularity(8);
                return (int) (2.0 * peopleAmount);

            default:
                return 0;
        }
    }
}
