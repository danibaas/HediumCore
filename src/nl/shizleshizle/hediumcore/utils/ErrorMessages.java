package nl.shizleshizle.hediumcore.utils;

import nl.shizleshizle.hediumcore.objects.User;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ErrorMessages {
    public static String prefix = ChatColor.RED.toString() + ChatColor.BOLD + "Error" + ChatColor.GOLD + ChatColor.BOLD + " >> " + ChatColor.YELLOW;

    public enum Messages {
        NOPERM, INVALID_USAGE, PLAYER_OFFLINE, PLAYERS_OFFLINE, NOPERM_WEARITEM, LOBBY, NO_CONSOLE
    }

    public static void doErrorMessage(CommandSender p, Messages message, String Entity) {
        if (message.equals(Messages.PLAYER_OFFLINE)) {
            p.sendMessage(prefix + "Player " + ChatColor.GOLD + Entity.toLowerCase() + ChatColor.YELLOW + " has not been found!");
        } else if (message.equals(Messages.INVALID_USAGE)) {
            p.sendMessage(prefix + "Invalid usage! You should try: " + ChatColor.GOLD + Entity.toLowerCase() + ChatColor.YELLOW + "!");
        } else if (message.equals(Messages.NOPERM)) {
            p.sendMessage(prefix + "You do not have permission to perform: " + ChatColor.GOLD + Entity.toLowerCase() + ChatColor.YELLOW + "!");
        } else if (message.equals(Messages.PLAYERS_OFFLINE)) {
            p.sendMessage(prefix + "The players " + ChatColor.GOLD + Entity.toLowerCase().trim() + ChatColor.YELLOW + " have not been found!");
        } else if (message.equals(Messages.NOPERM_WEARITEM)) {
            p.sendMessage(prefix + "You do not have permission to wear: " + ChatColor.GOLD + Entity.toLowerCase() + ChatColor.YELLOW + "!");
        } else if (message.equals(Messages.LOBBY)) {
            p.sendMessage(prefix + "You cannot use " + ChatColor.GOLD + Entity.toLowerCase() + ChatColor.YELLOW + " in the lobby!");
        } else if (message.equals(Messages.NO_CONSOLE)) {
            p.sendMessage(prefix + "You cannot use " + ChatColor.GOLD + Entity.toLowerCase() + ChatColor.YELLOW + " as Console!");
        } else {
            p.sendMessage(prefix + "An error occurred, unknown enum. Please tell the developers.");
        }
    }

    public static void doErrorMessage(User p, Messages message, String Entity) {
        if (message.equals(Messages.PLAYER_OFFLINE)) {
            p.sendMessage(prefix + "Player " + ChatColor.GOLD + Entity.toLowerCase() + ChatColor.YELLOW + " has not been found!");
        } else if (message.equals(Messages.INVALID_USAGE)) {
            p.sendMessage(prefix + "Invalid usage! You should try: " + ChatColor.GOLD + Entity.toLowerCase() + ChatColor.YELLOW + "!");
        } else if (message.equals(Messages.NOPERM)) {
            p.sendMessage(prefix + "You do not have permission to perform: " + ChatColor.GOLD + Entity.toLowerCase() + ChatColor.YELLOW + "!");
        } else if (message.equals(Messages.PLAYERS_OFFLINE)) {
            p.sendMessage(prefix + "The players " + ChatColor.GOLD + Entity.toLowerCase().trim() + ChatColor.YELLOW + " have not been found!");
        } else if (message.equals(Messages.NOPERM_WEARITEM)) {
            p.sendMessage(prefix + "You do not have permission to wear: " + ChatColor.GOLD + Entity.toLowerCase() + ChatColor.YELLOW + "!");
        } else if (message.equals(Messages.LOBBY)) {
            p.sendMessage(prefix + "You cannot use this command in the lobby!");
        } else {
            p.sendMessage(prefix + "An error occurred, unknown enum. Please tell the developers.");
        }
    }
}
