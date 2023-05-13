package controller;

import model.*;
import model.Enums.DataEnumFile;
import model.Enums.GroundColor;

import java.awt.desktop.AboutEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class NewGameController {
    private static Map current_map;
    private ArrayList<Map> maps = new ArrayList<>();
    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<Empire> empires = new ArrayList<>();
    private boolean mapSelected;
    private boolean playersSelected;
    public String selectMap(Matcher matcher) throws IOException {
        if (isMapSelected())
            return "map is already selected. enter \"deselect map\" to choose another map";
        String mapName = matcher.group("mapName");
        maps = SaveAndLoadData.LoadData(DataEnumFile.MAPS.getFileName(), DataEnumFile.MAPS.getDataType());
        for (Map map : maps) {
            if (map.getMapName().equals(mapName)) {
                current_map = map;
                setMapSelected(true);
                return "map selected successfully!";
            }
        }
        return "map name not valid!";
    }

    public String Selectplayers(Matcher matcher) throws IOException {
        if (isPlayersSelected())
            return "players already selected. type\"deselect players\" to choose another players";
        ArrayList<Player> savedplayers = new ArrayList<>();
        ArrayList<String> repeatedPlayer = new ArrayList<>();
        int size = Integer.parseInt(matcher.group("size"));
        if (size <= 0)
         // Player player =  Player.getPlayerByUsername(username);

            return "invalid size number!";
        savedplayers = SaveAndLoadData.LoadData(DataEnumFile.PLAYERS.getFileName(),DataEnumFile.PLAYERS.getDataType());
        if (matcher.group("player" + (size + 1)) != null) {
            return "you select players more than your specified size!";
        }
        for (int i = 1; i <= 8; i++) {
            if (matcher.group("player" + i) != null && i > size) {
                return "you should select players in order!";
            }
        }
        for (int i = 1; i <= size; i++) {
            String player = matcher.group("player" + i);
            if (player != null && !repeatedPlayer.contains(player))
                repeatedPlayer.add(player);
            else if (player != null && repeatedPlayer.contains(player))
                return "you entered a player's name two times!";
        }
        for (int i = 1; i <= size; i++) {
            String playerName = matcher.group("player" + i);
            for (Player player : savedplayers) {
                if (player.getUsername().equals(playerName) && !players.contains(player) && Player.getPlayerByUsername(playerName) != null) {
                    players.add(player);
                }
            }
        }
        if (players.size() == size) {
            setPlayersSelected(true);
            return size + " players selected successfully!";
        } else {
            players.clear();
            // add the upper line to fix another minor bug
            return "player invalid username.";
        }
    }

    public String gameStartCondition() {
        if (!isMapSelected())
            return "please select a map first!";
        if (!isPlayersSelected())
            return "please select players first";
        GroundColor[] colors = GroundColor.values();
        int base_scale = current_map.getSizeOfTheMap() == 200 ? 30: 60;
        int increase_coordinateSet = MapCoordinates(current_map.getSizeOfTheMap());
        if (increase_coordinateSet == 0)
           return "not enough players!";
        int x = base_scale, y = base_scale;
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            GroundColor color = colors[i % colors.length];
            /* it ensures that if the users are more than colors
            the colors repeat again in order which is not necessary in our game*/
            empires.add(new Empire(player, color, new Building(BuildingType.MAIN_CASTLE.getHitPoint(),
                    BuildingType.MAIN_CASTLE.getName(), BuildingType.MAIN_CASTLE, x, y),player.getNickname()));
            x += increase_coordinateSet;
            y += increase_coordinateSet;
        }

        return "**game started successfully**";
    }

    private int MapCoordinates(int sizeOfTheMap) {
        int doubleScale = (sizeOfTheMap == 200)? 1: 2;
        switch (players.size()) {
            case 2:
                return 140 * doubleScale;
            case 3:
                return 70 * doubleScale;
            case 4:
                return 46 * doubleScale;
            case 5:
                return 35 * doubleScale;
            case 6:
                return 28 * doubleScale;
            case 7:
                return 23 * doubleScale;
            case 8:
                return 20 * doubleScale;
        }
        return 0;
    }

    public String deselectMap() {
        setMapSelected(false);
        current_map = null;
        // todo to check whether it is necessary to new current_map
        return "map deselected successfully";
    }

    public String deselectPlayers() {
        setPlayersSelected(false);
        players.clear();
        // todo to check whether this line works well
        return "players deselected successfully";
    }

    public static Map getCurrent_map() {
        return current_map;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
        // mr freydoon get the arraylist of players from here
    }

    public static ArrayList<Empire> getEmpires() {
        return empires;
        // freydoon get access empires here;
    }

    public boolean isMapSelected() {
        return mapSelected;
    }

    public void setMapSelected(boolean mapSelected) {
        this.mapSelected = mapSelected;
    }

    public boolean isPlayersSelected() {
        return playersSelected;
    }

    public void setPlayersSelected(boolean playersSelected) {
        this.playersSelected = playersSelected;
    }
}
