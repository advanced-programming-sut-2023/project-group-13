package model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveAndLoadData {
    public static void  SaveToJson(ArrayList<Player> players) {
        try {
             FileWriter fileWriter = new FileWriter("players.json");
             fileWriter.write(new Gson().toJson(players));
             fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Player> LoadPlayer(ArrayList<Player> players) throws IOException {

        String json = new String(Files.readAllBytes(Paths.get("players.json")));
        return new Gson().fromJson(json, new TypeToken<List<Player>>(){}.getType());
    }

}
