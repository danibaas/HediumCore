package nl.shizleshizle.hediumcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Mute implements CommandExecutor {
    public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Mute" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        return false;
    }
}
