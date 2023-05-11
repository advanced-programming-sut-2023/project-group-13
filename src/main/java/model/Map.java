package model;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Map {
//    private ArrayList<Cell> map = new ArrayList<>();
    private Cell[][] map;
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
    public boolean loadMap(){
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
        try {
            FileWriter MapWriter = new FileWriter("map.json");
            MapWriter.write(new Gson().toJson(map));
            MapWriter.close();
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

}
