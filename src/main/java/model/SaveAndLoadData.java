package model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveAndLoadData {
    public static void  SaveToJson(ArrayList<?> date, String fileName) {
        try {
             FileWriter fileWriter = new FileWriter(fileName);
             fileWriter.write(new Gson().toJson(date));
             fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> ArrayList<T> LoadData(String fileName, Type type) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(fileName)));
        return new Gson().fromJson(json, type);
    }



}
