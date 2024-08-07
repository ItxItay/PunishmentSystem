package me.itay.punishmentsystem.Managers.PunishmentsManager;

import me.itay.punishmentsystem.Managers.FilesManager.PunishmentsConfig;
import me.itay.punishmentsystem.Utils.StaffLevelEnum;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Set;


public class Punishments {

    private JavaPlugin plugin;
    private final PunishmentsConfig punishmentsConfig;
    private FileConfiguration config;
    public Punishments(PunishmentsConfig punishmentsConfig, JavaPlugin plugin) {
        this.punishmentsConfig = punishmentsConfig;
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public Inventory AdminPunishmentsLoader(Inventory inv) {
        Set<String> punishmentsList = punishmentsConfig.getCustomConfig().getConfigurationSection("punishments").getKeys(false);

        int slotIndex = 12;

        for (String punishmentListString : punishmentsList) {
            String path = "punishments." + punishmentListString;
            if (punishmentsConfig.getCustomConfig().contains(path)) {
                Material material;
                if (punishmentsConfig.getCustomConfig().getString(path + ".level").equals("high")) {
                    material = Material.RED_BANNER;
                } else if (punishmentsConfig.getCustomConfig().getString(path + ".level").equals("medium")) {
                    material = Material.ORANGE_BANNER;
                } else if (punishmentsConfig.getCustomConfig().getString(path + ".level").equals("low")) {
                    material = Material.LIME_BANNER;
                } else {
                    // Default material or error handling
                    material = Material.WHITE_BANNER;
                }

                ItemStack item = new ItemStack(material);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(punishmentsConfig.getCustomConfig().getString(path + ".name"));
                meta.setLocalizedName(path);
                item.setItemMeta(meta);

                inv.setItem(slotIndex, item);
                slotIndex++;


                if (slotIndex == 17 || slotIndex == 26 || slotIndex == 35 || slotIndex == 44) {
                    slotIndex += 4;
                }
            }
        }
        return inv;
    }

    public Inventory ModeratorPunishmentsLoader(Inventory inv) {
        Set<String> punishmentsList = punishmentsConfig.getCustomConfig().getConfigurationSection("punishments").getKeys(false);
        int slotIndex = 12;

        for (String punishmentListString : punishmentsList) {
            String path = "punishments." + punishmentListString;
            String level = punishmentsConfig.getCustomConfig().getString(path + ".Staff-Level");
            StaffLevelEnum correctlevel = staffLevelEnum(level);
            if (correctlevel == null){
                continue;
            }
            if (hasPerm(StaffLevelEnum.MOD, correctlevel)){
                if (punishmentsConfig.getCustomConfig().contains(path)) {
                    Material material;
                    if (punishmentsConfig.getCustomConfig().getString(path + ".level").equals("high")) {
                        material = Material.RED_BANNER;
                    } else if (punishmentsConfig.getCustomConfig().getString(path + ".level").equals("medium")) {
                        material = Material.ORANGE_BANNER;
                    } else if (punishmentsConfig.getCustomConfig().getString(path + ".level").equals("low")) {
                        material = Material.LIME_BANNER;
                    } else {
                        // Default material or error handling
                        material = Material.WHITE_BANNER;
                    }

                    ItemStack item = new ItemStack(material);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(punishmentsConfig.getCustomConfig().getString(path + ".name"));
                    meta.setLocalizedName(path);
                    item.setItemMeta(meta);

                    inv.setItem(slotIndex, item);
                    slotIndex++;


                    if (slotIndex == 17 || slotIndex == 26 || slotIndex == 35 || slotIndex == 44) {
                        slotIndex += 4;
                    }
                }
            }


        }
        return inv;
    }



    public Inventory HelperPunishmentsLoader(Inventory inv) {
        Set<String> punishmentsList = punishmentsConfig.getCustomConfig().getConfigurationSection("punishments").getKeys(false);
        int slotIndex = 12;

        for (String punishmentListString : punishmentsList) {
            String path = "punishments." + punishmentListString;
            System.out.println(path);
            String level = punishmentsConfig.getCustomConfig().getString(path + ".Staff-Level");
            StaffLevelEnum correctlevel = staffLevelEnum(level);
            if (correctlevel == null){
                continue;
            }
            if (hasPerm(StaffLevelEnum.HELPER, correctlevel)){
                if (punishmentsConfig.getCustomConfig().contains(path)) {
                    Material material;
                    if (punishmentsConfig.getCustomConfig().getString(path + ".level").equals("high")) {
                        material = Material.RED_BANNER;
                    } else if (punishmentsConfig.getCustomConfig().getString(path + ".level").equals("medium")) {
                        material = Material.ORANGE_BANNER;
                    } else if (punishmentsConfig.getCustomConfig().getString(path + ".level").equals("low")) {
                        material = Material.LIME_BANNER;
                    } else {
                        // Default material or error handling
                        material = Material.WHITE_BANNER;
                    }

                    ItemStack item = new ItemStack(material);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(punishmentsConfig.getCustomConfig().getString(path + ".name"));
                    meta.setLocalizedName(path);
                    item.setItemMeta(meta);

                    inv.setItem(slotIndex, item);
                    slotIndex++;


                    if (slotIndex == 17 || slotIndex == 26 || slotIndex == 35 || slotIndex == 44) {
                        slotIndex += 4;
                    }
                }
            }


        }
        return inv;
    }
    public static StaffLevelEnum staffLevelEnum(String stafflevel){
        try {
            return StaffLevelEnum.valueOf(stafflevel.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    public boolean hasPerm(StaffLevelEnum current, StaffLevelEnum required) {
        return current == required || current.getPrev().contains(required);
    }
}
