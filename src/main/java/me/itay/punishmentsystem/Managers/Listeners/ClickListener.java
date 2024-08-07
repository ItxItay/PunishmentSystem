package me.itay.punishmentsystem.Managers.Listeners;

import me.itay.punishmentsystem.Inventory.PunishSelectInventory;
import me.itay.punishmentsystem.Managers.FilesManager.PunishmentsConfig;
import me.itay.punishmentsystem.PunishmentSystem;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;


public class ClickListener implements Listener {

    private JavaPlugin plugin;
    private final PunishmentsConfig punishmentsConfig;

    public ClickListener(PunishmentsConfig punishmentsConfig, JavaPlugin plugin) {
        this.punishmentsConfig = punishmentsConfig;
        this.plugin = plugin;;
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        UUID playerUuid = player.getUniqueId();
        Player targetPlayer = Bukkit.getPlayer(playerUuid);

        if (PunishmentSystem.getPlayerInventory(player.getUniqueId()) != event.getClickedInventory()) return;
        event.setCancelled(true);

        if (event.getCurrentItem() == null) {
            return;
        }
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem.getType() == Material.OAK_SIGN) {
            player.closeInventory();
            player.chat("/co lookup user:" + targetPlayer.getName() + " action:chat ");
        }

       // if (clickedItem.getType().toString().contains("BANNER")){PunishSelectInventory.openPunishSelectInventory(player);}

        if (clickedItem.getItemMeta().getLocalizedName().contains("punishments.")) {
            //player.sendMessage(clickedItem.getItemMeta().getLocalizedName());
            String path = clickedItem.getItemMeta().getLocalizedName();
            PunishSelectInventory.openPunishSelectInventory(player, path);
        }

        if (clickedItem.getType().toString().contains("DYE")) {
            ConfigurationSection punishmentData = punishmentsConfig.getCustomConfig().getConfigurationSection(clickedItem.getItemMeta().getLocalizedName() + ".PunishmentsLevel");
            String path = clickedItem.getItemMeta().getLocalizedName().toString();

            String punishtype = null;
            String punishtime = null;
            String punishreason = null;

            switch (clickedItem.getType()) {
                case LIME_DYE: //first
                    if (!punishmentData.contains("first")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis punishment doesn't exist"));
                        break;
                    }
                    punishtime = punishmentData.getString("first.time");
                    punishtype = punishmentData.getString("first.type");
                    punishreason = punishmentsConfig.getCustomConfig().getString(path + ".name") + " #1";
                    break;
                case GREEN_DYE: //second
                    if (!punishmentData.contains("second")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis punishment doesn't exist"));
                        break;
                    }
                    punishtime = punishmentData.getString("second.time");
                    punishtype = punishmentData.getString("second.type");
                    punishreason = punishmentsConfig.getCustomConfig().getString(path + ".name") + " #2";
                    break;
                case YELLOW_DYE: //third
                    if (!punishmentData.contains("third")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis punishment doesn't exist"));
                        break;
                    }
                    punishtime = punishmentData.getString("third.time");
                    punishtype = punishmentData.getString("third.type");
                    punishreason = punishmentsConfig.getCustomConfig().getString(path + ".name") + " #3";
                    break;
                case ORANGE_DYE: //fourth
                    if (!punishmentData.contains("fourth")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis punishment doesn't exist"));
                        break;
                    }
                    punishtime = punishmentData.getString("fourth.time");
                    punishtype = punishmentData.getString("fourth.type");
                    punishreason = punishmentsConfig.getCustomConfig().getString(path + ".name") + " #4";
                    break;
                case RED_DYE: //fifth
                    if (!punishmentData.contains("fifth")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis punishment doesn't exist"));
                        break;
                    }
                    punishtime = punishmentData.getString("fifth.time");
                    punishtype = punishmentData.getString("fifth.type");
                    punishreason = punishmentsConfig.getCustomConfig().getString(path + ".name") + " #5";
                    break;
            }
        }
    }
}
