package model.Enums;

import com.google.gson.reflect.TypeToken;
import model.Map;
import model.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;

public enum DataEnumFile {
    PLAYERS("players.json", new TypeToken<ArrayList<Player>>() {}.getType()),
    MAPS("Maps.json", new TypeToken<ArrayList<Map>>() {}.getType());
    // Add more enum values here if needed

    private final String fileName;
    private final Type dataType;

    DataEnumFile(String fileName, Type dataType) {
        this.fileName = fileName;
        this.dataType = dataType;
    }

    public String getFileName() {
        return fileName;
    }

    public Type getDataType() {
        return dataType;
    }

}
