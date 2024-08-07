package me.itay.punishmentsystem.Managers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class InvenoryItems {

    public static Inventory itemLoader(Inventory inv, ItemStack glass, ItemStack empty){
       for (int i = 0; i <= 53; i++) {
           inv.setItem(i, glass);
       }
        //empety zone:
        for (int i = 12; i <= 16; i++) {
            inv.setItem(i, empty);
        }
        for (int i = 21; i <= 25; i++) {
            inv.setItem(i, empty);
        }
        for (int i = 30; i <= 34; i++) {
            inv.setItem(i, empty);
        }
        for (int i = 39; i <= 43; i++) {
            inv.setItem(i, empty);
        }
        //player name:
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        inv.setItem(10, head);
        //ChatHistory
        ItemStack chathistory = new ItemStack(Material.OAK_SIGN);
        inv.setItem(19, chathistory);
        //History
        ItemStack history = new ItemStack(Material.CLOCK);
        inv.setItem(28, history);
        //resetNICKNAME
        ItemStack nickname = new ItemStack(Material.NAME_TAG);
        inv.setItem(37, nickname);

        return inv;
    }

    public static ItemStack ChangeItemDisplayName(ItemStack stack, String DisplayName) {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', DisplayName));
        stack.setItemMeta(meta);
        return stack;
    }

    public static ItemStack ChangeItemMeta(Material material, String displayName, String locallizename) {
        ItemStack is = new ItemStack(material);
        ItemMeta isMeta = is.getItemMeta();
        isMeta.setDisplayName(displayName);
        isMeta.setLocalizedName(locallizename);
        is.setItemMeta(isMeta);
        return is;
    }
}
