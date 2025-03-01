package me.itay.punishmentsystem;

import me.itay.punishmentsystem.Managers.FilesManager.PunishmentsConfig;
import me.itay.punishmentsystem.Managers.Listeners.ClickListener;
import me.itay.punishmentsystem.Managers.PunishmentsManager.Punishments;
import me.itay.punishmentsystem.Commands.PunishCommand;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public final class PunishmentSystem extends JavaPlugin {

    Logger logger = getLogger();
    private PunishmentsConfig punishmentsConfig;
    private Punishments punishments;
    private static PunishmentSystem plugin;

    @Override
    public void onEnable() {
        PunishmentSystem.plugin = this;

        logger.info("Programmed by ItxItay.");
        this.getCommand("punish").setExecutor(new PunishCommand());
        punishmentsConfig = new PunishmentsConfig().createCustomConfig();
        punishments = new Punishments(punishmentsConfig);
        getServer().getPluginManager().registerEvents(new ClickListener(punishmentsConfig), this);


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

    public Punishments getPunishments(){
        return punishments;
    }

    public static PunishmentSystem getPlugin() {
        return plugin;
    }
}
