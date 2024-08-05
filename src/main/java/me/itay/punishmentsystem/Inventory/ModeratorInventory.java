package me.itay.punishmentsystem.Inventory;

import me.itay.punishmentsystem.Managers.InvenoryItems;
import me.itay.punishmentsystem.PunishmentSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ModeratorInventory {
    private final PunishmentSystem plugin;
    public ModeratorInventory(PunishmentSystem plugin) {
        this.plugin = plugin;
    }
    public void openModeratorInventory(Player player){
        Inventory ModeratorINV = Bukkit.createInventory(null, 54, "Moderator Menu");
        ItemStack glass = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        InvenoryItems.ChangeItemDisplayName(glass, ChatColor.translateAlternateColorCodes('&', "&a"));
        ItemStack empty = new ItemStack(Material.AIR);
        ModeratorINV = InvenoryItems.itemLoader(ModeratorINV, glass, empty);

        PunishmentSystem.setPlayerInventory(player.getUniqueId(), ModeratorINV);
        plugin.getPunishments().ModeratorPunishmentsLoader(ModeratorINV);

        player.openInventory(ModeratorINV);
    }
}
