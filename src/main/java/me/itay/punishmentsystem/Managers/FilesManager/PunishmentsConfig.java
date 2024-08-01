package me.itay.punishmentsystem.Managers.FilesManager;

import me.itay.punishmentsystem.PunishmentSystem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PunishmentsConfig {
    private File customConfigFile;
    private FileConfiguration customConfig;

    public FileConfiguration getCustomConfig() {
        return customConfig;
    }

    public PunishmentsConfig createCustomConfig() {
        String configname = "punishments.yml";
        customConfigFile = new File(PunishmentSystem.getPlugin(PunishmentSystem.class).getDataFolder(), configname);
        if (!customConfigFile.exists()) {
            PunishmentSystem.getPlugin(PunishmentSystem.class).saveResource(configname, false);
        }
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
        return this;
    }
}
