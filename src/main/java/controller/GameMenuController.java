package controller;

import model.Empire;
import model.Map;

import java.util.regex.Matcher;

public class GameMenuController {
    public static Map map = NewGameController.getCurrent_map();
    public static int roundNumber = 0;
    public static Empire CurrentEmpire;




    public static String nextTurn() {
        roundNumber++;
        return "";
    }

    public String moveunit(Matcher matcher) {

    }
}
