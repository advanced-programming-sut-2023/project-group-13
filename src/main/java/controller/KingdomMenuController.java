package controller;

import model.Empire;

import java.util.regex.Matcher;

public class KingdomMenuController {
    public static int showFearRate() {
       return Empire.getCurrentEmpire().getFearRate();
    }
    public static void setFearRate(Matcher matcher) {
        int fearRate = Integer.parseInt(matcher.group("rateNumber"));
        Empire.getCurrentEmpire().setFearRate(fearRate);
    }
    public static void  setTaxRate(Matcher matcher) {
        int taxRate = Integer.parseInt(matcher.group("rateNumber"));
        Empire.getCurrentEmpire().setTaxRate(taxRate);
    }
    public static void  setFoodRate(Matcher matcher) {
        int foodRate = Integer.parseInt(matcher.group("rateNumber"));
        Empire.getCurrentEmpire().setFearRate(foodRate);
    }
    public static int showTaxRate() {
        return Empire.getCurrentEmpire().getTaxRate();
    }
    public static int showFoodRate() {
        return Empire.getCurrentEmpire().getFoodRate();
    }


    public static String showPopularityFactors() {
        String result = "";
        return result;
    }

    public static String showFoodList() {
        return "";
    }

    public static int showPopularity() {
        return Empire.getCurrentEmpire().getPopularity();
    }
}
