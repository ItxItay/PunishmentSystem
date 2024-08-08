package me.itay.punishmentsystem.Inventory;

import me.itay.punishmentsystem.Managers.InvenoryItems;
import me.itay.punishmentsystem.PunishmentSystem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PunishSelectInventory {

    public static void openPunishSelectInventory(Player player, String punishmentpath){
        Inventory inv = Bukkit.createInventory(null, 27, "Time");



        ItemStack first = InvenoryItems.ChangeItemMeta(Material.LIME_DYE, "פעם ראשונה", punishmentpath);
        inv.setItem(9, first);

        ItemStack second = InvenoryItems.ChangeItemMeta(Material.GREEN_DYE, "פעם שנייה", punishmentpath);
        inv.setItem(11, second);

        ItemStack third = InvenoryItems.ChangeItemMeta(Material.YELLOW_DYE, "פעם שלישית", punishmentpath);
        inv.setItem(13, third);

        ItemStack fourth = InvenoryItems.ChangeItemMeta(Material.ORANGE_DYE, "פעם רביעית", punishmentpath);
        inv.setItem(15, fourth);

        ItemStack fifth = InvenoryItems.ChangeItemMeta(Material.RED_DYE, "פעם חמישית", punishmentpath);
        inv.setItem(17, fifth);

        PunishmentSystem.setPlayerInventory(player.getUniqueId(), inv);
        player.openInventory(inv);
    }
}
