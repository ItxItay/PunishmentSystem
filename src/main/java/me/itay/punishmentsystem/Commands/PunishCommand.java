package me.itay.punishmentsystem.Commands;

import me.itay.punishmentsystem.Inventory.AdminInventory;
import me.itay.punishmentsystem.Inventory.HelperInventory;
import me.itay.punishmentsystem.Inventory.ModeratorInventory;
import me.itay.punishmentsystem.PunishmentSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;


public class PunishCommand implements CommandExecutor {

    private static final  HashMap<UUID, UUID> players = new HashMap<>();
    private static final String PERMISSION_PUNISH = "PunishmentSystem.command.punish";
    private static final String PERMISSION_IMMUNE = "PunishmentSystem.immune";
    private static final String PERMISSION_ADMIN = "PunishmentSystem.rank.admin";
    private static final String PERMISSION_MOD = "PunishmentSystem.rank.mod";
    private static final String PERMISSION_HELPER = "PunishmentSystem.rank.helper";
    private static Player targetPlayer;

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

        // Check if sender is trying to punish himself
        /*if (sender instanceof Player && sender.getName().equalsIgnoreCase(targetName)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot punish yourself!"));
            return false;
        }*/

        targetPlayer = sender.getServer().getPlayer(targetName);

        if (targetPlayer == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe player not found!"));
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
            AdminInventory adminInventory = new AdminInventory();
            adminInventory.openAdminInventory(player);
        } else if (sender.hasPermission(PERMISSION_MOD)) {
            ModeratorInventory moderatorInventory = new ModeratorInventory();
            moderatorInventory.openModeratorInventory(player);
        } else if (sender.hasPermission(PERMISSION_HELPER)) {
            HelperInventory helperInventory = new HelperInventory();
            helperInventory.openHelperInventory(player);
        }

        sender.sendMessage("Punishing player: " + targetPlayer.getName());
        players.put(player.getUniqueId(), targetPlayer.getUniqueId());
        return true;
    }

    public static Player playerPunish(Player player){
        return Bukkit.getPlayer(players.get(player.getUniqueId()));
    }
    public static void removePlayer(Player player){
        players.remove(player.getUniqueId());
    }

    public static HashMap<UUID, UUID> getPlayers() {
        return players;
    }

    public static Player getTargetPlayer() {
        return targetPlayer;
    }
}