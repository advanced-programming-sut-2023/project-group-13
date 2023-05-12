package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private Cell[][] map;
    private boolean isMapExist;
    private String mapName;
    private String formatMapName;
    private static ArrayList<Map> maps = new ArrayList<>();
    private int sizeOfTheMap;

    public Map(int sizeOfTheMap, String mapName) {
        this.sizeOfTheMap = sizeOfTheMap;
        this.mapName = mapName;
    }

    public void createMap() {
        map = new Cell[sizeOfTheMap][sizeOfTheMap];
//        map.add(new Cell());
        for (int i = 0; i < sizeOfTheMap; i++) {
            for (int j = 0; j < sizeOfTheMap; j++) {
                map[i][j] = new Cell();
            }
        }
        SavetoJason();
    }
    public boolean loadMap() throws IOException {
        maps = SaveAndLoadData.LoadData("Maps.json", new TypeToken<ArrayList<Map>>(){}.getType());
        formatMapName = mapName.replaceAll(" ","") + ".json";
        // todo to pass the mapname to this method
        for (Map map1 : maps) {
            if (map1.getMapName().equals(mapName)) {
                setMapExist(true);
            }
        }
        if (!isMapExist()) return false;
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(formatMapName)));
        } catch (IOException e) {
            System.out.println("error occured in loading the map!");
        }
         map = new Gson().fromJson(json, Cell[][].class);
        if (map == null) return false;
        int k = 0;
        return true;
    }

    public void SavetoJason() {
        // todo to pass the mapName to this method
        formatMapName = mapName.replaceAll(" ","") + ".json";
        try {
            FileWriter MapWriter = new FileWriter(formatMapName);
            MapWriter.write(new Gson().toJson(map));
            MapWriter.close();
            maps.add(this);
            SaveAndLoadData.SaveToJson(maps,"Maps.json");
        } catch (IOException e) {
            System.out.println("an error has happened");
        }

    }

    public int getSizeOfTheMap() {
        return sizeOfTheMap;
    }

    public Cell getMapCells(int x, int y) {
        return map[x][y];
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getMapName() {
        return mapName;
    }


    public static ArrayList<Map> getMaps() {
        return maps;
    }

    public boolean isMapExist() {
        return isMapExist;
    }

    public void setMapExist(boolean mapExist) {
        isMapExist = mapExist;
    }
}
