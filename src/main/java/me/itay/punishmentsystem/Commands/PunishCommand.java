package me.itay.punishmentsystem.Commands;

import me.itay.punishmentsystem.Inventory.AdminInventory;
import me.itay.punishmentsystem.Inventory.HelperInventory;
import me.itay.punishmentsystem.Inventory.ModeratorInventory;
import me.itay.punishmentsystem.Managers.InvenoryItems;
import me.itay.punishmentsystem.PunishmentSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class PunishCommand implements CommandExecutor {

    private PunishmentSystem plugin;

    public PunishCommand(PunishmentSystem plugin) {
        this.plugin = plugin;
    }

    private static final String PERMISSION_PUNISH = "PunishmentSystem.command.punish";
    private static final String PERMISSION_IMMUNE = "PunishmentSystem.immune";
    private static final String PERMISSION_ADMIN = "PunishmentSystem.rank.admin";
    private static final String PERMISSION_MOD = "PunishmentSystem.rank.mod";
    private static final String PERMISSION_HELPER = "PunishmentSystem.rank.helper";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cUsage: /punish <player name>"));
            return false;
        }

        String targetName = args[0];

        // Check if sender has permission
        if (!sender.hasPermission(PERMISSION_PUNISH)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!"));
            return false;
        }

        // Check if sender is trying to punish themself
      /*  if (sender instanceof Player && sender.getName().equalsIgnoreCase(targetName)) {
            sender.sendMessage("&cYou cannot punish yourself!");
            return false;
        }*/

        Player targetPlayer = sender.getServer().getPlayer(targetName);

        if (targetPlayer == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!"));
            return false;
        }

        // Check if target player has immunity permission
        if (targetPlayer.hasPermission(PERMISSION_IMMUNE)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot punish this player!"));
            return false;
        }
        Player player = (Player) sender;
        // Check sender rank permissions
        if (sender.hasPermission(PERMISSION_ADMIN)) {
            sender.sendMessage("Hello admin!");
            AdminInventory adminInventory = new AdminInventory(plugin);
            adminInventory.openAdminInventory(player);
        } else if (sender.hasPermission(PERMISSION_MOD)) {
            sender.sendMessage("Hello mod!");
            ModeratorInventory moderatorInventory = new ModeratorInventory(plugin);
            moderatorInventory.openModeratorInventory(player);
        } else if (sender.hasPermission(PERMISSION_HELPER)) {
            sender.sendMessage("Hello helper!");
            HelperInventory helperInventory = new HelperInventory(plugin);
            helperInventory.openHelperInventory(player);
        }

        sender.sendMessage("Punishing player: " + targetPlayer.getName());

        return true;
    }
}