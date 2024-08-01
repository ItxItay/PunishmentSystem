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

public class HelperInventory {
    private final Plugin plugin;
    public HelperInventory(Plugin plugin) {
        this.plugin = plugin;
    }
    public void openHelperInventory(Player player){
        Inventory HelperINV = Bukkit.createInventory(null, 54, "Helper Menu");
        ItemStack glass = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);
        InvenoryItems.ChangeItemDisplayName(glass, ChatColor.translateAlternateColorCodes('&', "&9"));
        ItemStack empty = new ItemStack(Material.AIR);
        HelperINV = InvenoryItems.itemLoader(HelperINV, glass, empty);

        PunishmentSystem.setPlayerInventory(player.getUniqueId(), HelperINV);

        player.openInventory(HelperINV);
    }
}
