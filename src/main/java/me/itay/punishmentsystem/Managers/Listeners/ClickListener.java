package me.itay.punishmentsystem.Managers.Listeners;

import me.itay.punishmentsystem.PunishmentSystem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;


public class ClickListener implements Listener {


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
        if (clickedItem != null && clickedItem.getType() == Material.OAK_SIGN) {
            player.closeInventory();
            player.chat("/co lookup user:" + targetPlayer.getName() + " action:chat ");
        }
    }
}
