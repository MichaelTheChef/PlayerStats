package me.mike.playerstats.manager;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class PlayerDataManager {
    private final Map<String, PlayerData> playerDataMap;
    private final FileConfiguration config;

    public PlayerDataManager(FileConfiguration config) {
        this.playerDataMap = new HashMap<>();
        this.config = config;
    }

    public PlayerData getPlayerData(String playerName) {
        return playerDataMap.computeIfAbsent(playerName, k -> {
            PlayerData newData = new PlayerData();
            savePlayerData(playerName, newData);
            return newData;
        });
    }

    public void savePlayerData(String playerName, PlayerData data) {
        config.set("players." + playerName + ".kills", data.getKills());
        config.set("players." + playerName + ".deaths", data.getDeaths());
        config.set("players." + playerName + ".money", data.getMoney());
    }

    public Map<String, PlayerData> getPlayerDataMap() {
        return playerDataMap;
    }
}

