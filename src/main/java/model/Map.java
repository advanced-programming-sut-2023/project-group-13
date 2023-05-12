package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
    public static Cell[][] loadMap(String mapName) throws IOException {
        boolean isMapExist = false;
        maps = SaveAndLoadData.LoadData("Maps.json", new TypeToken<ArrayList<Map>>(){}.getType());
        String formatMapName = mapName.replaceAll(" ","") + ".json";
        // todo to pass the mapname to this method
        for (Map map1 : maps) {
            if (map1.getMapName().equals(mapName)) {
                isMapExist = true;
                String json = null;
                try {
                    json = new String(Files.readAllBytes(Paths.get(formatMapName)));
                } catch (IOException e) {
                    System.out.println("error occured in loading the map!");
                }
                return new Gson().fromJson(json, Cell[][].class);
            }
        }
        return null;
    }

    public void SavetoJason() {
        // todo to pass the mapName to this method
        formatMapName = mapName.replaceAll(" ","") + ".json";
        try {
            FileWriter MapWriter = new FileWriter(formatMapName);
            MapWriter.write(new Gson().toJson(map));
            MapWriter.close();
//            if (maps.size() != 0) {
//                if (maps.contains(map))
//                    maps.remove(map);
//            }
            File file = new File("Maps.json");
            if (file.exists()) maps = SaveAndLoadData.LoadData("Maps.json", new TypeToken<ArrayList<Map>>(){}.getType());
            maps.add(this);
            System.out.println(mapName.length());
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

    public void setMap(Cell[][] loadedMap) {
        this.map = loadedMap;
    }
}
