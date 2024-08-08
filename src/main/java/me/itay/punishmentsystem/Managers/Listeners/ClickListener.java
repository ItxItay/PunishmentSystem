package me.itay.punishmentsystem.Managers.Listeners;

import me.itay.punishmentsystem.Commands.PunishCommand;
import me.itay.punishmentsystem.Inventory.PunishSelectInventory;
import me.itay.punishmentsystem.Managers.FilesManager.PunishmentsConfig;
import me.itay.punishmentsystem.PunishmentSystem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class ClickListener implements Listener {

    private PunishmentSystem plugin;
    private final PunishmentsConfig punishmentsConfig;

    HashMap<UUID, String> punishmentDataMap = new HashMap<>();

    public ClickListener(PunishmentsConfig punishmentsConfig, PunishmentSystem plugin) {
        this.punishmentsConfig = punishmentsConfig;
        this.plugin = plugin;;
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Set<String> punishmentsList = punishmentsConfig.getCustomConfig().getConfigurationSection("punishments").getKeys(false);
        if (PunishmentSystem.getPlayerInventory(player.getUniqueId()) != event.getClickedInventory()) return;
        event.setCancelled(true);
        Player targetPlayer = PunishCommand.playerPunish(player);


        if (event.getCurrentItem() == null) {
            return;
        }
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem.getType() == Material.OAK_SIGN) {
            player.closeInventory();
            player.chat("/co lookup user:" + targetPlayer.getName() + " action:chat ");
        }


        if (punishmentsList.contains(clickedItem.getItemMeta().getDisplayName().replaceAll(" ", "-"))) {
            String path = "punishments."+clickedItem.getItemMeta().getDisplayName().replaceAll(" ", "-");
            punishmentDataMap.put(player.getUniqueId(), path);
            PunishSelectInventory.openPunishSelectInventory(player, path);
        }

        if (clickedItem.getType().toString().contains("DYE")) {
           // ConfigurationSection punishmentData = punishmentsConfig.getCustomConfig().getConfigurationSection(clickedItem.getItemMeta().getDisplayName().replaceAll(" ", "-") + ".PunishmentsLevel");
            if (!punishmentDataMap.containsKey(player.getUniqueId())){return;}
            //String path = "punishments."+clickedItem.getItemMeta().getDisplayName().replaceAll(" ", "-");
            String path = punishmentDataMap.get(player.getUniqueId());
            ConfigurationSection punishmentData = punishmentsConfig.getCustomConfig().getConfigurationSection(path + ".PunishmentsLevel");


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
                    punishreason = punishmentsConfig.getCustomConfig().getString(path + ".reason") + " #1";
                    break;
                case GREEN_DYE: //second
                    if (!punishmentData.contains("second")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis punishment doesn't exist"));
                        break;
                    }
                    punishtime = punishmentData.getString("second.time");
                    punishtype = punishmentData.getString("second.type");
                    punishreason = punishmentsConfig.getCustomConfig().getString(path + ".reason") + " #2";
                    break;
                case YELLOW_DYE: //third
                    if (!punishmentData.contains("third")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis punishment doesn't exist"));
                        break;
                    }
                    punishtime = punishmentData.getString("third.time");
                    punishtype = punishmentData.getString("third.type");
                    punishreason = punishmentsConfig.getCustomConfig().getString(path + ".reason") + " #3";
                    break;
                case ORANGE_DYE: //fourth
                    if (!punishmentData.contains("fourth")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis punishment doesn't exist"));
                        break;
                    }
                    punishtime = punishmentData.getString("fourth.time");
                    punishtype = punishmentData.getString("fourth.type");
                    punishreason = punishmentsConfig.getCustomConfig().getString(path + ".reason") + " #4";
                    break;
                case RED_DYE: //fifth
                    if (!punishmentData.contains("fifth")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis punishment doesn't exist"));
                        break;
                    }
                    punishtime = punishmentData.getString("fifth.time");
                    punishtype = punishmentData.getString("fifth.type");
                    punishreason = punishmentsConfig.getCustomConfig().getString(path + ".reason") + " #5";
                    break;
            }
            player.closeInventory();
            PunishCommand.removePlayer(player);
            punishmentDataMap.remove(player.getUniqueId());
             player.sendMessage(Component.text("Click Me!")
                    .color(NamedTextColor.RED)
                    .clickEvent(ClickEvent.suggestCommand("/" + punishtype.toLowerCase() + " " + targetPlayer.getName() + ((punishtime != null) ? " " + punishtime : "") + " " + punishreason)));
        }
    }
}
