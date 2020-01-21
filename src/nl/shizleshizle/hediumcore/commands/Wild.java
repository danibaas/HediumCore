package nl.shizleshizle.hediumcore.commands;

import nl.shizleshizle.hediumcore.objects.User;
import nl.shizleshizle.hediumcore.permissions.Perm;
import nl.shizleshizle.hediumcore.permissions.PermGroup;
import nl.shizleshizle.hediumcore.utils.ErrorMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Wild implements CommandExecutor {
    public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Wild" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
    public static ArrayList<String> comfortSpawn = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            User p = new User((Player) sender);
            if (Perm.hasPerm(p, PermGroup.RANKED)) {

            }
        } else {
            ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.NO_CONSOLE, "/wild");
        }
        return false;
    }
}
