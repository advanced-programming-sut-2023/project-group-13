package controller;

import com.google.gson.reflect.TypeToken;
import model.Enums.DataEnumFile;
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
        maps = SaveAndLoadData.LoadData(DataEnumFile.MAPS.getFileName(), DataEnumFile.MAPS.getDataType());
        for (Map map : maps) {
            System.out.println("map " + ++counter + ": " + map.getMapName());
        }
    }

    public void showAllMembers() throws IOException {
        int counter = 0;
        players = SaveAndLoadData.LoadData(DataEnumFile.PLAYERS.getFileName(), DataEnumFile.PLAYERS.getDataType());
        for (Player player : players) {
            System.out.println("player " + ++counter + ": " + player.getNickname());

        }
    }
}
