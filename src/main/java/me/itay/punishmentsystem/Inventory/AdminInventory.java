package me.itay.punishmentsystem.Inventory;

import me.itay.punishmentsystem.Managers.InvenoryItems;
import me.itay.punishmentsystem.Managers.PunishmentsManager.Punishments;
import me.itay.punishmentsystem.PunishmentSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class AdminInventory {

    private final PunishmentSystem plugin;

    public AdminInventory(PunishmentSystem plugin) {
        this.plugin = plugin;
    }
    public void openAdminInventory(Player player){
        Inventory AdminINV = Bukkit.createInventory(null, 54, "Admin Menu");
        ItemStack glass = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        InvenoryItems.ChangeItemDisplayName(glass, ChatColor.translateAlternateColorCodes('&', "&c"));
        ItemStack empty = new ItemStack(Material.AIR);
        AdminINV = InvenoryItems.itemLoader(AdminINV, glass, empty);
        PunishmentSystem.setPlayerInventory(player.getUniqueId(), AdminINV);
        plugin.getPunishments().AdminPunishmentsLoader(AdminINV);
        player.openInventory(AdminINV);
    }
}
