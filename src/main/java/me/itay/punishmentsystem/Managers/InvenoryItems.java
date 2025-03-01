package me.itay.punishmentsystem.Managers;

import me.itay.punishmentsystem.Commands.PunishCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class InvenoryItems {

    public static Inventory itemLoader(Inventory inv, ItemStack glass, ItemStack empty){
       for (int i = 0; i <= 53; i++) {
           inv.setItem(i, glass);
       }
        //empty zone:
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
        String targetPlayer = PunishCommand.getTargetPlayer().getDisplayName();

        //player name:
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        ChangeItemDisplayName(head, "&e" +targetPlayer);
        inv.setItem(10, head);
        //ChatHistory
        ItemStack chatHistory = new ItemStack(Material.OAK_SIGN);
        ChangeItemDisplayName(chatHistory, "&e" + targetPlayer + "'s chat history");
        inv.setItem(19, chatHistory);
        //History
        ItemStack history = new ItemStack(Material.CLOCK);
        ChangeItemDisplayName(history, "&e" +targetPlayer + "'s punishments history");
        inv.setItem(28, history);
        //reset nickname
        ItemStack nickname = new ItemStack(Material.NAME_TAG);
        ChangeItemDisplayName(nickname, "&eReset " + targetPlayer + "'s Nickname");
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
