package controller;

import model.Empire;

public class GameMenuController {
    public static int roundNumber = 0;
    public static Empire CurrentEmpire;


    public static String nextTurn() {
        roundNumber++;
        return "";
    }
}
