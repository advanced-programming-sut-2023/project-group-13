package model;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private Cell[][] map;
    private Map mapfields;
    private String mapName;
    private static HashMap<String,Cell[][]> maps = new HashMap<>();
    private int sizeOfTheMap;

    public Map(int sizeOfTheMap) {
        this.sizeOfTheMap = sizeOfTheMap;
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
    public boolean loadMap() {
        // todo to pass the mapname to this method
        for (int i = 0; i < maps.size(); i++) {
            if (!maps.containsKey(mapName)) {
                System.out.println("no such map exist!");
                return false;
            }
        }
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("map.json")));
        } catch (IOException e) {
            System.out.println("error occured in loading the map!");
        }
         map = new Gson().fromJson(json, Cell[][].class);
        if (map == null) return false;
        int k = 0;
//        for (int i = 0; i < sizeOfTheMap; i++) {
//            for (int j = 0; j < sizeOfTheMap; j++) {  
//            }
//        }
        return true;
    }

    public void SavetoJason() {
        // todo to pass the mapName to this method
        mapfields.setMapName(mapName);
        try {
            FileWriter MapWriter = new FileWriter("map.json");
            MapWriter.write(new Gson().toJson(map));
            MapWriter.close();
            maps.put(mapfields.getMapName(), map);
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

    public static HashMap<String, Cell[][]> getMaps() {
        return maps;
    }
}
