package me.mike.playerstats;

import me.mike.playerstats.events.PlayerEventListener;
import me.mike.playerstats.manager.PlayerData;
import me.mike.playerstats.manager.PlayerDataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerStats extends JavaPlugin {
    private PlayerDataManager playerDataManager;

    @Override
    public void onEnable() {
        // Initialize the player data manager
        FileConfiguration config = getConfig();
        playerDataManager = new PlayerDataManager(config);

        // Load player data from the configuration file
        config.getConfigurationSection("players").getKeys(false).forEach(playerName ->
                playerDataManager.getPlayerData(playerName));

        getServer().getPluginManager().registerEvents(new PlayerEventListener(playerDataManager), this);
        getCommand("sendmoney").setExecutor(this);
        saveConfig();
    }

    @Override
    public void onDisable() {
        playerDataManager.getPlayerDataMap().forEach((playerName, playerData) ->
                playerDataManager.savePlayerData(playerName, playerData));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("sendmoney")) {
            // Implementation for the sendmoney command
            // Example: /sendmoney <targetPlayer> <amount>
            if (args.length == 2) {
                String targetPlayer = args[0];
                double amount;
                try {
                    amount = Double.parseDouble(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage("Invalid amount!");
                    return true;
                }

                PlayerData senderData = playerDataManager.getPlayerData(sender.getName());
                PlayerData targetData = playerDataManager.getPlayerData(targetPlayer);

                if (senderData.getMoney() >= amount) {
                    senderData.subtractMoney(amount);
                    targetData.addMoney(amount);
                    sender.sendMessage("Money sent successfully!");
                } else {
                    sender.sendMessage("Not enough money!");
                }

                return true;
            }
        }

        return false;
    }
}

