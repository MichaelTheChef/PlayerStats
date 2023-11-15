package me.mike.playerstats.events;

import me.mike.playerstats.manager.PlayerData;
import me.mike.playerstats.manager.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerEventListener implements Listener {
    private final PlayerDataManager playerDataManager;

    public PlayerEventListener(PlayerDataManager playerDataManager) {
        this.playerDataManager = playerDataManager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (player.getKiller() instanceof Player) {
            Player killer = player.getKiller();

            PlayerData killerData = playerDataManager.getPlayerData(killer.getName());
            PlayerData victimData = playerDataManager.getPlayerData(player.getName());

            killerData.incrementKills();
            victimData.incrementDeaths();
        }
    }
}
