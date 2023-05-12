package controller;

import com.google.gson.reflect.TypeToken;
import model.Map;
import model.Player;
import model.SaveAndLoadData;

import java.io.IOException;
import java.util.ArrayList;

public class RunGameController {
    private ArrayList<Map> maps = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    // todo to understand that it must be static or not
    public void ShowAllMaps() throws IOException {
        int counter = 0;
        maps = SaveAndLoadData.LoadData("Maps.json", new TypeToken<ArrayList<Map>>() {
        }.getType());
        for (Map map : maps) {
            System.out.println(++counter + map.getMapName());
        }
    }

    public void showAllMembers() throws IOException {
        int counter = 0;
        players = SaveAndLoadData.LoadData("players.json", new TypeToken<ArrayList<Player>>(){}.getType());
        for (Player player : players) {
            System.out.println(++counter + player.getNickname());
        }
    }
}
