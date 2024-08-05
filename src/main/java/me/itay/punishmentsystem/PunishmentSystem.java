package me.itay.punishmentsystem;


import me.itay.punishmentsystem.Managers.FilesManager.PunishmentsConfig;
import me.itay.punishmentsystem.Managers.Listeners.ClickListener;
import me.itay.punishmentsystem.Managers.PunishmentsManager.Punishments;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import me.itay.punishmentsystem.Commands.PunishCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public final class PunishmentSystem extends JavaPlugin {

    Logger logger = getLogger();
    private PunishmentsConfig punishmentsConfig;
    private Punishments punishments;
    @Override
    public void onEnable() {
        logger.info("Programmed by ItxItay.");
        this.getCommand("punish").setExecutor(new PunishCommand(this));
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        punishmentsConfig = new PunishmentsConfig().createCustomConfig();
        punishments = new Punishments(punishmentsConfig, this);

        saveDefaultConfig();
        reloadConfig();
    }


    private static final Map<UUID, Inventory> playerInventory = new HashMap<>();

    public static void setPlayerInventory(UUID playerId, Inventory inventory) {
        playerInventory.put(playerId, inventory);
    }
    public static Inventory getPlayerInventory(UUID playerId) {
        return playerInventory.get(playerId);
    }

    //CoreProtectAPI:
    private CoreProtectAPI getCoreProtect() {
        Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");

        // Check that CoreProtect is loaded
        if (plugin == null || !(plugin instanceof CoreProtect)) {
            return null;
        }

        // Check that the API is enabled
        CoreProtectAPI CoreProtect = ((CoreProtect) plugin).getAPI();
        if (CoreProtect.isEnabled() == false) {
            return null;
        }

        // Check that a compatible version of the API is loaded
        if (CoreProtect.APIVersion() < 10) {
            return null;
        }

        return CoreProtect;
    }

    public Punishments getPunishments(){
        return punishments;
    }
}
