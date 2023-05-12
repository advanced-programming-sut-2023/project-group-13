package controller;

import com.google.gson.reflect.TypeToken;
import model.Map;
import model.SaveAndLoadData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class NewGameController {
    private static Map current_map;
    private ArrayList<Map> maps = new ArrayList<>();
    public String selectMap(Matcher matcher) throws IOException {
        String mapName = matcher.group("mapName");
        maps = SaveAndLoadData.LoadData(mapName, new TypeToken<ArrayList<Map>>(){}.getType());
        for (Map map : maps) {
            if (map.getMapName().equals(mapName)) {
                current_map = map;
                return "map selected successfully!";
            }
        }
        return "map name not valid!";
    }

    public String Selectplayers(Matcher matcher) {
        // todo to implement SelectPlayers
    }

    public static Map getCurrent_map() {
        return current_map;
    }
}
